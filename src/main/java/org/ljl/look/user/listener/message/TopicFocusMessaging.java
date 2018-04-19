package org.ljl.look.user.listener.message;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.service.TopicFocusService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_TOPIC_FOCUS)
public class TopicFocusMessaging {
    @Autowired
    private TopicFocusService topicFocusService;

    @RabbitHandler
    public void process(TopicFocus topicFocus) {
        topicFocusService.add(topicFocus);
    }
}