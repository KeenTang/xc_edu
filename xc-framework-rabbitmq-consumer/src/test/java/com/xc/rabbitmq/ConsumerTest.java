package com.xc.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-27
 * Time: 21:57
 */
public class ConsumerTest {

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
        try {
            //创建连接
            connection = connectionFactory.newConnection();
            //创建通道
            Channel channel = connection.createChannel();
            //监听队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    //交换机
                    String exchange = envelope.getExchange();
                    System.out.println("exchange==" + exchange);
                    //消息ID
                    long messageId = envelope.getDeliveryTag();
                    System.out.println("messageId==" + messageId);
                    //消息
                    String message = new String(body, Charset.forName("UTF-8"));
                    System.out.println("message==" + message);
                }
            };
            //消费消息
            channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
