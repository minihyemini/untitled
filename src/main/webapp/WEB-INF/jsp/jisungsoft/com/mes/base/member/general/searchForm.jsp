<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-12
  Time: 오후 4:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 검색 -->
<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2>작업자관리</h2>
                <div class="float-right m-2">
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_reset(this);" data-type="search">초기화</button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_pageAddForm(this);" data-type="addForm">신규등록</button>
                    <button type="button" class="btn btn-secondary waves-effect waves-themed" onclick="fn_dataAction(this);" data-type="edit">저장</button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="generalMember" type="jisungsoft.com.persistence.dto.member.GeneralMember"--%>
                    <form:form commandName="generalMember" name="generalMemberList" class="needs-validation" method="post">
                        <form:hidden path="menuId" value="${menuData.menuNo}"/>
                        <%--                        <form:hidden path="pageIndex"/>--%>
                        <%--                        <form:hidden path="uniqId"/>--%>

                        <div class="panel-content">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustom01">사용자명</label>
                                    <input type="text" class="form-control" id="validationCustom01">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustom02">부서명</label>
                                    <input type="text" class="form-control" id="validationCustom02">
                                </div>
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="validationCustomUsername">그룹명</label>
                                    <input type="text" class="form-control" id="validationCustomUsername">
                                </div>
                            </div>
                        </div>
                        <div class="panel-content border-faded border-left-0 border-right-0 border-bottom-0 d-flex flex-row align-items-center">
                            <button class="btn btn-primary ml-auto" type="submit">
                                <i class="fal fa-search"></i>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //검색 --%>