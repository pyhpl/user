package org.ljl.look.user.service;

import org.ljl.look.user.entity.User;
import org.ljl.look.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void add(User user) {
        if (get(user.getOpenId()) == null) {
            userMapper.insert(user);
        }
    }

    public User get(String openId) {
        return userMapper.select(openId);
    }
}

