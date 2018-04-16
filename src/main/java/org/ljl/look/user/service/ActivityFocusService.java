package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.ActivityFocus;
import org.ljl.look.user.mapper.ActivityFocusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActivityFocusService {

    @Autowired
    private ActivityFocusMapper activityFocusMapper;

    public void add(ActivityFocus activityFocus) {
        ActivityFocus maybeExistedActivityFocus = activityFocusMapper.selectByFromUserAndActivityUuid(activityFocus.getFromUser(), activityFocus.getActivityUuid());
        if (maybeExistedActivityFocus == null) { // 不存在则新建记录
            activityFocusMapper.insert(activityFocus);
        } else { // 已存在则更新
            activityFocus.setUuid(maybeExistedActivityFocus.getUuid());
            activityFocusMapper.updateValidByUuid(ConstConfig.VALID, maybeExistedActivityFocus.getUuid());
        }
    }

    public List<ActivityFocus> getsByFromUser(String fromUser) {
        return activityFocusMapper.selectByFromUser(fromUser);
    }

    public void deleteByUuid(String uuid) {
        activityFocusMapper.deleteByUuid(uuid);
    }

    public ActivityFocus getByActivityUuidAndFromUser(String activityUuid, String fromUser) {
        ActivityFocus activityLike = ActivityFocus.builder()
                .activityUuid(activityUuid).fromUser(fromUser).valid(ConstConfig.VALID).build();
        return activityFocusMapper.selectByActivityUuidAndFromUserAndValid(activityLike);
    }
}
