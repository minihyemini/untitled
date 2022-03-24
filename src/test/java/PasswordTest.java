import egovframework.com.utl.sim.service.EgovFileScrty;

public class PasswordTest {

    public static void main(String[] args) throws Exception {
        String enpassword = EgovFileScrty.encryptPassword("20220314", "USRCNFRM_00000000112");
        System.out.println(enpassword);
    }
}
