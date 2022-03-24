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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/process/pdnRouting/searchForm.jsp"%>
<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-4">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="dt-pdnum" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th><spring:message code="pdnum.numName"/></th>
                                    <th><spring:message code="pdnum.name"/></th>
                                    <th><spring:message code="routingByPdnum.pdnumCnt"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:choose>
                                    <c:when test="${fn:length(resultList) > 0}">
                                        <c:forEach var="result" items="${resultList}" varStatus="status">
                                            <tr data-pdnumid="${result.pdnumId}" data-pdnum="${result.pdnumNum}" data-pdnumnm="${result.pdnumNm}">
                                                <td><c:out value="${result.pdnumNum}"/></td>
                                                <td><c:out value="${result.pdnumNm}"/></td>
                                                <td><c:out value="${result.pbrCnt}"/></td>
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
                        </div>
                    </div>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-8">
        <div id="panel-3" class="panel">
            <%--@elvariable id="pdnumbyroutgForm" type="jisungsoft.com.persistence.dto.mes.Pdnumbyroutg"--%>
            <form:form commandName="pdnumbyroutgForm" name="pdnumbyroutgForm" method="post">
                <form:hidden path="pdnumId"/>

                <div class="panel-hdr">
                    <button class="btn btn-success btn-sm ml-3" type="button" onclick="fn_searchFormData(this);">
                        <spring:message code="button.load"/>
                    </button>
                </div>
                <div class="panel-container show">
                    <div class="panel-content">
                        <div class="row">
                            <div class="form-group row col-md-4">
                                <form:label path="rtCode" cssClass="col-form-label col-12 col-lg-3 form-label text-lg-right">
                                    <spring:message code="routingByPdnum.routing"/>
                                </form:label>
                                <div class="col-12 col-lg-12">
                                    <form:select path="rtCode" cssClass="select2 form-control">
                                        <c:forEach var="code" items="${routingCodeList}" varStatus="status">
                                            <form:option value="${code.code}"><c:out value="${code.codeNm}"/></form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group row col-md-4">
                                <form:label path="pdnumNum" cssClass="col-form-label col-12 col-lg-3 form-label text-lg-right">
                                    <spring:message code="pdnum.numName"/>
                                </form:label>
                                <div class="col-12 col-lg-12">
                                    <form:input path="pdnumNum" cssClass="form-control" disabled="true"/>
                                </div>
                            </div>
                            <div class="form-group row col-md-4">
                                <form:label path="pdnumNm" cssClass="col-form-label col-12 col-lg-3 form-label text-lg-right">
                                    <spring:message code="pdnum.name"/>
                                </form:label>
                                <div class="col-12 col-lg-12">
                                    <form:input path="pdnumNm" cssClass="form-control" disabled="true"/>
                                </div>
                            </div>
                            <div class="form-group row col-md-12">
                                <div class="float-right m-2">
                                    <button class="btn btn-secondary btn-sm" type="button" onclick="fn_fieldAdd('');"><span><i class="fal fa-plus mr-1"></i> <spring:message code="button.field.add"/></span></button>
                                    <button class="btn btn-secondary btn-sm" type="button" onclick="fn_fieldDelete();"><span><i class="fal fa-minus mr-1"></i> <spring:message code="button.field.delete"/></span></button>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <table id="dt-sub-process" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th><div class="custom-control custom-checkbox"><input type="checkbox" class="form-control-input" id="pbr-check-all"></div></th>
                                        <th><spring:message code="process.master.code"/></th>
                                        <th><spring:message code="routingByPdnum.process.order"/></th>
                                        <th><spring:message code="routingByPdnum.performancePoint"/></th>
                                        <th><spring:message code="routingByPdnum.readTime"/></th>
                                        <th><spring:message code="routingByPdnum.sampleReadTime"/></th>
                                        <th><spring:message code="routingByPdnum.desc"/></th>
                                    </tr>
                                    </thead>
                                    <tbody id="pdnum-process"></tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<!-- Edit confirm Modal center Small -->
<div class="modal fade" id="edit-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="process.master.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="save.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="save" onclick="fn_dataAction(this);"><spring:message code="button.save"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Remove confirm Modal center Small -->
<div class="modal fade" id="remove-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="process.master.title"/></h5>
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