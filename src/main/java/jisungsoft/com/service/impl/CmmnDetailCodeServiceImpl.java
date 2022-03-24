package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.sym.code.CmmnDetailCodeVO;
import jisungsoft.com.repository.sym.CmmnDetailCodeMapper;
import jisungsoft.com.service.CmmnDetailCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("cmmnDetailCodeService")
public class CmmnDetailCodeServiceImpl extends EgovAbstractServiceImpl implements CmmnDetailCodeService {

	@Resource(name = "cmmnDetailCodeMapper")
	private CmmnDetailCodeMapper cmmnDetailCodeMapper;

	/**
	 * 공통상세코드 목록을 조회한다. (Paging)
	 */
	@Override
	public List<CmmnDetailCode> getCmmnDetailCodeList(CmmnDetailCode cmmnDetailCode) {
		return cmmnDetailCodeMapper.selectCmmnDetailCodeList(cmmnDetailCode);
	}

	/**
	 * 공통상세코드 목록을 조회한다. (Not Paging)
	 */
	@Override
	public List<CmmnDetailCode> getCmmnDetailCodeAllList(CmmnDetailCode cmmnDetailCode) {
		return cmmnDetailCodeMapper.selectCmmnDetailCodeAllList(cmmnDetailCode);
	}

	/**
	 * 공통상세코드 건수를 조회한다.
	 */
	@Override
	public int getCmmnDetailCodeListCnt(CmmnDetailCode cmmnDetailCode) {
		return cmmnDetailCodeMapper.selectCmmnDetailCodeListCnt(cmmnDetailCode);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnDetailCode getCmmnDetailCodeDetail(CmmnDetailCode  cmmnDetailCode) {
		return cmmnDetailCodeMapper.selectCmmnDetailCodeDetail(cmmnDetailCode);
	}

	/**
	 * 공통상세코드를 등록한다.
	 */
	@Override
	public void addCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		CmmnDetailCodeVO dataCmmnDetailCode = CmmnDetailCodeVO.createDataCmmnDetailCode(
				cmmnDetailCode.getCode(),
				cmmnDetailCode.getCodeId(),
				cmmnDetailCode.getCodeNm(),
				cmmnDetailCode.getCodeDc(),
				cmmnDetailCode.getUseAt(),
				cmmnDetailCode.getFrstRegisterId(), null, cmmnDetailCode.getSortOrdr());

		cmmnDetailCodeMapper.insertCmmnDetailCode(dataCmmnDetailCode);
	}

	/**
	 * 공통상세코드를 삭제한다.
	 */
	@Override
	public void removeCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		CmmnDetailCodeVO dataCmmnDetailCode = CmmnDetailCodeVO.createDataCmmnDetailCode(
				cmmnDetailCode.getCode(),
				null,
				null,
				null,
				null,
				null,
				null,
				null);

		cmmnDetailCodeMapper.deleteCmmnDetailCode(dataCmmnDetailCode);;
	}

	/**
	 * 공통상세코드를 수정한다.
	 */
	@Override
	public void editCmmnDetailCode(CmmnDetailCode cmmnDetailCode) throws Exception {
		CmmnDetailCodeVO dataCmmnDetailCode = CmmnDetailCodeVO.createDataCmmnDetailCode(
				cmmnDetailCode.getCode(),
				cmmnDetailCode.getCodeId(),
				cmmnDetailCode.getCodeNm(),
				cmmnDetailCode.getCodeDc(),
				cmmnDetailCode.getUseAt(),
				null,
				cmmnDetailCode.getLastUpdusrId(),
				cmmnDetailCode.getSortOrdr());
		cmmnDetailCodeMapper.updateCmmnDetailCode(dataCmmnDetailCode);;
	}
}
