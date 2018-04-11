package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import user.configuration.ConstConfig;
import user.entity.Tag;
import user.service.TagService;

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
    public List<String> gets(@RequestHeader(value = "token", required = false, defaultValue = ConstConfig.DEFAULT_VISITOR_TOKEN) String token) {
        return tagService.gets(stringRedisTemplate.opsForValue().get(token));
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@RequestBody Tag tag) {
        tagService.add(tag);
    }

    @PutMapping("s")
    @ResponseStatus(HttpStatus.CREATED)
    public void posts(@RequestBody List<Tag> tags) {
        tagService.adds(tags);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String uuid) {
        tagService.delete(uuid);
    }
}
