package com.metronet.ae.service;

import com.metronet.ae.dao.AEDao;
import com.metronet.ae.kafka.KafkaEventPublisher;
import com.metronet.ae.model.AcquisitionRequest;
import com.metronet.ae.model.AcquisitionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AcquisitionService {
    private final AEDao repo;
    private final KafkaEventPublisher kafkaPublisher;

    public AcquisitionResponse create(AcquisitionRequest req) {
        AcquisitionResponse res = new AcquisitionResponse(UUID.randomUUID().toString(), "CREATED", "Created");
        repo.save(res);
        kafkaPublisher.publishEvent("ACQUISITION_CREATED", res);
        return res;
    }

    public AcquisitionResponse update(String aeId, AcquisitionRequest req) {
        AcquisitionResponse res = new AcquisitionResponse(aeId, "UPDATED", "Updated");
        repo.save(res);
        kafkaPublisher.publishEvent("ACQUISITION_UPDATED", res);
        return res;
    }

    public List<AcquisitionResponse> getAll() {
        return repo.findAll();
    }

    public AcquisitionResponse getById(String id) {
        return repo.findById(id).orElseThrow();
    }

    public void delete(String id) {
        repo.deleteById(id);
        kafkaPublisher.publishEvent("ACQUISITION_DELETED", new AcquisitionResponse(id, "DELETED", "Deleted"));
    }
}
