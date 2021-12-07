package org.zzj.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.zzj.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

public class TestJob implements SimpleJob {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ChannelService channelService;

    @Override
    public void execute(ShardingContext shardingContext) {
        MDC.put("sheduleName", "TestJob");
        log.info("-------------  TestJob开始 -------------");
        //业务代码
        //channelService.findById(1);
        log.info("-------------  TestJob结束 -------------");
        MDC.remove("sheduleName");
    }
}

