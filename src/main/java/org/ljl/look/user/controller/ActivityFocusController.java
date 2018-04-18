package org.ljl.look.user.controller;

import org.ljl.look.user.entity.ActivityFocus;
import org.ljl.look.user.service.ActivityFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityFocusController {

    @Autowired
    private ActivityFocusService activityFocusService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/api/activity-focus")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody ActivityFocus activityFocus) {
        activityFocusService.add(activityFocus);
        return new HttpHeaders() {{
            set("uuid", activityFocus.getUuid());
        }};
    }

    @GetMapping("/api/user/activity-focus/s")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityFocus> getsByFromUser(@RequestHeader("token") String token) {
        return activityFocusService.getsByFromUser(stringRedisTemplate.opsForValue().get(token));
    }

    @DeleteMapping("/api/activity-focus")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        activityFocusService.deleteByUuid(uuid);
    }

    @GetMapping("/api/activity-focus")
    @ResponseStatus(HttpStatus.OK)
    public ActivityFocus get(@RequestHeader("token") String token, @RequestParam String activityUuid) {
        return activityFocusService.getByActivityUuidAndFromUser(activityUuid, stringRedisTemplate.opsForValue().get(token));
    }
}
















