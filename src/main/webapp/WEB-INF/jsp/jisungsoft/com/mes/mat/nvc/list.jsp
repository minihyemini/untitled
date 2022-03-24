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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/mat/nvc/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-client" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th rowspan="2" class="text-center">NO</th>
                            <th rowspan="2" class="text-center">품번</th>
                            <th rowspan="2" class="text-center">품명</th>
                            <th colspan="2" class="text-center">입고</th>
                            <th colspan="2" class="text-center">불량</th>
                            <th colspan="2" class="text-center">출고</th>
                            <th rowspan="2" class="text-center">표준단가</th>
                            <th rowspan="2" class="text-center">수량</th>
                        </tr>
                        <tr>
                            <th class="text-center">수량</th>
                            <th class="text-center">금액</th>
                            <th class="text-center">수량</th>
                            <th class="text-center">금액</th>
                            <th class="text-center">수량</th>
                            <th class="text-center">금액</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/mat/nvc/form.jsp" %>