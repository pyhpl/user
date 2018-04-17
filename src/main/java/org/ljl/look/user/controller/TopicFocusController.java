package org.ljl.look.user.controller;

import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.service.TopicFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic-focus")
public class TopicFocusController {

    @Autowired
    private TopicFocusService topicFocusService;

    @GetMapping("/s/hot")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> getsHot() {
        return topicFocusService.getHot();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody TopicFocus topicFocus) {
        topicFocusService.add(topicFocus);
        return new HttpHeaders() {{
            set("uuid", topicFocus.getUuid());
        }};
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        topicFocusService.deleteByUuid(uuid);
    }

    @RequestMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> gets(@RequestParam boolean focused, @RequestBody List<TopicFocus> topicFocuses) {
        if (focused) {
            return topicFocusService.getFocused(topicFocuses);
        }
        return null;
    }

}
