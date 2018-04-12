package org.ljl.look.user.aspect.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.ljl.look.user.entity.Tag;
import org.ljl.look.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class OpenIdAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * org.ljl.look.user.controller.UserController.post(..))")
    public void postUser(){}

    @Pointcut("execution(public * org.ljl.look.user.controller.TagController.post(..))")
    public void postTag() {}

    @Pointcut("execution(public * org.ljl.look.user.controller.TagController.posts(..))")
    public void postTags() {}

    @Before("postUser()||postTag()||postTags()")
    public void doBeforeWeavingOpenId(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String openId = stringRedisTemplate.opsForValue().get(request.getHeader("token"));
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            if (arg instanceof User) {
                ((User) arg).setOpenId(openId);
            } else if (arg instanceof Tag) {
                ((Tag) arg).setUserOpenId(openId);
            }
            if (arg instanceof List) {
                ((List) arg).forEach(e -> {
                    if (e instanceof Tag) {
                        ((Tag) e).setUserOpenId(openId);
                    }
                });
            }
        });
    }

}
