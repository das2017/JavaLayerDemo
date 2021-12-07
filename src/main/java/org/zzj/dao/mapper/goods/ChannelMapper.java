package org.zzj.dao.mapper.goods;

import org.zzj.dao.entity.goods.Channel;
import org.zzj.dto.request.ChannelQueryRequest;
import org.zzj.dto.response.ChannelQueryResponse;

import java.util.List;

public interface ChannelMapper {
    int insert(Channel record);

    int insertSelective(Channel record);

    int updateByPrimaryKey(Channel record);

    int updateByPrimaryKeySelective(Channel record);

    int deleteByPrimaryKey(Integer id);

    Channel selectByPrimaryKey(Integer id);

    List<Channel> selectAll();

    List<Channel> query(ChannelQueryRequest request);

    Integer queryTotal(ChannelQueryRequest request);
}
