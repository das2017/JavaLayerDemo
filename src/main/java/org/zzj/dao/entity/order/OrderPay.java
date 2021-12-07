package org.zzj.dao.entity.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderPay {
    private String payId;

    private Byte payStatus;

    private String orderId;

    private String consumeId;

    private Byte consumeType;

    private Byte payType;

    private BigDecimal amount;

    private Date payTime;

    private Byte payWay;

    private String serialNo;

    private Byte callback;

    private String remark;
}