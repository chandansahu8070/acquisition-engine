package com.metronet.ae.kafka;

import com.metronet.ae.model.AcquisitionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishEvent(String eventType, AcquisitionResponse payload) {
        kafkaTemplate.send("acquisition-events", eventType, payload);
        System.out.println("Kafka Event Published: " + eventType);
    }
}
