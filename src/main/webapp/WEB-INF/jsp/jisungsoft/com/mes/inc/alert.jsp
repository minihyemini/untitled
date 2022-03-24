<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-26
  Time: 오후 6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>

<t:importAttribute name="menuData"/>
<%--성공팝업--%>
<div class="modal fade" id="success-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <c:out value="${resultMessage}"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="success-close" data-dismiss="modal" onkeypress="alterSuccessClose(event);"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>

<!-- 저장 alert -->
<div class="modal fade" id="save-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><c:out value="${menuData.menuNm}"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="save.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="save" onclick="fn_dataAction(this);">
                    <spring:message code="button.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 수정 alert -->
<div class="modal fade" id="edit-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><c:out value="${menuData.menuNm}"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="edit.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.reset"/></button>
                <button type="button" class="btn btn-primary" data-target="edit" onclick="fn_dataAction(this);">
                    <spring:message code="button.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- 삭제 alert -->
<div class="modal fade" id="remove-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><c:out value="${menuData.menuNm}"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="remove.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <spring:message code="button.reset"/>
                </button>
                <button type="button" class="btn btn-primary" data-target="delete" onclick="fn_dataAction(this);">
                    <spring:message code="button.delete"/>
                </button>
            </div>
        </div>
    </div>
</div>

<%--데이터 없음 alert--%>
<div class="modal fade" id="nodata-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="nodata.alert"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" name="closeButton" id="nodata-close" data-dismiss="modal" onkeypress="alterClose(event);"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>

<%--선택된 데이터 없음 alert--%>
<div class="modal fade" id="selected-nodata-modal-alert" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <spring:message code="selected.nodata.message"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" name="closeButton" id="selected-nodata-close" data-dismiss="modal" onkeypress="alterClose(event);"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>