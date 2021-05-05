package hdfs.demo;

import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HDFSDemo {

 public static void main(String[] args) throws Exception {
  createFile();
  outFile();
  deleteFile();
  // copyFromLocalFile();
  // copyToLocalFile();
 }

 // 创建文件
 public static void createFile() throws Exception {
  Configuration conf = new Configuration();
  conf.set("fs.default.name", "hdfs://192.168.170.133:9000");
  FileSystem fs = FileSystem.get(conf);
  // 打开一个输出流
  FSDataOutputStream outputStream = fs.create(new Path("hdfs:/newfile2.txt"));
  outputStream.write("我是文件内容".getBytes());// 写入文件内容
  outputStream.close();
  fs.close();
  System.out.println("文件创建成功！");
 }

 // 删除文件
 public static void deleteFile() throws Exception {
  Configuration conf = new Configuration();
  conf.set("fs.default.name", "hdfs://192.168.170.133:9000");
  FileSystem fs = FileSystem.get(conf);
  Path path = new Path("hdfs:/newfile2.txt");
  boolean isok = fs.deleteOnExit(path);
  if (isok) {
   System.out.println("删除成功!");
  } else {
   System.out.println("删除失败！");
  }
  fs.close();
 }

 // 复制上传本地文件
 public static void copyFromLocalFile() throws Exception {
  // 1.创建配置器
  Configuration conf = new Configuration();
  conf.set("fs.default.name", "hdfs://192.168.170.128:9000");
  // 2.取得FileSystem文件系统 实例
  FileSystem fs = FileSystem.get(conf);
  // 3.创建可供hadoop使用的文件系统路径
  Path src = new Path("D:/copy_test.txt"); // 本地目录/文件
  Path dst = new Path("hdfs:/"); // 目标目录/文件
  // 4.拷贝上传本地文件（本地文件，目标路径） 至HDFS文件系统中
  fs.copyFromLocalFile(src, dst);
  System.out.println("文件上传成功!");
 }

 // 复制下载文件
 public static void copyToLocalFile() throws Exception {
  // 1.创建配置器
  Configuration conf = new Configuration();
  conf.set("fs.default.name", "hdfs://192.168.170.128:9000");
  // 2.取得FileSystem文件系统 实例
  FileSystem fs = FileSystem.get(conf);
  // 3.创建可供hadoop使用的文件系统路径
  Path src = new Path("hdfs:/newfile2.txt");// 目标目录/文件
  Path dst = new Path("D:/new.txt"); // 本地目录/文件
  // 4.从HDFS文件系统中拷贝下载文件（目标路径，本地文件）至本地
  // fs.copyToLocalFile(src, dst);
  fs.copyToLocalFile(false, src, dst, true);
  System.out.println("文件下载成功!");
 }

 // 查看文件内容并输出
 public static void outFile() throws Exception {
  // 1.创建配置器
  Configuration conf = new Configuration();
  conf.set("fs.default.name", "hdfs://192.168.170.133:9000");
  // 2.取得FileSystem文件系统 实例
  FileSystem fs = FileSystem.get(conf);
  InputStream in = fs.open(new Path("hdfs:/newfile2.txt"));
  IOUtils.copyBytes(in, System.out, 4096, false);
  IOUtils.closeStream(in);
 }
 
 

}
