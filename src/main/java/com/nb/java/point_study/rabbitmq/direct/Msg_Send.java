package com.nb.java.point_study.rabbitmq.direct;

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
        channel.exchangeDeclare("exc_1902_direct",BuiltinExchangeType.DIRECT);
        //5、创建队列
        channel.queueDeclare("order1902",false,false,false,null);
        channel.queueDeclare("user1902",false,false,false,null);
        //6、绑定队列到交换器
        channel.queueBind("order1902","exc_1902_direct","order");
        channel.queueBind("user1902","exc_1902_direct","user");
        //7、发布消息
        channel.basicPublish("exc_1902_direct","user",null,"新用户注册".getBytes());
        //销毁
        channel.close();
        connection.close();
    }
}
