<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="menu.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('menuForm');">
                        <spring:message code="button.init"/>
                    </button>
                    <%--
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_getPage(this);" data-target="addForm">
                        <spring:message code="button.newCreate"/>
                    </button>
                    --%>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert">
                        <spring:message code="button.save"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="window.location.reload();">
                        <spring:message code="button.refresh"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>

<div class="row">
    <div class="col-xl-6">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- 트리 -->
                    <div class="tree_wrap">
                        <div id='tree'></div>
                    </div>
                    <!-- //트리 -->
                </div>
            </div>
        </div>
    </div>
    <%@include file="/WEB-INF/jsp/jisungsoft/com/mes/sym/menu/form.jsp" %>
</div>

<!-- Save confirm Modal center Small -->
<div class="modal fade" id="save-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="menu.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="save.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" id="saveBtn" data-target="save" onclick="fn_dataAction(this);">
                    <spring:message code="button.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Remove confirm Modal center Small -->
<div class="modal fade" id="remove-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="menu.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="remove.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="delete" onclick="fn_dataAction(this);">
                    <spring:message code="button.delete"/></button>
            </div>
        </div>
    </div>
</div>

<%--프로그램 팝업--%>
<div class="modal fade" id="menuProgram-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="menuProgram.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="panel-container show">
                    <div class="panel-content">
                        <form name="searchClCodeForm" onsubmit="fn_searchMenuProgramData(this); return false;" method="post">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="searchKeyword"><spring:message code="title.search"/></label>
                                    <input type="text" class="form-control" name="searchKeyword" id="searchKeyword">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="searchCondition"><spring:message code="title.searchCondition"/></label>
                                    <select name="searchCondition" class="form-control" name="searchCondition" id="searchCondition">
                                        <option value="0"><spring:message code="title.all"/></option>
                                        <option value="1"><spring:message code="menuProgram.nameKorean"/></option>
                                        <option value="2"><spring:message code="menuProgram.desc"/></option>
                                        <option value="4"><spring:message code="menuProgram.name"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="submit" class="btn btn-primary ml-auto" data-target="search">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>

                        <div class="dataTables-scroll">
                            <table class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                                <thead>
                                <tr>
                                    <th><spring:message code="menuProgram.name"/></th>
                                    <th><spring:message code="menuProgram.nameKorean"/></th>
                                    <th><spring:message code="menuProgram.desc"/></th>
                                    <th><spring:message code="menuProgram.type"/></th>
                                    <th><spring:message code="menuProgram.url"/></th>
                                </tr>
                                </thead>
                                <tbody id="menuProgram-dataTable-body">
                                <c:choose>
                                    <c:when test="${fn:length(programList) > 0}">
                                        <c:forEach var="program" items="${programList}" varStatus="status">
                                            <tr onclick="fn_selectSubTableList(this);" data-id="${program.progrmId}" data-name="${program.progrmFileNm}">
                                                <td><c:out value="${program.progrmFileNm}"/></td>
                                                <td><c:out value="${program.progrmKoreanNm}"/></td>
                                                <td><c:out value="${program.progrmDc}"/></td>
                                                <td><c:out value="${program.progrmCode}"/></td>
                                                <td><c:out value="${program.url}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr><td colspan="7"><spring:message code="info.nodata.msg"/></td></tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>