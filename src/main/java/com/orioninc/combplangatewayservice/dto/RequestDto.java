package com.orioninc.combplangatewayservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RequestDto extends AbstractDto {
    private RequestStatus status;
    private OrganizationDto organization;
    private UserDto applicant;
    @JsonProperty(value = "publication")
    private PublicationDto publication;
}
