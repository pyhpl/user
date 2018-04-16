package org.ljl.look.user.controller;

import org.ljl.look.user.entity.TopicFocus;
import org.ljl.look.user.service.TopicFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic-focus")
public class TopicFocusController {

    @Autowired
    private TopicFocusService topicFocusService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<TopicFocus> get(@RequestParam String orderBy, @RequestParam String order, @RequestParam int limit) {
        return topicFocusService.getByCondition(orderBy, order, limit);
    }

}
