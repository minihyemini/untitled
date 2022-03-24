package jisungsoft.com.cmm.service;

import jisungsoft.com.cmm.FileVO;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Data
public class Summernote {

    /**
     * 사용자 고유ID
     */
    private String uniqId;
    /**
     * 응답
     */
    private HttpServletResponse response;
    /**
     * 파일명
     */
    private FileVO file;
    /**
     * 파일 목록
     */
    private List<FileVO> fileList;
    /**
     *
     */
    private List<MultipartFile> files;
}
