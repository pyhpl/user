package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Tag;
import org.ljl.look.user.entity.User;
import org.ljl.look.user.mapper.TagMapper;
import org.ljl.look.user.mapper.UserMapper;
import org.ljl.look.user.util.UuidTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TagMapper tagMapper;

    public void add(User user) {
        if (get(user.getOpenId()) == null) {
            userMapper.insert(user);
            // 初始化用户标签
            tagMapper.selectByUserOpenId(ConstConfig.DEFAULT_VISITOR_OPEN_ID).forEach(tag -> {
                tag.setUuid(UuidTool.getValue());
                tag.setUserOpenId(user.getOpenId());
                tag.setCreateDate(new Date());
                tag.setValid(ConstConfig.VALID);
                tagMapper.insert(tag);
            });
        }
    }

    public User get(String openId) {
        return userMapper.select(openId);
    }
}

