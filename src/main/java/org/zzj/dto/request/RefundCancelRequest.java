package org.zzj.dto.request;

import lombok.Data;

@Data
public class RefundCancelRequest {
    private String refundID;
    private String remark;
}
