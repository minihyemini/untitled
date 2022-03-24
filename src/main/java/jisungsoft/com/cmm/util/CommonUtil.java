package jisungsoft.com.cmm.util;

import egovframework.com.cmm.service.EgovProperties;
import org.springframework.util.StringUtils;

import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

public class CommonUtil {

	public static String SQLInjectionFilter(String str, int Length) throws Exception {
		Pattern unsecuredCharPattern;
		String UNSECURED = "";

		if (str.matches(".*_.*")) {
			UNSECURED = "[^\\p{Alnum}가-힣]+_[^\\p{Alnum}가-힣]|--|and|or|union|from|where|limit|reate|select|delete|update|insert|create|alter|drop";

		} else if (str.matches(".*-.*")) {
			UNSECURED = "[^\\p{Alnum}가-힣]+-[^\\p{Alnum}가-힣]|--|and|or|union|from|where|limit|reate|select|delete|update|insert|create|alter|drop";
		} else {
			UNSECURED = "[^\\p{Alnum}가-힣]|--|select|delete|and|or|union|from|where|limit|reate|update|insert|create|alter|drop";
		}

		unsecuredCharPattern = Pattern.compile(UNSECURED, Pattern.CASE_INSENSITIVE);

		str = str.substring(0, Length);

		str = str.replaceAll(" ", "");

		String matcher1[] = unsecuredCharPattern.split(str);
		Matcher matcher = unsecuredCharPattern.matcher(str);

		return matcher1.length > 0 ? matcher1[0] : "";
	}
	
	protected void sendResponseData(HttpServletResponse response, StringBuffer xmlSb) {
		try {
			response.setContentType("application/xml");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentLength(xmlSb.toString().getBytes("utf-8").length);
			response.getWriter().print(xmlSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String md5(String msg) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(msg.getBytes());

		return byteToHexString(md.digest());
	}

	public static String sha256(String msg)  throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());

		return byteToHexString(md.digest());
	}

