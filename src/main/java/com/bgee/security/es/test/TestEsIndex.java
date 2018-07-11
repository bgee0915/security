package com.bgee.security.es.test;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class TestEsIndex {

    private static String host = "192.168.199.128";
    private static int port = 9300;
    private TransportClient client = null;

    public void getClient()throws Exception {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(host),port));
        System.out.println(client);
        client.close();;
    }




}
