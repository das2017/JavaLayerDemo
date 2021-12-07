package org.zzj.controller;

import org.zzj.common.jms.JmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzj.dto.common.ApiResponse;

import java.util.Date;

@RestController
@RequestMapping("/test")
@CrossOrigin
@Slf4j
public class TestController extends BaseController {
    @Autowired
    private JmsProducer jmsProducer;

    @GetMapping("/mq")
    public ApiResponse mq() {
        jmsProducer.sendMQZhq("消息内容：" + new Date().toString());
        jmsProducer.sendMQZhq("消息内容：" + new Date().toString());
        jmsProducer.sendMQZhq("消息内容：" + new Date().toString());
        return success("mq test");
    }
}
