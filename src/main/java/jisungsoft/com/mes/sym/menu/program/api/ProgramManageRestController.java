package jisungsoft.com.mes.sym.menu.program.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.menu.MenuProgram;
import jisungsoft.com.service.MenuProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mes/sym/menu/program")
public class ProgramManageRestController {

    @Resource(name = "menuProgramService")
    private MenuProgramService menuProgramService;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @RequestMapping(value = "/detail.json", method = RequestMethod.POST)
    public ModelAndView detail(@RequestParam("id") Integer id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        MenuProgram menuProgram = new MenuProgram();
        menuProgram.setProgrmId(id);

        MenuProgram program = menuProgramService.getProgram(menuProgram);

        modelAndView.addObject("data", program);
        return modelAndView;
    }

    @RequestMapping(value = "/list.json", method = RequestMethod.POST)
    public ModelAndView list(MenuProgram menuProgram) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return modelAndView;
        }

        List<MenuProgram> resultList = menuProgramService.getProgramAllList(menuProgram);

        modelAndView.addObject("data", resultList);
        return modelAndView;
    }
}
