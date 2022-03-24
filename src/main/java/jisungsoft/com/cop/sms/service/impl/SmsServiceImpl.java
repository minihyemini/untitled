package jisungsoft.com.cop.sms.service.impl;

import com.google.gson.JsonArray;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cop.sms.service.SmsAUthVO;
import jisungsoft.com.cop.sms.service.SmsService;
import jisungsoft.com.cop.sms.service.SmsVO;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("smsService")
public class SmsServiceImpl extends EgovAbstractServiceImpl implements SmsService {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Resource(name="smsDAO")
    private SmsDAO smsDAO;

    @Resource(name = "egovSmsIdGnrService")
    private EgovIdGnrService idgenService;

    @Override
    public List<SmsVO> selectSmsInfs(SmsVO paramVO) throws Exception {
        return smsDAO.selectSmsInfs(paramVO);
    }

    @Override
    public int selectSmsInfsCnt(SmsVO paramVO) throws Exception {
        return smsDAO.selectSmsInfsCnt(paramVO);
    }

    @Override
    public SmsVO selectSmsInf(SmsVO paramVO) throws Exception {
        return smsDAO.selectSmsInf(paramVO);
    }

    @Override
    public SmsVO senderSms(SmsVO smsVO) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/egovframework/spring/com/context-sms.xml");
        String resultCode = "";
        String resultMsg  = "";
        String type = "SMS";

        SmsAUthVO smsAUthVO = (SmsAUthVO) context.getBean("coolSmsAUthVO");
        smsVO.setTrnsmisTelno(smsAUthVO.getTelNumber());
        Message coolsms = new Message(smsAUthVO.getClientId(), smsAUthVO.getClientSecret());

        String cn = smsVO.getTrnsmisCn();
        //문자열 바이트크기 추출
        int strLength = 0;
        char tempChar[] = new char[cn.length()];
        for (int i = 0; i < tempChar.length; i++) {
            tempChar[i] = cn.charAt(i);
            if (tempChar[i] < 128) {
                strLength++;
            } else {
                strLength += 2;
            }
        }

        //90바이트 이상 LMS 전환
        if (strLength >= 90) type = "LMS";
        smsVO.setTrnsmisCode(type);

        HashMap<String, String> params = new HashMap<String, String>();
        JsonArray messages = new JsonArray();
        params.put("to", smsVO.getRecptnTelno());
        params.put("from", smsAUthVO.getTelNumber());
        params.put("type", smsVO.getTrnsmisCode());
        params.put("text", smsVO.getTrnsmisCn());
        params.put("app_version", "aihub app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            obj.get("group_id");
            obj.get("success_count");
            obj.get("error_count");

            params.put("group_id", obj.get("group_id").toString());

            LOGGER.debug(obj.toString());
        } catch (CoolsmsException e) {
            LOGGER.debug(e.getMessage());
            resultCode = Integer.toString(e.getCode());
            resultMsg = e.getMessage();
            smsVO.setResultCode(resultCode);
            smsVO.setResultMssage(resultMsg);
        }

        return smsVO;
    }

    @Override
    public void insertSms(SmsVO smsVO) throws Exception {
        if (smsVO.getRecptnTelno().indexOf(",") > -1) {
            String[] recptnList = smsVO.getRecptnTelno().split(",");

            for(int i=0; i<recptnList.length; i++){
                smsVO.setRecptnTelno(recptnList[i]);

                SmsVO resVo = this.senderSms(smsVO);
                resVo.setSmsId(idgenService.getNextStringId());
                smsDAO.insertSmsInf(resVo);
                smsDAO.insertSmsRecptnInf(resVo);
            }
        } else {
            SmsVO resVo = this.senderSms(smsVO);
            resVo.setSmsId(idgenService.getNextStringId());
            smsDAO.insertSmsInf(resVo);
            smsDAO.insertSmsRecptnInf(resVo);
        }
    }

    @Override
    public SmsVO selectSmsAuthInf(SmsVO smsVO) throws Exception {
        return smsDAO.selectSmsAuthInf(smsVO);
    }

    @Override
    public void insertSmsAuth(SmsVO smsVO) throws Exception {
        SmsVO resVo = this.senderSms(smsVO);
        resVo.setSmsId(idgenService.getNextStringId());
        smsDAO.insertSmsInf(resVo);
        smsDAO.insertSmsRecptnInf(resVo);

        resVo.setAuthKey(smsVO.getAuthKey());
        resVo.setAuthAt(smsVO.getAuthAt());
        smsDAO.insertSmsAuth(resVo);
    }

    @Override
    public void updateSmsAuth(SmsVO smsVO) throws Exception {
        smsDAO.updateSmsAuth(smsVO);
    }

    @Override
    public void deleteSmsAuth(SmsVO smsVO) throws Exception {
        smsDAO.deleteSmsAuth(smsVO);
    }
}
