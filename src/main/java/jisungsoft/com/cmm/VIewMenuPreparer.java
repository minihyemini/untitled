package jisungsoft.com.cmm;

import egovframework.com.utl.fcc.service.EgovStringUtil;
import jisungsoft.com.cmm.code.AuthorityCode;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.model.LoginVO;
import jisungsoft.com.service.MenuViewService;
import lombok.SneakyThrows;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public class VIewMenuPreparer implements ViewPreparer {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VIewMenuPreparer.class);

    @Resource(name = "menuViewService")
    private MenuViewService menuViewService;

    //로그인 사용자 정보 및 권한
    private LoginVO authenticatedUser;

    @SneakyThrows
    @Override
    public void execute(Request tilesContext, AttributeContext attributeContext) {

        LOGGER.debug("-------------- ViewPreparer Start ----------------");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.debug(request.getRequestURL().toString());
        HashMap<String, Object> dataMap = (HashMap) request.getAttribute("data");

        //인증된사용자 여부
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        String auth = AuthorityCode.ROLE_ANONYMOUS.name();
        if (isAuthenticated) {
            authenticatedUser = (LoginVO) UserDetailsHelper.getAuthenticatedUser();
            auth = authenticatedUser.getAuthority();
        }


        Menu menu = new Menu();
        menu.setAuthorCode(auth);

        if (dataMap.get("category") != null) {
            menu.setMenuCategory(dataMap.get("category").toString());
        }

        menu.setMenuLv(1);
        List<Menu> dept1 = menuViewService.selectMenuDataList(menu);
        menu.setMenuLv(2);
        List<Menu> dept2 = menuViewService.selectMenuDataList(menu);
        menu.setMenuLv(3);
        List<Menu> dept3 = menuViewService.selectMenuDataList(menu);
        menu.setMenuLv(4);
        List<Menu> dept4 = menuViewService.selectMenuDataList(menu);

        Menu menuData = new Menu();
        String requestURI = dataMap.get("servletPath").toString();
        String menuId = (String) dataMap.get("menuId");
        String upperMenuNo = (String) dataMap.get("upperMenuNo");
        String category = (String) dataMap.get("category");

        int menuIdToInt = 0;
        int upperMenuNoToInt = 0;
        if (!EgovStringUtil.isEmpty(menuId)) {
            menuIdToInt = Integer.parseInt(menuId);
        }
        if (!EgovStringUtil.isEmpty(upperMenuNo)) {
            upperMenuNoToInt = Integer.parseInt(upperMenuNo);
            menuData.setUpperMenuNo(upperMenuNoToInt);
        }

        menuData.setMenuCategory(category);
        menuData.setUrl(requestURI);
        if (!requestURI.contains("/index.do")) {
            if (menuIdToInt == 0) {
//                menuData.setUrl((String) dataMap.get("strePath"));
                menuData.setProgrmQuery((String) dataMap.get("query"));
                if (dataMap.get("menuLv") != null && !dataMap.get("menuLv").equals("")) {
                    menuData.setMenuLv(Integer.parseInt((String) dataMap.get("menuLv")));
                }
            } else {
                menuData.setMenuNo(menuIdToInt);
            }
            menuData = menuViewService.selectMenuDataDetail(menuData);
        }

        if (menuData == null) {
            menuData = new Menu();
            menuData.setUrl(requestURI);
        }

        attributeContext.putAttribute("menuData", new Attribute(menuData), true);
        attributeContext.putAttribute("menuDepth1", new Attribute(dept1), true);
        attributeContext.putAttribute("menuDepth2", new Attribute(dept2), true);
        attributeContext.putAttribute("menuDepth3", new Attribute(dept3), true);
        attributeContext.putAttribute("menuDepth4", new Attribute(dept4), true);
        request.setAttribute("menuData", menuData);

        LOGGER.debug("-------------- ViewPreparer End ----------------");
    }
}
