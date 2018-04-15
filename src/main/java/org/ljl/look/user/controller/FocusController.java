package org.ljl.look.user.controller;

import org.ljl.look.user.entity.Focus;
import org.ljl.look.user.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus")
public class FocusController {

    @Autowired
    private FocusService focusService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpHeaders post(@RequestBody Focus focus) {
        focusService.add(focus);
        return new HttpHeaders() {{
            set("uuid", focus.getUuid());
        }};
    }

    @GetMapping("s")
    @ResponseStatus(HttpStatus.OK)
    public List<Focus> getsByFromUser(@RequestParam String fromUser) {
        return focusService.getsByFromUser(fromUser);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByUuid(@RequestParam String uuid) {
        focusService.deleteByUuid(uuid);
    }


}
















