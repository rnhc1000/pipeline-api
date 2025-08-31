package br.dev.ferreiras.pipeline_api.service;
import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class ClickEventKafkaProducer {

    private final Producer<String, String> producer;
    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(ClickEventKafkaProducer.class);

    public ClickEventKafkaProducer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.31.40.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 3);

        this.producer = new KafkaProducer<>(props);
    }

//    public void sendClickEvent(Object event) {
//        try {
//            String json = objectMapper.writeValueAsString(event);
//            ProducerRecord<String, String> record = new ProducerRecord<>("click-events", json);
//
//            producer.send(record, (metadata, exception) -> {
//                if (exception == null) {
//                    System.out.println("Sent event successfully to partition " + metadata.partition());
//                } else {
//                    System.err.println("Failed to send event: " + exception.getMessage());
//                }
//            });
//
//        } catch (Exception e) {
//            System.err.println("Serialization error: " + e.getMessage());
//        }
//    }

    public void sendClickEvent(ClickEventDto event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            ProducerRecord<String, String> record = new ProducerRecord<>("click-events", json);

            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    logger.info("::: Sent event successfully: {} :::", metadata.partition());
                } else {
                    logger.warn("::: Failed to send event: {} :::", exception.getMessage());
                }
            });

        } catch (Exception e) {
            logger.error("::: Serialization error: {} :::", e.getMessage());
        }
    }


    @PreDestroy
    public void shutdown() {
        logger.info("Shutting down Kafka Producer...");
        producer.close();
    }
}
