<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="col-xl-6">
    <div id="panel-1" class="panel">
        <div class="panel-container show">
            <div class="panel-content">
                <%--@elvariable id="menuForm" type="jisungsoft.com.persistence.dto.sym.menu.Menu"--%>
                <form:form commandName="menuForm" name="menuForm" method="post" class="needs-validation">
                    <form:hidden path="menuNo"/>
                    <form:hidden path="upperMenuNo"/>
                    <form:hidden path="menuCategory" value="MES"/>
                    <form:hidden path="menuLv"/>
                    <form:hidden path="progrmId"/>

                    <div class="form-row form-group">
                        <div class="col-md-12 mb-3">
                            <form:label path="menuNm" cssClass="form-label">
                                <spring:message code="menu.name"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <form:input path="menuNm" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                            <div class="invalid-feedback"><form:errors path="menuNm"></form:errors></div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <form:label path="progrmFileNm" cssClass="form-label">
                                <spring:message code="menu.program.name"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <form:input path="progrmFileNm" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true"/>
                            <div class="invalid-feedback"><form:errors path="progrmFileNm"></form:errors></div>
                        </div>
                        <div class="col-md-4 mt-4">
                            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#menuProgram-list-modal"><spring:message code="button.search"/></button>
                        </div>
                        <div class="col-md-6 mb-3">
                            <form:label path="authorCode" cssClass="form-label">
                                <spring:message code="menu.auth"/>
                            </form:label>
                            <c:forEach var="auth" items="${authList}" varStatus="status">
                                <c:if test="${!fn:contains(auth.authorCode, 'IS_AUTHENTICATED_')}">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" name="authorCode" id="auth_${status.count}" value="${auth.authorCode}">
                                        <label class="custom-control-label" for="auth_${status.count}">${auth.authorNm}</label>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <div class="invalid-feedback"><form:errors path="authorCode"></form:errors></div>
                        </div>
                        <div class="col-md-12 mb-3">
                            <form:label path="relateImageNm" cssClass="form-label">
                                <spring:message code="menu.imageName"/>
                            </form:label>
                            <form:input path="relateImageNm" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                            <div class="invalid-feedback"><form:errors path="relateImageNm"></form:errors></div>
                        </div>
                        <div class="col-md-12 mb-3">
                            <form:label path="relateImagePath" cssClass="form-label">
                                <spring:message code="menu.imagePath"/>
                            </form:label>
                            <form:input path="relateImagePath" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                            <div class="invalid-feedback"><form:errors path="relateImagePath"></form:errors></div>
                        </div>
                        <div class="col-md-12 mb-3">
                            <form:label path="menuOrdr" cssClass="form-label">
                                <spring:message code="menu.order"/>
                            </form:label>
                            <form:input path="menuOrdr" type="number" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="3"/>
                            <div class="invalid-feedback"><form:errors path="menuOrdr"></form:errors></div>
                        </div>
                        <div class="col-md-12 mb-3">
                            <form:label path="menuDc" cssClass="form-label">
                                <spring:message code="menu.desc"/>
                            </form:label>
                            <form:textarea path="menuDc" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                            <div class="invalid-feedback"><form:errors path="menuDc"></form:errors></div>
                        </div>
                    </div>
                    <div class="form-row form-group">
                        <div class="col-md-6 mb-3">
                            <form:label path="useAt" cssClass="form-label">
                                <spring:message code="menu.useAt"/>
                            </form:label>
                            <form:select path="useAt" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                <form:option value="N">미사용</form:option>
                                <form:option value="Y">사용</form:option>
                            </form:select>
                            <div class="invalid-feedback"><form:errors path="useAt"></form:errors></div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <form:label path="targetAt" cssClass="form-label">
                                <spring:message code="menu.targetAt"/>
                            </form:label>
                            <form:select path="targetAt" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                <form:option value="N">미사용</form:option>
                                <form:option value="Y">사용</form:option>
                            </form:select>
                            <div class="invalid-feedback"><form:errors path="targetAt"></form:errors></div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>