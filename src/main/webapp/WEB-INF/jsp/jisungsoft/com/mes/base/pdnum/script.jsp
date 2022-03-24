<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script>
    $(document).ready(function() {
        let table = $('#dt-basic').DataTable({
            scrollY: 400,
            scrollX: true,
            scrollXInner: "200%",
            scrollCollapse: true,
            select: 'single',
            paging: false,
            fixedColumns:
                {
                    leftColumns: 1
                },
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
                    /* excel api 개발 후 주석제거
                    action: function(e, dt, button, config) {
                        let $form = document.searchForm;
                        $form.action = '<c:url value='/mes/base/pdnum/list/excel.json'/>';
                        $form.submit();
                    }
                    */
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
                    extend: 'selected',
                    text: '<i class="fal fa-times mr-1"></i> <spring:message code="button.field.delete"/>',
                    name: 'delete',
                    className: 'btn-primary btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        let $form = document.pdNumForm;
                        let title = $form.pdnumNum.value;
                        $("#remove-modal-alert .modal-title").text(title);
                        $("#remove-modal-alert").modal();
                    }
                },
            ],
        });

        $('#dt-basic tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/pdnum/detail.json'/>",
                data:{'pdnumId' : id},
                dataType:'json',
                success:function (result){
                    let data = result.data;
                    let $form = document.pdNumForm;

                    $form.pdnumId.value = data.pdnumId;
                    $form.pdnumNum.value = data.pdnumNum;
                    $form.pdnumNm.value = data.pdnumNm;
                    $form.pdnumType.value = data.pdnumType;
                    $form.pdnumWght.value = data.pdnumWght;
                    $form.pdnumStnd.value = data.pdnumStnd;
                    $form.pdnumSfstck.value = data.pdnumSfstck;
                    $form.pdnumCode.value = data.pdnumCode;
                    $form.pdnumMtrscode.value = data.pdnumMtrscode;
                    $form.pdnumIotype.value = data.pdnumIotype;
                    $form.pdnumDrwappldate.value = data.pdnumDrwappldate;
                    $form.pdnumDrwqnty.value = data.pdnumDrwqnty;
                    $form.pdnumUnit.value = data.pdnumUnit;
                    $form.useAt.value = data.useAt;
                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        })
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = '';
        let target = $(obj).data("target");

        if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/base/pdnum/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.pdNumForm;
        let id = $form.pdnumId.value;


        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/base/pdnum/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/base/pdnum/edit.do'/>';
            }
        } else if (target == 'delete') {
            $form.action = '<c:url value='/mes/base/pdnum/remove.do'/>';
        }
        $form.submit();
    }

    function fn_getImage(obj) {
        let id = $(obj).data("id");
        let sn = $(obj).data("sn");

        $("#img-view").attr("src", "/cmm/photoView.do?atchFileId=" + id + "&sn=" + sn);
    }
</script>