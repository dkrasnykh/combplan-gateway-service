package com.orioninc.combplangatewayservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orioninc.combplangatewayservice.dto.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {
    private final KafkaTemplate<Long, RequestDto> kafkaStarshipTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RequestServiceImpl(KafkaTemplate<Long, RequestDto> kafkaStarshipTemplate,
                              ObjectMapper objectMapper) {
        this.kafkaStarshipTemplate = kafkaStarshipTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void produce(RequestDto dto) {
        log.info("<= sending {}", writeValueAsString(dto));
        kafkaStarshipTemplate.send("application-service.request", dto);
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
