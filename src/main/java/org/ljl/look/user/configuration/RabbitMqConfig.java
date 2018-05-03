package org.ljl.look.user.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    Queue topicFocusQueue() {
        return new Queue(ConstConfig.QUEUE_TOPIC_FOCUS);
    }

    @Bean
    Queue joinQueue() {
        return new Queue(ConstConfig.QUEUE_JOIN);
    }

    @Bean
    Queue joinMessageQueue() {
        return new Queue(ConstConfig.QUEUE_JOIN_MESSAGE);
    }
}
