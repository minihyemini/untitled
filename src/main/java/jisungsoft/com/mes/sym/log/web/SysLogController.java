package jisungsoft.com.mes.sym.log.web;

import jisungsoft.com.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Slf4j
@Controller
public class SysLogController {

    @Resource(name = "sysLogService")
    private SysLogService logInsertSysLog;
}
