<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오전 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    let dtClient = '';
    let dtOrder = '';

    $(document).ready(function() {
        dtClient = $('#dt-basic').DataTable({
            "scrollY": 300,
            "scrollX": true,
            scrollXInner: "200%",
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            select: 'single',
            fixedColumns:
                {
                    leftColumns: 1
                },
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "10%"
                },//수주번호
                {targets: 1, width: "10%"},//거래처명
                {targets: 2, width: "10%"},//담당자
                {targets: 3, width: "10%"},//수주일자
                {targets: 4, width: "10%"},//납기일자
                {targets: 5, width: "10%"},//상태
                {targets: 6, width: "5%"},//수량
                {targets: 7, width: "10%"},//부가세
                {targets: 8, width: "10%"},//총금액
                {targets: 9, width: "10%"},//등록일
                {targets: 10, width: "10%"},//수정일
                {targets: 11, width: "10%"},//수정자
                {targets: 12, width: "10%"},//비고
            ],
            lengthChange: false,
            dom:
                "<'row mb-3'<'col-sm-12 col-md-6 d-flex align-items-center justify-content-start'f><'col-sm-12 col-md-6 d-flex align-items-center justify-content-end'lB>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            buttons: [
                {
                    extend: 'excelHtml5',
                    text: '<spring:message code="button.excel"/>',
                    titleAttr: 'Generate Excel',
                    className: 'btn-outline-success btn-sm mr-1',
                },
                {
                    extend: 'copyHtml5',
                    text: '<spring:message code="button.copy"/>',
                    titleAttr: 'Copy to clipboard',
                    className: 'btn-outline-primary btn-sm mr-1'
                },
                {
                    extend: 'print',
                    text: '<spring:message code="button.print"/>',
                    titleAttr: 'Print Table',
                    className: 'btn-outline-primary btn-sm'
                },
            ]
        });

        dtOrder = $('#dt-basic2').DataTable({
            "scrollY": 300,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            select: 'single',
            responsive: false,
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "5%"
                },
                {targets: 1, width: "15%"},
                {targets: 2, width: "15%"},
                {targets: 3, width: "8%"},
                {targets: 4, width: "10%"},
                {targets: 5, width: "10%"},
                {targets: 6, width: "6%"},
                {targets: 7, width: "10%"},
                {targets: 8, width: "10%"},
            ],
            dom:
                "<'row mb-3'<'col-sm-12 col-md-6 d-flex align-items-center justify-content-start'f><'col-sm-12 col-md-6 d-flex align-items-center justify-content-end'lB>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
            buttons: [
                {
                    extend: 'excelHtml5',
                    text: '<spring:message code="button.excel"/>',
                    titleAttr: 'Generate Excel',
                    className: 'btn-outline-success btn-sm mr-1',
                },
                {
                    extend: 'copyHtml5',
                    text: '<spring:message code="button.copy"/>',
                    titleAttr: 'Copy to clipboard',
                    className: 'btn-outline-primary btn-sm mr-1'
                },
                {
                    extend: 'print',
                    text: '<spring:message code="button.print"/>',
                    titleAttr: 'Print Table',
                    className: 'btn-outline-primary btn-sm'
                },
                {
                    text: '<i class="fal fa-plus mr-1"></i> <spring:message code="button.field.add"/>',
                    name: 'add',
                    className: 'btn-success btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        let $form = document.orderForm;
                        let cltId = $form.cltId.value;
                        if (isEmpty(cltId)) {
                            $("#selected-nodata-modal-alert .modal-title").text('');
                            $("#selected-nodata-modal-alert .modal-body").text('<spring:message code="sales.receive.order.client.nodata"/>');
                            $("#selected-nodata-modal-alert").modal();
                            return;
                        }
                        fn_getPdNumData();
                    }
                },
                {
                    extend: 'selected',
                    text: '<i class="fal fa-times mr-1"></i> <spring:message code="button.field.delete"/>',
                    name: 'delete',
                    className: 'btn-primary btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        let $form = document.orderForm;
                        let id = $form.oiId.value;
                        if (!isEmpty(id)) {
                            $("#remove-modal-alert .modal-body").text('<spring:message code="hasdata.remove.alert"/>');
                            $("#remove-modal-alert").modal();
                        } else {
                            dt.row('.selected').remove().draw();
                        }
                    }
                },
            ],
        });

        $('#dt-basic tbody').on('click', 'tr', function(event) {
            let cltId = event.currentTarget.dataset.clt;
            let ordId = event.currentTarget.dataset.id;
            let $form = document.orderForm;
            $form.cltId.value = cltId;
            $form.ordId.value = ordId;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/sales/rod/order/list.json'/>",
                data:{'cltId':cltId, 'ordId':ordId},
                dataType:'json',
                success:function (result){
                    //테이블 초기화
                    dtOrder.clear().draw();
                    let detail = result.dataDetail;

                    $form.cltId.value = detail.cltId;
                    $form.cltCode.value = detail.cltCode;
                    $form.cltNm.value = detail.cltNm;
                    $form.ordId.value = detail.ordId;
                    $form.ordNum.value = detail.ordNum;
                    $form.orderDate.value = detail.orderDate;
                    $form.ordDlvrschdt.value = detail.ordDlvrschdt;
                    $form.ordDesc.value = detail.ordDesc;

                    let dataList = result.dataList;
                    for (let i=0; i<dataList.length; i++) {
                        let data = dataList[i];
                        fn_fieldAdd(data);
                    }

                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        });

        $('#dt-basic2 tbody').on('click', 'tr', function(event) {
            let node = dtOrder.row( this ).node();
            let id = node.children[1].lastChild.dataset.id;
            let $form = document.orderForm;
            $form.oiId.value = id;
        });

        $(document).on("click","#check-pdnum-all",function() {
            if ($("#check-pdnum-all").is(":checked")) {
                $("[name='pdnumCheck']").prop("checked", true);
            } else {
                $("[name='pdnumCheck']").prop("checked", false);
            }
        });
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = '';
        let target = $(obj).data("target");

        if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/sales/rod/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정/삭제 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.orderForm;
        let id = $form.ordId.value;

        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/sales/rod/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/sales/rod/edit.do'/>';
            }
            $form.submit();
        } else if (target == 'delete') {
            let $formData = $("form[name=orderForm]").serialize() ;
            dtOrder.row('.selected').remove().draw();

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/sales/rod/item/remove.json'/>",
                data:$formData,
                dataType:'json',
                success:function (result){
                    $("#remove-modal-alert").modal('hide');
                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        }
    }

    /*거래처 팝업*/
    function fn_getClientData(obj) {
        let type = $(obj).data('type');
        let $form = $("form[name=searchSubForm]").serialize() ;

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/sales/rod/client/list.json'/>",
            data:$form,
            dataType:'json',
            success:function (result){
                let dataList = result.dataList;
                fn_subDataList(dataList, type);
                $("#client-list-modal").modal();

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }

    function fn_subDataList(dataList, type) {
        let tbList = document.querySelector("#client-dataTable-body");
        let tbody = "<tbody>";

        for (let i=0; i<dataList.length; i++) {
            let data = dataList[i];
            tbody += "<tr>";
            tbody += "<td>"+data.cltNm+"</td>";
            tbody += "<td>"+data.cltCode+"</td>";
            tbody += "<td>"+data.cltBussnum+"</td>";
            tbody += "<td>"
            tbody += "<a href='javascript:void(0);' class='btn btn-xs btn-default waves-effect waves-themed' data-id='"+data.cltId+"' data-name='"+data.cltNm+"' data-code='"+data.cltCode+"' data-type='"+type+"' onclick='fn_selectSubData(this);'>"
            tbody += "<spring:message code="button.select"/>";
            tbody += "</td>";
            tbody += "</tr>";
        }

        tbody += "</tbody>";
        tbList.innerHTML = tbody;
    }

    function fn_selectSubData(obj) {
        let id = $(obj).data("id");
        let name = $(obj).data("name");
        let code = $(obj).data("code");
        let type = $(obj).data("type");
        if (type == 'search') {
            let $form = document.searchForm;
            $form.name.value = name;
            $form.code.value = code;
            $form.searchKeyword.value = id;

        } else if (type == 'form') {
            let $form = document.orderForm;

            $form.cltNm.value = name;
            $form.cltCode.value = code;
            $form.cltId.value = id;
        }

        $("#client-list-modal").modal('hide');
    }

    /*품번항목 팝업오픈*/
    function fn_getPdNumData() {
        let formData = $("[name='searchPdNumForm']").serialize();

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/base/pdnum/list.json'/>",
            data:formData,
            dataType:'json',
            success:function (result){
                let dataList = result.dataList;
                $("#check-pdnum-all").prop("checked", false);

                let tbList = document.querySelector("#pdnum-dataTable-body");
                let tbody = "<tbody>";

                for (let i=0; i<dataList.length; i++) {
                    let data = dataList[i];
                    let input = "<input type='checkbox' name='pdnumCheck' id='pdNum"+data.pdnumId+"' value='"+JSON.stringify(data)+"' class='form-control-input'>";
                    tbody += "<tr>";
                    tbody += "<td><div class='custom-control custom-checkbox'>"+input+"</div></td>";
                    tbody += "<td>"+data.pdnumNum+"</td>";
                    tbody += "<td>"+data.pdnumNm+"</td>";
                    tbody += "<td>"+data.pdnumTypeNm+"</td>";
                    tbody += "<td>"+data.pdnumStnd+"</td>";
                    tbody += "<td>"+data.pdnumUnit+"</td>";
                    tbody += "</tr>";
                }
                tbody += "</tbody>";
                tbList.innerHTML = tbody;

                $("#pdnum-list-modal").modal();
            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }

    /*품번항목 선택*/
    function fn_selectPdnumData() {
        $('input:checkbox[name="pdnumCheck"]').each(function() {
            if (this.checked == true) {
                let data = (JSON.parse(this.value));
                fn_fieldAdd(data);
            }
        });

        $('#pdnum-list-modal').modal('hide');
    }

    /*필드 추가*/
    function fn_fieldAdd(data) {
        let fieldCount = dtOrder.column(0).data().length;
        let no = fieldCount;
        let isDuplicated = false;

        if (fieldCount > 0) {
            /!*인풋시 중복 제거*!/
            let frm_data = $('[name="orderForm"]').serializeArray();
            $.each(frm_data, function(key, val) {
                let name = val.name.split(".")[1];
                if (!isEmpty(name)) {
                    if (name.indexOf('pdnumId') == 0) {
                        if (data.pdnumId == val.value) isDuplicated = true;
                    }
                }
            });
        } else {
            isDuplicated = false;
        }

        let tootipNoStr = "data-template='<div class=\"tooltip\" role=\"tooltip\"><div class=\"tooltip-inner bg-fusion-500\"></div></div>' data-toggle=\"tooltip\"";
        tootipNoStr += "data-original-title=\"문자형식은 입력이 불가능합니다.\"";

        let oiId = isEmpty(data.oiId) ? '' : data.oiId;
        let qunty = isEmpty(data.qunty) ? 0 : data.qunty;
        let unitPrice = isEmpty(data.unitPrice) ? 0 : data.unitPrice;
        let unitTotPrice = isEmpty(data.unitTotPrice) ? 0 : data.unitTotPrice;
        let surtaxRate = isEmpty(data.surtaxRate) ? 10 : data.surtaxRate;
        let surtax = isEmpty(data.surtax) ? 0 : data.surtax;

        if (!isDuplicated) {
            dtOrder.row.add([
                ++no,
                data.pdnumNum+
                '<input type="hidden" name="itemFormList['+fieldCount+'].pdnumId" value="'+data.pdnumId+'">'+
                '<input type="hidden" name="itemFormList['+fieldCount+'].oiId" data-id="'+oiId+'" value="'+oiId+'">',
                data.pdnumNm,
                '<div class="col-10 input-group input-group-sm"><input type="number" class="form-control form-control-sm" name="itemFormList['+fieldCount+'].qunty" value="'+qunty+'" autocomplete="true" numberOnly min="0" max="100"></div>',
                '<div class="col-10 input-group input-group-sm"><input type="text" class="form-control form-control-sm" name="itemFormList['+fieldCount+'].unitPrice" value="'+unitPrice+'" autocomplete="true" numberOnly></div>',
                unitTotPrice,
                '<div class="custom-control custom-checkbox"><input type="checkbox" class="form-control-input" id="surtaxCheck_'+fieldCount+'" name="itemFormList['+fieldCount+'].surtaxAt" value="Y" checked></div>',
                '<div class="col-10 input-group input-group-sm"><input type="number" class="form-control form-control-sm" name="itemFormList['+fieldCount+'].surtaxRate" value="'+surtaxRate+'" autocomplete="true" numberOnly min="0" max="100"></div>',
                surtax
            ]).draw(false);
        }
    }

</script>
