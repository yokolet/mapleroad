package mapleroad;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;

public class MapleRoadCollector {
    private OutputCollector<Text, Text> collector = null;
    private Text key = null;
    private Text value = null;
    
    MapleRoadCollector(Text key, Text value, OutputCollector<Text, Text> collector) {
        this.key = key;
        this.value = value;
        this.collector = collector;
    }

    public void collect(Object k, Object v) throws IOException {
        if (k instanceof String) {
            key.set((String)k);
        } else if (k instanceof Text) {
            key = (Text)k;
        }
        if (v instanceof String) {
            value.set((String)v);
        } else if (v instanceof Text) {
            value.set((Text)v);
        }
        collector.collect(key, value);
    }
}
