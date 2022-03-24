package jisungsoft.com.cmm;

import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
public class CalendarVO implements Serializable {
    /* 연도 */
    private String year;

    /* 월 */
    private String month;

    /* 일 */
    private String day;

    /* 주 */
    private Integer weeks = 0;

    /* 요일별(1:일, 2:월, 3:화, 4:수 ...) */
    private Integer week = 0;

    private Integer startWeekMonth = 0;

    private Integer lastDayMonth   = 0;
}
