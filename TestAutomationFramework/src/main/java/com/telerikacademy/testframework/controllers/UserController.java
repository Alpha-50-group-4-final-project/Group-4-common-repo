package com.telerikacademy.testframework.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @ApiOperation(value = "Get user data", response = Iterable.class)
    @GetMapping("/userdata")
    public List<String> getUserData() {
        return Arrays.asList("user1", "user2");
    }
}

