package jisungsoft.com.cmm.service;

import jisungsoft.com.cmm.CalendarVO;

import java.util.List;
import java.util.Map;

public interface CalendarService {

    List<Map> getCalInfoList(CalendarVO calendarVO) throws Exception;
}
