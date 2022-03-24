package jisungsoft.com.uss.ion.pwm.web;

import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.pwm.service.PopupManageService;
import jisungsoft.com.uss.ion.pwm.service.PopupVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cms/uss/ion/pwm")
public class PopupManageRestController {

    /** Service */
    @Resource(name = "popupManageService")
    private PopupManageService popupManageService;

    /**
     * 팝업창정보를 조회한다.
     * @param commandMap
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/popupInfo.json")
    public void popupInfo(HttpServletResponse response,
                          @RequestParam Map<String, Object> commandMap) throws Exception {
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));

        String popupId = (String) commandMap.get("popupId");
        PopupVO pvo = new PopupVO();
        pvo.setPopupId(popupId);
        PopupVO resultData = popupManageService.selectPopupDetail(pvo);

        String sPrint = "";
        sPrint = resultData.getPopupCn();
        sPrint = sPrint + "||" + resultData.getPopupWidthSize();
        sPrint = sPrint + "||" + resultData.getPopupVrticlSize();
        sPrint = sPrint + "||" + resultData.getPopupVrticlLc();
        sPrint = sPrint + "||" + resultData.getPopupWidthLc();
        sPrint = sPrint + "||" + resultData.getStopvewSetupAt();
        out.print(sPrint);
        out.flush();
    }

    /**
     * 팝업창정보를 조회한다.
     * @param commandMap
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/popupInfoList.json")
    public ModelAndView popupInfoList(HttpServletResponse response,
                          @RequestParam Map<String, Object> commandMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        PopupVO pvo = new PopupVO();
        List<?> resultData = popupManageService.selectPopupShowList(pvo);

        modelAndView.addObject("popupList",resultData);

        return modelAndView;
    }
}