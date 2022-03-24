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
                    <%--@elvariable id="pdfciltsForm" type="jisungsoft.com.persistence.dto.mes.Pdfcilts"--%>
                    <form:form commandName="pdfciltsForm" name="pdfciltsForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>
                        <form:hidden path="pdfciltsId" />

                        <div class="form-row form-group">
                            <div class="col-md-4 mb-3">
                                <form:label path="pdfciltsCode" cssClass="form-label">
                                    <spring:message code="pdfciltsCode.code"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="pdfciltsCode" cssClass="form-control" autocomplete="true"
                                            cssErrorClass="form-control is-invalid" maxlength="10"/>
                                <div class="invalid-feedback"><form:errors path="pdfciltsCode"></form:errors></div>
                            </div>

                            <div class="col-md-4 mb-3">
                                <form:label path="pdfciltsNm" cssClass="form-label">
                                    <spring:message code="pdfciltsCode.name"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="pdfciltsNm" cssClass="form-control" autocomplete="true"
                                            cssErrorClass="form-control is-invalid" maxlength="10" />
                                <div class="invalid-feedback"><form:errors path="pdfciltsNm"></form:errors></div>
                            </div>

                            <div class="col-md-2 mb-3">
                                <form:label path="useAt" cssClass="form-label">
                                    <spring:message code="clcode.useat"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:select path="useAt" cssClass="form-control">
                                    <form:option value="Y">사용</form:option>
                                    <form:option value="N">미사용</form:option>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="useAt"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-4 mb-3">
                                <form:label path="plcDvcode" cssClass="form-label">
                                    <spring:message code="pdfciltsCode.plcCode"/>
                                </form:label>
                                <form:input path="plcDvcode" cssClass="form-control" autocomplete="true"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="관리자 외 수정금지 항목입니다."
                                            cssErrorClass="form-control is-invalid" maxlength="10" />
                                <div class="invalid-feedback"><form:errors path="plcDvcode"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-10 mb-3">
                                <form:label path="pdfciltsDc" cssClass="form-label">
                                    <spring:message code="pdfciltsCode.desc"/>
                                </form:label>
                                <form:textarea path="pdfciltsDc" cssClass="form-control"></form:textarea>
                                <div class="invalid-feedback"><form:errors path="pdfciltsDc"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>