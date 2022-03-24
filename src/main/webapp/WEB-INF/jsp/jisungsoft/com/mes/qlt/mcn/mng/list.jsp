<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-14
  Time: 오후 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/qlt/mcn/mng/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>No.</th>
                            <th><spring:message code="qlt.mcn.mng.manageNumber"/></th>
                            <th><spring:message code="qlt.mcn.mng.type"/></th>
                            <th><spring:message code="qlt.mcn.mng.name"/></th>
                            <th><spring:message code="qlt.mcn.mng.standard"/></th>
                            <th><spring:message code="qlt.mcn.mng.machineNumber"/></th>
                            <th><spring:message code="qlt.mcn.mng.purchaseDate"/></th>
                            <th><spring:message code="qlt.mcn.mng.manufacturer"/></th>
                            <th><spring:message code="qlt.mcn.mng.calInterval"/></th>
                            <th><spring:message code="qlt.mcn.mng.use"/></th>
                            <th><spring:message code="qlt.mcn.mng.colInterval"/></th>
                            <th><spring:message code="qlt.mcn.mng.item"/></th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/qlt/mcn/mng/form.jsp" %>