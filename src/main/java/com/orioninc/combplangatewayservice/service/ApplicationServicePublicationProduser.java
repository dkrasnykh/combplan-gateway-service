package com.orioninc.combplangatewayservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.combplangatewayservice.dto.PublicationDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationServicePublicationProduser {
    private final ObjectMapper objectMapper;
    private final Producer<Long, String> producer;

    @Value("${kafka.topic.publication}")
    private String topic;

    @Autowired
    public ApplicationServicePublicationProduser(ObjectMapper objectMapper, Producer<Long, String> producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public void send(PublicationDto publication) {
        log.info("<= sending {}", publication.toString());
        ProducerRecord<Long, String> record = new ProducerRecord<>("application-service.publication", publication.getId(), writeValueAsString(publication));
        producer.send(record);
    }

    private String writeValueAsString(PublicationDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
