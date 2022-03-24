<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page import="egovframework.com.cmm.service.Globals" %>

<div class="blankpage-form-field">
    <div class="page-logo m-0 w-100 align-items-center justify-content-center rounded border-bottom-left-radius-0 border-bottom-right-radius-0 px-4">
        <a href="javascript:void(0)" class="page-logo-link press-scale-down d-flex align-items-center">
            <%--            <img src="<c:url value='/images/end_logo.gif'/>" alt="logo" aria-roledescription="logo">--%>
            <span class="page-logo-text mr-1">우성-MES</span>
            <i class="fal fa-angle-down d-inline-block ml-1 fs-lg color-primary-300"></i>
        </a>
    </div>
    <div class="card p-4 border-top-left-radius-0 border-top-right-radius-0">
        <c:url var="loginAction" value='/loginAction.do'/>
        <%--@elvariable id="loginVO" type="jisungsoft.com.persistence.model.LoginVO"--%>
        <form:form commandName="loginVO" name="loginForm" method="post" action="${loginAction}">
            <!-- 관리자/일반 구분 코드 -->
            <form:hidden path="userSe" />

            <div class="form-group">
                <label class="form-label" for="id"><spring:message code="login.name"/></label>
                <form:input path="id" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <span class="invalid-feedback"><form:errors path="id"/></span>
            </div>
            <div class="form-group">
                <label class="form-label" for="password"><spring:message code="login.password"/></label>
                <form:password path="password" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                <span class="invalid-feedback"><form:errors path="password"/></span>
            </div>
            <c:if test="${not empty fn:trim(message) &&  message ne ''}">
                <div class="form-group">
                    <span class="help-block color-danger-900"><c:out value='${message}'/></span>
                </div>
            </c:if>
            <div class="form-group text-left">
                <div class="custom-control custom-switch">
                    <form:checkbox path="autologin" id="autologin" cssClass="custom-control-input" value="Y"/>
                    <form:label path="autologin" cssClass="custom-control-label"><spring:message code="login.remember"/></form:label>
                        <%--                    <input type="checkbox" class="custom-control-input" id="customSwitch1">--%>
                        <%--                    <label class="custom-control-label" for="customSwitch1">Unchecked</label>--%>
                </div>
                <div class="custom-control custom-checkbox">
                        <%--                    <form:checkbox path="autologin" cssClass="custom-control-input" value=""/>--%>
                        <%--                    <form:label path="autologin" cssClass="custom-control-label"><spring:message code="login.remember"/></form:label>--%>
                        <%--                    <input type="checkbox" class="custom-control-input" id="rememberme">--%>
                        <%--                    <label class="custom-control-label" for="autologin"><spring:message code="login.remember"/></label>--%>
                </div>
            </div>
            <button type="submit" class="btn btn-default float-right"><spring:message code="login.signin.button"/></button>
        </form:form>
    </div>
    <div class="blankpage-footer text-center">
        <a href="javascript:alert('서비스 준비중입니다.');"><strong><spring:message code="button.login.ent"/></strong></a>
    </div>
</div>

<%--

<div class="login-footer p-2">
    <div class="row">
        <div class="col col-sm-12 text-center">
            <i><strong>System Message:</strong> You were logged out from 198.164.246.1 on Saturday, March, 2017 at 10.56AM</i>
        </div>
    </div>
</div>
--%>


<video poster="/images/backgrounds/clouds.png" id="bgvid" playsinline autoplay muted loop>
<%--        <source src="media/video/cc.webm" type="video/webm">--%>
<%--        <source src="media/video/cc.mp4" type="video/mp4">--%>
</video>

