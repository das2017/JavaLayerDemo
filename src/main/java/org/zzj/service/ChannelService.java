package org.zzj.service;

import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.ChannelModifyRequest;
import org.zzj.dto.request.ChannelQueryRequest;
import org.zzj.dto.response.ChannelQueryResponse;

import java.util.List;

public interface ChannelService {
    void add(ChannelModifyRequest request);

    void update(ChannelModifyRequest request);

    ChannelQueryResponse findById(Integer id);

    List<ChannelQueryResponse> findAll();

    PageResponse<ChannelQueryResponse> query(ChannelQueryRequest request);
}
