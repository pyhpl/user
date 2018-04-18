package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Join;
import org.ljl.look.user.mapper.JoinMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JoinService {
    @Autowired
    private JoinMapper joinMapper;

    public int countByActivityUuid(String activityUuid) {
        return joinMapper.countByActivityUuid(activityUuid);
    }

    public void add(Join join) {
        Join maybeExistedJoin = joinMapper.selectByFromUserAndActivityUuid(join.getFromUser(), join.getActivityUuid());
        if (maybeExistedJoin == null) { // 不存在则新建记录
            joinMapper.insert(join);
        } else { // 已存在则更新
            join.setUuid(maybeExistedJoin.getUuid());
            joinMapper.updateValidByUuid(ConstConfig.VALID, maybeExistedJoin.getUuid());
        }
    }

    public void deleteByUuid(String uuid) {
        joinMapper.deleteByUuid(uuid);
    }

    public Join getByActivityUuidAndFromUser(String activityUuid, String fromUser) {
        Join activityLike = Join.builder()
                .activityUuid(activityUuid).fromUser(fromUser).valid(ConstConfig.VALID).build();
        return joinMapper.selectByActivityUuidAndFromUserAndValid(activityLike);
    }

    public List<Join> getsByFromUser(String fromUser) {
        return joinMapper.selectByFromUser(fromUser);
    }
}
