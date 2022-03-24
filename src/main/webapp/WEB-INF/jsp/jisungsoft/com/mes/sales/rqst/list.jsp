<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오후 4:16
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
                <h2>제품출고의뢰</h2>
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Sles"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <label class="col-form-label col-1 form-label text-lg-right">판매일</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input class="form-control" id="datepicker4" readonly>
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                    </div>
                                </div>

                                <label class="col-form-label col-1 form-label text-lg-right">고객코드</label>
                                <div class="col-2 input-group input-group-sm">
                                    <input type="text" aria-label="First name" class="form-control" id="name-f">
                                    <input type="text" aria-label="Last name" class="form-control"id="name-l">
                                    <div class="input-group-append height-2">
                                        <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                    </div>
                                </div>

                                <label class="col-form-label col-1 form-label text-lg-right">납품유무</label>
                                <div class="col-1 input-group input-group-sm">
                                    <select class="form-control">
                                        <option>Y</option>
                                        <option>N</option>
                                    </select>
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
    <div class="col-xl-5">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>판매일</th>
                            <th>거래처코드</th>
                            <th>건수</th>
                            <th>비고</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="data" begin="0" end="11" step="1" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>2022-01-01</td>
                                <td>고객사0${status.count}</td>
                                <td></td>
                                <td>고객사0${status.count}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-7">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <form>
                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">판매번호</label>
                            <span class="text-danger">*</span>
                            <div class="col-3 input-group input-group-sm">
                                <input class="form-control" value="RV202104020001" readonly>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">납품일자</label>
                            <div class="col-3 input-group input-group-sm">
                                <input class="form-control" id="datepicker" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">거래처코드</label>
                            <div class="col-5 input-group input-group-sm">
                                <input type="text" class="form-control">
                                <input type="text" class="form-control">
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <button class="btn btn-success btn-sm ml-3 waves-effect waves-themed ml-auto mr-2" type="button">
                                행추가
                            </button>
                        </div>

                        <table id="dt-basic3" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th>LotNo</th>
                                <th>현재수량</th>
                                <th>중량(KG)</th>
                                <th>납품수량</th>
                                <th>납품중량</th>
                                <th>바코드</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="data" begin="0" end="2" step="1" varStatus="status">
                                <tr>
                                    <td>OM21090822 </td>
                                    <td>${status.count+3}</td>
                                    <td>0</td>
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
                                    <td>O21090821122${status.count}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-5">
        <div id="panel-4" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic2" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>NO</th>
                            <th>품번</th>
                            <th>품명</th>
                            <th>판매계획</th>
                            <th>납품수량</th>
                            <th>미납품수량</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>2022-01-01</td>
                            <td>완제품-001</td>
                            <td>300</td>
                            <td>290</td>
                            <td>10</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>2022-01-02</td>
                            <td>완제품-002</td>
                            <td>500</td>
                            <td>200</td>
                            <td>300</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>2022-01-03</td>
                            <td>완제품-003</td>
                            <td>200</td>
                            <td>300</td>
                            <td>-100</td>
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