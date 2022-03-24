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
                <h2><spring:message code="qlt.mcn.mng.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('qltMachineForm');">
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.mes.Machine"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="form-row">
                            <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="title.search"/></form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="searchKeyword" cssClass="form-control"></form:input>
                            </div>

                            <form:label path="searchCondition" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="title.searchCondition"/></form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="searchCondition" cssClass="form-control">
                                    <form:option value="0"><spring:message code="title.all"/></form:option>
                                    <form:option value="1"><spring:message code="qlt.mcn.mng.name"/></form:option>
                                    <form:option value="2"><spring:message code="qlt.mcn.mng.type"/></form:option>
                                    <form:option value="3"><spring:message code="qlt.mcn.mng.machineNumber"/></form:option>
                                </form:select>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>