<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-17
  Time: 오후 6:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2>검사작업지시</h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('');">
                        <spring:message code="button.init"/>
                    </button>
                    <%--
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_getPage(this);" data-target="addForm">
                        <spring:message code="button.newCreate"/>
                    </button>
                    --%>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert">
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.RcvIspInfo"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">조회기간</label>
                            <div class="col-2 input-group input-group-sm">
                                <input class="form-control" id="datepicker" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>
                            <div class="mt-1">~</div>
                            <div class="col-2 input-group input-group-sm">
                                <input class="form-control" id="datepicker2" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">품목구분</label>
                            <div class="col-2 input-group input-group-sm">
                                <select class="form-control">
                                    <option>전체</option>
                                </select>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>
