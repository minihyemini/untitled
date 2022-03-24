<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-28
  Time: 오후 7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xl-6">
    <div id="panel-3" class="panel">
        <div class="panel-container show">
            <div class="panel-content">
                <%--@elvariable id="groupForm" type="jisungsoft.com.persistence.dto.sec.Group"--%>
                <form:form commandName="groupForm" name="groupForm" method="post" class="needs-validation">
                    <%--부서ID--%>
                    <div class="form-row">
                        <form:label path="orgnztId" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="dept.id"/>
                            <span class="text-danger">*</span>
                        </form:label>
                        <div class="col-6 input-group input-group-sm" onclick="fn_getDeptData();">
                            <form:input path="orgnztId" cssClass="form-control" readonly="true"/>
                            <form:input path="orgnztNm" cssClass="form-control" readonly="true"/>
                            <div class="input-group-append height-2">
                                <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                    <%--그룹ID--%>
                    <div class="form-row">
                        <form:label path="groupId" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="group.id"/>
                            <span class="text-danger">*</span>
                        </form:label>
                        <div class="col-4 input-group input-group-sm">
                            <form:input path="groupId" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" autocomplete="true"
                                        data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                        data-original-title="그룹ID는 자동생성 됩니다." readonly="true"/>
                        </div>
                    </div>
                    <%--그룹명--%>
                    <div class="form-row">
                        <form:label path="groupNm" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="group.name"/>
                            <span class="text-danger">*</span>
                        </form:label>
                        <div class="col-4 input-group input-group-sm">
                            <form:input path="groupNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="40" autocomplete="true"/>
                            <div class="invalid-feedback"><form:errors path="groupNm"></form:errors></div>
                        </div>
                    </div>
                    <%--그룹설명--%>
                    <div class="form-row">
                        <form:label path="groupDc" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="group.desc"/>
                        </form:label>
                        <div class="col-8 input-group input-group-sm">
                            <form:input path="groupDc" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="50" autocomplete="true"/>
                            <div class="invalid-feedback"><form:errors path="groupDc"></form:errors></div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%--부서 팝업--%>
<div class="modal fade" id="dept-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="dept.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="panel-hdr">
                    <div class="col-12">
                        <form name="searchSubForm" method="post">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="searchKeyword"><spring:message code="title.search"/></label>
                                    <input type="text" class="form-control" name="searchKeyword" id="searchKeyword">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="searchCondition"><spring:message code="title.searchCondition"/></label>
                                    <select name="searchCondition" class="form-control" name="searchCondition" id="searchCondition">
                                        <option value="0"><spring:message code="title.all"/></option>
                                        <option value="1"><spring:message code="dept.id"/></option>
                                        <option value="2"><spring:message code="dept.name"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="button" class="btn btn-primary ml-auto" data-target="search" onclick="fn_getDeptData();">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel-container show">
                    <div class="panel-content">
                        <div class="dataTables-scroll">
                            <table id="dt-pdnum-list" class="table table-bordered table-hover table-striped table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th><spring:message code="dept.id"/></th>
                                    <th><spring:message code="dept.name"/></th>
                                    <th><spring:message code="input.cSelect"/></th>
                                </tr>
                                </thead>
                                <tbody id="dept-dataTable-body">
                                <tr>
                                    <td colspan="3">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
<%--                <button type="button" class="btn btn-primary" id="addRowBtn" onclick="fn_selectDeptData(this);"><spring:message code="button.add"/></button>--%>
            </div>
        </div>
    </div>
</div>
<%--//부서 팝업--%>