package jisungsoft.com.sym.log.clg.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{path}/sym/log/clg")
public class LoginLogRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogRestController.class);

}
