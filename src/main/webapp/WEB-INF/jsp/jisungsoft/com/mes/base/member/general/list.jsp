<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오후 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/member/general/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="dataTables_scroll">
                                <table id="dt-basic-example" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                                    <thead>
                                    <tr>
                                        <th><spring:message code="member.name"/></th>
                                        <th><spring:message code="member.id"/></th>
                                        <th><spring:message code="member.email"/></th>
                                        <th><spring:message code="member.dept.name"/></th>
                                        <th><spring:message code="member.group.name"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="list" items="${resultList}" varStatus="status">
                                        <tr>
                                            <td>Airi Satou</td>
                                            <td>Accountant</td>
                                            <td>Tokyo</td>
                                            <td>33</td>
                                            <td>2008/11/28</td>
                                            <td>$162,700</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
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
                    <%--@elvariable id="generalMemberEditForm" type="jisungsoft.com.mes.base.mber.form.GeneralMemberEditForm"--%>
                    <form:form commandName="generalMemberEditForm" name="generalMemberEditForm" class="needs-validation">
                        <form:hidden path="uniqId"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustom01">First name <span class="text-danger">*</span> </label>
                                    <input type="text" class="form-control" id="validationCustom01" placeholder="First name" value="Codex" required>
                                    <div class="valid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustom02">Last name <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="validationCustom02" placeholder="Last name" value="Lantern" required>
                                    <div class="valid-feedback">
                                        Looks good!
                                    </div>
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustomUsername">Username <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroupPrepend">@</span>
                                        </div>
                                        <input type="text" class="form-control" id="validationCustomUsername" placeholder="Username" aria-describedby="inputGroupPrepend" required>
                                        <div class="invalid-feedback">
                                            Please choose a username.
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-md-6 mb-3">
                                    <label class="form-label" for="validationCustom03">City <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="validationCustom03" placeholder="City" required>
                                    <div class="invalid-feedback">
                                        Please provide a valid city.
                                    </div>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="validationCustom03">State <span class="text-danger">*</span></label>
                                    <select class="custom-select" required="">
                                        <option value="">State</option>
                                        <option value="1">Michigan</option>
                                        <option value="2">New York</option>
                                        <option value="3">Oklahoma</option>
                                    </select>
                                    <div class="invalid-feedback">
                                        Please provide a valid state.
                                    </div>
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="validationCustom05">Zip <span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" id="validationCustom05" placeholder="Zip" required>
                                    <div class="invalid-feedback">
                                        Please provide a valid zip.
                                    </div>
                                </div>
                                <div class="col-12 mb-3">
                                    <label class="form-label" for="validationTextarea2">Comment <span class="text-danger">*</span></label>
                                    <textarea class="form-control" id="validationTextarea2" placeholder="Required example textarea" required=""></textarea>
                                    <div class="invalid-feedback">
                                        Please enter a message in the textarea.
                                    </div>
                                </div>
                                <div class="col-12">
                                    <label class="form-label mb-2">Please disclose your gender profile <span class="text-danger">*</span></label>
                                    <div class="custom-control custom-radio mb-2">
                                        <input type="radio" class="custom-control-input" id="GenderMale" name="radio-stacked" required="">
                                        <label class="custom-control-label" for="GenderMale">Male</label>
                                    </div>
                                    <div class="custom-control custom-radio mb-2">
                                        <input type="radio" class="custom-control-input" id="GenderFemale" name="radio-stacked" required="">
                                        <label class="custom-control-label" for="GenderFemale">Female</label>
                                    </div>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="genderPrivate" name="radio-stacked" required="">
                                        <label class="custom-control-label" for="genderPrivate">Prefer not to say</label>
                                        <div class="invalid-feedback">Please select at least one</div>
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