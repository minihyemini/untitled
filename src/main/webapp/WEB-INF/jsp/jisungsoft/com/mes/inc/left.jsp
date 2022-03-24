<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>

<t:importAttribute name="menuData"/>
<t:importAttribute name="menuDepth1" />
<t:importAttribute name="menuDepth2" />
<t:importAttribute name="menuDepth3" />
<t:importAttribute name="menuDepth4" />


<!-- BEGIN Left Aside -->
<aside class="page-sidebar">
    <div class="page-logo">
        <a href="<c:url value='/'/>" class="page-logo-link press-scale-down d-flex align-items-center position-relative">
            <span class="page-logo-text mr-1">인하풀로지 MES</span>
            <span class="position-absolute text-white opacity-50 small pos-top pos-right mr-2 mt-n2"></span>
            <i class="fal fa-angle-down d-inline-block ml-1 fs-lg color-primary-300"></i>
        </a>
    </div>
    <!-- BEGIN PRIMARY NAVIGATION -->
    <nav id="js-primary-nav" class="primary-nav" role="navigation">
        <div class="nav-filter">
            <div class="position-relative">
                <input type="text" id="nav_filter_input" placeholder="Filter menu" class="form-control" tabindex="0">
                <a href="#" onclick="return false;" class="btn-primary btn-search-close js-waves-off" data-action="toggle" data-class="list-filter-active" data-target=".page-sidebar">
                    <i class="fal fa-chevron-up"></i>
                </a>
            </div>
        </div>
        <!--
        <div class="info-card">
            <img th:src="@{/img/demo/avatars/avatar-admin.png}" src="#" class="profile-image rounded-circle" alt="Dr. Codex Lantern">
            <div class="info-card-text">
                <a href="#" class="d-flex align-items-center text-white">
                            <span class="text-truncate text-truncate-sm d-inline-block">
                                Dr. Codex Lantern
                            </span>
                </a>
                <span class="d-inline-block text-truncate text-truncate-sm">Toronto, Canada</span>
            </div>
            <img th:src="@{/img/card-backgrounds/cover-2-lg.png}" src="#" class="cover" alt="cover">
            <a href="#" onclick="return false;" class="pull-trigger-btn" data-action="toggle" data-class="list-filter-active" data-target=".page-sidebar" data-focus="nav_filter_input">
                <i class="fal fa-angle-down"></i>
            </a>
        </div>
        -->
        <ul id="js-nav-menu" class="nav-menu">
            <c:forEach var="depth1" items="${menuDepth1}" varStatus="status">
                <c:set var="collapsed" value=""/>
                <c:if test="${depth1.menuNo eq menuData.upperMenuNo or depth1.menuNo eq menuData.parentMenuNo  or depth1.url eq menuData.url}">
                    <c:set var="collapsed" value="active open"/>
                </c:if>

                <li class="${collapsed}">
                    <a href="javascript:void(0);">
                        <c:if test="${depth1.relateImageNm != null}">
                            <i class="${depth1.relateImageNm}"></i>
                        </c:if>
                        <span class="nav-link-text" data-i18n="nav.application_intel"><c:out value="${depth1.menuNm}"/></span>
                    </a>
                    <ul>
                        <c:forEach var="depth2" items="${menuDepth2}" varStatus="status2">
                            <c:set var="collapsed" value=""/>
                            <c:choose>
                                <c:when test="${depth2.url eq menuData.url}">
                                    <c:set var="collapsed" value="active"/>
                                </c:when>
                                <c:when test="${depth2.menuNo eq menuData.upperMenuNo}">
                                    <c:set var="collapsed" value="active open"/>
                                </c:when>
                            </c:choose>
                            <c:if test="${depth2.upperMenuNo eq depth1.menuNo}">
                                <li class="${collapsed}">
                                    <a href="javascript:void(0);"  data-toggle="collapse" onclick="fn_menuAction(this);" data-query="${depth2.progrmQuery}" data-id="${depth2.menuNo}" data-upper="${depth2.upperMenuNo}" data-url="<c:url value='${depth2.url}'/>" data-target="${depth2.targetAt}">
                                        <span class="nav-link-text" data-i18n="nav.application_intel_analytics_dashboard"><c:out value="${depth2.menuNm}"/></span>
                                    </a>
                                    <c:if test="${depth2.url eq '/'}">
                                        <ul>
                                            <c:forEach var="depth3" items="${menuDepth3}" varStatus="status">
                                                <c:set var="collapsed" value=""/>
                                                <c:if test="${depth3.url eq menuData.url}">
                                                    <c:set var="collapsed" value="active"/>
                                                </c:if>
                                                <c:if test="${depth3.upperMenuNo eq depth2.menuNo}">
                                                    <li class="${collapsed}">
                                                        <a href="javascript:void(0);"  data-toggle="collapse" onclick="fn_menuAction(this);" data-query="${depth3.progrmQuery}" data-id="${depth3.menuNo}" data-upper="${depth3.upperMenuNo}" data-url="<c:url value='${depth3.url}'/>" data-target="${depth3.targetAt}">
                                                            <span class="nav-link-text" data-i18n="nav.application_intel_analytics_dashboard">
                                                                <c:out value="${depth3.menuNm}"/>
                                                            </span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
        <div class="filter-message js-filter-message bg-success-600"></div>
    </nav>
    <!-- END PRIMARY NAVIGATION -->
    <!-- NAV FOOTER -->
    <!--
    <div class="nav-footer shadow-top">
        <a href="#" onclick="return false;" data-action="toggle" data-class="nav-function-minify" class="hidden-md-down">
            <i class="ni ni-chevron-right"></i>
            <i class="ni ni-chevron-right"></i>
        </a>
        <ul class="list-table m-auto nav-footer-buttons">
            <li>
                <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="Chat logs">
                    <i class="fal fa-comments"></i>
                </a>
            </li>
            <li>
                <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="Support Chat">
                    <i class="fal fa-life-ring"></i>
                </a>
            </li>
            <li>
                <a href="javascript:void(0);" data-toggle="tooltip" data-placement="top" title="Make a call">
                    <i class="fal fa-phone"></i>
                </a>
            </li>
        </ul>
    </div>
    -->
    <!-- END NAV FOOTER -->
</aside>
<!-- END Left Aside -->