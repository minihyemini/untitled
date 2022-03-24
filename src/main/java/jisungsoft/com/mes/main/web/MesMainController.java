package jisungsoft.com.mes.main.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RequestMapping("/mes")
@Controller
public class MesMainController {

    @RequestMapping(value = "/index.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String main(final Model model, Locale locale, HttpServletRequest request) {

        log.info("{} ::: mes main");

        return "jisungsoft/com/mes/main/index2.mes";
    }
}
