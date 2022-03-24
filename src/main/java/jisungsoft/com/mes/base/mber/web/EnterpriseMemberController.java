package jisungsoft.com.mes.base.mber.web;

import jisungsoft.com.mes.base.mber.form.EnterpriseMemberAddForm;
import jisungsoft.com.mes.base.mber.form.EnterpriseMemberEditForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 기업회원 컨트롤러
 */
@Controller
@RequestMapping("/mes/base/member/enterprise")
public class EnterpriseMemberController {

    /**
     * 회원 목록
     * @return
     */
    @GetMapping(value = "/list.do")
    public String list() {

        return "mes/member/enterprise/list";
    }

    /**
     * 회원추가 폼
     * @return
     */
    @GetMapping(value = "/add.do")
    public String addForm() {

        return "mes/member/enterprise/addForm";
    }

    /**
     * 회원수정 폼
     * @return
     */
    @GetMapping(value = "/edit.do")
    public String editForm() {

        return "mes/member/enterprise/editForm";
    }

    @PostMapping(value = "/add.do")
    public String addAction(EnterpriseMemberAddForm form) {

        return "redirect:/mes/member/enterprise/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(EnterpriseMemberEditForm form) {

        return "redirect:/mes/member/enterprise/list.do";
    }
}
