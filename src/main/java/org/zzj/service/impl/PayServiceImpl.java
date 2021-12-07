package org.zzj.service.impl;

import org.springframework.stereotype.Service;
import org.zzj.dao.entity.order.OrderPay;
import org.zzj.dao.mapper.order.OrderPayMapper;
import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.PayQueryRequest;
import org.zzj.dto.response.PayGetIDResponse;
import org.zzj.dto.response.PayQueryResponse;
import org.zzj.service.PayService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayServiceImpl implements PayService {
    @Resource
    private OrderPayMapper mapper;

    @Override
    public PageResponse<PayQueryResponse> query(PayQueryRequest request) {
        PageResponse<PayQueryResponse> page = new PageResponse<>();
        page.setPageIndex(request.getPageIndex());
        page.setPageSize(request.getPageSize());

        List<PayQueryResponse> modelList = new ArrayList<PayQueryResponse>();
        List<OrderPay> entityList = mapper.query(request);
        for (OrderPay item : entityList) {
            PayQueryResponse model = new PayQueryResponse();
            model.setPayID(item.getPayId());
            model.setOrderID(item.getOrderId());
            model.setConsumeID(item.getConsumeId());
            model.setConsumeType(item.getConsumeType());
            model.setPayType(item.getPayType());
            model.setAmount(item.getAmount());
            model.setPayTime(item.getPayTime());
            model.setPayWay(item.getPayWay());
            model.setSerialNo(item.getSerialNo());
            modelList.add(model);
        }
        page.setList(modelList);

        Integer total = mapper.queryTotal(request);
        page.setTotal(total);
        return page;
    }

    @Override
    public List<PayGetIDResponse> getID(String id) {

        List<PayGetIDResponse> modelList = new ArrayList<PayGetIDResponse>();
        List<OrderPay> entityList = mapper.getID(id);
        for (OrderPay item : entityList) {
            PayGetIDResponse model = new PayGetIDResponse();
            model.setPayID(item.getPayId());
            model.setConsumeID(item.getConsumeId());
            model.setConsumeType(item.getConsumeType());
            //从消费ID查出房型名或InnerJion房型表Room
            //model.setRoomType();
            model.setAmount(item.getAmount());
            modelList.add(model);
        }
        return modelList;
    }
}
