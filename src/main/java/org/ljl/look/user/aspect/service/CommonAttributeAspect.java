package org.ljl.look.user.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.user.entity.Discussion;
import org.ljl.look.user.entity.Focus;
import org.ljl.look.user.util.UuidTool;
import org.springframework.stereotype.Component;
import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Tag;

import java.util.Date;
import java.util.List;

@Aspect
@Component
@Slf4j
public class CommonAttributeAspect {

    @Pointcut("execution(public * org.ljl.look.user.service.TagService.add(..))")
    public void addTag(){}

    @Pointcut("execution(public * org.ljl.look.user.service.DiscussionService.add(..))")
    public void addDiscussion(){}

    @Pointcut("execution(public * org.ljl.look.user.service.FocusService.add(..))")
    public void addFocus(){}

    @Before("addTag()||addDiscussion()||addFocus()")
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
            discussion.setDiscussDate(new Date());
            discussion.setValid(ConstConfig.VALID);
        } else if (object instanceof Focus) {
            Focus focus = (Focus) object;
            focus.setUuid(UuidTool.getValue());
            focus.setFocusDate(new Date());
            focus.setValid(ConstConfig.VALID);
        }
    }

}














