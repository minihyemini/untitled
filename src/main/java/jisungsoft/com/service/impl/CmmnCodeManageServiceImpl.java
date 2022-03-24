package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.code.CmmnCode;
import jisungsoft.com.repository.sym.CmmnCodeMapper;
import jisungsoft.com.service.CmmnCodeService;
import jisungsoft.com.persistence.model.sym.code.CmmnCodeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("cmmnCodeService")
public class CmmnCodeManageServiceImpl extends EgovAbstractServiceImpl implements CmmnCodeService {

	@Resource
	private CmmnCodeMapper cmmnCodeMapper;

	/**
	 * 공통코드 목록을 조회한다. (Paging)
	 */
	@Override
	public List<CmmnCode> getCmmnCodeList(CmmnCode cmmnCode) {

		return cmmnCodeMapper.selectCmmnCodeList(cmmnCode);
	}

	@Override
	public List<CmmnCode> getCmmnCodeAllList(CmmnCode cmmnCode) {
		return cmmnCodeMapper.selectCmmnCodeAllList(cmmnCode);
	}

	/**
	 * 공통코드 건수를 조회한다.
	 */
	@Override
	public int getCmmnCodeListCnt(CmmnCode cmmnCode) {
		return cmmnCodeMapper.selectCmmnCodeListCnt(cmmnCode);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnCode getCmmnCodeDetail(CmmnCode cmmnCode) {
		return cmmnCodeMapper.selectCmmnCodeDetail(cmmnCode);
	}

	/**
	 * 공통코드를 등록한다.
	 */
	@Override
	public void addCmmnCode(CmmnCode cmmnCode) {
		CmmnCodeVO dataCmmnCode = CmmnCodeVO.createDataCmmnCode(cmmnCode.getCodeId(),
				cmmnCode.getCodeIdNm(), cmmnCode.getCodeIdDc(),
				cmmnCode.getUseAt(),
				cmmnCode.getClCode(),
				cmmnCode.getClCodeNm(),
				cmmnCode.getFrstRegisterId(),
				cmmnCode.getLastUpdusrId());
		cmmnCodeMapper.insertCmmnCode(dataCmmnCode);
	}

	/**
	 * 공통코드를 삭제한다.
	 */
	@Override
	public void removeCmmnCode(CmmnCode cmmnCode) throws Exception {
		CmmnCodeVO dataCmmnCode = CmmnCodeVO.createDataCmmnCode(cmmnCode.getCodeId(),
				null, null,
				null,
				null,
				null,
				null,
				null);
		cmmnCodeMapper.deleteCmmnCode(dataCmmnCode);
	}

	/**
	 * 공통코드를 수정한다.
	 */
	@Override
	public void editCmmnCode(CmmnCode cmmnCode) throws Exception {
		CmmnCodeVO dataCmmnCode = CmmnCodeVO.createDataCmmnCode(cmmnCode.getCodeId(),
				cmmnCode.getCodeIdNm(), cmmnCode.getCodeIdDc(),
				cmmnCode.getUseAt(),
				cmmnCode.getClCode(),
				cmmnCode.getClCodeNm(),
				cmmnCode.getFrstRegisterId(),
				cmmnCode.getLastUpdusrId());
		cmmnCodeMapper.updateCmmnCode(dataCmmnCode);
	}

	/**
	 * 공통코드를 수정한다. (사용하는 공통분류코드 기준)
	 */
	@Override
	public void editCmmnCodeAll(CmmnCode cmmnCode) throws Exception {
		CmmnCodeVO dataCmmnCode = CmmnCodeVO.createDataCmmnCode(cmmnCode.getCodeId(),
				cmmnCode.getCodeIdNm(), cmmnCode.getCodeIdDc(),
				cmmnCode.getUseAt(),
				cmmnCode.getClCode(),
				cmmnCode.getClCodeNm(),
				cmmnCode.getFrstRegisterId(),
				cmmnCode.getLastUpdusrId());

		cmmnCodeMapper.updateCmmnCodeAll(dataCmmnCode);
	}
}
