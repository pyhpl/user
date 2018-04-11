package user.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import user.configuration.ConstConfig;
import user.entity.Tag;
import user.util.UuidTool;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class CommonAttributeAspect {

    @Pointcut("execution(public * user.service.TagService.add(..))")
    public void addTag(){}

    @Before("addTag()")
    public void doBeforeAddTag(JoinPoint joinPoint) throws Exception {
        Tag tag = (Tag) joinPoint.getArgs()[0];
        tag.setUuid(UuidTool.getValue());
        if (tag.getUserOpenId() == null) {
            tag.setUserOpenId(ConstConfig.DEFAULT_VISITOR_OPEN_ID);
        }
        tag.setCreateDate(new Date());
        tag.setValid((short) 1);
    }

    @Pointcut("execution(public * user.service.TagService.adds(..))")
    public void addTags(){}


    @Before("addTags()")
    public void doBeforeAddTags(JoinPoint joinPoint) throws Exception {
        ((List<Tag>) joinPoint.getArgs()[0]).forEach(tag -> {
            tag.setUuid(UuidTool.getValue());
            tag.setCreateDate(new Date());
            tag.setValid((short) 1);
        });
    }
}














