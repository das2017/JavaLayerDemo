package org.zzj.dto.request;

import lombok.Data;
import org.zzj.dto.common.PageRequest;

import java.util.Date;

@Data
public class PayQueryRequest extends PageRequest {
    private String orderID;
    private String payID;
    private Date beginDate;
    private Date endDate;
}