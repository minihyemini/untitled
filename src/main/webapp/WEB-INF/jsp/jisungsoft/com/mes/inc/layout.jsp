<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="egovframework.com.cmm.service.Globals" %>

<t:importAttribute name="menuData"/>
<%--

<t:importAttribute name="viewPath" />
<t:importAttribute name="pageName" />
--%>
<c:set var="contentType"/>
<c:choose>
    <c:when test="${menuData.url eq Globals.GNR_LOGIN_PAGE}">
        <c:set var="contentType" value="login"/>
    </c:when>
    <c:when test="${menuData.url eq Globals.USR_LOGIN_PAGE}">
        <c:set var="contentType" value="login"/>
    </c:when>
    <c:otherwise>
        <c:set var="contentType" value="main"/>
    </c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="ko">
<head>
    <t:insertAttribute name="head" />
</head>
<body class="${contentType eq 'login' ? '' : 'mod-bg-1'}">
<!-- DOC: script to save and load page settings -->
<script src="<c:url value='/js/init.js'/>"></script>

<c:choose>
    <c:when test="${contentType eq 'login'}">
        <t:insertAttribute name="content" />
    </c:when>
    <c:otherwise>
        <!-- BEGIN Page Wrapper -->
        <div class="page-wrapper">
            <div class="page-inner">
                <t:insertAttribute name="left" />

                <div class="page-content-wrapper">
                    <t:insertAttribute name="header" />

                    <!-- BEGIN Page Content -->
                    <!-- the #js-page-content id is needed for some plugins to initialize -->
                    <main id="js-page-content" role="main" class="page-content">
                        <t:insertAttribute name="breadcrumb" />
                        <t:insertAttribute name="content" />
                    </main>
                    <!-- this overlay is activated only when mobile menu is triggered -->
                    <div class="page-content-overlay" data-action="toggle" data-class="mobile-nav-on"></div>
                    <t:insertAttribute name="footer" />
                    <t:insertAttribute name="shortcuts" />
                </div>
            </div>
        </div>
        <!-- END Page Wrapper -->
        <t:insertAttribute name="quickMenu" />
        <form name="menuLocationForm" method="post" onsubmit="return false;">
            <input type="hidden" name="menuId">
            <input type="hidden" name="upperMenuNo">
            <input type="hidden" name="menuNm">
        </form>
        <form name="logout"></form>
        <%--for file download--%>
        <form name="fileForm">
            <input type="hidden" name="fileId">
            <input type="hidden" name="fileSn">
            <input type="hidden" name="fileUrl">
            <input type="hidden" name="type">
            <input type="hidden" name="menuId">
        </form>
    </c:otherwise>
</c:choose>

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<%--<script src="//code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>--%>
<script src="<c:url value='/js/vendors.bundle.js'/>"></script>
<script src="<c:url value='/js/app.bundle.js'/>"></script>
<!-- bootstrap twbsPagination -->
<%--<script src="<c:url value='/plugins/twbs-pagination/jquery.twbsPagination.js'/>" type="text/javascript"></script>--%>
<script src="<c:url value='/js/formplugins/bootstrap-datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/js/formplugins/bootstrap-datepicker/bootstrap-datepicker.ko.min.js'/>"></script>
<script src="<c:url value='/js/formplugins/inputmask/inputmask.bundle.js'/>"></script>
<script src="<c:url value='/js/datagrid/datatables/datatables.bundle.js'/>"></script>
<script src="<c:url value='/js/datagrid/datatables/datatables.export.js'/>"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<t:insertAttribute name="commonScript" />
<t:insertAttribute name="script" />

<c:if test="${resultMessage != null && resultMessage ne ''}">
    <script>
        $(document).ready(function() {
            $("#success-modal-alert").modal('show');
            <%--alert('<c:out value="${resultMessage}"/>')--%>
        });
    </script>
</c:if>
<t:insertAttribute name="alert" />
</body>
</html>