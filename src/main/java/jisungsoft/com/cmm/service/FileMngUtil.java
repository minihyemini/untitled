package jisungsoft.com.cmm.service;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovResourceCloseHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import jisungsoft.com.cmm.FileVO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("FileMngUtil")
public class FileMngUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMngUtil.class);

    public static final int BUFF_SIZE = 2048;

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    /**
     * 첨부파일에 대한 목록 정보를 취득한다. (List)
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(List<MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
        try {
            int fileKey = fileKeyParam;

            String storePathString = "";
            String atchFileIdString = "";

            if ("".equals(storePath) || storePath == null) {
                storePathString = EgovProperties.getProperty("Globals.fileStorePath");
            } else {
                storePathString = EgovProperties.getProperty("Globals.fileStorePath") + EgovProperties.getProperty(storePath);
            }

            if ("".equals(atchFileId) || atchFileId == null) {
                atchFileIdString = idgenService.getNextStringId();
            } else {
                atchFileIdString = atchFileId;
            }

            File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

            if (!saveFolder.exists() || saveFolder.isFile()) {
                //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
                if (saveFolder.mkdirs()) {
                    LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
                } else {
                    LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
                }
            }

            String filePath = "";
            List<FileVO> result = new ArrayList<FileVO>();
            FileVO fvo;

            for (MultipartFile file : files) {
                String orginFileName = file.getOriginalFilename();

                //--------------------------------------
                // 원 파일명이 없는 경우 처리
                // (첨부가 되지 않은 input file type)
                //--------------------------------------
                if ("".equals(orginFileName)) {
                    continue;
                }
                ////------------------------------------

                int index = orginFileName.lastIndexOf(".");
                //String fileName = orginFileName.substring(0, index);
                String fileExt = orginFileName.substring(index + 1);
                String newName = KeyStr + getTimeStamp() + fileKey;

                long size = file.getSize();

                if (!"".equals(orginFileName)) {
                    filePath = storePathString + File.separator + newName + "." + fileExt;
                    file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
                }

                fvo = new FileVO();
                fvo.setFileExtsn(fileExt);
                fvo.setFileStreCours(storePathString);
                fvo.setFileSize(Long.toString(size));
                fvo.setOrignlFileNm(orginFileName);
                fvo.setStreFileNm(newName);
                fvo.setAtchFileId(atchFileIdString);
                fvo.setFileSn(String.valueOf(fileKey));

                result.add(fvo);

                fileKey++;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 첨부파일에 대한 목록 정보를 취득한다. (Map)
     *
     * @param files
     * @return
     * @throws Exception
     */
    public FileVO parseFileMapInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
        int fileKey = fileKeyParam;

        String storePathString = "";
        String atchFileIdString = "";

        if ("".equals(storePath) || storePath == null) {
            storePathString = EgovProperties.getProperty("Globals.fileStorePath");
        } else {
            storePathString = EgovProperties.getProperty(storePath);
        }

        if ("".equals(atchFileId) || atchFileId == null) {
            atchFileIdString = idgenService.getNextStringId();
        } else {
            atchFileIdString = atchFileId;
        }

        File saveFolder = new File(EgovWebUtil.filePathBlackList(storePathString));

        if (!saveFolder.exists() || saveFolder.isFile()) {
            //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
            if (saveFolder.mkdirs()){
                LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
            }else{
                LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
            }
        }

        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        String filePath = "";
        FileVO result = new FileVO();
        FileVO fvo;

        while (itr.hasNext()) {
            Map.Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            String orginFileName = file.getOriginalFilename();

            //--------------------------------------
            // 원 파일명이 없는 경우 처리
            // (첨부가 되지 않은 input file type)
            //--------------------------------------
            if ("".equals(orginFileName)) {
                continue;
            }
            ////------------------------------------

            int index = orginFileName.lastIndexOf(".");
            //String fileName = orginFileName.substring(0, index);
            String fileExt = orginFileName.substring(index + 1);
            String newName = KeyStr + getTimeStamp() + fileKey;
            long size = file.getSize();

            if (!"".equals(orginFileName)) {
                filePath = storePathString + File.separator + newName + "." + fileExt;
                file.transferTo(new File(EgovWebUtil.filePathBlackList(filePath)));
            }

            fvo = new FileVO();
            fvo.setFileExtsn(fileExt);
            fvo.setFileStreCours(storePathString);
            fvo.setFileSize(Long.toString(size));
            fvo.setOrignlFileNm(orginFileName);
            fvo.setStreFileNm(newName);
            fvo.setAtchFileId(atchFileIdString);
            fvo.setFileSn(String.valueOf(fileKey));

            result = fvo;
        }

        return result;
    }

    /**
     * 첨부파일을 서버에 저장한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
        InputStream stream = null;
        OutputStream bos = null;

        try {
            stream = file.getInputStream();
            File cFile = new File(stordFilePath);

            if (!cFile.isDirectory()) {
                boolean _flag = cFile.mkdir();
                if (!_flag) {
                    throw new IOException("Directory creation Failed ");
                }
            }

            bos = new FileOutputStream(stordFilePath + File.separator + newName);

            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];

            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } finally {
            EgovResourceCloseHelper.close(bos, stream);
        }
    }

    /**
     * 서버의 파일을 다운로드한다.
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String downFileName = "";
        String orgFileName = "";

        if ((String) request.getAttribute("downFile") == null) {
            downFileName = "";
        } else {
            downFileName = (String) request.getAttribute("downFile");
        }

        if ((String) request.getAttribute("orgFileName") == null) {
            orgFileName = "";
        } else {
            orgFileName = (String) request.getAttribute("orginFile");
        }

        orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

        File file = new File(EgovWebUtil.filePathBlackList(downFileName));

        if (!file.exists()) {
            throw new FileNotFoundException(downFileName);
        }

        if (!file.isFile()) {
            throw new FileNotFoundException(downFileName);
        }

        byte[] buffer = new byte[BUFF_SIZE]; //buffer size 2K.

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(orgFileName.getBytes(), "UTF-8"));
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        BufferedInputStream fin = null;
        BufferedOutputStream outs = null;

        try {
            fin = new BufferedInputStream(new FileInputStream(file));
            outs = new BufferedOutputStream(response.getOutputStream());
            int read = 0;

            while ((read = fin.read(buffer)) != -1) {
                outs.write(buffer, 0, read);
            }
        } finally {
            EgovResourceCloseHelper.close(outs, fin);
        }
    }

    /**
     * 첨부로 등록된 파일을 서버에 업로드한다.
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> uploadFile(MultipartFile file) throws Exception {

    HashMap<String, String> map = new HashMap<String, String>();
    //Write File 이후 Move File????
    String newName = "";
    String stordFilePath = EgovProperties.getProperty("Globals.fileStorePath");
    String orginFileName = file.getOriginalFilename();

    int index = orginFileName.lastIndexOf(".");
    //String fileName = orginFileName.substring(0, _index);
    String fileExt = orginFileName.substring(index + 1);
    long size = file.getSize();

    //newName 은 Naming Convention에 의해서 생성
    newName = getTimeStamp(); // 2012.11 KISA 보안조치
    writeFile(file, newName, stordFilePath);
    //storedFilePath는 지정
    map.put(Globals.ORIGIN_FILE_NM, orginFileName);
    map.put(Globals.UPLOAD_FILE_NM, newName);
    map.put(Globals.FILE_EXT, fileExt);
    map.put(Globals.FILE_PATH, stordFilePath);
    map.put(Globals.FILE_SIZE, String.valueOf(size));
    return map;
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     *
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
        InputStream stream = null;
        OutputStream bos = null;

        try {
            stream = file.getInputStream();
            File cFile = new File(EgovWebUtil.filePathBlackList(stordFilePath));

            if (!cFile.isDirectory()){
                //2017.03.03 	조성원 	시큐어코딩(ES)-부적절한 예외 처리[CWE-253, CWE-440, CWE-754]
                if (cFile.mkdirs()){
                    LOGGER.debug("[file.mkdirs] saveFolder : Creation Success ");
                }else{
                    LOGGER.error("[file.mkdirs] saveFolder : Creation Fail ");
                }
            }

            bos = new FileOutputStream(EgovWebUtil.filePathBlackList(stordFilePath + File.separator + newName));

            int bytesRead = 0;
            byte[] buffer = new byte[BUFF_SIZE];

            while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        } catch(Exception exception){
            exception.printStackTrace();
        }
            finally {
            EgovResourceCloseHelper.close(bos, stream);
        }
    }

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     *
     * @param response
     * @param streFileNm 파일저장 경로가 포함된 형태
     * @param orignFileNm
     * @throws Exception
     */
    public void downFile(HttpServletResponse response, String streFileNm, String orignFileNm) throws Exception {
        String downFileName = streFileNm;
        String orgFileName = orignFileNm;

        File file = new File(downFileName);

        if (!file.exists()) {
            throw new FileNotFoundException(downFileName);
        }

        if (!file.isFile()) {
            throw new FileNotFoundException(downFileName);
        }

        int fSize = (int) file.length();
        if (fSize > 0) {
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));

                String mimetype = "application/x-msdownload; UTF-8";
                orgFileName = URLEncoder.encode(orgFileName,"UTF-8").replaceAll("\\+", "%20");
                //response.setBufferSize(fSize);
                response.setContentType(mimetype);
                response.setHeader("Content-Disposition", "attachment; filename=" + orgFileName);
                response.setContentLength(fSize);
                //response.setHeader("Content-Transfer-Encoding","binary");
                //response.setHeader("Pragma","no-cache");
                //response.setHeader("Expires","0");
                FileCopyUtils.copy(in, response.getOutputStream());
            } finally {
                EgovResourceCloseHelper.close(in);
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    public void photoView(FileVO fileVO, HttpServletResponse response) throws Exception {

        String src = fileVO.getFileStreCours() + fileVO.getStreFileNm() + "." + fileVO.getFileExtsn();
        if (src != null) {
            String fileLoc = src;
            String ext = FilenameUtils.getExtension(fileLoc);

            File fileData          = new File(fileLoc);
            FileInputStream fis    = new FileInputStream(fileData);
            BufferedInputStream in = new BufferedInputStream(fis);
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();

            int imgByte;
            while ((imgByte = in.read()) != -1) {
                bStream.write(imgByte);
            }

            in.close();
            if (ext.toLowerCase().equals("gif")) {
                response.setContentType("image/gif");
            } else if (ext.toLowerCase().equals("png")) {
                response.setContentType("image/png");
            } else if (ext.toLowerCase().equals("jpg") || ext.toLowerCase().equals("jpeg")
                    || ext.toLowerCase().equals("jpe")) {
                response.setContentType("image/jpeg");
            }

            response.setContentLength(bStream.size());
            bStream.writeTo(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

    /**
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @see
     */
    private static String getTimeStamp() {

        String rtnStr = null;

        // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
        String pattern = "yyyyMMddhhmmssSSS";

        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());

        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }

    /**
     * 파일 타입 체크
     * @param fileType
     * @return
     */
    public static Boolean checkFileType(String fileType) {

        Boolean typeCheck = true;

        if ("JSP".equals(fileType) || "jsp".equals(fileType)) {
            typeCheck = false;
        } else if ("PHP".equals(fileType) || "php".equals(fileType)) {
            typeCheck = false;
        } else if ("HTML".equals(fileType) || "html".equals(fileType)) {
            typeCheck = false;
        } else if ("ASP".equals(fileType) || "asp".equals(fileType)) {
            typeCheck = false;
        } else if ("ASPX".equals(fileType) || "aspx".equals(fileType)) {
            typeCheck = false;
        } else if ("CGI".equals(fileType) || "cgi".equals(fileType)) {
            typeCheck = false;
        } else if ("PL".equals(fileType) || "pl".equals(fileType)) {
            typeCheck = false;
        } else if ("PY".equals(fileType) || "py".equals(fileType)) {
            typeCheck = false;
        } else if ("JS".equals(fileType) || "js".equals(fileType)) {
            typeCheck = false;
        }

        return typeCheck;
    }
}
