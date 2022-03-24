<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Modal center Large -->
<div class="modal fade" id="changepasswd-modal-lg-center" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <%--@elvariable id="memberForm" type="jisungsoft.com.mes.base.mber.form.MemberForm"--%>
            <form:form commandName="memberForm" name="passwordEditForm" id="passwordEditForm" method="post">
                <form:hidden path="uniqId"/>
                <form:hidden path="id"/>

                <div class="modal-header">
                    <h5 class="modal-title">
                        <spring:message code="member.list.title.password"/>
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"><i class="fal fa-times"></i></span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="panel-content">
                        <div class="form-row form-group">
                            <div class="col-md-6 mb-3">
                                <form:label path="password" cssClass="form-label">
                                    <spring:message code="member.password"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:password path="password" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="password"/></div>
                                <div class="valid-feedback"><spring:message code="invalid.password"/></div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <form:label path="rePassword" cssClass="form-label">
                                    <spring:message code="member.repassword"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:password path="rePassword" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="rePassword"/></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
                    <button type="button" class="btn btn-primary" data-target="savePasswd" onclick="fn_editPasswdAction();"><spring:message code="button.save"/></button>
                </div>
            </form:form>
        </div>
    </div>
</div>
