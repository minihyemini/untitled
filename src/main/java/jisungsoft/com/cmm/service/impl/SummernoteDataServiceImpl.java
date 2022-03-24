package jisungsoft.com.cmm.service.impl;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cryptography.EgovEnvCryptoService;
import jisungsoft.com.cmm.FileVO;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import jisungsoft.com.cmm.service.Summernote;
import jisungsoft.com.cmm.service.SummernoteDataService;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service("summernoteDataService")
public class SummernoteDataServiceImpl implements SummernoteDataService {

    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    //로그인 사용자 정보 및 권한
    private LoginVO authenticatedUser;

    private final String EN_DN_KEY = "SUM2021";

    private String summernotePath = "Globals.filePath.summernote";

    private String filePrefix = "summer_";

    /** 암호화서비스 */
    @Resource(name = "egovEnvCryptoService")
    EgovEnvCryptoService cryptoService;

    /**
     * File Service
     */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    @Override
    public Summernote summernoteFileSave(Summernote summernote) throws Exception {
        List<MultipartFile> files = summernote.getFiles();
        int fileKeyParam = 0;
        List<FileVO> fileList = fileMngUtil.parseFileInf(files, filePrefix+summernote.getUniqId()+"_", fileKeyParam, "sum", summernotePath);

        if (fileList.size() > 0) {
            for (FileVO vo : fileList) {
                String fileNm = vo.getStreFileNm();
                //파일명 암호화
                fileNm = cryptoService.encrypt(fileNm);
                vo.setStreFileNm(fileNm);
            }
        }

        summernote.setFileList(fileList);

        return summernote;
    }

    @Override
    public Summernote summernoteTempFileSave(Summernote summernote) throws Exception {

        return summernote;
    }

    @Override
    public void summernoteFileView(Summernote summernote) throws Exception {
        FileVO fileVO = summernote.getFile();
        String fileNm = fileVO.getStreFileNm();

        //파일명 복호화
        fileNm = cryptoService.decryptNone(fileNm);
        fileVO.setStreFileNm(fileNm);

        //파일 경로
        String strPath = EgovProperties.getProperty("Globals.fileStorePath") + EgovProperties.getProperty(summernotePath);
        fileVO.setFileStreCours(strPath);

        fileMngUtil.photoView(fileVO, summernote.getResponse());
    }

    @Override
    public void summernoteTempFileDelete(Summernote summernote) throws Exception {
        authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();

    }
}
