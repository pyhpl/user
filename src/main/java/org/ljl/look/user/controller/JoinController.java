package org.ljl.look.user.controller;

import org.ljl.look.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/join")
public class JoinController {

    @Autowired
    private JoinService joinService;

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public int countByActivityUuid(@RequestParam String activityUuid) {
        return joinService.countByActivityUuid(activityUuid);
    }
}
