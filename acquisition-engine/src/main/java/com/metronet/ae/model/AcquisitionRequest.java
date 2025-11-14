package com.metronet.ae.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcquisitionRequest {
    @NotBlank
    private String partnerId;

    @NotBlank
    private String externalId;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid msisdn")
    private String msisdn;
    private String planCode;
    private Object payload;
}
