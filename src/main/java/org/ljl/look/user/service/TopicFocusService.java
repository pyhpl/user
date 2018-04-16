package org.ljl.look.user.service;

import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.mapper.TopicFocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicFocusService {

    @Autowired
    private TopicFocusMapper topicFocusMapper;

    public List<TopicFocus> getByCondition(String orderBy, String order, int number) {
        return topicFocusMapper.selectByCondition(orderBy, order, number);
    }

}
