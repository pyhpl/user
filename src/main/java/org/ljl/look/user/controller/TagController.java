package org.ljl.look.user.controller;

import org.ljl.look.user.configuration.ConstConfig;
import org.ljl.look.user.entity.Tag;
import org.ljl.look.user.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<Tag> gets(@RequestHeader(value = "token", required = false, defaultValue = ConstConfig.DEFAULT_VISITOR_TOKEN) String token) {
        return tagService.gets(stringRedisTemplate.opsForValue().get(token));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody Tag tag) {
        tagService.add(tag);
        return new HttpHeaders() {{
            set("uuid", tag.getUuid());
        }};
    }

    @PostMapping("s")
    @ResponseStatus(HttpStatus.CREATED)
    public void posts(@RequestBody List<Tag> tags) {
        tagService.adds(tags);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String uuid) {
        tagService.delete(uuid);
    }

    @DeleteMapping("s")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<String> uuids) {
        tagService.deletes(uuids);
    }
}
