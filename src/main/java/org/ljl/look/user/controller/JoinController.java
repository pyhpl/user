package org.ljl.look.user.controller;

import org.ljl.look.user.entity.Join;
import org.ljl.look.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JoinController {

    @Autowired
    private JoinService joinService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/api/join/count")
    @ResponseStatus(HttpStatus.OK)
    public int countByActivityUuid(@RequestParam String activityUuid) {
        return joinService.countByActivityUuid(activityUuid);
    }

    @PostMapping("/api/join")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody Join join) {
        joinService.add(join);
        return new HttpHeaders() {{
            set("uuid", join.getUuid());
        }};
    }

    @DeleteMapping("/api/join")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        joinService.deleteByUuid(uuid);
    }

    @GetMapping("/api/join")
    @ResponseStatus(HttpStatus.OK)
    public Join get(@RequestHeader("token") String token, @RequestParam String activityUuid) {
        return joinService.getByActivityUuidAndFromUser(activityUuid, stringRedisTemplate.opsForValue().get(token));
    }

    @GetMapping("/api/user/join/s")
    @ResponseStatus(HttpStatus.OK)
    public List<Join> getsByFromUser(@RequestHeader("token") String token) {
        return joinService.getsByFromUser(stringRedisTemplate.opsForValue().get(token));
    }
}
