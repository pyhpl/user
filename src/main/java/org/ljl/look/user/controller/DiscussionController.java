package org.ljl.look.user.controller;

import org.ljl.look.user.entity.Discussion;
import org.ljl.look.user.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussion")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody Discussion discussion) {
        discussionService.add(discussion);
        return new HttpHeaders() {{
            set("uuid", discussion.getUuid());
        }};
    }

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<Discussion> getsByBelongToActivity(@RequestParam String belongToActivity) {
        return discussionService.getByBelongToActivity(belongToActivity);
    }
}
