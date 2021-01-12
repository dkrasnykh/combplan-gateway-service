package com.orioninc.combplangatewayservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.combplangatewayservice.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserProducerService {
    private final ObjectMapper objectMapper;
    private final Producer<Long, String> producer;

    @Value("${kafka.topic.user}")
    private String topic;

    @Autowired
    public UserProducerService(ObjectMapper objectMapper, Producer<Long, String> producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public void send(UserDto user) {
        log.info("<= sending {}", user.toString());
        ProducerRecord<Long, String> record = new ProducerRecord<>(topic, user.getId(), writeValueAsString(user));
        producer.send(record);
    }

    private String writeValueAsString(UserDto user) {
        try {
            return objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Writing value to JSON failed: " + user.toString());
        }
    }
}
