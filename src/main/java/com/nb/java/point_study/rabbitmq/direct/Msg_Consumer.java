package com.nb.java.point_study.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *@Author feri
 *@Date Created in 2019/7/18 10:08
 */
public class Msg_Consumer {
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
       // channel.exchangeDeclare("exchange1902",BuiltinExchangeType.FANOUT);
//        //5、创建队列
//        channel.queueDeclare("laoxing",false,false,false,null);

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
        //channel.queueBind("order1902","exc_1902_direct","");
        //7、发送消息
        Consumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-支付："+new String(body));
            }
        };
        channel.basicConsume("pay1902",true,consumer);

    }
}
