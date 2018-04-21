package org.ljl.look.user.service;

import org.ljl.look.user.entity.Administrator;
import org.ljl.look.user.mapper.AdministratorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Administrator get(String token) {
        String openId = stringRedisTemplate.opsForValue().get(token);
        return administratorMapper.selectByOpenId(openId);
    }

}
