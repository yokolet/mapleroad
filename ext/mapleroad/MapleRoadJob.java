package mapleroad;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class MapleRoadJob  {

    /**
     * @throws IOException 
     * @param args args[0]: mapper filename, 
     *             args[1]: reducer filename,
     *             args[2]: input,
     *             args[3]: output,
     *             args[4]: output key/value types
     * @throws  
     */
    public static void main(String[] args) throws IOException  {
        JobConf conf = new JobConf(MapleRoadJob.class);
        conf.set("mapper.file.name", args[0]);
        conf.set("reducer.file.name", args[1]);
        conf.set("mapper.code", getCodeAsString(args[0], StandardCharsets.UTF_8));
        System.out.println(conf.get("mapper.code"));
        conf.set("reducer.code", getCodeAsString(args[1], StandardCharsets.UTF_8));
        System.out.println(conf.get("reducer.code"));
        conf.setJobName("maplpleroad_job");
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        FileInputFormat.setInputPaths(conf, new Path(args[2]));
        FileOutputFormat.setOutputPath(conf, new Path(args[3]));
        
        if (args[4].equals("Text:Text")) {
            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(Text.class);
            conf.setMapperClass(MapleRoadMapper.class);
            conf.setReducerClass(MapleRoadReducer.class);
        } else if (args[4].equals("Text:Int")) {
            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(IntWritable.class);
            conf.setMapperClass(MapleRoadMapperTextInt.class);
            conf.setReducerClass(MapleRoadReducerTextInt.class);
        }

        JobClient.runJob(conf);
    }
    
    private static String getCodeAsString(String filename, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filename).toAbsolutePath());
        return new String(encoded, encoding);
    }

}
