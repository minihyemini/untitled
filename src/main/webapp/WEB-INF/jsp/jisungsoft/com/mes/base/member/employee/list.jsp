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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/member/employee/searchForm.jsp"%>
<%@page import="jisungsoft.com.mes.base.mber.MberSttusCode" %>
<%@page import="jisungsoft.com.mes.base.mber.SexdstnCode" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-employee-member" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="member.name"/></th>
                            <th><spring:message code="member.id"/></th>
                            <th><spring:message code="member.email"/></th>
                            <th><spring:message code="member.position.name"/></th>
                            <th>부서명</th>
                            <th>팀명</th>
                            <th>휴대전화번호</th>
                            <th>사무실전화번호</th>
                            <th>성별</th>
                            <th>외국인여부</th>
                            <th>근태체크</th>
                            <th>근태</th>
                            <th>생년월일</th>
                            <th>우편번호</th>
                            <th>주소</th>
                            <th>상세주소</th>
                            <th><spring:message code="member.author"/></th>
                            <th>입사일</th>
                            <th>퇴사일</th>
                            <th><spring:message code="member.joindate"/></th>
                            <th><spring:message code="member.lockat"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${resultList}" varStatus="status">
                            <tr data-id="${result.uniqId}">
                                <td><c:out value="${result.emplyrNm}"/></td>
                                <td><c:out value="${result.emplyrId}"/></td>
                                <td><c:out value="${result.emailAdres}"/></td>
                                <td>
                                    <c:forEach var="code" items="${positionList}" varStatus="status">
                                        <c:if test="${result.ofcpsNm eq code.code}">
                                            <c:out value="${code.codeNm}"/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td><c:out value="${result.orgnztNm}"/></td>
                                <td><c:out value="${result.groupNm}"/></td>
                                <td><c:out value="${result.mbtlnum}"/></td>
                                <td><c:out value="${result.offmTelno}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.sexdstnCode eq SexdstnCode.M.name()}">
                                            <spring:message code="member.male"/>
                                        </c:when>
                                        <c:otherwise>
                                            <spring:message code="member.female"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.foreignerAt eq 'Y'}">
                                            <spring:message code="member.foreigner"/>
                                        </c:when>
                                        <c:otherwise>
                                            <spring:message code="member.local"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${result.attendanceAt}"/></td>
                                <td>
                                    <c:forEach var="statusCode" items="${mberStatusCodes}" varStatus="status">
                                        <c:if test="${result.emplyrSttusCode eq statusCode.code}">
                                            <c:out value="${statusCode.codeNm}"/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>
                                    <fmt:parseDate var="brthdy" value="${result.brthdy}" pattern="yyyyMMdd" />
                                    <fmt:formatDate value="${brthdy}" pattern="yyyy.MM.dd (E)"/>
                                </td>
                                <td><c:out value="${result.zip}"/></td>
                                <td><c:out value="${result.adres}"/></td>
                                <td><c:out value="${result.detailAdres}"/></td>
                                <td>
                                    <c:forEach var="author" items="${authorInfoList}" varStatus="status">
                                        <c:if test="${author.authorCode eq result.authorCode}">
                                            <c:out value="${author.authorNm}"/>
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td><c:out value="${result.joiningDate}"/></td>
                                <td><c:out value="${result.quittingDate}"/></td>
                                <td><c:out value="${result.sbscrbDe}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${result.lockAt eq 'Y'}">
                                            <span class="badge badge-danger"><spring:message code="badge.danger"/></span>
                                        </c:when>
                                        <c:when test="${result.lockAt == null or result.lockAt eq 'N'}">
                                            <span class="badge badge-success"><spring:message code="badge.success"/></span>
                                        </c:when>
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

<%-- 수정 폼--%>
<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/member/employee/form.jsp"%>

<%-- 패스워드 변경--%>
<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/member/employee/passwordEditForm.jsp"%>

<%--상세보기--%>
<%--<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/member/employee/detail.jsp"%>--%>

<!-- Remove confirm Modal center Small -->
<div class="modal fade" id="remove-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="member.title"/></h5>
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
                    <spring:message code="button.delete"/>
                </button>
            </div>
        </div>
    </div>
</div>