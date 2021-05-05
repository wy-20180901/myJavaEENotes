package elasticsearch.demo;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

/**
 * 员工信息增删改查
 */
public class EmployeeCRUDApp {

 public static void main(String[] args) throws Exception {
  //指定集群名称
  Settings settings = Settings.builder().put("cluster.name", "es_cluster")
    .put("client.transport.sniff", true)// 开启集群嗅探器，可以自动发现新加入集群的节点
    .build();
  // 建立传输客户端对象，并指定集群中其中一个节点的IP与端口，默认传输客户端连接的端口是9300，与REST的HTTP端口相区分
  TransportClient client = new PreBuiltTransportClient(settings)
    .addTransportAddress(new TransportAddress(InetAddress
      .getByName("192.168.170.133"), 9300));

  // 添加员工信息
//  addEmploy(client);
  // 更新员工信息
   undateEmployee(client);
  // 删除员工信息
  // delEmployee(client);
  // 查询员工信息
  // getEmployee(client);
  // 关闭连接
  client.close();
 }

 /**
  * 添加员工信息
  */
 public static void addEmploy(TransportClient client) throws Exception {
  //构建JSON对象
  XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
    .field("name", "zhangsan")
    .field("age", 27)
    .field("position", "software engineer")
    .field("country", "China")
    .field("join_date", "2018-10-21")
    .field("salary", "10000")
    .endObject();
  //执行索引操作，指定索引为company，文档id为1。若Elasticsearch集群中不存在该索引则会自动创建
  IndexResponse response = client.prepareIndex("company", "_doc", "1")
    .setSource(builder).get();
  //输出返回结果
  System.out.println(response.getResult());
 }

 /**
  * 更新员工信息
  */
 public static void undateEmployee(TransportClient client) throws Exception {
  //构建JSON对象
  XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
    .field("name", "zhangsan").endObject();
  //执行更新，更新id为1的员工信息的name字段
  UpdateResponse response = client.prepareUpdate("company", "_doc", "1")
    .setDoc(builder).get();
  System.out.println(response.getResult());
 }

 /**
  * 查询员工信息
  */
 public static void getEmployee(TransportClient client) {
  //执行查询，查询id为1的员工信息
  GetResponse response = client.prepareGet("company", "_doc", "1").get();
  System.out.println(response.getSourceAsString());
 }

 /**
  * 删除员工信息
  */
 public static void delEmployee(TransportClient client) {
  //执行删除，删除id为1的员工信息
  DeleteResponse response = client.prepareDelete("company", "_doc", "1").get();
  System.out.println(response.getResult());
 }

}