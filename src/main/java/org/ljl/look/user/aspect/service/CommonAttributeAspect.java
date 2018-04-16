package org.ljl.look.user.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.user.entity.*;
import org.ljl.look.user.util.UuidTool;
import org.springframework.stereotype.Component;
import org.ljl.look.user.configuration.ConstConfig;

import java.util.Date;

@Aspect
@Component
@Slf4j
public class CommonAttributeAspect {

    @Pointcut("execution(public * org.ljl.look.user.service.TagService.add(..))")
    public void addTag(){}

    @Pointcut("execution(public * org.ljl.look.user.service.DiscussionService.add(..))")
    public void addDiscussion(){}

    @Pointcut("execution(public * org.ljl.look.user.service.ActivityFocusService.add(..))")
    public void addActivityFocus(){}

    @Pointcut("execution(public * org.ljl.look.user.service.ActivityLikeService.add(..))")
    public void addLike(){}

    @Pointcut("execution(public * org.ljl.look.user.service.JoinService.add(..))")
    public void addJoin(){}

    @Before("addTag()||addDiscussion()||addActivityFocus()||addLike()||addJoin()")
    public void doBeforeAdd(JoinPoint joinPoint) throws Exception {
        Object object = joinPoint.getArgs()[0];
        if (object instanceof Tag) {
            Tag tag = (Tag) object;
            tag.setUuid(UuidTool.getValue());
            tag.setCreateDate(new Date());
            tag.setValid(ConstConfig.VALID);
        } else if (object instanceof Discussion) {
            Discussion discussion = (Discussion) object;
            discussion.setUuid(UuidTool.getValue());
            discussion.setLikeCount(0);
            discussion.setDislikeCount(0);
            if (discussion.getBelongToDiscussion() == null) {
                discussion.setBelongToDiscussion(ConstConfig.SINGLE_DISCUSSION);
            }
            if (discussion.getToUser() == null) {
                discussion.setToUser(ConstConfig.NONE_USER);
            }
            discussion.setDiscussDate(new Date());
            discussion.setValid(ConstConfig.VALID);
        } else if (object instanceof ActivityFocus) {
            ActivityFocus activityFocus = (ActivityFocus) object;
            activityFocus.setUuid(UuidTool.getValue());
            activityFocus.setFocusDate(new Date());
            activityFocus.setValid(ConstConfig.VALID);
        } else if (object instanceof ActivityLike) {
            ActivityLike activityLike = (ActivityLike) object;
            activityLike.setUuid(UuidTool.getValue());
            activityLike.setValid(ConstConfig.VALID);
        } else if (object instanceof Join) {
            Join join =  (Join) object;
            join.setUuid(UuidTool.getValue());
            join.setJoinDate(new Date());
            join.setValid(ConstConfig.VALID);
        }
    }

}














