package com.orioninc.combplangatewayservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrganizationDto extends AbstractDto {
    private String name;
}
