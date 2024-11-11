package com.fastbee.sip.handler.req;

import com.fastbee.sip.handler.IReqHandler;
import com.fastbee.sip.server.IGBListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sip.InvalidArgumentException;
import javax.sip.RequestEvent;
import javax.sip.SipException;
import java.text.ParseException;

@Slf4j
@Component
public class InviteReqHandler extends ReqAbstractHandler implements InitializingBean, IReqHandler {
    @Autowired
    private IGBListener sipListener;

    @Override
    public void processMsg(RequestEvent evt) {
        log.info("接收到INVITE信息");
        try {
            responseAck(evt);
        } catch (SipException | InvalidArgumentException | ParseException e) {
            log.error("[回复INVITE信息失败]，{}", e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String method = "INVITE";
        sipListener.addRequestProcessor(method, this);
    }
}