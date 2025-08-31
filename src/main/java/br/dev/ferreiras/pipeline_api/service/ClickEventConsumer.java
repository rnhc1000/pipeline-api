package br.dev.ferreiras.pipeline_api.service;

import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ClickEventConsumer {

//    @KafkaListener(topics = "click-events", groupId = "click-event-group")
//    public void listen(ClickEventDto event) {
//        System.out.println("Received click event: " + event);
//    }

    private static final Logger logger = LoggerFactory.getLogger(ClickEventConsumer.class);

    private final ObjectMapper objectMapper;

    public ClickEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "click-events", groupId = "click-event-group")
    public void listen(String json) {
        try {
            ClickEventDto event = objectMapper.readValue(json, ClickEventDto.class);
            logger.info("::: Event Received: {} :::", event.toString());
        } catch (Exception e) {
            logger.error("::: Deserialization error: {} :::", e.getMessage());
        }
    }

}
