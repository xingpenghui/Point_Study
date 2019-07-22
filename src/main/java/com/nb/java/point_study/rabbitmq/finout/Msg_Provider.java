package com.nb.java.point_study.rabbitmq.finout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2019/7/18 10:08
 */
public class Msg_Provider {
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
        channel.exchangeDeclare("exchange1902",BuiltinExchangeType.FANOUT);
        //5、创建队列
        channel.queueDeclare("laoxing",false,false,false,null);
        channel.queueDeclare("jingjie",false,false,false,null);
        //6、绑定队列
        /**
         * 参数说明：
         * 1、交换器名称
         * 2、路由规则
         * 3、要绑定的队列名称*/
        //channel.exchangeBind("exchange1902","",qn);
        /**
         * 队列绑定
         * 参数说明：
         * 1、队列名称
         * 2、交换器名称
         * 3、路由规则*/
        channel.queueBind("laoxing","exchange1902","");
        channel.queueBind("jingjie","exchange1902","");
        //7、发送消息
        channel.basicPublish("exchange1902","",null,"小雨：请假，约了心理医生".getBytes());
        channel.close();
        connection.close();
    }
}