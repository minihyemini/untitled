<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<%--@elvariable id="pdNumForm" type="jisungsoft.com.persistence.dto.mes.Pdnum"--%>
<form:form commandName="pdNumForm" name="pdNumForm" method="post" class="needs-validation" enctype="multipart/form-data">
    <form:hidden path="menuId" value="${menuData.menuNo}"/>
    <form:hidden path="pdnumId"/>

    <div class="row">
        <div class="col-xl-6">
            <div id="panel-4" class="panel">
                <div class="panel-container show">
                    <div class="panel-content">
                        <table id="dt-imagefile" class="table table-bordered table-hover table-striped w-100 table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th><spring:message code="pdnum.imageName"/></th>
                                <th><spring:message code="common.fileSize"/></th>
                                <th><spring:message code="common.regist.date"/></th>
                            </tr>
                            </thead>
                                <%--
                                <tbody id="img-file-list">
                                <tr>
                                    <td colspan="2">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                                --%>
                        </table>
                    </div>
                    <div class="panel-content">
                        <div class="form-row form-group">
                            <div class="col-md-12 mb-3 height-lg">
                                <img id="img-view" class="img-fluid" src="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xl-6">
            <div id="panel-5" class="panel">
                <div class="panel-container show">
                    <div class="panel-content">
                        <div class="form-row">
                            <form:label path="imgFile" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.image"/>
                            </form:label>
                            <div class="col-8 input-group input-group-sm">
                                <div class="custom-file">
                                    <form:input path="imgFile" type="file" class="custom-file-input"/>
                                    <form:label path="imgFile" cssClass="custom-file-label">
                                        <spring:message code="button.select"/>
                                    </form:label>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <br />
                        </div>
                        <div class="form-row">
                            <form:label path="atchFile" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="pdnum.file"/>
                            </form:label>
                            <div class="col-8 input-group input-group-sm">
                                <div class="custom-file">
                                    <form:input path="atchFile" type="file" class="custom-file-input" multiple="multiple"/>
                                    <form:label path="atchFile" cssClass="custom-file-label">
                                        <spring:message code="button.select"/>
                                    </form:label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form:form>