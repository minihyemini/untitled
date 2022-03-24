package jisungsoft.com.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Slf4j
@Controller
public class MainController {

    @RequestMapping(value = "/index.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String main(Locale locale) {
        log.info("{} ::: main");

        return "redirect:/mes/index.do";
    }
}
