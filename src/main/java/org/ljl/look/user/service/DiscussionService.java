package org.ljl.look.user.service;

import org.ljl.look.user.entity.Discussion;
import org.ljl.look.user.mapper.DiscussionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DiscussionService {

    @Autowired
    private DiscussionMapper discussionMapper;

    public List<Discussion> getByBelongToActivity(String belongToActivity) {
        return discussionMapper.selectByBelongToActivity(belongToActivity);
    }

    public void add(Discussion discussion) {
        discussionMapper.insert(discussion);
    }

}
