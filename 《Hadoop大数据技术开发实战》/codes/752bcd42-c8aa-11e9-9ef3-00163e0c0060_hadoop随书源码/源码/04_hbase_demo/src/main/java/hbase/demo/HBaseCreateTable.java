package hbase.demo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
/**
 * 在HBase中创建一张表
 */
public class HBaseCreateTable{
	public static void main(String[] args) throws Exception {
		//创建HBase配置对象
		Configuration conf=HBaseConfiguration.create();
		//指定ZooKeeper集群地址
		conf.set("hbase.zookeeper.quorum", "192.168.170.133:2181,192.168.170.134:2181,192.168.170.135:2181");
		//创建连接对象Connection
		Connection conn=ConnectionFactory.createConnection(conf);
		//得到数据库管理员对象
		Admin admin=conn.getAdmin();
		//创建表描述，并指定表名
		TableName tableName=TableName.valueOf("t1");
		HTableDescriptor desc=new HTableDescriptor(tableName);
		//创建列族描述
		HColumnDescriptor family=new HColumnDescriptor("f1");
		//指定列族
		desc.addFamily(family);
		//创建表
		admin.createTable(desc);
		System.out.println("create table success!!");
	}
}
