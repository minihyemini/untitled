<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>

<!-- BEGIN Page Header -->
<header class="page-header" role="banner">
    <!-- we need this logo when user switches to nav-function-top -->
    <div class="page-logo">
        <a href="#" class="page-logo-link press-scale-down d-flex align-items-center position-relative" data-toggle="modal" data-target="#modal-shortcut">
            <img src="/images/logo.jpg" alt="SmartAdmin WebApp" aria-roledescription="logo">
            <span class="page-logo-text mr-1">SmartAdmin WebApp</span>
            <span class="position-absolute text-white opacity-50 small pos-top pos-right mr-2 mt-n2"></span>
            <i class="fal fa-angle-down d-inline-block ml-1 fs-lg color-primary-300"></i>
        </a>
    </div>
    <!-- DOC: nav menu layout change shortcut -->
    <div class="hidden-md-down dropdown-icon-menu position-relative">
        <a href="#" class="header-btn btn js-waves-off" data-action="toggle" data-class="nav-function-hidden" title="Hide Navigation">
            <i class="ni ni-menu"></i>
        </a>
        <ul>
            <li>
                <a href="#" class="btn js-waves-off" data-action="toggle" data-class="nav-function-minify" title="Minify Navigation">
                    <i class="ni ni-minify-nav"></i>
                </a>
            </li>
            <li>
                <a href="#" class="btn js-waves-off" data-action="toggle" data-class="nav-function-fixed" title="Lock Navigation">
                    <i class="ni ni-lock-nav"></i>
                </a>
            </li>
        </ul>
    </div>
    <!-- DOC: mobile button appears during mobile width -->
    <div class="hidden-lg-up">
        <a href="#" class="header-btn btn press-scale-down" data-action="toggle" data-class="mobile-nav-on">
            <i class="ni ni-menu"></i>
        </a>
    </div>
    <!--
            <div class="search">
                <form class="app-forms hidden-xs-down" role="search" action="page_search.html" autocomplete="off">
                    <input type="text" id="search-field" placeholder="Search for anything" class="form-control" tabindex="1">
                    <a href="#" onclick="return false;" class="btn-danger btn-search-close js-waves-off d-none" data-action="toggle" data-class="mobile-search-on">
                        <i class="fal fa-times"></i>
                    </a>
                </form>
            </div>
    -->
    <div class="info-card-text text-right">
        <div class="fs-lg text-truncate text-truncate-lg">
            <c:out value="${authenticatedUser.name}"/>
        </div>
    </div>
    <div class="ml-auto d-flex">
        <!-- activate app search icon (mobile) -->
        <%--
        <div class="hidden-sm-up">
            <a href="#" class="header-icon" data-action="toggle" data-class="mobile-search-on" data-focus="search-field" title="Search">
                <i class="fal fa-search"></i>
            </a>
        </div>
        --%>
        <!-- app settings -->
        <%--
        <div class="hidden-md-down">
            <a href="#" class="header-icon">
                <i class="fal fa-cog"></i>
            </a>
        </div>
--%>
        <!-- app user menu -->
        <div>
            <a href="#" data-toggle="dropdown" title="drlantern@gotbootstrap.com" class="header-icon d-flex align-items-center justify-content-center ml-2">
                <i class="fal fa-user"></i>
                <!--                    <img th:src="@{/img/demo/avatars/avatar-admin.png}" src="#" class="profile-image rounded-circle" alt="Dr. Codex Lantern">-->
            </a>
            <div class="dropdown-menu dropdown-menu-animated dropdown-lg">
                <div class="dropdown-divider m-0"></div>
                <%--
                <a href="#" class="dropdown-item">
                    <span data-i18n="drpdwn.settings">Settings</span>
                </a>
                --%>
                <div class="dropdown-divider m-0"></div>
                <a href="#" class="dropdown-item" data-action="app-fullscreen">
                    <span data-i18n="drpdwn.fullscreen"><spring:message code="button.fullscreen"/></span>
                    <i class="float-right text-muted fw-n">F11</i>
                </a>
                <a href="#" class="dropdown-item" data-action="app-print">
                    <span data-i18n="drpdwn.print"><spring:message code="button.print"/></span>
                    <i class="float-right text-muted fw-n">Ctrl + P</i>
                </a>
                <div class="dropdown-multilevel dropdown-multilevel-left">
                    <div class="dropdown-item">
                        <spring:message code="button.language"/>
                    </div>
                    <div class="dropdown-menu">
                        <a href="javascript:void(0);" onclick="fn_localeAction(this);" class="dropdown-item active" data-action="lang" data-lang="en">English (US)</a>
                        <a href="javascript:void(0);" onclick="fn_localeAction(this);" class="dropdown-item active" data-action="lang" data-lang="ko">한국어</a>
                    </div>
                </div>
                <div class="dropdown-divider m-0"></div>
                <a class="dropdown-item fw-500 pt-3 pb-3" href="javascript:void(0);" onclick="actionLogout();">
                    <span data-i18n="drpdwn.page-logout">Logout</span>
                    <span class="float-right fw-n">&commat;<c:out value="${authenticatedUser.name}"/></span>
                </a>
            </div>
        </div>
    </div>
</header>
<!-- END Page Header -->

<!--

<ol class="breadcrumb page-breadcrumb" th:fragment="frg-breadcrumb">
<li class="breadcrumb-item"><a href="javascript:void(0);">SmartAdmin</a></li>
<li class="breadcrumb-item">category_1</li>
<li class="breadcrumb-item">category_2</li>
<li class="breadcrumb-item active">Page Titile</li>
<li class="position-absolute pos-top pos-right d-none d-sm-block"><span class="js-get-date"></span></li>
</ol>

<div class="subheader" th:fragment="subheader">
<h1 class="subheader-title">
<i class='subheader-icon fal fa-'></i> Page <span class='fw-300'>Title</span> <sup class='badge badge-primary fw-500'>ADDON</sup>
<small>
blank description
</small>
</h1>
<div class="subheader-block">
Right content of header
</div>
</div>
-->