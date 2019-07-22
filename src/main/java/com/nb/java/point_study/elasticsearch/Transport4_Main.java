package com.nb.java.point_study.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;

/**
 *@Author feri
 *@Date Created in 2019/7/19 11:53
 */
public class Transport4_Main {
    public static void main(String[] args) {
        //1、创建设置对象
        Settings settings=Settings.builder().put("cluster.name","qfjava").build();
        //2、创建连接对象
        Client client=new PreBuiltTransportClient(settings).
                addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.189.141",9300)));
        //3、操作ES服务器
        //实现批处理
        BulkRequestBuilder bulkRequestBuilder=client.prepareBulk();
        for(int i=1;i<10001;i++){
            Offer offer=new Offer();
            offer.setAddress("叙利亚");
            offer.setMoney(200000);
            offer.setName("轩轩");
            offer.setId(1000000+i);
            bulkRequestBuilder.add(client.prepareIndex("es1902","offer",offer.getId()+"").
                    setSource(JSON.toJSONString(offer),XContentType.JSON));
        }
        //执行操作
        BulkResponse bulkItemResponses=bulkRequestBuilder.get();
        System.out.println("批处理："+bulkItemResponses.status().name());
        //4、销毁
        client.close();
    }
}
