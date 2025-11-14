package com.metronet.ae.dao;

import com.metronet.ae.entity.AcquisitionEntity;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AEDao extends JpaRepository<AcquisitionEntity, UUID> {
    Optional<AcquisitionEntity> findByExternalIdAndPartnerId(String externalId, String partnerId);

}
