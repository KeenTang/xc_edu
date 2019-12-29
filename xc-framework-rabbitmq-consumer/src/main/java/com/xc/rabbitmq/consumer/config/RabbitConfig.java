package com.xc.rabbitmq.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-29
 * Time: 14:12
 */
@Configuration
public class RabbitConfig {
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String EXCHANGE_TOPICS_INFORM = "exchange_topics_inform";
    public static final String ROUTING_KEY_EMAIL = "inform.#.email.#";
    public static final String ROUTING_KEY_SMS = "inform.#.sms.#";

    /**
     * 声明消息队列的交换机
     *
     * @return
     */
    @Bean(EXCHANGE_TOPICS_INFORM)
    public Exchange exchange() {
        //durable表示是否持久化
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPICS_INFORM).durable(true).build();
    }

    /**
     * 声明电子邮件的消息队列
     *
     * @return
     */
    @Bean(QUEUE_INFORM_EMAIL)
    public Queue emailQueue() {
        return new Queue(QUEUE_INFORM_EMAIL);
    }

    /**
     * 声明短信的消息队列
     *
     * @return
     */
    @Bean(QUEUE_INFORM_SMS)
    public Queue smsQueue() {
        return new Queue(QUEUE_INFORM_SMS);
    }

    /**
     * 绑定Email队列的到交换机
     *
     * @param emailQueue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingEmailQueue(@Qualifier(QUEUE_INFORM_EMAIL) Queue emailQueue,
                                     @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange).with(ROUTING_KEY_EMAIL).noargs();
    }

    @Bean
    public Binding bindingSmsQueue(@Qualifier(QUEUE_INFORM_SMS) Queue smsQueue,
                                   @Qualifier(EXCHANGE_TOPICS_INFORM) Exchange exchange) {
        return BindingBuilder.bind(smsQueue).to(exchange).with(ROUTING_KEY_SMS).noargs();
    }
}
