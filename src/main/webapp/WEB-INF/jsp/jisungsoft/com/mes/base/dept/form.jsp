<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-01-30
  Time: 오후 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xl-6">
    <div id="panel-3" class="panel">
        <div class="panel-container show">
            <div class="panel-content">
                <%--@elvariable id="deptForm" type="jisungsoft.com.persistence.dto.member.Dept"--%>
                <form:form commandName="deptForm" name="deptForm" method="post" class="needs-validation">

                    <%--부서코드--%>
                    <div class="form-row">
                        <form:label path="orgnztId" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="dept.id"/>
                            <span class="text-danger">*</span>
                        </form:label>
                        <div class="col-8 input-group input-group-sm">
                            <form:input path="orgnztId" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" autocomplete="true"
                                        data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                        data-original-title="부서코드는 자동생성 됩니다." readonly="true"/>
                            <div class="invalid-feedback"><form:errors path="orgnztId"></form:errors></div>
                        </div>
                    </div>

                    <%--부서명--%>
                    <div class="form-row">
                        <form:label path="orgnztNm" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="dept.name"/>
                            <span class="text-danger">*</span>
                        </form:label>

                        <div class="col-8 input-group input-group-sm">
                            <form:input path="orgnztNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="40" autocomplete="true"/>
                            <div class="invalid-feedback"><form:errors path="orgnztNm"></form:errors></div>
                        </div>
                    </div>

                    <%--비고--%>
                    <div class="form-row">
                        <form:label path="orgnztDc" cssClass="col-form-label col-2 form-label text-lg-right">
                            <spring:message code="dept.desc"/>
                        </form:label>

                        <div class="col-8 input-group input-group-sm">
                            <form:input path="orgnztDc" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="50" autocomplete="true"/>
                            <div class="invalid-feedback"><form:errors path="orgnztDc"></form:errors></div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>