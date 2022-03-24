<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오전 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="egovframework.com.cmm.service.Globals" %>

<t:importAttribute name="menuData"/>

<meta charset="utf-8">
<title>
    MES
</title>
<meta name="description" content="Page Titile">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no, minimal-ui">
<!-- Call App Mode on ios devices -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- Remove Tap Highlight on Windows Phone IE -->
<meta name="msapplication-tap-highlight" content="no">
<!-- base css -->
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/vendors.bundle.css'/>">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/app.bundle.css'/>">
<!-- Place favicon.ico in the root directory -->
<link rel="apple-touch-icon" sizes="180x180" href="<c:url value='/images/favicon/apple-touch-icon.png'/>">
<link rel="icon" type="image/png" sizes="32x32" href="<c:url value='/images/favicon/favicon-32x32.png'/>">
<link rel="mask-icon" href="<c:url value='/images/favicon/safari-pinned-tab.svg'/>" color="#5bbad5">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/common.css'/>">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/formplugins/bootstrap-datepicker/bootstrap-datepicker.css'/>">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/formplugins/bootstrap-daterangepicker/bootstrap-daterangepicker.css'/>">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/datagrid/datatables/datatables.bundle.css'/>">
<link type="text/css" rel="stylesheet" media="all" href="<c:url value='/plugins/fancytree/skin-lion/ui.fancytree.css'/>">
<link type="text/css" rel="stylesheet" media="all" href="<c:url value='/plugins/jquery-ui-contextmenu/jquery-ui.css'/>">
<link rel="stylesheet" media="screen, print" href="<c:url value='/css/statistics/c3/c3.css'/>">
<link rel="stylesheet" media="screen, print" href="/css/miscellaneous/reactions/reactions.css">
<link rel="stylesheet" media="screen, print" href="/css/miscellaneous/fullcalendar/fullcalendar.bundle.css">
<link rel="stylesheet" media="screen, print" href="/css/miscellaneous/jqvmap/jqvmap.bundle.css">

<c:if test="${menuData.url eq Globals.GNR_LOGIN_PAGE or menuData.url eq Globals.USR_LOGIN_PAGE}">
    <!-- Optional: page related CSS-->
    <link rel="stylesheet" media="screen, print" href="<c:url value='/css/page-login.css'/>">
</c:if>