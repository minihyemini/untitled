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
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('clCodeAddForm');"><spring:message code="button.init"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_getPage(this);" data-target="list"><spring:message code="button.list"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert"><spring:message code="button.save"/></button>
                </div>
            </div>

            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="clCodeForm" type="jisungsoft.com.persistence.dto.sym.code.CmmnClCode"--%>
                    <form:form commandName="clCodeForm" name="clCodeAddForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <div class="col-md-4 mb-3">
                                <form:label path="clCode" cssClass="form-label">
                                    <spring:message code="clcode.id"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="clCode" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="clCode"></form:errors></div>
                            </div>
                            <div class="col-md-4 mb-3">
                                <form:label path="clCodeNm" cssClass="form-label">
                                    <spring:message code="clcode.name"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="clCodeNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="clCodeNm"></form:errors></div>
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
                                <form:label path="clCodeDc" cssClass="form-label">
                                    <spring:message code="clcode.desc"/>
                                </form:label>
                                <form:textarea path="clCodeDc" cssClass="form-control"></form:textarea>
                                <div class="invalid-feedback"><form:errors path="clCodeDc"></form:errors></div>
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
                <h5 class="modal-title"><spring:message code="clcode.title"/></h5>
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