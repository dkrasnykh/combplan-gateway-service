package com.orioninc.combplangatewayservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.combplangatewayservice.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationServiceOrganizationProduser {
    private final ObjectMapper objectMapper;
    private final Producer<Long, String> producer;

    @Value("kafka.topic.organization")
    private String topic;

    @Autowired
    public ApplicationServiceOrganizationProduser(ObjectMapper objectMapper, Producer<Long, String> producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public void send(OrganizationDto organization) {
        log.info("<= sending {}", organization.toString());
        ProducerRecord<Long, String> record = new ProducerRecord<>("application-service.organization", organization.getId(), writeValueAsString(organization));
        producer.send(record);
    }

    private String writeValueAsString(OrganizationDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
