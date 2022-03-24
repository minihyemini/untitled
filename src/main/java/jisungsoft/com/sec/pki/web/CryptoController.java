package jisungsoft.com.sec.pki.web;

import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 *  Class Name  : CryptoController.java
 *  Description : 암호화/복호화 위한  Business class
 *  Menu : CMS > 보안 > 암호화/복호화
 */
@Controller
@RequestMapping("/cms/sec/pki")
public class CryptoController {

    /** 암호화서비스 */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;


    @RequestMapping(value="/cryptoInfo.do")
    public String cryptoInfo(@RequestParam Map<String, Object> commandMap,
                             ModelMap model) throws Exception {
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            return "redirect:/page/uat/uia/loginUsr.do";
        }

        String plainText = (String)commandMap.get("plainText");

        if ( plainText != null ) {
            int plainTextLen = plainText.length();
            String cryptText = encrypt(plainText);
            String decryptText = decrypt(cryptText);
            int decryptTextLen = decryptText.length();

            model.addAttribute("plainText", plainText);
            model.addAttribute("plainTextLen", plainTextLen);
            model.addAttribute("cryptText", cryptText);
            model.addAttribute("decryptText", decryptText);
            model.addAttribute("decryptTextLen", decryptTextLen);
        }
        return "jisungsoft/com/cms/sec/pki/cryptoInfo.cms";
    }

    /**
     * 암호화
     * @param encrypt
     */
    private String encrypt(String encrypt) {
        return cryptoService.encryptNone(encrypt);
    }

    /**
     * 복호화
     * @param decrypt
     */
    private String decrypt(String decrypt){
        return cryptoService.decryptNone(decrypt);
    }
}
