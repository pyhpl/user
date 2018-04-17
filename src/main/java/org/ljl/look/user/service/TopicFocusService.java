package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.mapper.TopicFocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class TopicFocusService {

    @Autowired
    private TopicFocusMapper topicFocusMapper;

    public void add(TopicFocus topicFocus) {
        TopicFocus maybeExistedTopicFocus = topicFocusMapper.selectByFromUserAndTopicUuid(topicFocus.getFromUser(), topicFocus.getTopicUuid());
        if (maybeExistedTopicFocus == null) { // 不存在则新建记录
            topicFocusMapper.insert(topicFocus);
        } else { // 已存在则更新
            topicFocus.setUuid(maybeExistedTopicFocus.getUuid());
            topicFocusMapper.updateValidByUuid(ConstConfig.VALID, maybeExistedTopicFocus.getUuid());
        }
    }

    public void deleteByUuid(String uuid) {
        topicFocusMapper.deleteByUuid(uuid);
    }

    public List<TopicFocus> getFocused(List<TopicFocus> topicFocuses) {
        List<TopicFocus> focusedTopics = new ArrayList<>();
        topicFocuses.forEach(topicFocus -> {
            TopicFocus e = topicFocusMapper.selectByFromUserAndTopicUuidAndValid(topicFocus.getFromUser(), topicFocus.getTopicUuid());
            if (e != null) {
                focusedTopics.add(topicFocus);
            }
        });
        return focusedTopics;
    }

    public List<TopicFocus> getHot() {
        return topicFocusMapper.selectTopicUuidGroupByTopicUuidOrderByCount().stream()
                .map(topicUuid ->
                    TopicFocus.builder().topicUuid(topicUuid).build()
                )
                .collect(Collectors.toList());
    }
}
