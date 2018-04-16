package org.ljl.look.user.controller;

import org.ljl.look.user.entity.ActivityLike;
import org.ljl.look.user.service.ActivityLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity-like")
public class ActivityLikeController {

    @Autowired
    private ActivityLikeService activityLikeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody ActivityLike activityLike) {
        activityLikeService.add(activityLike);
        return new HttpHeaders() {{
            set("uuid", activityLike.getUuid());
        }};
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String uuid) {
        activityLikeService.delete(uuid);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int countByActivityUuid(@RequestParam String activityUuid) {
        return activityLikeService.countByActivityUuid(activityUuid);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ActivityLike get(@RequestHeader("token") String token, @RequestParam String activityUuid) {
        return activityLikeService.getByActivityUuidAndFromUser(activityUuid, stringRedisTemplate.opsForValue().get(token));
    }
}
