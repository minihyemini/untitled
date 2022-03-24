<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:importAttribute name="menuData"/>
<t:importAttribute name="menuDepth1" />
<t:importAttribute name="menuDepth2" />
<t:importAttribute name="menuDepth3" />
<t:importAttribute name="menuDepth4" />

<ol class="breadcrumb breadcrumb-sm breadcrumb-arrow">
    <li>
        <a href="/">
            <i class="fal fa-home"></i>
        </a>
    </li>
    <c:forEach var="depth1" items="${menuDepth1}" varStatus="status">
        <c:if test="${depth1.menuNo eq menuData.upperMenuNo or depth1.menuNo eq menuData.parentMenuNo  or depth1.url eq menuData.url}">
            <li>
                <a href="#">
                    <span class=""><c:out value="${depth1.menuNm}"/></span>
                </a>
            </li>
        </c:if>
    </c:forEach>
    <c:forEach var="depth2" items="${menuDepth2}" varStatus="status">
        <c:if test="${menuData.menuNo eq depth2.menuNo or menuData.upperMenuNo eq depth2.menuNo}">
            <li class="active">
                <a href="#">
                    <span class=""><c:out value="${depth2.menuNm}"/></span>
                </a>
            </li>
        </c:if>
    </c:forEach>
    <c:forEach var="depth3" items="${menuDepth3}" varStatus="status">
        <c:if test="${menuData.menuNo eq depth3.menuNo or menuData.upperMenuNo eq depth3.menuNo}">
            <li class="active">
                <a href="#">
                    <span class=""><c:out value="${depth3.menuNm}"/></span>
                </a>
            </li>
        </c:if>
    </c:forEach>
    <c:forEach var="depth4" items="${menuDepth4}" varStatus="status">
        <c:if test="${menuData.menuNo eq depth4.menuNo or menuData.upperMenuNo eq depth4.menuNo}">
            <li class="active">
                <a href="#">
                    <span class="hidden-md-down"><c:out value="${depth3.menuNm}"/></span>
                </a>
            </li>
        </c:if>
    </c:forEach>
    <li class="position-absolute pos-top pos-right d-none d-sm-block mt-5 mr-5 font-weight-bold">
        <h5><span class="js-get-date"></span></h5>
    </li>
</ol>
<%--

<ol class="breadcrumb page-breadcrumb">
    <li class="breadcrumb-item"><a href="javascript:void(0);">SmartAdmin</a></li>
    <li class="breadcrumb-item">category_1</li>
    <li class="breadcrumb-item">category_2</li>
    <li class="breadcrumb-item active">Page Titile</li>
    <li class="position-absolute pos-top pos-right d-none d-sm-block"><span class="js-get-date"></span></li>
</ol>
--%>
