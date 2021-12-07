package org.zzj.dto.response;

import lombok.Data;
import org.zzj.dto.common.PageResponse;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PayGetIDResponse implements Serializable {
    private String payID;
    private String consumeID;
    private byte consumeType;
    private byte roomType;
    private BigDecimal amount;
}

