package mapleroad;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.jruby.Ruby;
import org.jruby.RubyRuntimeAdapter;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.Helpers;
import org.jruby.runtime.builtin.IRubyObject;

public class MapleRoadMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {
    private String mapper_code = null;
    private Text keyText = new Text();
    private Text valueText = new Text();
    
    @Override
    public void configure(JobConf job) {
        mapper_code = job.get("mapper.code");
    }

    @Override
    public void map(LongWritable key, Text value,
            OutputCollector<Text, Text> outputCollector, Reporter arg3) throws IOException {
        Ruby runtime = Ruby.getGlobalRuntime();
        RubyRuntimeAdapter adapter = JavaEmbedUtils.newRuntimeAdapter();
        IRubyObject receiver = adapter.eval(runtime, mapper_code);
        IRubyObject rubyKey = JavaUtil.convertJavaToRuby(runtime, key);
        IRubyObject rubyValue = JavaUtil.convertJavaToRuby(runtime, value);
        MapleRoadCollector collector =
                new MapleRoadCollector(keyText, valueText, outputCollector);
        IRubyObject rubyCollector = JavaUtil.convertJavaToRuby(runtime, collector);
        Helpers.invoke(runtime.getCurrentContext(), receiver, "map", rubyKey, rubyValue, rubyCollector);
    }

}
