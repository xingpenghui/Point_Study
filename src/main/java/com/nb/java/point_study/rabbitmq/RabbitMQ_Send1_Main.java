package com.nb.java.point_study.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2019/7/17 15:59
 */
public class RabbitMQ_Send1_Main {
    //http://39.105.189.141:15672
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
        //4、创建队列
        /**
         * 定义队列 参数说明
         * 1、队列名称
         * 2、是否持久化 队列消息是否存储到磁盘
         * 3、是否独占队列
         * 4、是否断开之后自动删除消息
         * 5、额外设置的数据信息 */
        channel.queueDeclare("queue1902",false,false,false,null);
        //5、发送消息
        /*参数说明：
         * 1、交换机名称
         * 2、队列名称
         * 3、属性参数
         * 4、发送的消息内容 要求字节*/
        channel.basicPublish("","queue1902",null,"你睡着了吗".getBytes());
        //6、关闭
        channel.close();
        connection.close();

    }
}
