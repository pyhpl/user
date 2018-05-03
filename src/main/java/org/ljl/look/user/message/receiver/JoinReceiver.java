package org.ljl.look.user.message.receiver;

import com.fasterxml.jackson.core.type.TypeReference;
import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Join;
import org.ljl.look.user.message.wrapper.MessageWrapper;
import org.ljl.look.user.service.JoinService;
import org.ljl.look.user.util.JsonTool;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = ConstConfig.QUEUE_JOIN)
public class JoinReceiver {
    @Autowired
    private JoinService joinService;

    @RabbitHandler
    public void process(String joinMessageJson) {
        MessageWrapper<Join> joinMessageWrapper = JsonTool.fromJson(joinMessageJson, new TypeReference<MessageWrapper<Join>>() {});
        switch (joinMessageWrapper.getMethod()) {
            case POST:
                joinService.add(joinMessageWrapper.getBody());
                break;
        }
    }
}
