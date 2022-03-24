package jisungsoft.com.sym.log.clg.web;

import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.sym.log.clg.service.LoginLogService;
import jisungsoft.com.sym.log.clg.service.LoginLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 *  Class Name  : LoginLogController.java
 *  Description : 회원 로그인 이력정보 Business class
 *  Menu : LMS > 회원정보 > 로그인이력 (ROLE_USER, ROLE_MANAGER)
 */
@Controller
@RequestMapping("/{path}/sym/log/clg")
public class LoginLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogController.class);

    /**
     * 접속 이력 서비스
     */
    @Resource(name = "loginLogService")
    LoginLogService loginLogService;

    @Resource(name="propertiesService")
    protected EgovPropertyService propertyService;

    @RequestMapping(value="/loginLogList.do")
    public String selectLoginLogInf(@ModelAttribute("loginLogVO") LoginLogVO loginLogVO,
                                    @PathVariable(value="path") String path,
                                    ModelMap model) throws Exception {

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {

            return "redirect:/page/uat/uia/loginUsr.do";
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        if (!user.getAuthority().equals(AuthorityCode.ROLE_ADMIN.name())) {
            loginLogVO.setLoginId(user.getUniqId());
        }

        loginLogVO.setPageUnit(propertyService.getInt("pageUnit"));
        loginLogVO.setPageSize(propertyService.getInt("pageSize"));

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(loginLogVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(loginLogVO.getPageUnit());
        paginationInfo.setPageSize(loginLogVO.getPageSize());

        loginLogVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        loginLogVO.setLastIndex(paginationInfo.getLastRecordIndex());
        loginLogVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<LoginLogVO> resultList = loginLogService.selectLoginLogInf(loginLogVO);
        int totCnt = loginLogService.selectLoginLogInfCnt(loginLogVO);
        paginationInfo.setTotalRecordCount(totCnt);

        model.addAttribute("resultList", resultList);
        model.addAttribute("paginationInfo", paginationInfo);

        return "jisungsoft/com/" +path+"/sym/log/clg/loginLogList."+path;
    }

    @RequestMapping(value="/loginLogDetail.do")
    public String selectLoginLog(@ModelAttribute("loginLogVO") LoginLogVO loginLogVO,
                                 @PathVariable(value="path") String path,
                                 @RequestParam("logId") String logId,
                                 ModelMap model) throws Exception {


        loginLogVO.setLogId(logId.trim());

        LoginLogVO vo = loginLogService.selectLoginLog(loginLogVO);
        model.addAttribute("result", vo);

        return "jisungsoft/com/" +path+"/sym/log/clg/LoginLogDetail."+path;
    }
}
