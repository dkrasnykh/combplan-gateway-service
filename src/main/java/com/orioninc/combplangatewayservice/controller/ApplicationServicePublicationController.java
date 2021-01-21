package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.dto.PublicationDto;
import com.orioninc.combplangatewayservice.service.ApplicationServicePublicationProduser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publications")
public class ApplicationServicePublicationController {
    private final ApplicationServicePublicationProduser publicationService;

    @Autowired
    public ApplicationServicePublicationController(ApplicationServicePublicationProduser publicationProduser) {
        this.publicationService = publicationProduser;
    }

    @PostMapping
    public void sendRequest(@RequestBody PublicationDto dto) {
        publicationService.send(dto);
    }
}
