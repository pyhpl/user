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
@RequestMapping("/api/activity-focus")
public class ActivityFocusController {

    @Autowired
    private ActivityFocusService activityFocusService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody ActivityFocus activityFocus) {
        activityFocusService.add(activityFocus);
        return new HttpHeaders() {{
            set("uuid", activityFocus.getUuid());
        }};
    }

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<ActivityFocus> getsByFromUser(@RequestParam String fromUser) {
        return activityFocusService.getsByFromUser(fromUser);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        activityFocusService.deleteByUuid(uuid);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ActivityFocus get(@RequestHeader("token") String token, @RequestParam String activityUuid) {
        return activityFocusService.getByActivityUuidAndFromUser(activityUuid, stringRedisTemplate.opsForValue().get(token));
    }
}
















