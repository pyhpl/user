package user.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import user.configuration.ConstConfig;

/**
 * 加载系统初始化数据（游客数据）
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        stringRedisTemplate.opsForValue().set(
                ConstConfig.DEFAULT_VISITOR_TOKEN,
                ConstConfig.DEFAULT_VISITOR_OPEN_ID
        );
    }
}
