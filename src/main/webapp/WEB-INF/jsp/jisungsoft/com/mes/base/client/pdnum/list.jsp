<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-25
  Time: 오후 1:49
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
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="client.pdnum.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('clientPdNumForm');">
                        <spring:message code="button.init"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert">
                        <spring:message code="button.save"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="window.location.reload();">
                        <spring:message code="button.refresh"/>
                    </button>
                    <button type="button" class="btn btn-primary ml-auto" onclick="fn_getPage(this);" data-target="search">
                        <i class="fal fa-search"></i>
                    </button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Client"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right">
                                    <spring:message code="title.search"/>
                                </form:label>
                                <div class="col-2 input-group input-group-sm">
                                    <form:input path="searchKeyword" cssClass="form-control"></form:input>
                                </div>

                                <form:label path="searchCondition" cssClass="col-form-label col-1 form-label text-lg-right">
                                    <spring:message code="title.searchCondition"/>
                                </form:label>
                                <div class="col-2 input-group input-group-sm">
                                    <form:select path="searchCondition" cssClass="form-control">
                                        <form:option value="0"><spring:message code="title.all"/></form:option>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-6">
        <div id="panel-2" class="panel">
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
                            <th>적용수</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(resultList) > 0}">
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr data-clt="${result.cltId}">
                                        <td><c:out value="${result.cltNm}"/></td>
                                        <td><c:out value="${result.cltOwnrnm}"/></td>
                                        <td><c:out value="${result.cltBusstype}"/></td>
                                        <td><c:out value="${result.cltBussnum}"/></td>
                                        <td><c:out value="${result.cltCprtnum}"/></td>
                                        <td><c:out value="${result.pdnumClientCnt}"/></td>
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

    <%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/client/pdnum/form.jsp"%>
</div>
<%-- //테이블 데이터--%>