package org.zzj.listener;

import org.springframework.jms.annotation.JmsListener;
import org.zzj.common.jms.JmsDefine;

public class ZhqListener {

    @JmsListener(destination = JmsDefine.QName_Zhq, containerFactory = "jmsListenerContainerQueue")
    public void readMQZhq(String json) {
        //接收json消息
        //调用业务处理方法，其它不处理。
        System.out.println("zhq receive：" + json);
    }
}