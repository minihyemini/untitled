package jisungsoft.com.map.info.web;

import egovframework.com.cmm.service.Globals;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/map/info")
public class InfoDataController {

    @RequestMapping("/location.do")
    public String location(ModelMap model) throws Exception
    {
        model.addAttribute("mapKey", Globals.MAP_KEY);
        return "jisungsoft/com/page/map/info/location.page";
    }
}
