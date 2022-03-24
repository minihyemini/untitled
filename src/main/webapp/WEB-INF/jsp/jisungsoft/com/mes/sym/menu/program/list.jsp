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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/sym/menu/program/searchForm.jsp" %>

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
                            <th><spring:message code="menuProgram.name"/></th>
                            <th><spring:message code="menuProgram.nameKorean"/></th>
                            <th><spring:message code="menuProgram.desc"/></th>
                            <th><spring:message code="menuProgram.type"/></th>
                            <th><spring:message code="menuProgram.url"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(resultList) > 0}">
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr data-id="${result.progrmId}">
                                        <td><c:out value="${result.progrmFileNm}"/></td>
                                        <td><c:out value="${result.progrmKoreanNm}"/></td>
                                        <td><c:out value="${result.progrmDc}"/></td>
                                        <td><c:out value="${result.progrmCode}"/></td>
                                        <td><c:out value="${result.url}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="8">
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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/sym/menu/program/form.jsp" %>