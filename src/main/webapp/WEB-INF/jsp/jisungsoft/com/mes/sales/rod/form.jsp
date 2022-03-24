<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-07
  Time: 오후 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-3" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <%--@elvariable id="orderForm" type="jisungsoft.com.persistence.dto.mes.Order"--%>
                    <form:form commandName="orderForm" name="orderForm" id="orderForm" method="post" class="needs-validation">
                        <form:hidden path="ordId" />
                        <form:hidden path="cltId" />
                        <form:hidden path="ordType"/>
                        <form:hidden path="oiId"/>

                        <div class="form-row">
                            <%--수주번호--%>
                            <form:label path="ordNum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="sales.receive.order.salesNum"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="ordNum" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" autocomplete="true"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="수주번호는 자동생성 됩니다." readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="ordNum"></form:errors></div>
                            </div>

                            <%--주문일--%>
                            <form:label path="orderDate" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="sales.receive.order.orderDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="orderDate" cssClass="form-control" id="datepicker2" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="orderDate"></form:errors></div>
                            </div>

                            <%--판매일--%>
                            <form:label path="ordDlvrschdt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="sales.receive.order.salesDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="ordDlvrschdt" cssClass="form-control" id="datepicker3" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="ordDlvrschdt"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--거래처코드--%>
                            <form:label path="cltCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="client.code"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltCode" cssClass="form-control" onclick="fn_getClientData(this);" readonly="true" autocomplete="true" data-type="form"/>
                                <form:input path="cltNm" cssClass="form-control" onclick="fn_getClientData(this);" readonly="true" autocomplete="true" data-type="form"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="cltCode"></form:errors></div>
                            </div>

                            <%--거래유형--%>
                                <%--
                            <form:label path="ordCategory" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="sales.receive.order.category"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="ordCategory" cssClass="form-control">
                                    <c:forEach var="category" items="${categoryList}" varStatus="status">
                                        <form:option value="${category.code}">${category.codeNm}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="invalid-feedback"><form:errors path="ordCategory"></form:errors></div>
                                --%>

                            <%--주문상태--%>
                            <form:label path="ordStatus" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="common.status"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="ordStatus" cssClass="form-control">
                                    <c:forEach var="stts" items="${statusList}" varStatus="status">
                                        <form:option value="${stts.code}">${stts.codeNm}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="invalid-feedback"><form:errors path="ordStatus"></form:errors></div>
                        </div>

                        <div class="form-row">
                            <%--비고--%>
                            <label class="col-form-label col-1 form-label text-lg-right">비고</label>
                            <div class="col-5 input-group input-group-sm">
                                <form:input path="ordDesc" cssClass="form-control"/>
                                <div class="invalid-feedback"><form:errors path="ordDesc"></form:errors></div>
                            </div>
                        </div>

                        <table id="dt-basic2" class="table table-bordered table-hover table-striped w-100 dataTable table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th>NO</th>
                                <th><spring:message code="pdnum.numName"/></th>
                                <th><spring:message code="pdnum.name"/></th>
                                <th><spring:message code="sales.receive.order.quantity"/></th>
                                <th><spring:message code="sales.receive.order.unitPrice"/></th>
                                <th><spring:message code="sales.receive.order.amount"/></th>
                                <th><spring:message code="sales.receive.order.vatIncluded"/></th>
                                <th><spring:message code="sales.receive.order.vatRate"/></th>
                                <th><spring:message code="sales.receive.order.vat"/></th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </form:form>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>

<%--거래처 팝업--%>
<div class="modal fade" id="client-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="client.title"/></h5>
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
                                        <option value="1"><spring:message code="client.name"/></option>
                                        <option value="2"><spring:message code="client.code"/></option>
                                        <option value="2"><spring:message code="client.bussNum"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="button" class="btn btn-primary ml-auto" data-target="search" onclick="fn_getClientData();">
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
                            <table id="dt-clint-list" class="table table-bordered table-hover table-striped table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th><spring:message code="client.name"/></th>
                                    <th><spring:message code="client.code"/></th>
                                    <th><spring:message code="client.bussNum"/></th>
                                    <th><spring:message code="input.cSelect"/></th>
                                </tr>
                                </thead>
                                <tbody id="client-dataTable-body">
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
            </div>
        </div>
    </div>
</div>
<%--//거래처 팝업--%>

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
                                    <th><spring:message code="pdnum.type"/></th>
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
