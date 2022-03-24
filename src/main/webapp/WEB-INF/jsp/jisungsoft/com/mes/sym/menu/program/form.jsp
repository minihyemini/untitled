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
                    <%--@elvariable id="menuProgramForm" type="jisungsoft.com.persistence.dto.sym.menu.MenuProgram"--%>
                    <form:form commandName="menuProgramForm" name="menuProgramForm" method="post" class="needs-validation">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>
                        <form:hidden path="progrmId"/>
                        <div class="form-row form-group">
                            <div class="col-md-6 mb-3">
                                <form:label path="progrmFileNm" cssClass="form-label">
                                    <spring:message code="menuProgram.name"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="progrmFileNm" cssClass="form-control"
                                            cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="progrmFileNm"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-6 mb-3">
                                <form:label path="progrmKoreanNm" cssClass="form-label">
                                    <spring:message code="menuProgram.nameKorean"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="progrmKoreanNm" cssClass="form-control"
                                            cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="progrmKoreanNm"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-6 mb-3">
                                <form:label path="url" cssClass="form-label">
                                    <spring:message code="menuProgram.url"/>
                                    <span class="text-danger">*</span>
                                </form:label>
                                <form:input path="url" cssClass="form-control"
                                            cssErrorClass="form-control is-invalid" />
                                <div class="invalid-feedback"><form:errors path="url"></form:errors></div>
                            </div>
                        </div>
                        <div class="form-row form-group">
                            <div class="col-md-6 mb-3">
                                <form:label path="progrmDc" cssClass="form-label">
                                    <spring:message code="menuProgram.desc"/>
                                </form:label>
                                <form:textarea path="progrmDc" cssClass="form-control"
                                            cssErrorClass="form-control is-invalid"/>
                                <div class="invalid-feedback"><form:errors path="progrmDc"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>