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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/pdnum/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="pdnum.numName"/></th>
                            <th><spring:message code="pdnum.name"/></th>
                            <th><spring:message code="pdnum.type"/></th>
                            <th><spring:message code="pdnum.weight"/></th>
                            <th><spring:message code="pdnum.standard"/></th>
                            <th><spring:message code="pdnum.safeStock"/></th>
                            <th><spring:message code="pdnum.code"/></th>
                            <th><spring:message code="pdnum.materialCode"/></th>
                            <th><spring:message code="pdnum.inOutType"/></th>
                            <th><spring:message code="pdnum.drowApproval"/></th>
                            <th><spring:message code="pdnum.drowQntity"/></th>
                            <th><spring:message code="pdnum.unit"/></th>
                            <th><spring:message code="common.regist.id"/></th>
                            <th><spring:message code="common.regist.date"/></th>
                            <th><spring:message code="common.edit.id"/></th>
                            <th><spring:message code="common.edit.date"/></th>
                            <th><spring:message code="common.useAt"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr data-id="${result.pdnumId}">
                                <td><c:out value="${result.pdnumNum}"/></td>
                                <td><c:out value="${result.pdnumNm}"/></td>
                                <td><c:out value="${result.pdnumTypeNm}"/></td>
                                <td><c:out value="${result.pdnumWght}"/></td>
                                <td><c:out value="${result.pdnumStnd}"/></td>
                                <td><c:out value="${result.pdnumSfstck}"/></td>
                                <td><c:out value="${result.pdnumCode}"/></td>
                                <td><c:out value="${result.pdnumMtrscode}"/></td>
                                <td><c:out value="${result.pdnumIotypeNm}"/></td>
                                <td>
                                    <fmt:parseDate var="parseRegDate" value="${result.pdnumDrwappldate}" pattern="yyyy-MM-dd"/>
                                    <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td><c:out value="${result.pdnumDrwqnty}"/></td>
                                <td><c:out value="${result.pdnumUnit}"/></td>
                                <td><c:out value="${result.frstRegisterUserId}"/></td>
                                <td>
                                    <fmt:parseDate var="parseRegDate" value="${result.frstRegistPnttm}" pattern="yyyy-MM-dd"/>
                                    <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td><c:out value="${result.lastUpdusrUserId}"/></td>
                                <td>
                                    <fmt:parseDate var="parseRegDate" value="${result.lastUpdtPnttm}" pattern="yyyy-MM-dd"/>
                                    <fmt:formatDate value="${parseRegDate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.useAt eq 'Y'}">
                                            <button type="button" class="btn btn-xs btn-primary waves-effect waves-themed"><spring:message code="button.use"/></button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-xs btn-danger waves-effect waves-themed"><spring:message code="button.unused"/></button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/pdnum/form.jsp" %>