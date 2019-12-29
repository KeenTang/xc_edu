package com.xc.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-27
 * Time: 21:25
 */
public class ProducerTest {
    private static final String QUEUE_NAME = "ProducerTest";

    public static void main(String[] args) {
        //通过连接工厂创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机
        connectionFactory.setVirtualHost("/");
        Connection connection = null;
        Channel channel=null;
        try {
            //创建连接
            connection = connectionFactory.newConnection();
            //创建通道
            channel = connection.createChannel();
            //声明队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicPublish("",QUEUE_NAME,null,"Hello Keen".getBytes("UTF-8"));
            System.out.println("运行完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
