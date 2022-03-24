<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-14
  Time: 오후 5:52
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
                    <%--@elvariable id="qltMachineForm" type="jisungsoft.com.persistence.dto.mes.Machine"--%>
                    <form:form commandName="qltMachineForm" name="orderForm" id="orderForm" method="post" class="needs-validation">
                        <form:hidden path="miId" />

                        <div class="form-row">
                            <%--관리번호--%>
                            <form:label path="miMngNum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="sales.receive.order.salesNum"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miMngNum" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" autocomplete="true"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="관리번호는 자동생성 됩니다." readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="miMngNum"></form:errors></div>
                            </div>

                            <%--분류--%>
                            <form:label path="miType" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.type"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="miType" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <form:option value=""></form:option>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="miType"></form:errors></div>
                            </div>

                            <%--머신명--%>
                            <form:label path="miName" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="qlt.mcn.mng.name"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miName" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="miType"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--규격--%>
                            <form:label path="miStandard" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.standard"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="miStandard" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <form:option value=""></form:option>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="miStandard"></form:errors></div>
                            </div>

                            <%--기기번호--%>
                            <form:label path="miDvcNum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.machineNumber"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miDvcNum" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="miDvcNum"></form:errors></div>
                            </div>

                            <%--판매일--%>
                            <form:label path="miPurchaseDate" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.purchaseDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miPurchaseDate" cssClass="form-control" id="datepicker3" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="miPurchaseDate"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--제조사--%>
                            <form:label path="miMenufacturer" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.manufacturer"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miMenufacturer" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="miMenufacturer"></form:errors></div>
                            </div>

                            <%--검교정주기--%>
                            <form:label path="miCalInterval" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.calInterval"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="miCalInterval" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <c:forEach var="months" begin="1" end="12" step="1" varStatus="status">
                                        <form:option value="${months}">${months}<spring:message code="common.month"/></form:option>
                                    </c:forEach>
                                </form:select>
<%--                                <form:input path="miCalInterval" cssClass="form-control" cssErrorClass="form-control is-invalid"/>--%>
                                <div class="invalid-feedback"><form:errors path="miCalInterval"></form:errors></div>
                            </div>

                            <%--측정항목--%>
                            <form:label path="miItem" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="qlt.mcn.mng.item"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="miItem" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="miItem"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>