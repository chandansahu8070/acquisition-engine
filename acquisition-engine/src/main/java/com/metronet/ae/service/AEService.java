package com.metronet.ae.service;

import com.metronet.ae.dao.AEDao;
import com.metronet.ae.entity.AcquisitionEntity;
import com.metronet.ae.model.AcquisitionRequest;
import com.metronet.ae.model.AcquisitionResponse;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class AEService {
    private final AEDao aeDao;

    public AEService(AEDao aeDao) {
        this.aeDao = aeDao;
    }

    @Transactional
    public AcquisitionResponse create(AcquisitionRequest req) {
        AcquisitionEntity entity = AcquisitionEntity.builder()
                .id(UUID.randomUUID())
                .partnerId(req.getPartnerId())
                .externalId(req.getExternalId())
                .msisdn(req.getMsisdn())
                .planCode(req.getPlanCode())
                .payload(req.getPayload() != null ? req.getPayload().toString() : null)
                .status("CREATED")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        aeDao.save(entity);
        return toResponse(entity, "Created");
    }

    public AcquisitionResponse getById(String id) {
        return aeDao.findById(UUID.fromString(id))
                .map(e -> toResponse(e, "Fetched"))
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public List<AcquisitionResponse> getAll() {
        return aeDao.findAll().stream()
                .map(e -> toResponse(e, "OK"))
                .toList();
    }

    @Transactional
    public AcquisitionResponse update(String id, AcquisitionRequest req) {
        AcquisitionEntity entity = aeDao.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Not Found"));
        entity.setPlanCode(req.getPlanCode());
        entity.setMsisdn(req.getMsisdn());
        entity.setUpdatedAt(Instant.now());
        aeDao.save(entity);
        return toResponse(entity, "Updated");
    }

    @Transactional
    public void delete(String id) {
        aeDao.deleteById(UUID.fromString(id));
    }

    private AcquisitionResponse toResponse(AcquisitionEntity e, String msg) {
        return AcquisitionResponse.builder()
                .aeId(e.getId().toString())
                .status(e.getStatus())
                .message(msg)
                .build();
    }
}
