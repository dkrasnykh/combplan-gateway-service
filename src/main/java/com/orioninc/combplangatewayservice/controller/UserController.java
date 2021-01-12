package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.dto.UserDto;
import com.orioninc.combplangatewayservice.service.UserProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserProducerService userService;

    @Autowired
    public UserController(UserProducerService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void sendUser(@RequestBody UserDto dto) {
        userService.send(dto);
    }
}
