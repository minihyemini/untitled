package jisungsoft.com.cmm.util;

import jisungsoft.com.cmm.service.FileMngService;
import jisungsoft.com.cmm.service.FileMngUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommonLibrary {

    /** FileMngUtil */
    @Resource(name = "FileMngUtil")
    private FileMngUtil fileMngUtil;

    /** File Service */
    @Resource(name = "fileMngService")
    private FileMngService fileMngService;

    /* 디렉토리 생성(옵션으로 썸네일 디렉토리까지 생성)*/
    public void mkdirs(String path, boolean subDirectory) throws Exception {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (subDirectory) {
            folder = new File(path + "middle/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            folder = new File(path + "small/");
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
    }

    /* 파일 삭제(썸네일 파일까지 삭제) */
    public void fileDelete(String path, String realFile) throws Exception {
        File fullpath = new File(path + realFile);
        if (fullpath.exists()) {
            fullpath.delete();
        }
        fullpath = new File(path + "middle/" + realFile);
        if (fullpath.exists()) {
            fullpath.delete();
        }
        fullpath = new File(path + "small/" + realFile);
        if (fullpath.exists()) {
            fullpath.delete();
        }
    }

    /* 타임스탬프 */
    public String timestamp() throws Exception {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        return dayTime.format(new Date(time));
    }

    /* 오늘 날짜를 지정한 형식으로 리턴 */
    public String today(String type) throws Exception {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = null;

        if ("y.m.d".equals(type)) {
            dayTime = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        } else if ("m.d".equals(type)) {
            dayTime = new SimpleDateFormat("MM.dd", Locale.KOREA);
        }
        if ("ymd".equals(type)) {
            dayTime = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        }
        return dayTime.format(new Date(time));
    }

    /* 날짜형식을 yyMMdd를 yyyy.mm.dd로 변경 */
    public String dateParse(String date) throws Exception {
        String newDate = "";
        String format = "";
        String format2 = "";

        if (date.length() == 14) {
            format = "yyyyMMddHHmmss";
            format2 = "yyyy.MM.dd HH:mm:ss";
        } else {
            format = "yyyyMMdd";
            format2 = "yyyy.MM.dd";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format2, Locale.KOREA);

        try {
            newDate = sdf2.format(sdf.parse(date));
        } catch(Exception e) {
            newDate = "-";
        }
        return newDate;
    }

    /* 시간을 HHmm을 HH:mm으로 변경 */
    public String timeParse(String time) throws Exception {
        if (time == null || time.length() != 4) {
            return "-";
        } else {
            return time.substring(0, 2) + ":" + time.substring(2, 4);
        }
    }

    /* 줄바꿈 기호를 태그로 변경 */
    public String nl2br(String str) throws Exception {
        str = str.replaceAll("\r\n", "<br />");
        str = str.replaceAll("\r", "<br />");
        str = str.replaceAll("\n", "<br />");
        return str;
    }

    /* 태그로 줄바꿈 기호로 변경 */
    public String br2nl(String str) throws Exception {
        str = str.replaceAll("<br />", "\r\n");
        str = str.replaceAll("<br/>", "\r\n");
        str = str.replaceAll("<br>", "\r\n");
        return str;
    }

    /* 텍스트를 HTML로 변경 */
    public String toHtml(String str) throws Exception {
        str = str.replaceAll("  ", "&nbsp;&nbsp;");
        str = str.replaceAll("\r\n", "<br />");
        str = str.replaceAll("\r", "<br />");
        str = str.replaceAll("\n", "<br />");
        return str;
    }

    /* 숫자 유효성 체크 */
    public boolean isNumeric(String str) throws Exception {
        return str.matches("[-+]?\\d*\\.?\\d+");
    }

    /* HashMap을 Json으로 변환 */
    public String toJson(HashMap<String, Object> data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(data);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    /* 날짜를 비교하여 날짜간의 차이를 계산 */
    public long diffDate(String begin, String end) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        Date beginDate = formatter.parse(begin);
        Date endDate = formatter.parse(end);

        long diff = endDate.getTime() - beginDate.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffDays;
    }

    /* 시간을 비교하여 시간간의 차이를 계산 */
    public long diffTime(String begin, String end) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        Date beginDate = formatter.parse(begin);
        Date endDate = formatter.parse(end);

        long diff = endDate.getTime() - beginDate.getTime();
        long diffTimes = diff / (24 * 60 * 60);

        return diffTimes;
    }

    public String week(String str) throws Exception {
        SimpleDateFormat sdf = null;
        if (str.length() == 14) {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
            sdf = new SimpleDateFormat("yyyyMMdd");
        }
        Date date = sdf.parse(str);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch (dayOfWeek) {
            case 1:
                week = "일요일";
                break;
            case 2:
                week = "월요일";
                break;
            case 3:
                week = "화요일";
                break;
            case 4:
                week = "수요일";
                break;
            case 5:
                week = "목요일";
                break;
            case 6:
                week = "금요일";
                break;
            case 7:
                week = "토요일";
                break;
        }

        return week;
    }

    public String weekend(String str) throws Exception {
        SimpleDateFormat sdf = null;
        if (str.length() == 14) {
            sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        } else {
            sdf = new SimpleDateFormat("yyyyMMdd");
        }
        Date date = sdf.parse(str);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return "sun";
        } else if (dayOfWeek == 7) {
            return "sat";
        } else {
            return "-";
        }
    }

    public String convertTime(int time) {
        int hour = time / 3600;
        int minute = time % 3600 / 60;
        int second = time % 3600 % 60;

        return String.format("%02d", hour) + String.format("%02d", minute) + String.format("%02d", second);
    }

    public String convertSecond(String str) {
        String[] split = str.split(":");
        int second = 0;

        if (split.length != 3) {
            return "-";
        } else {
            second = Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
            return Integer.toString(second);
        }
    }

    public String getBrowser(String userAgent) throws Exception {
        userAgent = userAgent.toLowerCase();
        if (userAgent != null) {
            if (userAgent.indexOf("trident") > -1) {
                return "MSIE";
            } else if (userAgent.indexOf("chrome") > -1) {
                return "Chrome";
            } else if (userAgent.indexOf("opera") > -1) {
                return "Opera";
            } else if (userAgent.indexOf("iphone") > -1 && userAgent.indexOf("mobile") > -1) {
                return "iPhone";
            } else if (userAgent.indexOf("android") > -1 && userAgent.indexOf("mobile") > -1) {
                return "Android";
            }
        }
        return "Firefox";
    }

    public String stringCut(String str, int len) throws Exception {
        if (str.length() <= len) {
            return str;
        }

        StringCharacterIterator sci = new StringCharacterIterator(str);
        StringBuffer buffer = new StringBuffer();
        buffer.append(sci.first());
        for (int i = 1; i < len; i++) {
            if (i < len - 1) {
                buffer.append(sci.next());
            } else {
                char c = sci.next();
                if (c != 32) { // 마지막 문자가 공백이 아닐 경우
                    buffer.append(c);
                }
            }
        }

        buffer.append("..");
        return buffer.toString();
    }

    /* 패턴비교만 */
    public boolean strstr(String regexp, String str) {
        Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    /* 패턴비교하여 결과리턴 */
    public String pregMatch(String regexp, String str, int number) {
        Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return matcher.group(number).trim(); // trim 적용
        } else {
            return "false";
        }
    }

    /* 패턴비교하여 결과리턴 */
    public ArrayList<String> pregMatchAll(String regexp, String str, int number) {
        Pattern pattern = Pattern.compile(regexp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(str);
        ArrayList<String> matches = new ArrayList<String>();

        while (matcher.find()) {
            matches.add(matcher.group(number).trim()); // trim 적용
        }

        return matches;
    }

    public String removeSpace(String str) {
        str = str.replaceAll("\\p{Space}", ""); // 모든 공백 제거
        str = str.replaceAll("&nbsp;", "");
        str = str.replaceAll(" ", "");
        return str;
    }

    public String regexp(String str) {
        str = str.replace("\\", "\\\\");
        str = str.replace("(", "\\(");
        str = str.replace(")", "\\)");
        str = str.replace(".", "\\.");
        str = str.replace("[", "\\[");
        str = str.replace("]", "\\]");
        str = str.replace("{", "\\{");
        str = str.replace("}", "\\}");
        return str.trim();
    }

    public String date(String str) {
        String year = "";
        String month = "";
        String day = "";
        String hour = "";
        String minute = "";

        if (str.length() == 12) {
            year = str.substring(0, 4);
            month = str.substring(4, 6);
            day = str.substring(6, 8);
            hour = str.substring(8, 10);
            minute = str.substring(10, 12);

            str = year + "." + month + "." + day + " " + hour + ":" + minute;
        } else if (str.length() == 8) {
            year = str.substring(0, 4);
            month = str.substring(4, 6);
            day = str.substring(6, 8);

            str = year + "." + month + "." + day;
        }

        return str;
    }

    /* 문자열 줄바꿈을 특정 태그로 변환 */
    public String split(String str, String delimiter, String startStr, String endStr) {
        String split[] = str.split(delimiter);

        str = "";
        for (int i = 0; i < split.length; i++) {
            str += startStr + split[i] + endStr;
        }

        return str;
    }

    /* 파일명 반환(baseName) */
    public String baseName(String str) {
        if (str.lastIndexOf("/") > -1) {
            int pos = str.lastIndexOf("/");
            str = str.substring(pos + 1); /* "/"는 미포함 */
        }

        return str;
    }

    public String[] weekCalendar(int number, String str) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        String week = this.week(str);
        if ("일요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        } else if ("월요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
        } else if ("화요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.TUESDAY);
        } else if ("수요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.WEDNESDAY);
        } else if ("목요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.THURSDAY);
        } else if ("금요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.FRIDAY);
        } else if ("토요일".equals(week)) {
            calendar.setFirstDayOfWeek(Calendar.SATURDAY);
        }
        calendar.add(Calendar.WEEK_OF_MONTH, number);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, (-(dayOfWeek - 1)));

        String[] array = new String[7];
        for (int i = 0; i < 7; i++) {
            array[i] = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return array;
    }

    public String[] weekCalendar() throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, (-(dayOfWeek - 1)));

        String[] array = new String[7];
        for (int i = 0; i < 7; i++) {
            array[i] = sdf.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return array;
    }

    public String nameMask(String str) {
        return str = str.replaceAll("(?<=.{1}).", "*");
    }

    public String imageExists(String realPath, String image, String noimage) {
        File file = new File(realPath + image);
        if (file.exists() && file.isFile()) {
            return "/" + image;
        } else {
            return noimage;
        }
    }

    public String firstUpper(String str) {
        String newStr = str.substring(0, 1);
        newStr = newStr.toUpperCase();
        newStr += str.substring(1);

        return newStr;
    }

    public String removeTag(String str) throws Exception {
        return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
    }

    public String removeTag2Space(String str) throws Exception {
        return str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", " ");
    }

    public String lastDay(String year, String month, String day) throws Exception {
        if (this.isNumeric(year) && this.isNumeric(month) && this.isNumeric(day)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            int lastDay = calendar.getActualMaximum(Calendar.DATE);

            if (Integer.toString(lastDay).length() == 2) {
                return Integer.toString(lastDay);
            } else {
                return "0" + Integer.toString(lastDay);
            }
        } else {
            return "-";
        }
    }

    public String addDay(String day, int number) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String newDate = "";
        try {
            Date date = df.parse(day);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, number);

            newDate = df.format(calendar.getTime());
        } catch (Exception e) {
            return "-";
        }

        return newDate;
    }

    /**
     * excel parser
     * @param streFileNm
     * @return
     */
    public JSONArray excelRead(String streFileNm) {
        JSONArray jArray = new JSONArray();
        JSONObject data = new JSONObject();
        List<String> title = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(streFileNm);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            int rowindex = 0;
            int columnindex = 0;

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();

            for (rowindex = 0; rowindex < rows; rowindex++) {
                XSSFRow row = sheet.getRow(rowindex);
                data = new JSONObject();

                if (row != null) {
                    int cells = row.getPhysicalNumberOfCells();

                    for (columnindex = 0; columnindex <= cells; columnindex++) {
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";

                        if (cell == null) {
                            continue;
                        } else {
                            switch (cell.getCellType()) {
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    value = cell.getNumericCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    value = cell.getStringCellValue() + "";
                                    break;
                                case XSSFCell.CELL_TYPE_BLANK:
                                    value = cell.getBooleanCellValue() + "";
                                case XSSFCell.CELL_TYPE_ERROR:
                                    value = cell.getErrorCellValue() + "";
                            }
                        }
                        if (rowindex == 0) {
                            title.add(value);
                        } else {
                            data.put(title.get(columnindex), value);
                        }
                    }
                }
                if (rowindex > 0) jArray.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            data.put("error", "파일 형식이 잘못되었습니다.");
            jArray.add(data);

            return jArray;
        }

        return jArray;
    }
}
