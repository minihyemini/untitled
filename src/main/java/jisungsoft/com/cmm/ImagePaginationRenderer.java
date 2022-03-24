package jisungsoft.com.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.ServletContext;

public class ImagePaginationRenderer extends AbstractPaginationRenderer {

	private ServletContext servletContext;
	private boolean prev;
	private boolean next;
	private int totalPageCount;
	private int pageSize;
	private int currentPageNo;
	
	public ImagePaginationRenderer() {
	
	}

	/** 홈페이지 페이징 */
	public void initVariables(){
		if(prev) {
			firstPageLabel = "<li><a class=\"page-item first\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" tabindex=\"0\">처음</a></li>";
			previousPageLabel = "<li><a class=\"page-item prev\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\" tabindex=\"0\">이전</a></li>";
		} else {
			firstPageLabel = "<li><a class=\"page-item first disabled\" href=\"#\" tabindex=\"-1\">처음</a></li>";
			previousPageLabel = "<li><a class=\"page-item prev disabled\" href=\"#\" tabindex=\"-1\">이전</a></li>";
		}

		currentPageLabel = "<li><a class=\"page-item active\" href=\"#\">{0}</a></li>";
		otherPageLabel = "<li><a class=\"page-item\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">{2}</a></li>";

		if(next) {
			nextPageLabel = "<li><a class=\"page-item next\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">다음</a></li>";
			lastPageLabel = "<li><a class=\"page-item last\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">마지막</a></li>";
		} else {
			nextPageLabel = "<li><a class=\"page-item next disabled\" href=\"#\">다음</a></li>";
			lastPageLabel = "<li><a class=\"page-item last disabled\" href=\"#\">마지막</a></li>";
		}
	}

	/**
	 * CMS 페이징
	 */
	public void cmsInitVariables() {
		if(prev) {
			firstPageLabel = "<div class=\"page_control_prev\"><a href=\"#\" class=\"page_number first\" tabindex=\"0\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">처음</a>";
			previousPageLabel = "<a href=\"#\" class=\"page_number prev\" tabindex=\"0\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">이전</a></div>";
		} else {
			firstPageLabel = "<div class=\"page_control_prev\"><a href=\"#\" class=\"page_number first\" tabindex=\"0\" href=\"#\">처음</a>";
			previousPageLabel = "<a href=\"#\" class=\"page_number prev\" tabindex=\"0\" href=\"#\">이전</a></div>";
		}
		currentPageLabel = "<span class=\"page_number current\" tabindex=\"0\" href=\"#\">{0}</span>";
		otherPageLabel = "<a href=\"\" class=\"page_number\" tabindex=\"0\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">{2}</a>";
		if(next) {
			nextPageLabel = "<div class=\"page_control_next\"><a href=\"#\" class=\"page_number next\" tabindex=\"0\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">다음</a>";
			lastPageLabel = "<a href=\"#\" class=\"page_number last\" tabindex=\"0\" href=\"?pageIndex={1}\" onclick=\"{0}({1});return false;\">마지막</a></div>";
		} else {
			nextPageLabel = "<a href=\"#\" class=\"page_number next\" tabindex=\"0\" href=\"#\">다음</a>";
			lastPageLabel = "<a href=\"#\" class=\"page_number last\" tabindex=\"0\" href=\"#\">마지막</a>";
		}
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		pageSize = paginationInfo.getPageSize();
		currentPageNo = paginationInfo.getCurrentPageNo();
		totalPageCount = paginationInfo.getTotalPageCount();

		prev = currentPageNo > pageSize ? true : false;
		next = currentPageNo < totalPageCount ? true : false;

		if (jsFunction.indexOf("cms") > 0) {
			cmsInitVariables();
		} else {
			initVariables();
		}

		return super.renderPagination(paginationInfo, jsFunction);
	}

}
