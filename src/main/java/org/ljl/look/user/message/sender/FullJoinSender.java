package org.ljl.look.user.message.sender;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.message.wrapper.FullJoinWrapper;
import org.ljl.look.user.message.wrapper.MessageWrapper;
import org.ljl.look.user.util.JsonTool;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FullJoinSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendToAdd(FullJoinWrapper fullJoinWrapper) {
        rabbitTemplate.convertAndSend(
                ConstConfig.QUEUE_JOIN_MESSAGE,
                JsonTool.toJson(
                        MessageWrapper.builder().method(MessageWrapper.MessageMethod.POST).body(fullJoinWrapper).build()
                )
        );
    }
}
