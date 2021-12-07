package org.zzj.common.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class JmsProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMQZhq(String json) {
        //只负责发送消息
        System.out.println("zhq send: " + json);
        Queue mq = new ActiveMQQueue(JmsDefine.QName_Zhq);
        jmsMessagingTemplate.convertAndSend(mq, json);
    }
}
