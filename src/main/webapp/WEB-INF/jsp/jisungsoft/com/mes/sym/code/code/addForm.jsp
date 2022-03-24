<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="title.create"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('codeAddForm');"><spring:message code="button.init"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_getPage(this);" data-target="list"><spring:message code="button.list"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert"><spring:message code="button.save"/></button>
                </div>
            </div>

            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="codeForm" type="jisungsoft.com.persistence.dto.sym.code.CmmnCode"--%>
                    <form:form commandName="codeForm" name="codeAddForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <div class="col-md-4 mb-3">
                                <form:label path="clCode" cssClass="form-label">
                                    <spring:message code="clcode.id"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="clCode" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="clCode"></form:errors></div>
                            </div>
                            <div class="col-md-4 mt-4">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#clcode-list-modal"><spring:message code="button.search"/></button>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-4 mb-3">
                                <form:label path="codeId" cssClass="form-label">
                                    <spring:message code="code.id"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="codeId" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="codeId"></form:errors></div>
                            </div>
                            <div class="col-md-4 mb-3">
                                <form:label path="codeIdNm" cssClass="form-label">
                                    <spring:message code="code.name"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="codeIdNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="codeIdNm"></form:errors></div>
                            </div>
                            <div class="col-md-4 mb-3">
                                <form:label path="useAt" cssClass="form-label">
                                    <spring:message code="clcode.useat"/>
                                </form:label>
                                <form:select path="useAt" cssClass="form-control">
                                    <form:option value="Y">사용</form:option>
                                    <form:option value="N">미사용</form:option>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="useAt"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-12 mb-3">
                                <form:label path="codeIdDc" cssClass="form-label">
                                    <spring:message code="code.desc"/>
                                </form:label>
                                <form:textarea path="codeIdDc" cssClass="form-control"></form:textarea>
                                <div class="invalid-feedback"><form:errors path="codeIdDc"></form:errors></div>
                            </div>
                        </div>
                        <%----%>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal center Small -->
<div class="modal fade" id="save-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="code.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="save.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="saveAdd" onclick="fn_dataAction(this);"><spring:message code="button.save"/></button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="clcode-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="clcode.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="panel-container show">
                    <div class="panel-content">
                        <form name="searchClCodeForm" onsubmit="fn_searchClCodeData(this); return false;" method="post">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="searchKeyword"><spring:message code="title.search"/></label>
                                    <input type="text" class="form-control" name="searchKeyword" id="searchKeyword">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="searchCondition"><spring:message code="title.searchCondition"/></label>
                                    <select name="searchCondition" class="form-control" name="searchCondition" id="searchCondition">
                                        <option value="0"><spring:message code="title.all"/></option>
                                        <option value="1"><spring:message code="clcode.id"/></option>
                                        <option value="2"><spring:message code="clcode.name"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="submit" class="btn btn-primary ml-auto" data-target="search">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="dataTables_scroll">
                    <table class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead>
                        <tr>
                            <th><spring:message code="clcode.id"/></th>
                            <th><spring:message code="clcode.name"/></th>
                            <th><spring:message code="common.regist.id"/></th>
                            <th><spring:message code="common.regist.date"/></th>
                            <th><spring:message code="common.edit.id"/></th>
                            <th><spring:message code="common.edit.date"/></th>
                        </tr>
                        </thead>
                        <tbody id="clCode-dataTable-body">
                        <c:choose>
                            <c:when test="${fn:length(cmmnClCodeList) > 0}">
                                <c:forEach var="clcode" items="${cmmnClCodeList}" varStatus="status">
                                    <tr onclick="fn_selectClCode(this);" data-id="${clcode.clCode}">
                                        <td><c:out value="${clcode.clCode}"/></td>
                                        <td><c:out value="${clcode.clCodeNm}"/></td>
                                        <td><c:out value="${clcode.frstRegisterUserId}"/></td>
                                        <td><c:out value="${clcode.frstRegistPnttm}"/></td>
                                        <td><c:out value="${clcode.lastUpdusrUserId}"/></td>
                                        <td><c:out value="${clcode.lastUpdtPnttm}"/></td>
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
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>