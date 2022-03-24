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
                <h2>생산실적관리</h2>
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Pdperformce"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">납기일자</label>
                            <div class="col-2 input-group input-group-sm">
                                <input class="form-control" id="datepicker" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">품명</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <input type="text" class="form-control">
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
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
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>작업지시CODE</th>
                            <th>생산공장</th>
                            <th>생산담당자</th>
                            <th>품번</th>
                            <th>품명</th>
                            <th>단위</th>
                            <th>규격</th>
                            <th>생산지시량</th>
                            <th>생산량</th>
                            <th>잔량</th>
                            <th>완료여부</th>
                            <th>창고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>W20210121001</td>
                            <td>1-공장</td>
                            <td>홍길동</td>
                            <td>ASSY-001</td>
                            <td>완제품-001</td>
                            <td>EA</td>
                            <td></td>
                            <td>10</td>
                            <td>10</td>
                            <td>0</td>
                            <td>완료</td>
                            <td>1-창고</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>W20210121001</td>
                            <td>1-공장</td>
                            <td>홍길동</td>
                            <td>ASSY-001</td>
                            <td>완제품-001</td>
                            <td>EA</td>
                            <td></td>
                            <td>10</td>
                            <td>10</td>
                            <td>0</td>
                            <td>완료</td>
                            <td>1-창고</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>W20210121001</td>
                            <td>1-공장</td>
                            <td>홍길동</td>
                            <td>ASSY-001</td>
                            <td>완제품-001</td>
                            <td>EA</td>
                            <td></td>
                            <td>10</td>
                            <td>10</td>
                            <td>0</td>
                            <td>완료</td>
                            <td>1-창고</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>W20210121001</td>
                            <td>1-공장</td>
                            <td>홍길동</td>
                            <td>ASSY-001</td>
                            <td>완제품-001</td>
                            <td>EA</td>
                            <td></td>
                            <td>10</td>
                            <td>10</td>
                            <td>0</td>
                            <td>완료</td>
                            <td>1-창고</td>
                        </tr>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>