package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import user.service.TokenService;

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
}




