<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-18
  Time: 오전 10:04
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
                <h2>제품출고관리</h2>
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Pdperformce"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">출고일자</label>
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

                            <label class="col-form-label col-1 form-label text-lg-right">공장</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">창고</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
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
                            <th>품명</th>
                            <th>품번</th>
                            <th>규격</th>
                            <th>수량</th>
                            <th>단위</th>
                            <th>담당자명</th>
                            <th>거래처코드</th>
                            <th>거래처명</th>
                            <th>입고일자</th>
                            <th>출고일자</th>
                            <th>진행상태</th>
                            <th>통화</th>
                            <th>단가</th>
                            <th>공장</th>
                            <th>창고</th>
                            <th>비고</th>
                            <th>등록일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="data" begin="0" end="5" step="1" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>ASSY-0100${status.count}</td>
                                <td>완성품-${status.count}</td>
                                <td>A0000-1</td>
                                <td>5</td>
                                <td>EA</td>
                                <td>지성소프트</td>
                                <td>A20210102001</td>
                                <td>지성소프트</td>
                                <td>2021-06-0${status.count}</td>
                                <td>2021-09-0${status.count}</td>
                                <td>출고완료</td>
                                <td>KRW</td>
                                <td>500000</td>
                                <td>1공장</td>
                                <td>영업창고</td>
                                <td></td>
                                <td>2021-09-0${status.count}</td>
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

<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <form>
                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">품명</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <input type="text" class="form-control">
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">출고일</label>
                            <div class="col-3 input-group input-group-sm">
                                <input class="form-control" id="datepicker2" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">고객사</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">수량</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">단위</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">단가</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control">
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">공장</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">창고</label>
                            <div class="col-3 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">진행상태</label>
                            <div class="col-3 input-group input-group-sm">
                                <select class="form-control">
                                    <option>입고중</option>
                                    <option>입고완료</option>
                                    <option>입고취소</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">비고</label>
                            <div class="col-7 input-group input-group-sm">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>