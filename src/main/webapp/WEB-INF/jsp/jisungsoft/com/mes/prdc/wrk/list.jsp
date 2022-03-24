<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오후 5:32
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

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2>생산작업지시</h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('codeEditForm');">
                        <spring:message code="button.init"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_getPage(this);" data-target="addForm">
                        <spring:message code="button.newCreate"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#edit-modal-alert">
                        <spring:message code="button.save"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="window.location.reload();">
                        <spring:message code="button.refresh"/>
                    </button>
                    <button type="button" class="btn btn-primary ml-auto" onclick="fn_getPage(this);" data-target="search">
                        <i class="fal fa-search"></i>
                    </button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Order"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <label class="col-form-label col-1 form-label text-lg-right">작업일자</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input class="form-control" id="datepicker4" readonly>
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                    </div>
                                </div>

                                <label class="col-form-label col-1 form-label text-lg-right">설비코드</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input type="text" aria-label="First name" class="form-control" id="name-f">
                                    <input type="text" aria-label="Last name" class="form-control"id="name-l">
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-4">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>공장코드</th>
                            <th>공장명</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="data" begin="0" end="2" step="1" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>F20210102${status.count}</td>
                                <td>${status.count}-공장</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-8">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <form class="needs-validation">
                        <div class="form-group row">
                            <label class="col-form-label col-2 form-label text-lg-right">선택된 설비코드</label>
                            <div class="col-3 input-group input-group-sm">
                                <input class="form-control" readonly>
                            </div>

                            <label class="col-form-label col-2 form-label text-lg-right">선택된 설비명</label>
                            <div class="col-3 input-group input-group-sm">
                                <input class="form-control" readonly>
                            </div>

                            <div class="col-2 input-group input-group-sm">
                                <button class="btn btn-success btn-sm" type="button">
                                    <span><i class="fal fa-plus mr-1"></i> 행추가</span>
                                </button>
                            </div>
                        </div>

                        <table id="dt-basic2" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th>NO</th>
                                <th>품번</th>
                                <th>품명</th>
                                <th>작업순서</th>
                                <th>계획수량</th>
                                <th>실적수량</th>
                                <th>비고</th>
                                <th>작업코드</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="data" begin="0" end="2" step="1" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>ASSY-00${status.count}</td>
                                    <td>완제품-00${status.count}</td>
                                    <td>
                                        <div class="col-10 input-group input-group-sm">
                                            <input type="text" class="form-control form-control-sm">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="col-10 input-group input-group-sm">
                                            <input type="text" class="form-control form-control-sm">
                                        </div>
                                    </td>
                                    <td>0</td>
                                    <td></td>
                                    <td>W2021012100${status.count}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>
