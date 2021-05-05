package hbase.demo.filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.client.*;

public class HBaseFilterDemo {
	static Configuration conf=null;
	static{
		//创建HBase配置对象
		conf=HBaseConfiguration.create();
		//加上这一句，就不需要将代码发布到服务器中执行了，直接eclipse中运行就可以。不加这一句，需要将代码导出jar，上传到HBase服务器执行。
		conf.set("hbase.zookeeper.quorum", "centos01:2181,centos02:2181,centos03:2181");
	}
	
	public static void main(String[] args) throws Exception {
//		filterTest();
		
	}
	
	/**
	 * 创建表t1 ,列族f1
	 * @throws Exception
	 */
	public static void filterTest() throws Exception {
		Connection conn=ConnectionFactory.createConnection(conf);
		//得到user_info表的连接
		Table table =conn.getTable(TableName.valueOf("t1"));
		Scan scan = new Scan();
//		RegexStringComparator comp = new RegexStringComparator("you."); // 以 you 开头的字符串
//		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("family"), Bytes.toBytes("qualifier"), CompareOp.EQUAL, comp);
		//1. 行键过滤器：筛选出行键为row1的一行数据
//		Filter filter = new RowFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("row2"))); 
		//2. 列族过滤器：筛选出列族为f1的所有数据
//		Filter filter = new FamilyFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("f1")));
		//3. 列过滤器：筛选出列为name的所有数据
//		Filter filter = new QualifierFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("name"))); 
		//4. 值过滤器：筛选出一行中的值包含"xiaoming"的所有单元格数据
		Filter filter = new ValueFilter(CompareOp.EQUAL, new SubstringComparator("beijing"));
		//5. 单列值过滤器：用一列的值决定该行是否被过滤
		//筛选出name列不包含xiaoming的所有行数据
		/*Filter filter = new SingleColumnValueFilter(Bytes.toBytes("f1"), Bytes.toBytes("name"),CompareFilter.CompareOp.NOT_EQUAL, new SubstringComparator("xiaoming"));  
		//如果某行列name不存在，那么该行将被过滤掉，false则不进行过滤，默认为false。
		((SingleColumnValueFilter) filter).setFilterIfMissing(true);*/
		
		//6. 分页过滤器，未完善
		/*Filter filter=new PageFilter(5);
		scan.setStartRow(Bytes.toBytes("row2"));*/
		scan.setFilter(filter);
		ResultScanner rs = table.getScanner(scan);  
	    for (Result res : rs) {  
	      System.out.println(res);  
	      printResult(res);
	      
	    }  
		 /*for (Result result : rs){
            for (Cell cell:result.rawCells()){
                System.out.println("Cell: "+cell+", Value: "+Bytes.toString(cell.getValueArray(),cell.getValueLength()));
            }
        }*/
		 
	    rs.close();  
	}
	
	public static void printResult(Result res){
		if(!res.toString().equals("keyvalues=NONE")){
			for(Cell cell:res.listCells()){
				if(null!=cell)
					
					System.out.println(new String(cell.getRow())+"\t"+new String(cell.getQualifier())+"\t"+new String(cell.getValue())+"\t"+cell.getTimestamp());
			}
		}
	}
	
}
