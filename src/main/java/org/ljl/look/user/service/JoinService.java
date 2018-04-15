package org.ljl.look.user.service;

import org.ljl.look.user.mapper.JoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinService {
    @Autowired
    private JoinMapper joinMapper;

    public int countByActivityUuid(String activityUuid) {
        return joinMapper.countByActivityUuid(activityUuid);
    }
}
