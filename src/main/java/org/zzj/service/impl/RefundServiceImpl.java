package org.zzj.service.impl;

import org.springframework.stereotype.Service;
import org.zzj.dao.entity.order.OrderRefund;
import org.zzj.dao.mapper.order.OrderRefundMapper;
import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.RefundCancelRequest;
import org.zzj.dto.request.RefundManualRequest;
import org.zzj.dto.request.RefundQueryRequest;
import org.zzj.dto.response.RefundGetIDResponse;
import org.zzj.dto.response.RefundQueryResponse;
import org.zzj.service.RefundSerivce;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefundServiceImpl implements RefundSerivce {
    @Resource
    private OrderRefundMapper mapper;

    @Override
    public PageResponse<RefundQueryResponse> query(RefundQueryRequest request) {
        PageResponse<RefundQueryResponse> page = new PageResponse<>();
        page.setPageIndex(request.getPageIndex());
        page.setPageSize(request.getPageSize());

        List<RefundQueryResponse> modelList = new ArrayList<RefundQueryResponse>();
        List<OrderRefund> entityList = mapper.query(request);
        for (OrderRefund item : entityList) {
            RefundQueryResponse model = new RefundQueryResponse();
            model.setRefundID(item.getRefundId());
            model.setOrderID(item.getOrderId());
            model.setPayID(item.getPayId());
            //InnerJoin关联查询order_pay表
            //model.setPayInfo();
            model.setConsumeID(item.getConsumeId());
            model.setAmount(item.getAmount());
            model.setRefundTime(item.getRefundTime());
            model.setRefundReason(item.getRefundReason());
            model.setRefundWay(item.getRefundWay());
            model.setRefundType(item.getRefundType());
            model.setRefundStatus(item.getRefundStatus());
            model.setSerialNo(item.getSerialNo());
            model.setRemark(item.getRemark());
            modelList.add(model);
        }
        page.setList(modelList);

        Integer total = mapper.queryTotal(request);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<RefundGetIDResponse> getID(String id) {
        List<RefundGetIDResponse> modelList = new ArrayList<>();
        List<OrderRefund> entityList = mapper.getID(id);
        for (OrderRefund item : entityList) {
            RefundGetIDResponse model = new RefundGetIDResponse();
            model.setRefundID(item.getRefundId());
            model.setConsumeID(item.getConsumeId());
            //innerJion消费记录表和房型表
            //model.setConsumeType();
            //model.setRoomType();
            model.setAmount(item.getAmount());
            modelList.add(model);
        }
        return modelList;
    }

    @Override
    public void manual(RefundManualRequest request) {
        mapper.manual(request);
    }

    @Override
    public void cancel(RefundCancelRequest request) {
        mapper.cancel(request);
    }
}
