package org.zzj.service;

import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.RefundCancelRequest;
import org.zzj.dto.request.RefundManualRequest;
import org.zzj.dto.request.RefundQueryRequest;
import org.zzj.dto.response.RefundGetIDResponse;
import org.zzj.dto.response.RefundQueryResponse;

import java.util.List;

public interface RefundSerivce {

    PageResponse<RefundQueryResponse> query(RefundQueryRequest request);

    List<RefundGetIDResponse> getID(String id);

    void manual(RefundManualRequest request);

    void cancel(RefundCancelRequest request);
}
