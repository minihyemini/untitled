package jisungsoft.com.mes.sym.menu.api;

import egovframework.com.cmm.EgovMessageSource;
import jisungsoft.com.cmm.util.UserDetailsHelper;
import jisungsoft.com.persistence.dto.sym.menu.Menu;
import jisungsoft.com.persistence.dto.sym.menu.MenuCreat;
import jisungsoft.com.repository.sym.MenuCreateMapper;
import jisungsoft.com.service.MenuService;
import jisungsoft.com.service.MenuProgramService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mes/sym/menu")
public class MenuManageRestController {

    @Resource(name = "menuService")
    protected MenuService menuManageService;

    @Resource(name = "menuProgramService")
    protected MenuProgramService menuProgramService;

    @Resource(name = "menuCreateMapper")
    private MenuCreateMapper menuCreateMapper;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;
/*

    @RequestMapping(value = "/programDetail.json", method = RequestMethod.POST)
    public ModelAndView programDetail(MenuProgram menuProgram) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        MenuProgram result = menuProgramService.getProgram(menuProgram);
        modelAndView.addObject("result", result);

        return modelAndView;
    }
*/
/*

    @RequestMapping(value = "/programList.json", method = RequestMethod.POST)
    public ModelAndView programList(MenuProgram menuProgram) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(menuProgram.getPageIndex());
        paginationInfo.setRecordCountPerPage(menuProgram.getPageUnit());
        paginationInfo.setPageSize(menuProgram.getPageSize());

        menuProgram.setFirstIndex(paginationInfo.getFirstRecordIndex());
        menuProgram.setLastIndex(paginationInfo.getLastRecordIndex());
        menuProgram.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<MenuProgram> resultList = menuProgramService.getProgramList(menuProgram);

        int totCnt = menuProgramService.getProgramListTotCnt(menuProgram);
        paginationInfo.setTotalRecordCount(totCnt);

        modelAndView.addObject("result", resultList);
        modelAndView.addObject("paginationInfoJs", paginationInfo);

        return modelAndView;
    }
*/

    @RequestMapping(value = "/upperMenuByPk.json", method = RequestMethod.POST)
    public ModelAndView upperMenuByPk(Menu menu) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        menu.setUpperMenuNo(menu.getMenuNo());
        int result = menuManageService.getUpperMenuNoByPk(menu);
        modelAndView.addObject("result", result);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/menuView.json")
    public List<HashMap<String, String>> menuTreeView(@RequestParam Map<String, Object> commandMap) throws Exception {
        List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();

        String type = (String) commandMap.get("type");
        Menu menu = new Menu();

        switch (type) {
            case "root":
                menu.setMenuLv(0);
                List<Menu> rootList = menuManageService.getMenuManageAllList(menu);

                for (Menu menuData : rootList) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String category = menuData.getMenuCategory();
                    Integer menuLv = menuData.getMenuLv();
                    String categoryNm = "";
                    if (category.equals("MES")) {
                        categoryNm = "MES";
                    }

                    String title = "<span>"+"/["+categoryNm+"]</span>";

                    map.put("folder", "true");
                    map.put("lazy", "true");
                    map.put("title", title);
                    map.put("menuNo", Integer.toString(menuData.getMenuNo()));
                    map.put("menuLv", Integer.toString(menuLv));
                    map.put("mode", "category");
                    map.put("category", category);

                    dataList.add(map);
                }

                break;
            case "category":
                menu.setMenuLv(Integer.parseInt(commandMap.get("menuLv").toString())+1);
                menu.setMenuCategory(commandMap.get("category").toString());
                List<Menu> menuGroupList = menuManageService.getMenuManageAllList(menu);
                for (Menu menuData : menuGroupList) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String category = menuData.getMenuCategory();
                    int menuNo = menuData.getMenuNo();
                    int upperMenuNo = menuData.getUpperMenuNo();
                    String programNm = menuData.getProgrmFileNm();
                    int menuLv = menuData.getMenuLv();
                    int subCnt = menuData.getSubCnt();
                    String title = "<span onclick=\"fn_pageAction(this);\" data-lv=\""+menuLv+"\""+" data-category=\""+category+"\" data-id=\""+menuNo+"\" data-upperid=\""+upperMenuNo+"\" data-type=\""+programNm+"\">"+menuData.getMenuNm()+"</span>";

                    if (subCnt > 0) {
                        map.put("folder", "true");
                        map.put("lazy", "true");
                    }

                    map.put("title", title);
                    map.put("category", category);
                    map.put("mode", "menu");
                    map.put("menuNo", Integer.toString(menuNo));
                    map.put("upperMenuNo", Integer.toString(upperMenuNo));
                    map.put("menuLv", Integer.toString(menuLv));
//                    map.put("programNm", programNm);

                    dataList.add(map);
                }

