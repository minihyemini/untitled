<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-05
  Time: 오후 5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
<div class="col-xl-12">
  <div id="panel-1" class="panel">
    <div class="panel-container show">
      <div class="panel-content">
          <%--@elvariable id="detailCodeForm" type="jisungsoft.com.persistence.dto.sym.code.CmmnDetailCode"--%>
        <form:form commandName="detailCodeForm" name="detailCodeEditForm" method="post" class="needs-validation">
          <form:hidden path="menuId" value="${menuData.menuNo}"/>

          <div class="form-row form-group">
            <div class="col-md-4 mb-3">
              <form:label path="codeId" cssClass="form-label">
                <spring:message code="code.id"/>
                <span class="text-danger">*</span>
              </form:label>
              <form:input path="codeId" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" readonly="true"/>
              <div class="invalid-feedback"><form:errors path="codeId"></form:errors></div>
            </div>
          </div>
          <div class="form-row">
            <div class="col-md-4 mb-3">
              <form:label path="code" cssClass="form-label">
                <spring:message code="detailCode.id"/>
                <span class="text-danger">*</span>
              </form:label>
              <form:input path="code" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" readonly="true"/>
              <div class="invalid-feedback"><form:errors path="code"></form:errors></div>
            </div>
            <div class="col-md-4 mb-3">
              <form:label path="codeNm" cssClass="form-label">
                <spring:message code="detailCode.name"/>
                <span class="text-danger">*</span>
              </form:label>
              <form:input path="codeNm" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20"/>
              <div class="invalid-feedback"><form:errors path="codeNm"></form:errors></div>
            </div>
            <div class="col-md-4 mb-3">
              <form:label path="useAt" cssClass="form-label">
                <spring:message code="clcode.useat"/>
              </form:label>
              <form:select path="useAt" cssClass="form-control">
                <form:option value="Y">사용</form:option>
                <form:option value="N">미사용</form:option>
              </form:select>
              <div class="invalid-feedback"><form:errors path="useAt"></form:errors></div>
            </div>
          </div>
          <div class="form-row form-group">
            <div class="col-md-12 mb-3">
              <form:label path="codeDc" cssClass="form-label">
                <spring:message code="code.desc"/>
              </form:label>
              <form:textarea path="codeDc" cssClass="form-control"></form:textarea>
              <div class="invalid-feedback"><form:errors path="codeDc"></form:errors></div>
            </div>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>
</div>