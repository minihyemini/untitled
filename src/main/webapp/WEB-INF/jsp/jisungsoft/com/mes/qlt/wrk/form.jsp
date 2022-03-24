<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-22
  Time: 오후 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 테이블 데이터--%>
<div class="row">
    <div class="col-xl-12">
        <div id="panel-4" class="panel">
            <div class="panel-container show">
                <div class="panel-content">
                    <form>
                        <div class="form-row">
                            <%--
                            <label class="col-form-label col-1 form-label text-lg-right">품명</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="text" class="form-control" readonly>
                                <input type="text" class="form-control">
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-search"></i></span>
                                </div>
                            </div>
                            --%>

                            <label class="col-form-label col-1 form-label text-lg-right">입고수량</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="number" class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">검사수량</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="number" class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">대기수량</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="number" class="form-control">
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">우선순위</label>
                            <div class="col-2 input-group input-group-sm">
                                <input type="number" class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">생산의뢰수량</label>
                            <div class="col-2 input-group input-group-sm">
                                <input class="form-control">
                            </div>

                            <label class="col-form-label col-1 form-label text-lg-right">생산완료요청일</label>
                            <div class="col-2 input-group input-group-sm">
                                <input class="form-control" id="datepicker3" readonly>
                                <div class="input-group-append height-2">
                                    <span class="input-group-text fs-xl"><i class="fal fa-calendar"></i></span>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <label class="col-form-label col-1 form-label text-lg-right">비고</label>
                            <div class="col-5 input-group input-group-sm">
                                <textarea class="form-control"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%-- //테이블 데이터--%>