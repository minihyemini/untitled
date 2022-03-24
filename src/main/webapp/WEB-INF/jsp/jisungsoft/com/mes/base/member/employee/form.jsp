<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-06
  Time: 오후 3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
    <div class="col-xl-12">
        <div id="panel-1" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <%--@elvariable id="memberForm" type="jisungsoft.com.mes.base.mber.form.MemberForm"--%>
                    <form:form commandName="memberForm" name="memberForm" method="post" class="needs-validation">
                        <form:hidden path="uniqId"/>
                        <form:hidden path="userTy"/>
                        <form:hidden path="sbscrbDe"/>

                        <div class="form-row">
                            <%--성명--%>
                            <form:label path="name" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="member.name"/>
                            </form:label>

                            <div class="col-3 input-group input-group-sm">
                                <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="10" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="name"></form:errors></div>
                            </div>

                            <%--사원번호--%>
                            <form:label path="id" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.id"/>
                                <span class="text-danger">*</span>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="id" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="20" readonly="true"
                                            data-template='<div class="tooltip" role="tooltip"><div class="tooltip-inner bg-fusion-500"></div></div>' data-toggle="tooltip"
                                            data-original-title="사원번호는(년도_2자리+그룹ID_2자리+인덱스3자리) 자동생성 됩니다."/>
                            </div>

                            <%--이메일--%>
                            <form:label path="email" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.email"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input type="email" path="email" cssClass="form-control" cssErrorClass="form-control is-invalid" maxlength="30" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="email"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--휴대전화번호--%>
                            <form:label path="mobileNum" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.mobile"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="mobileNum" data-inputmask="'mask': '999-9999-9999'" class="form-control" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="mobileNum"></form:errors></div>
                            </div>

                            <%--사무실전화번호--%>
                            <form:label path="offmTelno" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.tel"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="offmTelno" data-inputmask="'mask': '999-9999-9999'" class="form-control" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="offmTelno"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--성별--%>
                            <form:label path="sexdstnCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.sexdstn"/>
                            </form:label>
                            <div class="col-1 input-group input-group-sm">
                                <form:select path="sexdstnCode" cssClass="form-control">
                                    <form:option value="${SexdstnCode.M.name()}"><spring:message code="member.male"/></form:option>
                                    <form:option value="${SexdstnCode.F.name()}"><spring:message code="member.female"/></form:option>
                                </form:select>
                            </div>

                            <%--외국인여부--%>
                            <form:label path="foreignerAt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.foreignerAt"/>
                            </form:label>
                            <div class="col-1 input-group input-group-sm">
                                <form:select path="foreignerAt" cssClass="form-control">
                                    <form:option value="N"><spring:message code="member.local"/></form:option>
                                    <form:option value="Y"><spring:message code="member.foreigner"/></form:option>
                                </form:select>
                            </div>

                            <%--근태체크여부--%>
                            <form:label path="attendanceAt" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.attendanceAt"/>
                            </form:label>
                            <div class="col-1 input-group input-group-sm">
                                <form:select path="attendanceAt" cssClass="form-control">
                                    <form:option value="Y"><spring:message code="input.yes"/></form:option>
                                    <form:option value="N"><spring:message code="input.no"/></form:option>
                                </form:select>
                            </div>

                            <%--근태선택여부--%>
                            <form:label path="emplyrSttusCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.emplyrSttusCode"/>
                            </form:label>
                            <div class="col-1 input-group input-group-sm">
                                <form:select path="emplyrSttusCode" cssClass="form-control">
                                    <c:forEach var="statusCode" items="${mberStatusCodes}" varStatus="status">
                                        <form:option value="${statusCode.code}"><c:out value="${statusCode.codeNm}"/></form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--권한정보--%>
                            <form:label path="authorCode" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.author"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:select path="authorCode" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <c:forEach var="author" items="${authorInfoList}" varStatus="status">
                                        <form:option value="${author.authorCode}"
                                                     selected="${fn:contains(author.authorCode,'ROLE_USER') ? 'true' : 'false'}"
                                                     disabled="${fn:contains(author.authorCode,'IS_AUTHENTICATED') ? 'true' : 'false'}">
                                            <c:out value="${author.authorNm}"/>
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="authorCode"></form:errors></div>
                            </div>

                            <%--그룹--%>
                            <form:label path="groupId" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.group.name"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:select path="groupId" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <c:forEach var="group" items="${groupList}" varStatus="status">
                                        <form:option value="${group.groupId}"><c:out value="${group.groupNm}"/></form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="groupId"></form:errors></div>
                            </div>

                            <%--직위명--%>
                            <form:label path="ofcpsNm" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.position.name"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:select path="ofcpsNm" cssClass="form-control" cssErrorClass="form-control is-invalid">
                                    <c:forEach var="pos" items="${positionList}" varStatus="status">
                                        <form:option value="${pos.code}"><c:out value="${pos.codeNm}"/></form:option>
                                    </c:forEach>
                                </form:select>
                                <div class="invalid-feedback"><form:errors path="ofcpsNm"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--생년월일--%>
                            <form:label path="birthday" cssClass="col-form-label col-1 form-label text-lg-right">
                                <span class="text-danger">*</span><spring:message code="member.birthday"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="birthday" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="Select date" id="datepicker" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="birthday"></form:errors></div>
                            </div>

                            <%--입사일--%>
                            <form:label path="joiningDate" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="member.joinDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="joiningDate" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="Select date" id="datepicker2" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="joiningDate"></form:errors></div>
                            </div>

                            <%--퇴사일--%>
                            <form:label path="quittingDate" cssClass="col-form-label col-2 form-label text-lg-right">
                                <spring:message code="member.quitDate"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="quittingDate" cssClass="form-control" cssErrorClass="form-control is-invalid" placeholder="Select date" id="datepicker3" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                                <div class="invalid-feedback"><form:errors path="quittingDate"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--우편번호--%>
                            <form:label path="zip" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.zip"/>
                            </form:label>
                            <div class="col-2 input-group input-group-sm">
                                <form:input path="zip" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true"/>
                                <div class="input-group-append height-2">
                                    <button type="button" class="btn btn-outline-default waves-effect waves-themed" onclick="execDaumPostcode();">
                                        <spring:message code="button.find.address"/>
                                    </button>
                                </div>
                                <div class="invalid-feedback"><form:errors path="zip"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--주소--%>
                            <form:label path="address" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.address"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="address" cssClass="form-control" cssErrorClass="form-control is-invalid" readonly="true"/>
                                <div class="invalid-feedback"><form:errors path="address"></form:errors></div>
                            </div>
                        </div>

                        <div class="form-row">
                            <%--상세주소--%>
                            <form:label path="detailAddress" cssClass="col-form-label col-1 form-label text-lg-right">
                                <spring:message code="member.address.detail"/>
                            </form:label>
                            <div class="col-3 input-group input-group-sm">
                                <form:input path="detailAddress" cssClass="form-control" cssErrorClass="form-control is-invalid" autocomplete="true"/>
                                <div class="invalid-feedback"><form:errors path="detailAddress"></form:errors></div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>