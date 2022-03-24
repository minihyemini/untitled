<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오전 11:22
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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/mat/buy/oder/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="buy.order.orderNum"/></th>
                            <th><spring:message code="sales.receive.order.clientName"/></th>
<%--                            <th><spring:message code="sales.receive.order.category"/></th>--%>
                            <th><spring:message code="buy.order.orderDate"/></th>
                            <th>납기일자</th>
                            <th><spring:message code="common.status"/></th>
                            <th><spring:message code="buy.order.quantity"/></th>
                            <th><spring:message code="buy.order.vat"/></th>
                            <th><spring:message code="buy.order.salesTotal"/></th>
                            <th><spring:message code="common.regist.date"/></th>
                            <th><spring:message code="common.regist.id"/></th>
                            <th><spring:message code="common.edit.date"/></th>
                            <th><spring:message code="common.edit.id"/></th>
                            <th><spring:message code="buy.order.description"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultData}" varStatus="status">
                            <tr data-id="${result.ordId}" data-clt="${result.cltId}">
                                <td><c:out value="${result.ordNum}"/></td>
                                <td><c:out value="${result.cltNm}"/></td>
<%--                                <td><c:out value="${result.ordCategoryNm}"/></td>--%>
                                <td><c:out value="${result.orderDate}"/></td>
                                <td><c:out value="${result.ordDuedate}"/></td>
                                <td><c:out value="${result.ordStatusNm}"/></td>
                                <td><c:out value="${result.qunty}"/></td>
                                <td><fmt:formatNumber value="${result.totSurtax}" pattern="#,###"/></td>
                                <td><fmt:formatNumber value="${result.totPrice}" pattern="#,###"/></td>
                                <td><c:out value="${result.frstRegistPnttm}"/></td>
                                <td><c:out value="${result.frstRegisterUserId}"/></td>
                                <td><c:out value="${result.lastUpdtPnttm}"/></td>
                                <td><c:out value="${result.lastUpdusrUserId}"/></td>
                                <td><c:out value="${result.ordDesc}"/></td>
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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/mat/buy/oder/form.jsp" %>