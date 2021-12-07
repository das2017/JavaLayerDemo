package org.zzj.dto.request;

import lombok.Data;
import org.zzj.dto.common.PageRequest;

import java.util.Date;

@Data
public class RefundQueryRequest extends PageRequest {
    private String orderID;
    private byte refundReason;
    private byte refundType;
    private byte refundWay;
    private byte refundStatus;
    private Date beginDate;
    private Date endDate;
}
