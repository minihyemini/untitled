package jisungsoft.com.cop.sms.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cop.sms.service.SmsConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("smsConfigService")
public class SmsConfigServiceImpl extends EgovAbstractServiceImpl implements SmsConfigService {

    @Resource(name = "egovSmsCfgIdGnrService")
    private EgovIdGnrService idgenService;
}
