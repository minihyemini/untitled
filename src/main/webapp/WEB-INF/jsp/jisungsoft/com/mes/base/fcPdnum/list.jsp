<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:51
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

<%@include file="/WEB-INF/jsp/jisungsoft/com/mes/base/fcPdnum/searchForm.jsp" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-6">
        <div id="panel-2" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-basic" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th><spring:message code="pdfciltsCode.code"/></th>
                            <th><spring:message code="pdfciltsCode.name"/></th>
                            <th><spring:message code="FciltsPdNum.cnt"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                            <c:when test="${fn:length(resultList) > 0}">
                                <c:forEach var="result" items="${resultList}" varStatus="status">
                                    <tr data-id="${result.pdfciltsId}">
                                        <td><c:out value="${result.pdfciltsCode}"/></td>
                                        <td><c:out value="${result.pdfciltsNm}"/></td>
                                        <td><c:out value="${result.pdNumCnt}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="8">
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
            <div class="panel-hdr">
                <div class="ml-1">
                    <button class="btn btn-success btn-sm mr-1" type="button" onclick="fn_getPdNumData();">
                        <span><i class="fal fa-plus mr-1"></i> <spring:message code="FciltsPdNum.add.pdnum"/></span>
                    </button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <%--@elvariable id="fciltsPdNumForm" type="jisungsoft.com.persistence.dto.mes.FciltsPdNum"--%>
                    <form:form commandName="fciltsPdNumForm" name="fciltsPdNumForm" method="post">
                        <form:hidden path="pdfciltsId"/>
                        <form:hidden path="fcpdId"/>
                        <form:hidden path="pdnumId"/>
                        <table id="dt-sub-basic" class="table table-bordered table-hover table-striped table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="form-control-input" id="check-fciltsPdNum-all">
                                    </div>
                                </th>
                                <th><spring:message code="pdnum.numName"/></th>
                                <th><spring:message code="pdnum.name"/></th>
                            </tr>
                            </thead>
                            <tbody id="fcilts-pd-num"></tbody>
                        </table>
                    </form:form>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%--품번목록 팝업--%>
<div class="modal fade" id="pdnum-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="pdnum.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="panel-hdr">
                    <div class="col-12">
                        <form name="searchPdNumForm" method="post">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="searchKeyword"><spring:message code="title.search"/></label>
                                    <input type="text" class="form-control" name="searchKeyword" id="searchKeyword">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="searchCondition"><spring:message code="title.searchCondition"/></label>
                                    <select name="searchCondition" class="form-control" name="searchCondition" id="searchCondition">
                                        <option value="0"><spring:message code="title.all"/></option>
                                        <option value="1"><spring:message code="pdnum.numName"/></option>
                                        <option value="2"><spring:message code="pdnum.name"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="button" class="btn btn-primary ml-auto" data-target="search" onclick="fn_getPdNumData();">
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
                                    <th>
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="form-control-input" id="check-pdnum-all">
                                        </div>
                                    </th>
                                    <th><spring:message code="pdnum.numName"/></th>
                                    <th><spring:message code="pdnum.name"/></th>
                                    <th><spring:message code="pdnum.standard"/></th>
                                    <th><spring:message code="pdnum.unit"/></th>
                                </tr>
                                </thead>
                                <tbody id="pdnum-dataTable-body">
                                <tr>
                                    <td colspan="5">
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
                <button type="button" class="btn btn-primary" id="addRowBtn" onclick="fn_selectPdnumData(this);"><spring:message code="button.add"/></button>
            </div>
        </div>
    </div>
</div>
<%--//품번목록 팝업--%>