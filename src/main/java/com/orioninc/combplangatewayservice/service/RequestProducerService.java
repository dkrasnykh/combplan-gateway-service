package com.orioninc.combplangatewayservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.combplangatewayservice.dto.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestProducerService {
    private final ObjectMapper objectMapper;
    private final Producer<Long, String> producer;

    @Value("${kafka.topic.request}")
    private String topic;

    @Autowired
    public RequestProducerService(ObjectMapper objectMapper, Producer<Long, String> producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public void send(RequestDto request) {
        log.info("<= sending {}", request.toString());
        ProducerRecord<Long, String> record = new ProducerRecord<>(topic, request.getId(), writeValueAsString(request));
        producer.send(record);
    }

    private String writeValueAsString(RequestDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + dto.toString());
        }
    }
}
