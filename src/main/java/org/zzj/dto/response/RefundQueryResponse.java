package org.zzj.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class RefundQueryResponse implements Serializable {
    private String refundID;
    private String orderID;
    private String payID;
    private String payInfo;
    private String consumeID;
    private BigDecimal amount;
    private Date refundTime;
    private byte refundReason;
    private byte refundWay;
    private byte refundType;
    private byte refundStatus;
    private byte orderSource;
    private String serialNo;
    private String remark;
}
