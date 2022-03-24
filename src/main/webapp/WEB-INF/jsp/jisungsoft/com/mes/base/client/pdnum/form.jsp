<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-25
  Time: 오후 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-xl-6">
    <div id="panel-3" class="panel">
        <%--@elvariable id="clientPdNumForm" type="jisungsoft.com.persistence.dto.mes.PdnumClient"--%>
        <form:form commandName="clientPdNumForm" name="clientPdNumForm" method="post">
            <form:hidden path="cltId"/>

            <div class="panel-hdr">
                <div class="ml-1">
                    <button class="btn btn-success btn-sm mr-1" type="button" onclick="fn_getPdNumData();">
                        <span><i class="fal fa-plus mr-1"></i> <spring:message code="FciltsPdNum.add.pdnum"/></span>
                    </button>
                </div>
            </div>

            <div class="panel-container show">
                <div class="panel-content">
                    <!-- datatable start -->
                    <table id="dt-pdnum" class="table table-bordered table-hover table-striped table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th>
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" name="formCheck" id="form-check-all" class="form-control-input"/>
                                </div>
                            </th>
                            <th><spring:message code="pdnum.numName"/></th>
                            <th><spring:message code="pdnum.name"/></th>
                            <th><spring:message code="pdnum.type"/></th>
                            <th><spring:message code="pdnum.standard"/></th>
                            <th><spring:message code="pdnum.unit"/></th>
                        </tr>
                        </thead>
                    </table>
                    <!-- datatable end -->
                </div>
            </div>
        </form:form>
    </div>
</div>

<%--품번목록 팝업--%>
<div class="modal fade" id="pdnum-list-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><spring:message code="pdnum.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><i class="fal fa-times"></i></span>
                </button>
            </div>
            <div class="modal-body">
                <div class="panel-hdr">
                    <div class="col-12">
                        <form name="searchPdNumForm" method="post">
                            <div class="form-row">
                                <div class="col-md-4 mb-3">
                                    <label class="form-label" for="searchKeyword"><spring:message code="title.search"/></label>
                                    <input type="text" class="form-control" name="searchKeyword" id="searchKeyword">
                                </div>
                                <div class="col-md-3 mb-3">
                                    <label class="form-label" for="searchCondition"><spring:message code="title.searchCondition"/></label>
                                    <select name="searchCondition" class="form-control" name="searchCondition" id="searchCondition">
                                        <option value="0"><spring:message code="title.all"/></option>
                                        <option value="1"><spring:message code="pdnum.numName"/></option>
                                        <option value="2"><spring:message code="pdnum.name"/></option>
                                    </select>
                                </div>
                                <div class="col-md-3 mt-4">
                                    <button type="submit" class="btn btn-primary ml-auto" data-target="search">
                                        <i class="fal fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel-container show">
                    <div class="panel-content">
                        <div class="dataTables-scroll">
                            <table id="dt-pdnum-list" class="table table-bordered table-hover table-striped table-sm">
                                <thead class="thead-dark">
                                <tr>
                                    <th>
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" class="form-control-input" id="check-pdnum-all">
                                        </div>
                                    </th>
                                    <th><spring:message code="pdnum.numName"/></th>
                                    <th><spring:message code="pdnum.name"/></th>
                                    <th><spring:message code="pdnum.type"/></th>
                                    <th><spring:message code="pdnum.standard"/></th>
                                    <th><spring:message code="pdnum.unit"/></th>
                                </tr>
                                </thead>
                                <tbody id="pdnum-dataTable-body">
                                <tr>
                                    <td colspan="5">
                                        <div class="text-center">
                                            <spring:message code="info.nodata.msg"/>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="button.close"/></button>
                <button type="button" class="btn btn-primary" id="addRowBtn" onclick="fn_selectPdnumData(this);"><spring:message code="button.add"/></button>
            </div>
        </div>
    </div>
</div>
<%--//품번목록 팝업--%>
