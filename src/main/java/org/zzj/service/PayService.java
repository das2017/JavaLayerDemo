package org.zzj.service;

import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.PayQueryRequest;
import org.zzj.dto.response.PayGetIDResponse;
import org.zzj.dto.response.PayQueryResponse;

import java.util.List;

public interface PayService {

    PageResponse<PayQueryResponse> query(PayQueryRequest request);

    List<PayGetIDResponse> getID(String id);
}