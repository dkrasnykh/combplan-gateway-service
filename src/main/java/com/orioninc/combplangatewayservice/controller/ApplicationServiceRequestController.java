package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.dto.RequestDto;
import com.orioninc.combplangatewayservice.service.ApplicationServiceRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class ApplicationServiceRequestController {
    private final ApplicationServiceRequestProducer requestService;

    @Autowired
    public ApplicationServiceRequestController(ApplicationServiceRequestProducer requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public void sendRequest(@RequestBody RequestDto dto) {
        requestService.send(dto);
    }
}