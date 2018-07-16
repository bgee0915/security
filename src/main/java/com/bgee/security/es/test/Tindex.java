package com.bgee.security.es.test;

import com.google.gson.JsonObject;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class Tindex {

    private static String host = "192.168.199.128";
    private static int port = 9300;
    private static TransportClient client = null;

    private static void getClient() throws Exception {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));

    }


    public static void main(String[] args) throws  Exception{
        getClient();
//        JsonObject json = new JsonObject();
//        json.addProperty("name","java编程思想");
//        json.addProperty("publishDate","2011-12-12");
//        json.addProperty("price","50");
//
//        IndexResponse response = client.prepareIndex("book","java","1")
//                                    .setSource(json.toString(), XContentType.JSON)
//                                    .get();
//        System.out.println("索引名称:  " + response.getIndex());
//        System.out.println("索引类型:  " + response.getType());
//        System.out.println("索引id:  " + response.getId());
//        System.out.println("当前状态:  " + response.status());

//        GetResponse getResponse = client.prepareGet("book","java","1").get();
//        System.out.println(getResponse.getSourceAsString());

    }
}
