package com.nb.java.point_study.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetSocketAddress;

/**
 *@Author feri
 *@Date Created in 2019/7/19 11:53
 */
public class Transport3_Main {
    public static void main(String[] args) {
        //1、创建设置对象
        Settings settings=Settings.builder().put("cluster.name","qfjava").build();
        //2、创建连接对象
        Client client=new PreBuiltTransportClient(settings).
                addTransportAddress(new TransportAddress(new InetSocketAddress("39.105.189.141",9300)));
        //3、操作ES服务器
        //条件查询
        //1、创建查询内容
        //范围查询
       // RangeQueryBuilder rangeQueryBuilder=QueryBuilders.rangeQuery("id").gt("1").lt("10");
        //模糊查询
        WildcardQueryBuilder wildcardQueryBuilder=QueryBuilders.wildcardQuery("name","*小*");
        //单词查询 精确查询
        TermQueryBuilder termQueryBuilder=QueryBuilders.termQuery("id","101");
        //2、条件拼接
        BoolQueryBuilder boolQueryBuilder=QueryBuilders.boolQuery();
        boolQueryBuilder.should(wildcardQueryBuilder);
        boolQueryBuilder.should(termQueryBuilder);
        //3、创建查询对象
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        //4、执行条件查询  链式编程
        SearchResponse searchResponse=client.prepareSearch("es1902").//指定查询的索引名称
                setTypes("offer").  //设置查询的类型
                addSort("id",SortOrder.DESC). //设置排序
                setQuery(searchSourceBuilder.query()).  //设置查询条件
                get(); //获取查询结果

        //5、解析查询结果
        SearchHits searchHits=searchResponse.getHits();
        SearchHit[] arrhits=searchHits.getHits();
        //遍历数组
        for(SearchHit sh:arrhits){
            System.out.println(sh.getSourceAsString());
        }


        //4、销毁
        client.close();
    }
}
