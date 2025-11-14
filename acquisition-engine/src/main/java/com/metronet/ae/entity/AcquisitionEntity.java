package com.metronet.ae.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "acquisition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcquisitionEntity {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "partner_id")
    private String partnerId;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "plan_code")
    private String planCode;

    @Column(name = "payload", columnDefinition = "text")
    private String payload;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
