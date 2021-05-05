package mapreduce.demo;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 数据去重类
 */
public class Dedup {

 // map函数将输入中的value复制到输出数据的key上，并直接输出
 public static class Map extends Mapper<Object, Text, Text, Text> {
  private static Text line = new Text();// 每行数据

  // 实现map函数
  public void map(Object key, Text value, Context context) throws IOException,
    InterruptedException {
   line = value;
   context.write(line, new Text(""));
  }
 }

 // reduce函数将输入中的key复制到输出数据的key上，并直接输出
 public static class Reduce extends Reducer<Text, Text, Text, Text> {
  // 实现reduce函数
  public void reduce(Text key, Iterable<Text> values, Context context)
    throws IOException, InterruptedException {
   context.write(key, new Text(""));
  }
 }

 public static void main(String[] args) throws Exception {
  Configuration conf = new Configuration();
  //构建任务对象
  Job job = Job.getInstance(conf, "Data Deduplication");
  job.setJarByClass(Dedup.class);

  // 设置Map、Combine和Reduce处理类
  job.setMapperClass(Map.class);
  job.setCombinerClass(Reduce.class);
  job.setReducerClass(Reduce.class);

  // 设置输出类型
  job.setOutputKeyClass(Text.class);
  job.setOutputValueClass(Text.class);

  // 设置输入和输出目录
  FileInputFormat.addInputPath(job, new Path("/input/"));
  FileOutputFormat.setOutputPath(job, new Path("/output"));
  System.exit(job.waitForCompletion(true) ? 0 : 1);
 }
}