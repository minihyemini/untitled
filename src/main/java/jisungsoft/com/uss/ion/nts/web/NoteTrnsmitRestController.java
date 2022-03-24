package jisungsoft.com.uss.ion.nts.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitService;
import jisungsoft.com.uss.ion.nts.service.NoteTrnsmitVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/{path}/uss/ion/nts")
public class NoteTrnsmitRestController {
    /** Service */
    @Resource(name = "noteTrnsmitService")
    private NoteTrnsmitService noteTrnsmitService;

    /**
     * 수신자목록 조회
     *
     * @param commandMap
     * @param noteTrnsmitVO
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/trns/list.json")
    public ModelAndView userList(@RequestParam Map<String, Object> commandMap, NoteTrnsmitVO noteTrnsmitVO) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        String searchKeyword   = (String) commandMap.get("searchKeyword");
        String searchCondition = (String) commandMap.get("searchCondition");

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(noteTrnsmitVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(noteTrnsmitVO.getPageUnit());
        paginationInfo.setPageSize(noteTrnsmitVO.getPageSize());

        noteTrnsmitVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        noteTrnsmitVO.setLastIndex(paginationInfo.getLastRecordIndex());
        noteTrnsmitVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
        noteTrnsmitVO.setSearchKeyword(searchKeyword);
        noteTrnsmitVO.setSearchCondition(searchCondition);

        List<?> resultList = noteTrnsmitService.selectNoteTrnsmitCnfirm(noteTrnsmitVO);
        int totCnt = noteTrnsmitService.selectNoteTrnsmitCnfirmCnt(noteTrnsmitVO);
        paginationInfo.setTotalRecordCount(totCnt);

        modelAndView.addObject("resultList", resultList);
        modelAndView.addObject("paginationInfoJs", paginationInfo);

        return modelAndView;
    }

    /**
     * 수신자 삭제
     * @param commandMap 파라메터전달용 commandMap
     * @return void
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/trns/delete.json")
    public void noteTrnsmitCnfirmDelete(@RequestParam Map<String, Object> commandMap) throws Exception {
        String noteId  = (String) commandMap.get("noteId");
        String noteTrnsmitId  = (String) commandMap.get("noteTrnsmitId");
        String noteRecptnId   = (String) commandMap.get("noteRecptnId");

        NoteTrnsmitVO nvo = new NoteTrnsmitVO();
        nvo.setNoteId(noteId);
        nvo.setNoteTrnsmitId(noteTrnsmitId);
        nvo.setNoteRecptnId(noteRecptnId);
        noteTrnsmitService.deleteNoteRecptn(nvo);
    }
}
