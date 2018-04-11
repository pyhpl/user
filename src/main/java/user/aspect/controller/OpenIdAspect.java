package user.aspect.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import user.entity.Tag;
import user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Slf4j
public class OpenIdAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(public * user.controller.UserController.post(..))")
    public void postUser(){}

    @Pointcut("execution(public * user.controller.TagController.posts(..))")
    public void postTags() {}

    @Before("postUser()||postTags()")
    public void doBeforeWeavingOpenId(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String openId = stringRedisTemplate.opsForValue().get(request.getHeader("token"));
        Arrays.stream(joinPoint.getArgs()).forEach(arg -> {
            if (arg instanceof User) {
                ((User) arg).setOpenId(openId);
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
