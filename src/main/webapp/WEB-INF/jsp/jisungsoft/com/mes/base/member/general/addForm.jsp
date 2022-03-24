<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-30
  Time: 오후 2:18
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
                <h2>등록</h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset(this);" data-type="addForm"><spring:message code="button.init"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_pageList(this);" data-type="list"><spring:message code="button.list"/></button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_dataAction(this);" data-type="add"><spring:message code="button.save"/></button>
                </div>
            </div>

            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="generalMemberAddForm" type="jisungsoft.com.mes.base.mber.form.GeneralMemberAddForm"--%>
                    <form:form commandName="generalMemberAddForm" name="generalMemberAddForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <form:label path="name" cssClass="form-label">
                                        <spring:message code="member.name"/>
                                        <span class="text-danger">*</span>
                                    </form:label>
                                    <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                                    <div class="invalid-feedback"><form:errors path="name"></form:errors></div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <form:label path="id" cssClass="form-label">
                                        <spring:message code="member.id"/>
                                        <span class="text-danger">*</span>
                                    </form:label>
                                    <form:input path="id" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                    <div class="invalid-feedback"><form:errors path="id"></form:errors></div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <form:label path="email" cssClass="form-label">
                                        <spring:message code="member.form.email"/>
<%--                                        <span class="text-danger">*</span>--%>
                                    </form:label>
                                    <form:input type="email" path="email" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="30"/>
                                    <div class="invalid-feedback"><form:errors path="email"></form:errors></div>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-md-6 mb-3">
                                    <form:label path="password" cssClass="form-label">
                                        <spring:message code="member.password"/>
                                        <span class="text-danger">*</span>
                                    </form:label>
                                    <form:password path="password" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                    <div class="invalid-feedback"><form:errors path="password"></form:errors></div>
                                    <div class="valid-feedback"><spring:message code="invalid.password"/></div>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <form:label path="rePassword" cssClass="form-label">
                                        <spring:message code="member.repassword"/>
                                        <span class="text-danger">*</span>
                                    </form:label>
                                    <form:password path="rePassword" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                    <div class="invalid-feedback"><form:errors path="rePassword"></form:errors></div>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-md-6 mb-3 ">
                                    <label class="form-label"><spring:message code="member.birthday"/></label>
                                    <div class="input-group">
                                        <form:input path="birthday" cssClass="form-control" placeholder="Select date" id="datepicker" readonly="true"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text fs-xl">
                                                <i class="fal fa-calendar"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-md-6 mb-3 ">
                                    <form:label path="mobileNum" cssClass="form-label"><spring:message code="member.mobile"/></form:label>
                                    <form:input path="mobileNum" data-inputmask="'mask': '999-9999-9999'" class="form-control"/>
                                    <span class="help-block">010-9999-9999</span>
                                </div>
                                <div class="col-md-6 mb-3 ">
                                    <form:label path="offmTelno" cssClass="form-label"><spring:message code="member.tel"/></form:label>
                                    <form:input path="offmTelno" data-inputmask="'mask': '999-9999-9999'" class="form-control"/>
                                    <span class="help-block">(999) 999-9999</span>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-md-3 mb-3">
                                    <form:label path="zip" cssClass="form-label">
                                        <spring:message code="member.zip"/>
                                    </form:label>
                                    <form:input path="zip" cssClass="form-control" readonly="true"/>
                                    <div class="invalid-feedback"><form:errors path="zip"></form:errors></div>
                                </div>
                                <div class="col-md-1 mt-4">
                                    <button type="button" class="btn btn-outline-default waves-effect waves-themed" onclick="execDaumPostcode();"><spring:message code="button.find.address"/></button>
                                </div>
                                <div class="col-md-8 mb-3"></div>
                                <div class="col-md-6 mb-3">
                                    <form:label path="address" cssClass="form-label">
                                        <spring:message code="member.address"/>
                                    </form:label>
                                    <form:input path="address" cssClass="form-control" readonly="true"/>
                                    <div class="invalid-feedback"><form:errors path="address"></form:errors></div>
                                </div>
                                <div class="col-md-10 mb-3">
                                    <form:label path="detailAddress" cssClass="form-label">
                                        <spring:message code="member.address.detail"/>
                                    </form:label>
                                    <form:input path="detailAddress" cssClass="form-control"/>
                                    <div class="invalid-feedback"><form:errors path="detailAddress"></form:errors></div>
                                </div>
                            </div>
                            <div class="form-row form-group">

                            </div>
                            <div class="form-row form-group">

                            </div>
                        </div>
                        <%----%>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>