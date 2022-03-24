<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="pdfciltsCode.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" data-toggle="modal" data-target="#save-modal-alert">
                        <spring:message code="button.save"/>
                    </button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="window.location.reload();">
                        <spring:message code="button.refresh"/>
                    </button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="fciltsPdNumForm" type="jisungsoft.com.persistence.dto.mes.FciltsPdNum"--%>
                    <form:form commandName="fciltsPdNumForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <form:label path="searchKeyword" cssClass="form-label"><spring:message code="title.search"/></form:label>
                                    <form:input path="searchKeyword" cssClass="form-control"/>
                                </div>
                                <div class="col-md-2 mb-3">
                                    <form:label path="searchCondition" cssClass="form-label"><spring:message code="title.searchCondition"/></form:label>
                                    <form:select path="searchCondition" cssClass="form-control">
                                        <form:option value="0"><spring:message code="title.all"/></form:option>
                                        <form:option value="1"><spring:message code="pdfciltsCode.code"/></form:option>
                                        <form:option value="2"><spring:message code="pdfciltsCode.name"/></form:option>
                                    </form:select>
                                </div>
                                <div class="col-md-2 mt-4">
                                    <button type="button" class="btn btn-primary ml-auto" onclick="fn_getPage(this);" data-target="search">
                                        <i class="fal fa-search"></i>
                                    </button>
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