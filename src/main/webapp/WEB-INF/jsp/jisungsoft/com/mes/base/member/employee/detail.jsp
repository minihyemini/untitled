<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-20
  Time: 오후 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- Modal center Large -->
<div class="modal fade" id="member-detail-modal-lg-center" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="member.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-row">
                    <table class="table table-responsive dtr-details w-100">
                        <colgroup>
                            <col class="w-50">
                            <col class="w-100">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th><spring:message code="member.name"/>:</th>
                            <td><div id="member-name"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.id"/>:</th>
                            <td><div id="member-id"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.email"/>:</th>
                            <td><div id="member-email"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.mobile"/>:</th>
                            <td><div id="member-mobile"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.tel"/>:</th>
                            <td><div id="member-tel"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.sexdstn"/>:</th>
                            <td><div id="member-sexdstn"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.foreignerAt"/>:</th>
                            <td><div id="member-foreignerAt"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.attendanceAt"/>:</th>
                            <td><div id="member-attendanceAt"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.emplyrSttusCode"/>:</th>
                            <td><div id="member-emplyrSttusCode"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.author"/>:</th>
                            <td><div id="member-author"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.position.name"/>:</th>
                            <td><div id="member-position-name"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.birthday"/>:</th>
                            <td><div id="member-birthday"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.joinDate"/>:</th>
                            <td><div id="member-joinDate"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.quitDate"/>:</th>
                            <td><div id="member-quitDate"></div></td>
                        </tr>
                        <tr>
                            <th> <spring:message code="member.zip"/>:</th>
                            <td><div id="member-zip"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.address"/>:</th>
                            <td><div id="member-address"></div></td>
                        </tr>
                        <tr>
                            <th><spring:message code="member.address.detail"/>:</th>
                            <td><div id="member-address-detail"></div></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
            </div>
        </div>
    </div>
</div>