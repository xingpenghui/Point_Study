package com.nb.java.point_study.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2019/7/18 14:14
 */
public class Msg_Send {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1、创建连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //设置连接信息
        factory.setHost("39.105.189.141");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        //2、获取连接对象
        Connection connection=factory.newConnection();
        //3、获取通道对象
        Channel channel=connection.createChannel();
        //4、定义交换器
        channel.exchangeDeclare("exc_1902_topic",BuiltinExchangeType.TOPIC);
        //5、创建队列
        channel.queueDeclare("pay1902",false,false,false,null);
        channel.queueDeclare("msg1902",false,false,false,null);
        channel.queueDeclare("oss1902",false,false,false,null);
        channel.queueDeclare("msg1901",false,false,false,null);
        //6、绑定队列到交换器
        channel.queueBind("pay1902","exc_1902_topic","pay.#");
        channel.queueBind("msg1902","exc_1902_topic","msg.#");
        channel.queueBind("oss1902","exc_1902_topic","oss.#");
        channel.queueBind("msg1901","exc_1902_topic","msg.#");
        //7、发布消息
        channel.basicPublish("exc_1902_topic","msg.",null,"订单222预支付信息".getBytes());
        //销毁
        channel.close();
        connection.close();
    }
}
