package test.com.bgee.security.es.test;

import com.google.gson.JsonObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.InetSocketAddress;

// 连接 es 集群
public class TindexConTest {
    private static String host = "192.168.199.128";
    private static int port = 9300;
    public static final String CLUSTER_NAME = "my-application";
    private static Settings.Builder settings = Settings.builder().put("cluster.name",CLUSTER_NAME);
    private static TransportClient client = null;

    @Before
    public void start(){
        System.out.println("start: -------------------------------------");
        clients();
    }
    @After
    public void end(){
        client.close();
        System.out.println("end: ---------------------------------------");
    }
    private static void clients(){
        try {
            client = new PreBuiltTransportClient(settings.build())
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        System.out.println("go");
        System.out.println(client);
    }


    // 创建 index
    @Test
    public void create(){
        JsonObject json = new JsonObject();
        json.addProperty("name","java编程思想");
        json.addProperty("publishDate","2011-12-12");
        json.addProperty("price","50");

        IndexResponse response = client.prepareIndex("book","java","1")
                .setSource(json.toString(), XContentType.JSON)
                .get();
        System.out.println("索引名称:  " + response.getIndex());
        System.out.println("索引类型:  " + response.getType());
        System.out.println("索引id:  " + response.getId());
        System.out.println("当前状态:  " + response.status());
    }


    // 获取 Index里面的值
    @Test
    public void get(){
        GetResponse getResponse = client.prepareGet("book","java","1").get();
        JsonObject json = new JsonObject();
        System.out.println(getResponse.getSource().get("name"));
        System.out.println(getResponse.getSource().get("publishDate"));
        System.out.println(getResponse.getSource().get("price   "));
        System.out.println(getResponse.getId());
        System.out.println(getResponse.getType());
        System.out.println(getResponse.getIndex());
        System.out.println(getResponse.getSourceAsString());
    }

    @Test
    public void update(){
        JsonObject json = new JsonObject();
        json.addProperty("name","java编程思想2");
        json.addProperty("publishDate","2013-12-12");
        json.addProperty("price","52");

        UpdateResponse update = client.prepareUpdate("book","java","1")
                .setDoc(json.toString(),XContentType.JSON)
                .get();
        System.out.println("索引名称:  " + update.getIndex());
        System.out.println("索引类型:  " + update.getType());
        System.out.println("索引id:  " + update.getId());
        System.out.println("当前状态:  " + update.status());
    }

    @Test
    public void delete(){
        DeleteResponse delete = client.prepareDelete("book","java","1").get();
        System.out.println("索引名称:  " + delete.getIndex());
        System.out.println("索引类型:  " + delete.getType());
        System.out.println("索引id:  " + delete.getId());
        System.out.println("当前状态:  " + delete.status());
    }
}
