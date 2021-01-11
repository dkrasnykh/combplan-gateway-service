package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.config.RequestProducer;
import com.orioninc.combplangatewayservice.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestProducer requestProducer;

    @Autowired
    public RequestController(RequestProducer requestProducer) {
        this.requestProducer = requestProducer;
    }

    @PostMapping
    public void sendRequest(@RequestBody RequestDto dto) {
        requestProducer.send(dto);
    }
}