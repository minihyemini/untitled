package jisungsoft.com.cmm.util;

/**
 * 모바일 암호화 Custom Type Handler
 */
public class MobileCipherTypeHandler extends AbstractCipherTypeHandler {

    @Override
    protected boolean isCipher() {
        return false;
    }
}
