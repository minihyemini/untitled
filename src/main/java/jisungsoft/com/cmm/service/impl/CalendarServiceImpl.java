package jisungsoft.com.cmm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jisungsoft.com.cmm.CalendarVO;
import jisungsoft.com.cmm.service.CalendarService;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("calendarService")
public class CalendarServiceImpl extends EgovAbstractServiceImpl implements CalendarService {

    @Override
    public List<Map> getCalInfoList(CalendarVO calVO) throws Exception {
        Calendar cal = Calendar.getInstance();
        /* 현재 날짜 */
        String todayYear  = Integer.toString(cal.get(Calendar.YEAR));
        String todayMonth = Integer.toString(cal.get(Calendar.MONTH)+1);
        String todayDay   = Integer.toString(cal.get(Calendar.DATE));

        if(calVO.getYear() == null || calVO.getYear().isEmpty()){
            calVO.setYear(Integer.toString(cal.get(Calendar.YEAR)));
        }
        if(calVO.getMonth()==null || calVO.getMonth().isEmpty()){
            calVO.setMonth(Integer.toString(cal.get(Calendar.MONTH) + 1));
        }
        int iYear  = Integer.parseInt(calVO.getYear());
        int iMonth = Integer.parseInt(calVO.getMonth());

        if (iMonth < 1){
            iYear--;
            iMonth = 12;
        } else if (iMonth > 12){
            iYear++;
            iMonth = 1;
        }
        if (iYear < 1){
            iYear = 1;
            iMonth = 1;
        } else if (iYear > 9999){
            iYear = 9999;
            iMonth = 12;
        }
        calVO.setYear(Integer.toString(iYear));
        calVO.setMonth(Integer.toString(iMonth));

        /* calendar 계산 된 기준일 */
        cal.set(iYear,iMonth-1,1);
        int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
        int lastDay   = cal.getActualMaximum(Calendar.DATE);
        int week      = cal.get(Calendar.DAY_OF_WEEK);
        String year   = Integer.toString(iYear);
        String month  = Integer.toString(iMonth);

        calVO.setStartWeekMonth(firstWeek);
        calVO.setLastDayMonth(lastDay);
        calVO.setYear(year);
        calVO.setMonth(month);

        List calInfoList = new ArrayList();
        String tmpDay = "";

        for(int i = 0; i < 42; i++) {
            ListOrderedMap map   = new ListOrderedMap();
            int cc = i + 1;
            int dd = cc - firstWeek + 1;

            if (dd > 0 && dd <= lastDay) {
                tmpDay = Integer.toString(dd);
            } else {
                tmpDay = "";
            }
            if(year.equals(todayYear) && month.equals(todayMonth) && tmpDay.equals(todayDay)){
                map.put("today",    "Y");
            }else{
                map.put("today",    "N");
            }
            map.put("year",     year);
            map.put("month",    month);
            map.put("day",      tmpDay);
            map.put("weeks",    (cc - 1) / 7 + 1);
            map.put("week",     (week-1) % 7 + 1);
            map.put("nowDay", todayDay);

            if (dd > 0 && dd <= lastDay) {
                week ++;
            }
            calInfoList.add(map);

        }
        return calInfoList;
    }
}
