<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-29
  Time: 오후 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-lg-12">
        <div id="panel-1" class="panel">
            <div class="panel-hdr">
                <h2>
                    생산실적<%-- <span class="fw-300"><i>Sink (example)</i></span>--%>
                </h2>
                <div class="panel-toolbar">
                    <button class="btn btn-panel" data-action="panel-collapse" data-toggle="tooltip" data-offset="0,10" data-original-title="Collapse"></button>
                    <button class="btn btn-panel" data-action="panel-fullscreen" data-toggle="tooltip" data-offset="0,10" data-original-title="Fullscreen"></button>
                    <button class="btn btn-panel" data-action="panel-close" data-toggle="tooltip" data-offset="0,10" data-original-title="Close"></button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <div class="panel-tag">
                        월 생산실적
                    </div>

                    <div id="js-checkbox-toggles" class="d-flex mb-3">
                        <div class="custom-control custom-switch mr-2">
                            <input type="checkbox" class="custom-control-input" name="gra-0" id="gra-0" checked="">
                            <label class="custom-control-label" for="gra-0">생산계획</label>
                        </div>
                        <div class="custom-control custom-switch mr-2">
                            <input type="checkbox" class="custom-control-input" name="gra-1" id="gra-1" checked="">
                            <label class="custom-control-label" for="gra-1">생산량</label>
                        </div>
                        <div class="custom-control custom-switch mr-2">
                            <input type="checkbox" class="custom-control-input" name="gra-2" id="gra-2" checked="">
                            <label class="custom-control-label" for="gra-2">불량수량</label>
                        </div>
                    </div>
                    <div id="flot-toggles" class="w-100 mt-4" style="height: 300px"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div id="panel-2" class="panel">
            <div class="panel-hdr">
                <h2>
                    영업실적<%-- <span class="fw-300"><i>Chart (example)</i></span>--%>
                </h2>
                <div class="panel-toolbar">
                    <button class="btn btn-panel" data-action="panel-collapse" data-toggle="tooltip" data-offset="0,10" data-original-title="Collapse"></button>
                    <button class="btn btn-panel" data-action="panel-fullscreen" data-toggle="tooltip" data-offset="0,10" data-original-title="Fullscreen"></button>
                    <button class="btn btn-panel" data-action="panel-close" data-toggle="tooltip" data-offset="0,10" data-original-title="Close"></button>
                </div>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <div class="panel-tag">
                        월 영업실적
                    </div>
                    <div id="flot-sales" class="w-100" style="height: 350px"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div id="panel-3" class="panel">
            <div class="panel-hdr">
                <h2 class="js-get-date"></h2>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <div id="calendar"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div id="panel-4" class="panel">
            <div class="panel-hdr">
                <h2>공지사항</h2>
            </div>
            <div class="panel-container show">
                <div class="panel-content">
                    <table id="dt-bbs" class="table table-bordered table-hover table-striped w-100 table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach begin="1" end="10" step="1" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>공지사항_TEST_${status.count}</td>
                                <td>홍길동</td>
                                <td>2022-01-01</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </div>
    </div>
</div>