	/**
	 * 암호화
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES256(String msg, String key) throws Exception {

		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		byte[] saltBytes = bytes;

		// Password-Based Key Derivation function 2
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		// 70000번 해시하여 256 bit 길이의 키를 만든다.
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);

		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		// 알고리즘/모드/패딩
		// CBC : Cipher Block Chaining Mode
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();

		// Initial Vector(1단계 암호화 블록용)
		byte[] ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] encryptedTextBytes = cipher.doFinal(msg.getBytes("UTF-8"));
		byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];

		System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
		System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
		System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);

		return Base64.getEncoder().encodeToString(buffer);
	}

	/**
	 * 복호화
	 * @param msg
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES256(String msg, String key) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(msg));

		byte[] saltBytes = new byte[20];
		buffer.get(saltBytes, 0, saltBytes.length);
		byte[] ivBytes = new byte[cipher.getBlockSize()];
		buffer.get(ivBytes, 0, ivBytes.length);
		byte[] encryoptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes.length];
		buffer.get(encryoptedTextBytes);

		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray(), saltBytes, 70000, 256);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes));
		byte[] decryptedTextBytes = cipher.doFinal(encryoptedTextBytes);

		return new String(decryptedTextBytes);
	}

	/**
	 * 날짜 형식 변환
	 * @param date
	 * @return
	 */
	public static String transformDate(String date) {
		SimpleDateFormat beforeFormat = null;
		SimpleDateFormat afterFormat = null;

		if (date == null || date.equals("")) {
			return null;
		} else if (date.length() == 12) {
			beforeFormat = new SimpleDateFormat("yyyyMMddHHmm");
			afterFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		} else if (date.length() == 8) {
			beforeFormat = new SimpleDateFormat("yyyyMMdd");
			afterFormat = new SimpleDateFormat("yyyy.MM.dd");
		}

		Date tempDate = null;

		try {
			tempDate = beforeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String transDate = afterFormat.format(tempDate);

		return transDate;
	}

	public static String byteToHexString(byte[] data) {

		StringBuilder sb = new StringBuilder();
		for(byte b : data) {
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/**
	 * 이메일 정규식 체크
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) { 
		boolean err = false; 
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; 
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(email); 
		if(m.matches()) { 
			err = true; 
		}

		return err;
	}

	/**
	 * 휴대폰번호 정규식 체크
	 * @param email
	 * @return
	 */
	public static boolean isValidPhone(String email) {
		boolean err = false;
		String regex = "^(01\\d{1}|02|0505|0502|0506|0\\d{1,2})-?(\\d{3,4})-?(\\d{4})";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(email);
		if(m.matches()) {
			err = true;
		}

		return err;
	}

	/**
	 * 비밀번호 정규식 체크
	 * @param pw
	 * @return
	 */
	public static boolean validationPassword(String pw) {
		//8~20내 숫자, 문자, 특수문자 1개 이상 포함
		String pattern1 = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*+=])[A-Za-z\\d!@#$%^&*+=]{8,20}$";
		String pattern2 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%^*#?&])[A-Za-z[0-9]$@$!%^*#?&]{8,20}$";

		Pattern p = Pattern.compile(pattern1);
		Matcher m = p.matcher(pw);

		if (m.matches()) {
			return true;
		}

		return false;
	}

	/**
	 * 한글 정규식 체크
	 * @param value
	 * @return
	 */
	public static boolean isKorean(String value) {
		boolean result = false;

		if (value == null) {
			result = false;
		}

		if(value.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
			result = true;
		}

		return result;
	}

	/**
	 * 모든 HTML 태그를 제거하고 반환한다.
	 *
	 * @param html
	 * @throws Exception
	 */
	public static String removeTag(String html) {
		String result = "";
		if (html != null) {
			result = html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		}
		return result;
	}

	/**
	 * URL에서 파라미터를 파싱한다
	 * **/
	public static Map<String, String> getQueryMap(String query) {
		if (query == null || query.equals("")) return null;

		int pos1 = query.indexOf("?");

		if (pos1 >= 0) {
			query = query.substring(pos1+1);
		}

		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}

		return map;
	}

	/**
	 * 이미지 파일 확장자 대문자가 있을경우 소문자로 변경
	 * @param ext
	 * @return
	 */
	public static String imageExtensionConvert(String ext) {
		if (ext == null || ext.trim().equals("")) {
			return "";
		}

		String returnValue = ext;
		String[] imageExtensions = {"gif", "png", "jpg", "png", "jpeg", "jpe"};

		for (int i=0; i < imageExtensions.length; i++) {
			if (imageExtensions[i].equals(returnValue.toLowerCase())) {
				returnValue = returnValue.toLowerCase();
			}
		}
		return returnValue;
	}

	/**
	 * 이미지 파일 체크
	 * @param ext
	 * @return
	 */
	public static boolean isImageFile(String ext) {
		String imageExtName = EgovProperties.getProperty("Globals.fileImage.Extensions");

		String[] imageExtensions = imageExtName.split("\\.");
		ext = imageExtensionConvert(ext);

		for (int i = 0; i < imageExtensions.length; i++) {
			if (imageExtensions[i].equals(ext)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 6자리 난수생성
	 * @return
	 */
	public static String getUUIDKey() {
		String key = UUID.randomUUID().toString().replace("-", "");

		return key;
	}

	/**
	 * 난수 생성(size 미 지정시 기본 6자리)
	 * @return
	 */
	public static String getAuthCode(int size) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;

		while(buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}

		return buffer.toString();
	}

	/**
	 * XSS 방지 처리.
	 *
	 * @param data
	 * @return
	 */
	public static String unscript(String data) {
		if (data == null || data.trim().equals("")) {
			return "";
		}
		String ret = data;
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
		ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");
		return ret;
	}
}
