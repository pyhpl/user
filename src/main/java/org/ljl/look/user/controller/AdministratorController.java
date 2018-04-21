package org.ljl.look.user.controller;

import org.ljl.look.user.entity.Administrator;
import org.ljl.look.user.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Administrator get(@RequestHeader("token") String token) {
        return administratorService.get(token);
    }

}
