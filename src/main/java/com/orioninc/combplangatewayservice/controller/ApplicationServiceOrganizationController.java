package com.orioninc.combplangatewayservice.controller;

import com.orioninc.combplangatewayservice.dto.OrganizationDto;
import com.orioninc.combplangatewayservice.service.ApplicationServiceOrganizationProduser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organizations")
public class ApplicationServiceOrganizationController {
    private final ApplicationServiceOrganizationProduser organizationService;

    @Autowired
    public ApplicationServiceOrganizationController(ApplicationServiceOrganizationProduser service) {
        this.organizationService = service;
    }

    @PostMapping
    public void sendOrganization(@RequestBody OrganizationDto dto) {
        organizationService.send(dto);
    }
}
