<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/sym/code/detail/searchForm.jsp"%>
<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th><spring:message code="code.id"/></th>
                                    <th><spring:message code="code.name"/></th>
                                    <th><spring:message code="detailCode.id"/></th>
                                    <th><spring:message code="detailCode.name"/></th>
                                    <th><spring:message code="common.regist.id"/></th>
                                    <th><spring:message code="common.regist.date"/></th>
                                    <th><spring:message code="common.edit.id"/></th>
                                    <th><spring:message code="common.edit.date"/></th>
                                    <th><spring:message code="common.useAt"/></th>
                                    <th><spring:message code="title.etc"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(resultList) > 0}">
                                        <c:forEach var="result" items="${resultList}" varStatus="status">
                                            <tr onclick="fn_getEditFormData(this);" data-id="${result.code}">
                                                <td><c:out value="${result.codeId}"/></td>
                                                <td><c:out value="${result.codeIdNm}"/></td>
                                                <td><c:out value="${result.code}"/></td>
                                                <td><c:out value="${result.codeNm}"/></td>
                                                <td><c:out value="${result.frstRegisterUserId}"/></td>
                                                <td><c:out value="${result.frstRegistPnttm}"/></td>
                                                <td><c:out value="${result.lastUpdusrUserId}"/></td>
                                                <td><c:out value="${result.lastUpdtPnttm}"/></td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${result.useAt eq 'Y'}">
                                                            <span class="badge badge-success"><spring:message code="badge.use"/></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge badge-danger"><spring:message code="badge.unused"/></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-danger waves-effect waves-themed" data-toggle="modal" data-target="#remove-modal-alert" onclick="fn_getEditFormData(this);" data-id="${result.code}">
                                                        <spring:message code="button.delete"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td colspan="10">
                                                <div class="text-center">
                                                    <spring:message code="info.nodata.msg"/>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%@ include file="/WEB-INF/jsp/jisungsoft/com/mes/sym/code/detail/editForm.jsp"%>

<!-- Edit confirm Modal center Small -->
<div class="modal fade" id="edit-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="detailCode.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="edit.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="saveEdit" onclick="fn_dataAction(this);"><spring:message code="button.save"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Remove confirm Modal center Small -->
<div class="modal fade" id="remove-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="code.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="remove.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="delete" onclick="fn_dataAction(this);"><spring:message code="button.delete"/></button>
            </div>
        </div>
    </div>
</div>