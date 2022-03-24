package egovframework.com.cmm.service;

/**
 *  Class Name : Globals.java
 *  Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.19    박지욱          최초 생성
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 01. 19
 *  @version 1.0
 *  @see
 *
 */

public class Globals {
	//OS 유형
    public static final String OS_TYPE = EgovProperties.getProperty("Globals.OsType");
    //DB 유형
    public static final String DB_TYPE = EgovProperties.getProperty("Globals.DbType");
    //메인 페이지
    public static final String MAIN_PAGE = EgovProperties.getProperty("Globals.MainPage");
	//일반회원 로그인 페이지
	public static final String GNR_LOGIN_PAGE = EgovProperties.getProperty("Globals.GnrLoginPage");
	//업무회원 로그인 페이지
	public static final String USR_LOGIN_PAGE = EgovProperties.getProperty("Globals.UsrLoginPage");
	//기업회원 로그인 페이지
	public static final String ENT_LOGIN_PAGE = EgovProperties.getProperty("Globals.EntLoginPage");
    //ShellFile 경로
    public static final String SHELL_FILE_PATH = EgovProperties.getPathProperty("Globals.ShellFilePath");
    //퍼로퍼티 파일 위치
    public static final String CONF_PATH = EgovProperties.getPathProperty("Globals.ConfPath");
    //Server정보 프로퍼티 위치
    public static final String SERVER_CONF_PATH = EgovProperties.getPathProperty("Globals.ServerConfPath");
    //Client정보 프로퍼티 위치
    public static final String CLIENT_CONF_PATH = EgovProperties.getPathProperty("Globals.ClientConfPath");
    //파일포맷 정보 프로퍼티 위치
    public static final String FILE_FORMAT_PATH = EgovProperties.getPathProperty("Globals.FileFormatPath");

    //파일 업로드 원 파일명
	public static final String ORIGIN_FILE_NM = "originalFileName";
	//파일 확장자
	public static final String FILE_EXT = "fileExtension";
	//파일크기
	public static final String FILE_SIZE = "fileSize";
	//업로드된 파일명
	public static final String UPLOAD_FILE_NM = "uploadFileName";
	//파일경로
	public static final String FILE_PATH = "filePath";

	//메일발송요청 XML파일경로
	public static final String MAIL_REQUEST_PATH = EgovProperties.getPathProperty("Globals.MailRequestPath");
	//메일발송응답 XML파일경로
	public static final String MAIL_RESPONSE_PATH = EgovProperties.getPathProperty("Globals.MailRResponsePath");

	// G4C 연결용 IP (localhost)
	public static final String LOCAL_IP = EgovProperties.getProperty("Globals.LocalIp");

	//SMS 정보 프로퍼티 위치
	public static final String SMSDB_CONF_PATH = EgovProperties.getPathProperty("Globals.SmsDbConfPath");

	public static final String ENCRYP_KEY = EgovProperties.getProperty("encryp.key");

	public static final String MONITOR_GROUP_ID = EgovProperties.getProperty("monitor.groupId");
	public static final String MAIN_CODE = EgovProperties.getProperty("Globals.main.code");


	//약도관리
	public static final String MAP_KEY = EgovProperties.getProperty("roughMap.js.appkey");
	public static final String MAP_API_KEY = EgovProperties.getProperty("roughMap.restApi.appkey");

	//발행번호prefix
	public static final String ORDER_LOT_NUM_PREFIX = EgovProperties.getProperty("order.lotNum.prefix");
	public static final String RECEIVE_LOT_NUM_PREFIX = EgovProperties.getProperty("receive.lotNum.prefix");
	public static final String ORDER_NUMBER_PREFIX = EgovProperties.getProperty("order.number.prefix");
	public static final String RECEIVE_NUMBER_PREFIX = EgovProperties.getProperty("receive.number.prefix");
}
