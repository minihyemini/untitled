package jisungsoft.com.cmm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.cmm.service.FileMngService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fileMngService")
public class FileMngServiceImpl extends EgovAbstractServiceImpl implements FileMngService {

    @Resource(name = "fileMngDAO")
    private FileManageDAO fileMngDAO;

    @Override
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
        return fileMngDAO.selectFileInfs(fvo);
    }

    @Override
    public String insertFileInf(FileVO fvo) throws Exception {
        String atchFileId = fvo.getAtchFileId();
        fileMngDAO.insertFileInf(fvo);
        return atchFileId;
    }

    @Override
    public String insertFileInfs(List<?> fvoList) throws Exception {
        String atchFileId = "";

        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertFileInfs(fvoList);
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    @Override
    public void updateFileInfs(List<?> fvoList) throws Exception {
        fileMngDAO.updateFileInfs(fvoList);
    }

    @Override
    public void deleteFileInfs(List<?> fvoList) throws Exception {
        fileMngDAO.deleteFileInfs(fvoList);
    }

    @Override
    public void deleteFileInf(FileVO fvo) throws Exception {
        fileMngDAO.deleteFileInf(fvo);
    }

    @Override
    public FileVO selectFileInf(FileVO fvo) throws Exception {
        return fileMngDAO.selectFileInf(fvo);
    }

    @Override
    public int getMaxFileSN(FileVO fvo) throws Exception {
        return fileMngDAO.getMaxFileSN(fvo);
    }

    @Override
    public void deleteAllFileInf(FileVO fvo) throws Exception {
        fileMngDAO.deleteAllFileInf(fvo);
    }

    @Override
    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception {
        List<FileVO> result = fileMngDAO.selectFileListByFileNm(fvo);
        int cnt = fileMngDAO.selectFileListCntByFileNm(fvo);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    @Override
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
        return fileMngDAO.selectImageFileList(vo);
    }

    /**
     * 썸머노트 이미지 삭제
     */
    @Override
    public void deleteSummernoteImage(FileVO vo) throws Exception {
        fileMngDAO.deleteSummernoteImage(vo);
    }
}
