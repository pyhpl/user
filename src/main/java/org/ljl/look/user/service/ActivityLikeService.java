package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.ActivityLike;
import org.ljl.look.user.mapper.ActivityLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityLikeService {

    @Autowired
    private ActivityLikeMapper activityLikeMapper;

    public void add(ActivityLike activityLike) {
        activityLike.setValid(ConstConfig.UNVALID);
        ActivityLike maybeExistedActivityLike = activityLikeMapper.selectByActivityUuidAndFromUserAndValid(activityLike);
        if (maybeExistedActivityLike != null) {
            activityLike.setUuid(maybeExistedActivityLike.getUuid());
            activityLikeMapper.updateValidByUuid(maybeExistedActivityLike.getUuid());
        } else {
            activityLike.setValid(ConstConfig.VALID);
            activityLikeMapper.insert(activityLike);
        }
    }

    public void delete(String uuid) {
        activityLikeMapper.delete(uuid);
    }

    public int countByActivityUuid(String activityUuid) {
        return activityLikeMapper.countByActivityUuid(activityUuid);
    }

    public ActivityLike getByActivityUuidAndFromUser(String activityUuid, String fromUser) {
        ActivityLike activityLike = ActivityLike.builder()
                .activityUuid(activityUuid).fromUser(fromUser).valid(ConstConfig.VALID).build();
        return activityLikeMapper.selectByActivityUuidAndFromUserAndValid(activityLike);
    }
}
