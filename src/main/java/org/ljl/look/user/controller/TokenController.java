package org.ljl.look.user.controller;

import org.ljl.look.user.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String get(@RequestParam String code) {
        return tokenService.get(code);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader("token") String token) {
        tokenService.delete(token);
    }
}




