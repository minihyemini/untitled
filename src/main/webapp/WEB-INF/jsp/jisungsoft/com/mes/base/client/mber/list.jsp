<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-19
  Time: 오후 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2><spring:message code="enterprise.member.title"/></h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('clientForm');">
                        <spring:message code="button.init"/>
                    </button>
                    <%--
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset('clientForm');">
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
                    <%--@elvariable id="searchForm" type="jisungsoft.com.persistence.dto.member.EnterpriseMember"--%>
                    <form:form commandName="searchForm" name="searchForm" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>

                        <div class="panel-content">
                            <div class="form-row">
                                <form:label path="searchKeyword" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="title.search"/></form:label>
                                <div class="col-2 input-group input-group-sm">
                                    <form:input path="searchKeyword" cssClass="form-control"></form:input>
                                </div>

                                <form:label path="searchCondition" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="title.searchCondition"/></form:label>
                                <div class="col-2 input-group input-group-sm">
                                    <form:select path="searchCondition" cssClass="form-control">
                                        <form:option value="0"><spring:message code="title.all"/></form:option>
                                        <form:option value="1">담당자명</form:option>
                                        <form:option value="2">담당자ID</form:option>
                                    </form:select>
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

<%-- 거래처 데이터--%>
<div class="row">
    <div class="col-xl-6">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-client" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="client.name"/></th>
                            <th><spring:message code="client.bussNum"/></th>
                            <th><spring:message code="client.corporationNum"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(clientList) > 0}">
                                <c:forEach var="result" items="${clientList}" varStatus="status">
                                    <tr data-id="${result.cltId}">
                                        <td><c:out value="${result.cltNm}"/></td>
                                        <td><c:out value="${result.cltBussnum}"/></td>
                                        <td><c:out value="${result.cltCprtnum}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="15">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>

    <div class="col-xl-6">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-client-member" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>담당자명</th>
                            <th>담당자ID</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(resultList) > 0}">
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr data-id="${result.cltId}">
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="15">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //거래처 데이터--%>

<%-- 담당자 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-4" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="clientMberForm" type="jisungsoft.com.persistence.dto.member.EnterpriseMember"--%>
                    <form:form commandName="clientMberForm" name="employeeMemberForm" method="post" class="needs-validation">
                        <form:hidden path="uniqId"/>
                        <form:hidden path="userTy"/>

                        <div class="form-row">
                                <%--성명--%>
                            <form:label path="entrprsMberNm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="enterprise.member.name"/>
                                <span class="text-danger">*</span>
                            </form:label>

                            <div class="col-3 input-group input-group-sm">
                                <form:input path="entrprsMberNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="entrprsMberNm"></form:errors></div>
                            </div>

                                <%--담당자ID--%>
                            <form:label path="entrprsMberId" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="enterprise.member.id"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="entrprsMberId" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" disabled="true"/>
                            </div>

                                <%--이메일--%>
                            <form:label path="entrprsMberEmailAdres" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.email"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input type="email" path="entrprsMberEmailAdres" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="30" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="entrprsMberEmailAdres"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--휴대전화번호--%>
                            <form:label path="mbtlnum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.mobile"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="mbtlnum" data-inputmask="'mask': '999-9999-9999'" class="form-control" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="mbtlnum"></form:errors></div>
                            </div>

                                <%--사무실전화번호--%>
                            <form:label path="telno" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="member.tel"/></form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="telno" data-inputmask="'mask': '999-9999-9999'" class="form-control" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="telno"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--성별--%>
                            <form:label path="sexdstnCode" cssClass="col-form-label col-1 form-label text-lg-right"><spring:message code="member.sexdstn"/></form:label>
                            <div class="col-1 input-group input-group-sm">
                                <form:select path="sexdstnCode" cssClass="form-control">
                                    <form:option value="${SexdstnCode.M.name()}"><spring:message code="member.male"/></form:option>
                                    <form:option value="${SexdstnCode.F.name()}"><spring:message code="member.female"/></form:option>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--권한정보--%>
                            <form:label path="authorCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.author"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:select path="authorCode" cssClass="form-control">
                                    <c:forEach var="author" items="${authorInfoList}" varStatus="status">
                                        <form:option value="${author.authorCode}" disabled="${fn:contains(author.authorCode,'IS_AUTHENTICATED') ? 'true' : 'false'}">
                                            <c:out value="${author.authorNm}"/>
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="authorCode"></form:errors></div>
                            </div>

                                <%--직위명--%>
                            <form:label path="ofcpsNm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.position.name"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="ofcpsNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="30" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="ofcpsNm"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--우편번호--%>
                            <form:label path="zip" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.zip"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="zip" cssClass="form-control" readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="zip"></form:errors></div>
                                <div class="input-group-append height-2">
                                    <button type="button" class="btn btn-outline-default waves-effect waves-themed" onclick="execDaumPostcode();">
                                        <spring:message code="button.find.address"/>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--주소--%>
                            <form:label path="adres" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.address"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="adres" cssClass="form-control" readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="adres"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--상세주소--%>
                            <form:label path="detailAdres" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.address.detail"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="detailAdres" cssClass="form-control" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="detailAdres"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //담당자 데이터--%>
