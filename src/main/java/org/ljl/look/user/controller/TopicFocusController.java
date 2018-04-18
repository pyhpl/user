package org.ljl.look.user.controller;

import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.service.TopicFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicFocusController {

    @Autowired
    private TopicFocusService topicFocusService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/api/topic-focus/s/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> getsHot() {
        return topicFocusService.getHot();
    }

    @PostMapping("/api/topic-focus")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody TopicFocus topicFocus) {
        topicFocusService.add(topicFocus);
        return new HttpHeaders() {{
            set("uuid", topicFocus.getUuid());
        }};
    }

    @DeleteMapping("/api/topic-focus")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        topicFocusService.deleteByUuid(uuid);
    }

    @RequestMapping("/api/topic-focus/s")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> gets(@RequestParam boolean focused, @RequestBody List<TopicFocus> topicFocuses) {
        if (focused) {
            return topicFocusService.getFocused(topicFocuses);
        }
        return null;
    }

    @GetMapping("/api/user/topic-focus/s")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> getsByFromUser(@RequestHeader("token") String token) {
        return topicFocusService.getsByFromUser(stringRedisTemplate.opsForValue().get(token));
    }

}
