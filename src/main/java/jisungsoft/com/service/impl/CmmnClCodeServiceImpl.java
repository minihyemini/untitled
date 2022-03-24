package jisungsoft.com.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.persistence.dto.sym.code.CmmnClCode;
import jisungsoft.com.repository.sym.CmmnClCodeMapper;
import jisungsoft.com.repository.sym.CmmnCodeMapper;
import jisungsoft.com.service.CmmnClCodeService;
import jisungsoft.com.persistence.model.sym.code.CmmnClCodeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(readOnly = true)
@Service("cmmnClCodeService")
public class CmmnClCodeServiceImpl extends EgovAbstractServiceImpl implements CmmnClCodeService {

	@Resource(name = "cmmnClCodeMapper")
	private CmmnClCodeMapper cmmnClCodeMapper;

	@Resource(name = "cmmnCodeMapper")
	private CmmnCodeMapper cmmnCodeMapper;

	/**
	 * 공통분류코드 목록을 조회한다. (Paging)
	 */
	@Override
	public List<CmmnClCode> getCmmnClCodeList(CmmnClCode cmmnClCode) {
		return cmmnClCodeMapper.selectCmmnClCodeList(cmmnClCode);
	}

	@Override
	public List<CmmnClCode> getCmmnClCodeAllList(CmmnClCode cmmnClCode) {
		return cmmnClCodeMapper.selectCmmnClCodeAllList(cmmnClCode);
	}

	/**
	 * 공통분류코드 건수를 조회한다.
	 */
	@Override
	public int getCmmnClCodeListCnt(CmmnClCode cmmnClCode) {
		return cmmnClCodeMapper.selectCmmnClCodeListCnt(cmmnClCode);
	}

	/**
	 * 공통분류코드 상세항목을 조회한다.
	 */
	@Override
	public CmmnClCode getCmmnClCodeDetail(CmmnClCode  cmmnClCode) {
		return cmmnClCodeMapper.selectCmmnClCodeDetail(cmmnClCode);
	}

	/**
	 * 공통분류코드를 등록한다.
	 */
	@Override
	public void addCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		CmmnClCodeVO dataCmmClCodeData = CmmnClCodeVO.createDataCmmClCodeData(
				cmmnClCode.getClCode(),
				cmmnClCode.getClCodeNm(),
				cmmnClCode.getClCodeDc(),
				cmmnClCode.getUseAt(),
				cmmnClCode.getFrstRegisterId());

		cmmnClCodeMapper.insertCmmnClCode(dataCmmClCodeData);
	}

	/**
	 * 공통분류코드를 삭제한다.
	 */
	@Override
	public void removeCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		CmmnClCodeVO dataCmmClCodeData = CmmnClCodeVO.createDataCmmClCodeData(
				cmmnClCode.getClCode(),
				null,
				null,
				null,
				null);

		cmmnClCodeMapper.deleteCmmnClCode(dataCmmClCodeData);
	}

	/**
	 * 공통분류코드를 수정한다.
	 */
	@Override
	public void editCmmnClCode(CmmnClCode cmmnClCode) throws Exception {
		/* 공통코드 update */
		/*
		if(cmmnClCodeVO.getUseAt().equals("Y")) {
			CmmnCodeVO codeVO = new CmmnCodeVO();
			codeVO.setClCode(cmmnClCodeVO.getClCode());
			codeVO.setLastUpdusrId(cmmnClCodeVO.getLastUpdusrId());
			cmmnClCodeManageDAO.updateCmmnClCodeNonUse(cmmnClCodeVO);
			cmmnCodeManageService.updateCmmnCodeAll(codeVO);
		}
		*/

		CmmnClCodeVO dataCmmClCodeData = CmmnClCodeVO.createDataCmmClCodeData(
				cmmnClCode.getClCode(),
				cmmnClCode.getClCodeNm(),
				cmmnClCode.getClCodeDc(),
				cmmnClCode.getUseAt(),
				cmmnClCode.getFrstRegisterId());

		cmmnClCodeMapper.updateCmmnClCode(dataCmmClCodeData);
	}
}
