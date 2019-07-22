package com.nb.java.point_study.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;

/**
 *@Author feri
 *@Date Created in 2019/7/19 11:53
 */
public class Transport2_Main {
    public static void main(String[] args) {
        //1、创建设置对象
        Settings settings=Settings.builder().put("cluster.name","qfjava").build();
        //2、创建连接对象
        Client client=new PreBuiltTransportClient(settings).
                addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.189.141",9300)));
        //3、操作ES服务器 CRUD

        Offer offer=new Offer();
        offer.setAddress("柬埔寨");
        offer.setMoney(20000);
        offer.setName("小雨");
        offer.setId(10);
        //新增
        IndexResponse indexResponse=client.prepareIndex("es1902","offer",offer.getId()+"").
                setSource(JSON.toJSONString(offer),XContentType.JSON).get();
        System.out.println("新增："+indexResponse.status().name());
        //修改
        offer.setName("轩轩");
        UpdateResponse updateResponse=client.prepareUpdate("es1902","offer",offer.getId()+"").
                setDoc(JSON.toJSONString(offer),XContentType.JSON).get();
        System.out.println("修改："+updateResponse.status().name());
        //查询
        GetResponse getResponse=client.prepareGet("es1902","offer",offer.getId()+"").get();
        String json=getResponse.getSourceAsString();
        System.out.println("查询："+json);
        //删除
        DeleteResponse deleteResponse=client.prepareDelete("es1902","offer",offer.getId()+"").get();
        System.out.println("删除："+deleteResponse.status().name());



        //4、销毁
        client.close();
    }
}
