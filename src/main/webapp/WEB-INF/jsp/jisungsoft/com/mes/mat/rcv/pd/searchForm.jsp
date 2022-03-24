<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-14
  Time: 오후 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="mat.rcv.pd.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('orderForm');">
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Material"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <form:label path="searchDate" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.registrationDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="searchDate" id="datepicker" cssClass="form-control" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>

                            <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.code"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" name="name" class="form-control" onclick="fn_getClientData(this);" data-type="search" readonly>
                                <input type="text" name="code" class="form-control" onclick="fn_getClientData(this);" data-type="search" readonly>
                                <form:hidden path="searchKeyword"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.numName"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" name="name" class="form-control" onclick="" data-type="search" readonly>
                                <input type="text" name="code" class="form-control" onclick="" data-type="search" readonly>
                                <form:hidden path="searchKeyword2"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>

                            <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.name"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="searchKeyword3" cssClass="form-control"/>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>