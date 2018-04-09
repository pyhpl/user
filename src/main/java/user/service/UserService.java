package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.User;
import user.mapper.UserMapper;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void add(User user) {
        if (get(user.getOpenId()) != null) {
            userMapper.insert(user);
        }
    }

    public User get(String openId) {
        return userMapper.select(openId);
    }
}
