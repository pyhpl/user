package org.ljl.look.user.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

    @Before("addTag()")
    public void doBeforeAddTag(JoinPoint joinPoint) throws Exception {
        Tag tag = (Tag) joinPoint.getArgs()[0];
        tag.setUuid(UuidTool.getValue());
        tag.setCreateDate(new Date());
        tag.setValid(ConstConfig.VALID);
    }

//    @Pointcut("execution(public * org.ljl.look.user.service.TagService.adds(..))")
//    public void addTags(){}
//
//
//    @Before("addTags()")
//    public void doBeforeAddTags(JoinPoint joinPoint) throws Exception {
//        ((List<Tag>) joinPoint.getArgs()[0]).forEach(tag -> {
//            tag.setUuid(UuidTool.getValue());
//            tag.setCreateDate(new Date());
//            tag.setValid((short) 1);
//        });
//    }
}














