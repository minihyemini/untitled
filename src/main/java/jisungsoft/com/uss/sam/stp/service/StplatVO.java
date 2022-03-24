package jisungsoft.com.uss.sam.stp.service;

import jisungsoft.com.persistence.dto.Default;
import lombok.Data;

@Data
public class StplatVO extends Default {

	private static final long serialVersionUID = -274932760833893110L;

	/** 이용약관ID */
	private String useStplatId;

	/** 이용약관명 */
	private String useStplatNm;

	/** 이용약관내용 */
	private String useStplatCn;

	/** 정보제공동의내용 */
	private String infoProvdAgreCn;

	/** 마케팅활용내용 */
	private String marketingAgreCn;

	/** 사용여부 */
	private String useAt;

	/** 최초등록자ID */
	private String frstRegisterId;

	/** 최초등록시점 */
	private String frstRegistPnttm;

	/** 최종수정자ID */
	private String lastUpdusrId;

	/** 최종등록시점 */
	private String lastUpdtPnttm;
}
