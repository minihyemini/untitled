package jisungsoft.com.mes.sales.rod.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.persistence.dto.mes.Client;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.dto.mes.Pdnum;
import jisungsoft.com.service.ClientService;
import jisungsoft.com.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 수주관리
 */
@Slf4j
@RestController
@RequestMapping("/mes/sales/rod")
public class SalesReceiveOrderRestController {

    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "clientService")
    private ClientService clientService;

    @Resource(name = "orderService")
    private OrderService orderService;

    @RequestMapping("/client/list.json")
    public ModelAndView clientList(Client formData) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        List<Client> clientAllList = clientService.getClientAllList(formData);
        modelAndView.addObject("dataList", clientAllList);

        return modelAndView;
    }

    @RequestMapping("/order/list.json")
    public ModelAndView orderList(Order formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        Order orderDetail = orderService.getRequestOrderDetail(formData);
        List<Order> requestOrderList = orderService.getRequestOrderItemListAll(formData);

        modelAndView.addObject("dataDetail", orderDetail);
        modelAndView.addObject("dataList", requestOrderList);
        return modelAndView;
    }

    @PostMapping("/item/remove.json")
    public ModelAndView orderRemove(Order formData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        orderService.removeRequestOrderItem(formData);

        return modelAndView;
    }

    /**
     * 엑셀 출력
     */
    @RequestMapping("/list/excel.json")
    public ModelAndView userList(Order formData) throws Exception {
        ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<Object> list = orderService.getClientOrderListMap(formData);

        String filename = "product_number_list";
        String[] columnArr = {"번호", "성명", "아이디", "가입일"};
        String[] columnVarArr = {"idx", "mberNm", "mberId", "sbscrbDe"};

        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", "product number list");
        dataMap.put("list", null);

        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", filename);

        return mav;
    }
}
