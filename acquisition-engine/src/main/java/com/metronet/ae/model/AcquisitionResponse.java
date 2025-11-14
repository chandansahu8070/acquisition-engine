package com.metronet.ae.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcquisitionResponse {
    private String aeId;
    private String status;
    private String message;
}