                break;
            case "menu":
                menu.setMenuCategory(commandMap.get("category").toString());
                menu.setMenuLv(Integer.parseInt(commandMap.get("menuLv").toString())+1);
                menu.setUpperMenuNo(Integer.parseInt(commandMap.get("menuNo").toString()));

                List<Menu> subMenuList = menuManageService.getMenuManageAllList(menu);
                if (subMenuList.size() > 0) {
                    for (Menu menuData : subMenuList) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        int menuNo = menuData.getMenuNo();
                        int upperMenuNo = menuData.getUpperMenuNo();
                        String programNm = menuData.getProgrmFileNm();
                        int programId = menuData.getProgrmId() != null ? menuData.getProgrmId() : 0;
                        int menuLv = menuData.getMenuLv();
                        String category = menuData.getMenuCategory();
                        int subCnt = menuData.getSubCnt();;
                        String title = "<span onclick=\"fn_pageAction(this);\" data-lv=\""+menuLv+"\""+" data-category=\""+category+"\" data-id=\""+menuNo+"\" data-upperid=\""+upperMenuNo+"\" data-type=\""+programNm+"\">"+menuData.getMenuNm() + "</span>";

                        if (subCnt > 0) {
                            map.put("folder", "true");
                            map.put("lazy", "true");
                        }

                        map.put("mode", "menu");
                        map.put("title", title);
                        map.put("menuNo", Integer.toString(menuNo));
                        map.put("subCnt", Integer.toString(subCnt));
                        map.put("upperMenuNo", Integer.toString(upperMenuNo));
                        map.put("programNm", programNm);
                        map.put("programId", Integer.toString(programId));
                        map.put("category", category);
                        map.put("menuLv", Integer.toString(menuLv));

                        dataList.add(map);
                    }
                }

                break;
        }

        return dataList;
    }

    @ResponseBody
    @RequestMapping(value = "/menuForm.json")
    public Menu menuTreeForm(@RequestParam Map<String, Object> commandMap) throws Exception {

        int menuNo = Integer.parseInt(commandMap.get("menuNo").toString());
        Menu menu = new Menu();
        menu.setMenuNo(menuNo);
        Menu menuManage = menuManageService.getMenuManage(menu);

        MenuCreat menuCreat = new MenuCreat();
        menuCreat.setMenuNo(menu.getMenuNo());
        List<MenuCreat> menuCreats = menuCreateMapper.selectMenuCreatAuthList(menuCreat);
        menuManage.setMenuCreatList(menuCreats);

        if (menuManage == null) {
            menuManage = new Menu();
        }

        return menuManage;
    }

    @ResponseBody
    @RequestMapping(value = "/edit.json")
    public ModelAndView menuUpdate(@ModelAttribute ("menu") Menu menu) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //보안 취약점
        boolean isAuthenticated = UserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            modelAndView.setViewName("redirect:/page/uat/uia/loginUsr.do");
            return modelAndView;
        }

        modelAndView.setViewName("jsonView");

        if (menu.getMenuNo() == null) {
            modelAndView.addObject("resultMsg", egovMessageSource.getMessage("fail.common.update"));

            return modelAndView;
        }

        menuManageService.editMenuManage(menu);

        modelAndView.addObject("resultMsg", egovMessageSource.getMessage("success.common.update"));

        return modelAndView;
    }
}
