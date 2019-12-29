package com.xc.rabbitmq.consumer.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import com.xc.rabbitmq.consumer.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Desc:
 *
 * @author: keen
 * Date: 2019-12-29
 * Time: 14:57
 */
@Component
@Slf4j
public class RabbitmqMessageProcessHandler {

    @RabbitListener(queues = RabbitConfig.QUEUE_INFORM_EMAIL)
    public void processEmailMessage(String msg, Message message, Channel channel) {
        log.info("message:{}", msg);
    }
}
