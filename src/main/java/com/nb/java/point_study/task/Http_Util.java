package com.nb.java.point_study.task;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;

/**
 *@Author feri
 *@Date Created in 2019/7/9 15:00
 */
public class Http_Util {

    //基于HttpClient 实现的get 请求 获取响应字符串
    public static String getStr(String url){
        //1、创建客户端对象
        CloseableHttpClient httpClient=HttpClientBuilder.create().build();
        //2、创建请求方式
        HttpGet httpGet=new HttpGet(url);
        try {
            //3、发起请求
            HttpResponse httpResponse=httpClient.execute(httpGet);
            //4、验证响应是否成功
            if(httpResponse.getStatusLine().getStatusCode()==200){
                //5、获取响应结果
                return EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
