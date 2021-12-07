package org.zzj.common.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsComsumer {

    //@JmsListener(destination = JmsDefine.QName_Zhq, containerFactory = "jmsListenerContainerQueue")
    public void readMQZhq(String json) {
        //接收json消息
        //调用业务处理方法，其它不处理。
        //把所有信息消费都写到Listener文件夹中
        System.out.println("zhq receive：" + json);
    }
}
