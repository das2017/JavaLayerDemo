package org.zzj.service.impl;

import com.alibaba.fastjson.JSON;
import org.zzj.common.constants.ApiCode;
import org.zzj.common.custom.ApiException;
import org.zzj.common.redis.RedisKey;
import org.zzj.common.redis.RedisTTL;
import org.zzj.common.utils.ListUtil;
import org.zzj.common.utils.Md5Util;
import org.zzj.dao.entity.goods.Channel;
import org.zzj.dao.mapper.goods.ChannelMapper;
import org.zzj.dto.common.PageResponse;
import org.zzj.dto.request.ChannelModifyRequest;
import org.zzj.dto.request.ChannelQueryRequest;
import org.zzj.dto.response.ChannelQueryResponse;
import org.zzj.service.ChannelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {
    @Resource
    private ChannelMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void add(ChannelModifyRequest request) {
        Channel entity = new Channel();
        BeanUtils.copyProperties(request, entity);
        mapper.insertSelective(entity);
    }

    @Override
    public void update(ChannelModifyRequest request) {
        if (1 > 0) {
            throw new ApiException(ApiCode.ERROR, ApiCode.ERRMSG_PARAM_MISSING, request);
        }
        Channel entity = new Channel();
        BeanUtils.copyProperties(request, entity);
        mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public ChannelQueryResponse findById(Integer id) {
        if (1 > 0) {
            //throw new ApiException(ApiCode.ERROR, ApiCode.ERRMSG_DEFAULT);
        }
        final String cacheKey = String.format(RedisKey.ChannelService_FindById, id);
        ChannelQueryResponse model = new ChannelQueryResponse();
        if (!redisTemplate.hasKey(cacheKey)) {
            Channel entity = mapper.selectByPrimaryKey(id);
            if (model != null) {
                BeanUtils.copyProperties(entity, model);
                String cacheValue = JSON.toJSONString(model);
                redisTemplate.opsForValue().set(cacheKey, cacheValue, RedisTTL.MINUTES_10);
            }
        } else {
            String cacheValue = redisTemplate.opsForValue().get(cacheKey).toString();
            model = JSON.parseObject(cacheValue, ChannelQueryResponse.class);
        }
        return model;
    }

    @Override
    public List<ChannelQueryResponse> findAll() {
        final String cacheKey = RedisKey.ChannelService_FindAll;
        List<ChannelQueryResponse> modelList = null;

        if (!redisTemplate.hasKey(cacheKey)) {
            List<Channel> entityList = mapper.selectAll();
            modelList = ListUtil.copyListProperties(entityList, ChannelQueryResponse::new);
            if (modelList.stream().count() > 0) {
                //不默认10分钟
                String cacheValue = JSON.toJSONString(modelList);
                redisTemplate.opsForValue().set(cacheKey, cacheValue);
            }
        } else {
            String cacheValue = redisTemplate.opsForValue().get(cacheKey).toString();
            modelList = JSON.parseArray(cacheValue, ChannelQueryResponse.class);
        }
        return modelList;
    }

    @Override
    public PageResponse<ChannelQueryResponse> query(ChannelQueryRequest request) {
        String md5 = Md5Util.Md5(JSON.toJSONString(request));
        //值少可以$串起来
        String cacheKey = String.format(RedisKey.ChannelService_Query, md5);

        PageResponse<ChannelQueryResponse> page = new PageResponse<>();
        if (!redisTemplate.hasKey(cacheKey)) {
            page.setPageIndex(request.getPageIndex());
            page.setPageSize(request.getPageSize());

            List<Channel> entityList = mapper.query(request);
            List<ChannelQueryResponse> modelList = ListUtil.copyListProperties(entityList, ChannelQueryResponse::new);
            page.setList(modelList);

            Integer total = mapper.queryTotal(request);
            page.setTotal(total);

            if (total > 0) {
                String cacheValue = JSON.toJSONString(page);
                redisTemplate.opsForValue().set(cacheKey, cacheValue, RedisTTL.MINUTES_10);
            }
        } else {
            String cacheValue = redisTemplate.opsForValue().get(cacheKey).toString();
            page = JSON.parseObject(cacheValue, PageResponse.class);
        }
        return page;
    }
}
