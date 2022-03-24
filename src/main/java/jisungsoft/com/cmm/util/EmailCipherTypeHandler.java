package jisungsoft.com.cmm.util;

/**
 * 이메일 암호화 Custom Type Handler
 */
public class EmailCipherTypeHandler extends AbstractCipherTypeHandler{

    @Override
    protected boolean isCipher() {
        return false;
    }
}
