package com.nb.java.point_study.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import sun.rmi.transport.Connection;

import java.net.InetSocketAddress;

/**
 *@Author feri
 *@Date Created in 2019/7/19 11:53
 */
public class Transport_Main {
    public static void main(String[] args) {
        //1、创建设置对象
        Settings settings=Settings.builder().put("cluster.name","qfjava").build();
        //2、创建连接对象
        Client client=new PreBuiltTransportClient(settings).
                addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.189.141",9300)));
        //3、操作ES服务器 CRUD
        //新增
        /**
         * 新增或修改 id就是新增 id就是修改
         * 参数说明：
         * 1、索引名称
         * 2、类型
         * 3、id 唯一值*/
        IndexResponse indexResponse=client.prepareIndex("es1902","food","1").
                setSource("{\"id\":1,\"name\":\"鸡腿\"}",XContentType.JSON).get();
        System.out.println("新增："+indexResponse.status().toString());

        //4、销毁
        client.close();
    }
}
