<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 7:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="code.title"/></h2>
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
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="cmmnCode" type="jisungsoft.com.persistence.dto.sym.code.CmmnCode"--%>
                    <form:form commandName="cmmnCode" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <form:label path="searchKeyword" cssClass="form-label"><spring:message code="title.search"/></form:label>
                                    <form:input path="searchKeyword" cssClass="form-control"></form:input>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <form:label path="searchCondition" cssClass="form-label"><spring:message code="title.searchCondition"/></form:label>
                                    <form:select path="searchCondition" cssClass="form-control">
                                        <form:option value="0"><spring:message code="title.all"/></form:option>
                                        <form:option value="1"><spring:message code="code.id"/></form:option>
                                        <form:option value="2"><spring:message code="code.name"/></form:option>
                                    </form:select>
                                </div>
                                <div class="col-md-1 mb-3">
                                    <form:label path="useAt" cssClass="form-label"><spring:message code="code.useat"/></form:label>
                                    <form:select path="useAt" cssClass="form-control">
                                        <form:option value="Y">Y</form:option>
                                        <form:option value="N">N</form:option>
                                    </form:select>
                                </div>
                            </div>
                            <%--
                            <div class="form-row">
                                <div class="col-md-2 mb-3">
                                    <div class="input-group">
                                        <form:input path="searchFromDate" cssClass="form-control" placeholder="Select date" id="datepicker1" readonly="true"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text fs-xl">
                                                <i class="fal fa-calendar-alt"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                &nbsp;~&nbsp;
                                <div class="col-md-2 mb-3">
                                    <div class="input-group">
                                        <form:input path="searchToDate" cssClass="form-control" placeholder="Select date" id="datepicker2" readonly="true"/>
                                        <div class="input-group-append">
                                            <span class="input-group-text fs-xl">
                                                <i class="fal fa-calendar-alt"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            --%>
                        </div>
                        <div class="panel-content border-faded border-left-0 border-right-0 border-bottom-0 d-flex flex-row align-items-center">
                            <button type="button" class="btn btn-primary ml-auto" onclick="fn_getPage(this);" data-target="search">
                                <i class="fal fa-search"></i>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>