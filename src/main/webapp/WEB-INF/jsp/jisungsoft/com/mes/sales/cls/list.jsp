<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-18
  Time: 오후 1:51
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
                <h2>검수마감관리</h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('');">
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
                                <label class="col-form-label col-1 form-label text-lg-right">마감일자</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input class="form-control" id="datepicker4" readonly>
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                    </div>
                                </div>

                                <label class="col-form-label col-1 form-label text-lg-right">거래처코드</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input type="text" class="form-control" readonly>
                                    <input type="text" class="form-control">
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                    </div>
                                </div>

                                <label class="col-form-label col-1 form-label text-lg-right">품번</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input type="text" class="form-control" readonly>
                                    <input type="text" class="form-control">
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
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>거래처코드</th>
                            <th>거래처명</th>
                            <th>마감수</th>
                            <th>미마감수</th>
                            <th>출고수량</th>
                            <th>미출고수량</th>
                            <th>출하액</th>
                            <th>VAT</th>
                            <th>마감금액</th>
                            <th>마감일자</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="data" begin="0" end="11" step="1" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>A20220102001</td>
                                <td>지성소프트</td>
                                <td>0</td>
                                <td>0</td>
                                <td>5</td>
                                <td>0</td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>2022-01-02</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>