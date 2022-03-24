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

<%@ page import="jisungsoft.com.mes.base.client.ClientType" %>
<%@ include file="/WEB-INF/jsp/jisungsoft/com/mes/base/client/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-client" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="client.name"/></th>
                            <th><spring:message code="client.ownerName"/></th>
                            <th><spring:message code="client.bussType"/></th>
                            <th><spring:message code="client.bussNum"/></th>
                            <th><spring:message code="client.corporationNum"/></th>
                            <th><spring:message code="client.zip"/></th>
                            <th><spring:message code="client.address"/></th>
                            <th><spring:message code="client.telNum"/></th>
                            <th><spring:message code="client.faxNum"/></th>
                            <th><spring:message code="client.email"/></th>
                            <th><spring:message code="client.setupDate"/></th>
                            <th><spring:message code="client.transactionBeginDate"/></th>
                            <th><spring:message code="client.transactionEndDate"/></th>
                            <th><spring:message code="client.deadLineDate"/></th>
<%--                            <th><spring:message code="title.etc"/></th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(resultList) > 0}">
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr data-id="${result.cltId}">
                                        <td><c:out value="${result.cltNm}"/></td>
                                        <td><c:out value="${result.cltOwnrnm}"/></td>
                                        <td><c:out value="${result.cltBusstype}"/></td>
                                        <td><c:out value="${result.cltBussnum}"/></td>
                                        <td><c:out value="${result.cltCprtnum}"/></td>
                                        <td><c:out value="${result.cltZip}"/></td>
                                        <td><c:out value="${result.cltAddr}"/></td>
                                        <td><c:out value="${result.cltTelno}"/></td>
                                        <td><c:out value="${result.cltFaxnum}"/></td>
                                        <td><c:out value="${result.cltEmail}"/></td>
                                        <td>
                                            <fmt:parseDate var="parseRegDate" value="${result.cltSetupdt}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td>
                                            <fmt:parseDate var="parseRegDate" value="${result.cltDlbegindt}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td>
                                            <fmt:parseDate var="parseRegDate" value="${result.cltDlendt}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <td>
                                            <fmt:parseDate var="parseRegDate" value="${result.cltDeadlndt}" pattern="yyyy-MM-dd"/>
                                            <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                        </td>
                                        <%--
                                        <td>
                                            <button type="button" class="btn btn-xs btn-danger waves-effect waves-themed"
                                                    data-toggle="modal" data-target="#remove-modal-alert"
                                                    onclick="fn_getEditFormData(this);"
                                                    data-id="${result.cltId}">
                                                <spring:message code="button.delete"/>
                                            </button>
                                        </td>
                                        --%>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="15">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/client/form.jsp" %>