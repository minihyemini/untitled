package jisungsoft.com.mes.sales.rod.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.access.service.EgovUserDetailsHelper;
import jisungsoft.com.persistence.OrderType;
import jisungsoft.com.mes.sales.rod.validation.OrderFormValidator;
import jisungsoft.com.persistence.dto.mes.Order;
import jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.CmmnDetailCodeService;
import jisungsoft.com.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 수주관리
 */
@Slf4j
@Controller
@RequestMapping("/mes/sales/rod")
public class SalesReceiveOrderController {
    @Autowired
    private SessionLocaleResolver localeResolver;
    /**
     * 메세지 서비스
     */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;
    /**
     * 주문관리 서비스
     */
    @Resource(name = "orderService")
    private OrderService orderService;
    /**
     * 상세코드 서비스
     */
    @Resource(name = "cmmnDetailCodeService")
    private CmmnDetailCodeService cmmnDetailCodeService;

    private final String VIEW_PATH = "jisungsoft/com/mes/sales/rod";
    private final String REDIRECT_PATH = "redirect:/mes/sales/rod";

    /**
     * Redirect Validation form
     * @return
     */
    @ModelAttribute("orderForm")
    public Order newOrderForm() {
        Order order = new Order();
        //주문타입 - 수주
        order.setOrdType(OrderType.RECEIVE.name());
        return order;
    }

    @RequestMapping(value = "/list.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(@ModelAttribute Order searchForm, final Model model, Locale locale, HttpServletRequest request) {
        log.info("Session locale is {}.", locale);
        localeResolver.resolveLocale(request);

        CmmnDetailCode cmmnDetailCode = new CmmnDetailCode();
        //거래유형코드
        cmmnDetailCode.setCodeId(OrderType.ODRCTGR.name());
        List<CmmnDetailCode> categoryList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);
        //주문상태코드
        cmmnDetailCode.setCodeId(OrderType.RCVSTATUS.name());
        List<CmmnDetailCode> statusList = cmmnDetailCodeService.getCmmnDetailCodeAllList(cmmnDetailCode);

        searchForm.setOrdType(OrderType.RECEIVE.name());
        List<Order> resultData = orderService.getClientOrderListAll(searchForm);

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("resultData", resultData);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("statusList", statusList);

        return VIEW_PATH + "/list.mes";
    }

    @PostMapping(value = "/add.do")
    public String addAction(@Validated @ModelAttribute("orderForm") Order form, final BindingResult bindingResult, Locale locale,
                            final RedirectAttributes attr) {

        new OrderFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "orderForm", bindingResult);
            attr.addFlashAttribute("orderForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setFrstRegisterId(user.getUniqId());
            form.setOrdType(OrderType.RECEIVE.name());
            orderService.addRequestOrder(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.insert", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.insert", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/edit.do")
    public String editAction(@Validated @ModelAttribute("orderForm") Order form, final BindingResult bindingResult, Locale locale,
                             final RedirectAttributes attr) {

        new OrderFormValidator().validate(form, bindingResult);
        attr.addFlashAttribute("menuId", form.getMenuId());

        if (bindingResult.hasErrors()) {
            log.info("errors={} ", bindingResult);
            attr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +  "orderForm", bindingResult);
            attr.addFlashAttribute("orderForm", form);

            return REDIRECT_PATH+"/list.do";
        }

        try {
            // 로그인 사용자 정보 가져오기
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            form.setLastUpdusrId(user.getUniqId());
            orderService.editRequestOrder(form);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.update", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.update", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }

    @PostMapping(value = "/remove.do")
    public String removeAction(@Validated @ModelAttribute Order order,
                               final BindingResult bindingResult, Model model, Locale locale,
                               final RedirectAttributes attr) {

        try {
            orderService.removeRequestOrderItem(order);

            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("success.common.delete", locale));
        } catch (DataAccessException e) {
            log.info("{DataAccessException} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.sql", locale));
        } catch (Exception e) {
            log.info("{}{} :::", e.getMessage());
            attr.addFlashAttribute("resultMessage", egovMessageSource.getMessage("fail.common.delete", locale));
        }

        return REDIRECT_PATH+"/list.do";
    }
}
