<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="clientForm" type="jisungsoft.com.persistence.dto.mes.Client"--%>
                    <form:form commandName="clientForm" name="clientForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>
                        <form:hidden path="cltId" />

                        <div class="form-row">
                            <%--거래처 코드--%>
                            <form:label path="cltCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.code"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltCode" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" autocomplete="true"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="거래처코드는 자동생성 됩니다." readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="cltCode"></form:errors></div>
                            </div>

                            <%--거래처명--%>
                            <form:label path="cltNm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.name"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltNm" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="20"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="필수 입력값입니다."/>
                                <div class="invalid-feedback"><form:errors path="cltNm"></form:errors></div>
                            </div>

                            <%--대표자 성명--%>
                            <form:label path="cltOwnrnm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.ownerName"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltOwnrnm" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="cltOwnrnm"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row">
                                <%--업태--%>
                            <form:label path="cltBusstype" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.bussType"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltBusstype" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="cltBusstype"></form:errors></div>
                            </div>

                                <%--업태--%>
                            <form:label path="cltType" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.type"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:select path="cltType" cssClass="form-control">
                                    <form:option value="">선택</form:option>
                                    <form:option value="${ClientType.PARTNER.name()}">협력사</form:option>
                                    <form:option value="${ClientType.CUSTOMER.name()}">고객사</form:option>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-row">
                                <%--사업자 번호--%>
                            <form:label path="cltBussnum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.bussNum"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltBussnum" cssClass="form-control" data-inputmask="'mask': '999-99-99999'" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="cltBussnum"></form:errors></div>
                            </div>

                            <%--법인 번호--%>
                            <form:label path="cltCprtnum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.corporationNum"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltCprtnum" cssClass="form-control" data-inputmask="'mask': '999999-99999999'" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="cltCprtnum"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--전화번호--%>
                            <form:label path="cltCprtnum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.telNum"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltTelno" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" onkeypress="return fn_press(event, 'number')" maxlength="15"/>
                                <div class="invalid-feedback"><form:errors path="cltTelno"></form:errors></div>
                            </div>

                                <%--팩스번호--%>
                            <form:label path="cltFaxnum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.faxNum"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltFaxnum" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" onkeypress="return fn_press(event, 'number')" maxlength="15"/>
                                <div class="invalid-feedback"><form:errors path="cltFaxnum"></form:errors></div>
                            </div>

                                <%--이메일--%>
                            <form:label path="cltEmail" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.email"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltEmail" cssClass="form-control" data-inputmask="'alias': 'email'" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="30"/>
                                <div class="invalid-feedback"><form:errors path="cltEmail"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--설립일자--%>
                            <form:label path="cltSetupdt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.setupDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltSetupdt" id="datepicker" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true" maxlength="10"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="cltSetupdt"></form:errors></div>
                            </div>

                                <%--마감 시작일--%>
                            <form:label path="cltDeadlndt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.deadLineDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltDeadlndt" id="datepicker4" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true" maxlength="10"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="cltDeadlndt"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--거래시작일--%>
                            <form:label path="cltDlbegindt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.transactionBeginDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltDlbegindt" id="datepicker2" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true" maxlength="10"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="cltDlbegindt"></form:errors></div>
                            </div>

                            <%--거래종료일--%>
                            <form:label path="cltDlendt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.transactionEndDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltDlendt" id="datepicker3" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true" maxlength="10"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="cltDlendt"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                                <%--우편번호--%>
                            <form:label path="cltZip" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.zip"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="cltZip" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <button class="btn btn-default waves-effect waves-themed" type="button"  onclick="execDaumPostcode();"><spring:message code="button.search"/></button>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                                <%--주소--%>
                            <form:label path="cltAddr" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.address"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="cltAddr" cssClass="form-control" readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="cltAddr"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row">
                                <%--상세주소--%>
                            <form:label path="cltDetailAddr" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="client.detailAddress"/>
                            </form:label>
                            <div class="col-4 input-group input-group-sm">
                                <form:input path="cltDetailAddr" cssClass="form-control" autocomplete="true" maxlength="30"/>
                                <div class="invalid-feedback"><form:errors path="cltDetailAddr"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

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
                        <form name="searchPdNumForm" onsubmit="fn_getPdNumList(this); return false;" method="post">
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
                                    <button type="submit" class="btn btn-primary ml-auto" data-target="search">
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
            </div>
        </div>
    </div>
</div>
<%--//품번목록 팝업--%>
