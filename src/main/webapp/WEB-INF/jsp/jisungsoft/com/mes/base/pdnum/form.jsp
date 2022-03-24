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
                    <%--@elvariable id="pdNumForm" type="jisungsoft.com.persistence.dto.mes.Pdnum"--%>
                    <form:form commandName="pdNumForm" name="pdNumForm" method="post" class="needs-validation" enctype="multipart/form-data">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>
                        <form:hidden path="pdnumId"/>

                        <div class="form-row">
                            <%--품번번--%>
                           <form:label path="pdnumNum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.numName"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="pdnumNum" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="20"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="필수 입력값 입니다."/>
                                <div class="invalid-feedback"><form:errors path="pdnumNum"></form:errors></div>
                            </div>

                            <%--품명--%>
                            <form:label path="pdnumNm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.name"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="pdnumNm" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true" maxlength="30"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="필수 입력값 입니다."/>
                                <div class="invalid-feedback"><form:errors path="pdnumNm"></form:errors></div>
                            </div>

                            <%--제품구분--%>
                            <form:label path="pdnumType" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.type"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="pdnumType" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <form:option value="">선택</form:option>
                                    <c:forEach var="type" items="${pdnumTypes}" varStatus="status">
                                        <form:option value="${type.code}"><c:out value="${type.codeNm}"/></form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="pdnumType"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--단위중량--%>
                            <form:label path="pdnumWght" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.weight"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="pdnumWght" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"
                                            onkeypress="return fn_press(event, 'number');"/>
                                <div class="invalid-feedback"><form:errors path="pdnumWght"></form:errors></div>
                            </div>

                            <%--품목규격--%>
                            <form:label path="pdnumStnd" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.standard"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="pdnumStnd" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
                                <div class="invalid-feedback"><form:errors path="pdnumStnd"></form:errors></div>
                            </div>

                            <%--품목단위--%>
                            <form:label path="pdnumUnit" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.unit"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="pdnumUnit" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--품목코드--%>
                            <form:label path="pdnumCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.code"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="pdnumCode" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="pdnumCode"></form:errors></div>
                            </div>

                            <%--품목자재코드--%>
                            <form:label path="pdnumMtrscode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.materialCode"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="pdnumMtrscode" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="pdnumCode"></form:errors></div>
                            </div>

                            <%--도면승인일--%>
                            <form:label path="pdnumDrwappldate" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.drowApproval"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <div class="input-group">
                                    <form:input path="pdnumDrwappldate" id="datepicker" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true"/>
                                    <div class="input-group-append">
                                        <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                    </div>
                                </div>
                                <div class="invalid-feedback"><form:errors path="pdnumDrwappldate"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--내외작구분--%>
                            <form:label path="pdnumIotype" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="pdnum.inOutType"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="pdnumIotype" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <form:option value="">선택</form:option>
                                    <c:forEach var="type" items="${inOutTypes}" varStatus="status">
                                        <form:option value="${type.code}"><c:out value="${type.codeNm}"/></form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="pdnumIotype"></form:errors></div>
                            </div>

                            <%--도면수량--%>
                            <form:label path="pdnumDrwqnty" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.drowQntity"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="pdnumDrwqnty" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" onkeypress="return fn_press(event, 'number')"/>
                            </div>

                            <%--안전재고--%>
                            <form:label path="pdnumSfstck" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.safeStock"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="pdnumSfstck" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" onkeypress="return fn_press(event, 'number')"/>
                                <div class="invalid-feedback"><form:errors path="pdnumSfstck"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row">
                            <form:label path="useAt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="common.useAt"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:select path="useAt" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <form:option value="Y"><spring:message code="input.yes"/></form:option>
                                    <form:option value="N"><spring:message code="input.no"/></form:option>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="useAt"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>