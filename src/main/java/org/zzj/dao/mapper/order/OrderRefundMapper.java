package org.zzj.dao.mapper.order;

import org.zzj.dao.entity.order.OrderRefund;
import org.zzj.dto.request.RefundCancelRequest;
import org.zzj.dto.request.RefundManualRequest;
import org.zzj.dto.request.RefundQueryRequest;

import java.util.List;

public interface OrderRefundMapper {

    int deleteByPrimaryKey(String refundId);

    int insert(OrderRefund record);

    int insertSelective(OrderRefund record);

    OrderRefund selectByPrimaryKey(String refundId);

    int updateByPrimaryKeySelective(OrderRefund record);

    int updateByPrimaryKey(OrderRefund record);

    List<OrderRefund> query(RefundQueryRequest request);

    Integer queryTotal(RefundQueryRequest request);

    Integer manual(RefundManualRequest request);

    Integer cancel(RefundCancelRequest request);

    List<OrderRefund> getID(String refundId);
}