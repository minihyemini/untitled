package jisungsoft.com.uss.ion.pwm.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class PopupVO extends Default {

	private static final long serialVersionUID = -2158843750145387861L;

	/** 팝업ID */
	private String popupId;

	/** 팝업제목명 */
	private String popupSjNm;

	/** 파일URL */
	private String fileUrl;

	/** 팝업내용 */
	private String popupCn;

	/** 팝업가로위치 */
	private String popupWidthLc = "0";

	/** 팝업가로사이즈 */
	private String popupWidthSize = "0";

	/** 게시시작일 */
	private String ntceBgnde;

	/** 게시종료일 */
	private String ntceEndde;

	/** 그만보기설정여부 */
	private String stopvewSetupAt;

	/** 게시여부 */
	private String ntceAt;

	/** 이미지파일 */
	private String atchFileId;
	private String fileSn;

	/** 팝업세로위치 */
	private String popupVrticlLc = "0";

	/** 팝업세로사이즈 */
	private String popupVrticlSize = "0";

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종등록시점 */
	private String lastUpdtPnttm;
}
