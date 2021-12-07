package org.zzj.dao.entity.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderRefund {
    private String refundId;

    private Byte refundStatus;

    private String orderId;

    private String consumeId;

    private String payId;

    private BigDecimal amount;

    private Date refundTime;

    private Byte refundReason;

    private Byte refundWay;

    private Byte refundType;

    private String serialNo;

    private Byte receiptChannel;

    private String refundAccount;

    private String remark;

    private Byte callback;
}