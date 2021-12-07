package org.zzj.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundManualRequest {
    private String refundID;
    private byte refundType;
    private byte refundWay;
    private byte receiptChannel;
    private String refundAccount;
    private BigDecimal refundAmount;
    private String remark;
}
