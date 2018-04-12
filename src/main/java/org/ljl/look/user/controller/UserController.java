package org.ljl.look.user.controller;

import org.ljl.look.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ljl.look.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public User get(@RequestParam String openId) {
        return userService.get(openId);
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void post(@Validated @RequestBody User user) {
        userService.add(user);
    }
}