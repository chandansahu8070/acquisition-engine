package com.metronet.ae.kafka;


import com.metronet.ae.model.AcquisitionResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventConsumer {
    @KafkaListener(topics = "acquisition-events", groupId = "acquisition-engine-group")
    public void consume(AcquisitionResponse message) {
        System.out.println("Received Event: " + message);
    }
}
