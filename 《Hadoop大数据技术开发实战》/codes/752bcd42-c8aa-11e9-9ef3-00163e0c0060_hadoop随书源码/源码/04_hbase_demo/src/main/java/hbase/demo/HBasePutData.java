package hbase.demo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
/**
 * 向表t1中添加三条数据
 */
public class HBasePutData{
	public static void main(String[] args) throws Exception {
		//创建HBase配置对象
		Configuration conf=HBaseConfiguration.create();
		//指定ZooKeeper集群地址
		conf.set("hbase.zookeeper.quorum", 
				"192.168.170.133:2181,192.168.170.134:2181,192.168.170.135:2181");
  		//创建数据库连接对象Connection
  		Connection conn=ConnectionFactory.createConnection(conf);
  		//Table负责与记录相关的操作,如增删改查等
  		TableName tableName=TableName.valueOf("t1");
  		Table table=conn.getTable(tableName);
        
        Put put = new Put(Bytes.toBytes("row7"));// 设置rowkey
        //添加列数据，指定列族、列名与列值
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"),
        		Bytes.toBytes("xiaoming24"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), 
        		Bytes.toBytes("33"));
        put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("address"), 
        		Bytes.toBytes("beijing"));
        
        Put put2 = new Put(Bytes.toBytes("row8"));// 设置rowkey
        //添加列数据，指定列族、列名与列值
        put2.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), 
        		Bytes.toBytes("xiaoming23333"));
        put2.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), 
        		Bytes.toBytes("30"));
        put2.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("address"), 
        		Bytes.toBytes("beijing2"));
        
        Put put3 = new Put(Bytes.toBytes("row9"));// 设置rowkey
        //添加列数据，指定列族、列名与列值
        put3.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), 
        		Bytes.toBytes("31"));
        put3.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("address"), 
        		Bytes.toBytes("beijing3"));
        
        //执行添加数据
        table.put(put);
        table.put(put2);
        table.put(put3);
        //释放资源
        table.close();
        System.out.println("put data success!!");
	}
}
