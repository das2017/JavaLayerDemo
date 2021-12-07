package org.zzj.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayQueryResponse implements Serializable {
    private String payID;
    private String orderID;
    private String consumeID;
    private byte consumeType;
    private byte payType;
    private BigDecimal amount;
    private Date payTime;
    private byte payWay;
    private String serialNo;
}
