package org.ljl.look.user.service;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Activity;
import org.ljl.look.user.entity.Join;
import org.ljl.look.user.entity.User;
import org.ljl.look.user.feign.ActivityServiceFeign;
import org.ljl.look.user.mapper.JoinMapper;
import org.ljl.look.user.message.sender.FullJoinSender;
import org.ljl.look.user.message.wrapper.FullJoinWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JoinService {
    @Autowired
    private JoinMapper joinMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private FullJoinSender fullJoinSender;
    @Autowired
    private ActivityServiceFeign activityServiceFeign;

    public int countByActivityUuid(String activityUuid) {
        return joinMapper.countByActivityUuid(activityUuid);
    }

    public void add(Join join) {
        Join maybeExistedJoin = joinMapper.selectByFromUserAndActivityUuid(join.getFromUser(), join.getActivityUuid());
        if (maybeExistedJoin == null) { // 不存在则新建记录
            joinMapper.insert(join);
            sendUserJoinMessage(join);
        } else { // 已存在则更新
            join.setUuid(maybeExistedJoin.getUuid());
            joinMapper.updateValidByUuid(ConstConfig.VALID, maybeExistedJoin.getUuid());
        }
    }

    private void sendUserJoinMessage(Join join) {
        FullJoinWrapper fullJoinWrapper = new FullJoinWrapper();
        // 设置用户信息
        User user = userService.get(join.getFromUser());
        fullJoinWrapper.setOpenId(user.getOpenId());
        fullJoinWrapper.setName(user.getName());
        fullJoinWrapper.setAvatar(user.getAvatar());
        // 设置活动信息
        Activity activity = activityServiceFeign.getActivity(join.getActivityUuid());
        fullJoinWrapper.setActivityTitle(activity.getTitle());
        fullJoinWrapper.setActivityUuid(activity.getUuid());
        fullJoinWrapper.setActivityPublishUser(activity.getPublishUser());
        // 发送信息
        fullJoinSender.sendToAdd(fullJoinWrapper);
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
