package mapleroad;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.jruby.RubyFixnum;

public class MapleRoadCollectorTextInt {
    private OutputCollector<Text, IntWritable> collector = null;
    private Text key = null;
    private IntWritable value = null;
    
    MapleRoadCollectorTextInt(Text key, IntWritable value, OutputCollector<Text, IntWritable> collector) {
        this.key = key;
        this.value = value;
        this.collector = collector;
    }

    public void collect(Object k, Object v) throws IOException {
        if (k instanceof String) {
            key.set((String)k);
        } else if (k instanceof Text) {
            key.set((Text)k);
        }
        if (v instanceof RubyFixnum) {
            Long longValue = ((RubyFixnum)v).getLongValue();
            value.set(longValue.intValue());
        } else if (v instanceof Long) {
            value.set(((Long)v).intValue());
        } else if (v instanceof Integer) {
            value.set(((Integer)v).intValue());
        }
        collector.collect(key, value);
    }
}
