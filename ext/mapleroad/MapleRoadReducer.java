package mapleroad;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Helpers;
import org.jruby.runtime.builtin.IRubyObject;

public class MapleRoadReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
    private String reducer_code = null;
    private Text keyText = new Text();
    private Text valueText = new Text();
    
    @Override
    public void configure(JobConf job) {
        reducer_code = job.get("reducer.code");
    }

    @Override
    public void reduce(Text key, Iterator<Text> values,
            OutputCollector<Text, Text> outputCollector, Reporter arg3) throws IOException {;
        Ruby runtime = Ruby.getGlobalRuntime();
        RubyRuntimeAdapter adapter = JavaEmbedUtils.newRuntimeAdapter();
        IRubyObject receiver = adapter.eval(runtime, reducer_code);
        IRubyObject rubyKey = JavaUtil.convertJavaToRuby(runtime, key);
        IRubyObject rubyValues = JavaUtil.convertJavaToRuby(runtime, values);
        MapleRoadCollector collector =
                new MapleRoadCollector(keyText, valueText, outputCollector);
        IRubyObject rubyCollector = JavaUtil.convertJavaToRuby(runtime, collector);
        Helpers.invoke(runtime.getCurrentContext(), receiver, "reduce", rubyKey, rubyValues, rubyCollector);
    }

}
