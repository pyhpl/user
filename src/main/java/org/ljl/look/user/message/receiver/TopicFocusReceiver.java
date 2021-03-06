package org.ljl.look.user.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.message.wrapper.MessageWrapper;
import org.ljl.look.user.service.TopicFocusService;
import org.ljl.look.user.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_TOPIC_FOCUS)
public class TopicFocusReceiver {
    @Autowired
    private TopicFocusService topicFocusService;

    @RabbitHandler
    public void process(String topicFocusMessageJson) {
        MessageWrapper<TopicFocus> topicFocusMessageWrapper = JsonTool.fromJson(topicFocusMessageJson, new TypeReference<MessageWrapper<TopicFocus>>() {});
        switch (topicFocusMessageWrapper.getMethod()) {
            case POST:
                topicFocusService.add(topicFocusMessageWrapper.getBody());
                break;
        }
    }
}
