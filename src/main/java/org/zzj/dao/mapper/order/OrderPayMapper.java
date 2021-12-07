package org.zzj.dao.mapper.order;

import org.zzj.dao.entity.order.OrderPay;
import org.zzj.dto.request.PayQueryRequest;

import java.util.List;

public interface OrderPayMapper {
    int deleteByPrimaryKey(String payId);

    int insert(OrderPay record);

    int insertSelective(OrderPay record);

    OrderPay selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(OrderPay record);

    int updateByPrimaryKey(OrderPay record);

    List<OrderPay> query(PayQueryRequest request);

    Integer queryTotal(PayQueryRequest request);

    List<OrderPay> getID(String payId);
}