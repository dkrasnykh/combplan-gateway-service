package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.dto.RequestDto;
import com.orioninc.combplangatewayservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public void sendRequest(@RequestBody RequestDto dto) {
        requestService.produce(dto);
    }
}