package org.ljl.look.user.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    Queue queue() {
        return new Queue(ConstConfig.QUEUE_TOPIC_FOCUS);
    }
}
