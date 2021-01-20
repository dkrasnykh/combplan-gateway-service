package com.orioninc.combplangatewayservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequestDto extends AbstractDto {
    private RequestStatus status;
    private OrganizationDto organization;
    private UserDto applicant;
    private String title;
    private String description;
    @JsonProperty(value = "co_authors")
    private Set<UserDto> coAuthors;
}
