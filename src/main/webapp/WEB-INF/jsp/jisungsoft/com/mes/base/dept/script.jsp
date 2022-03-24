<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오후 3:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(document).ready(function() {
        let dtDept = $('#dt-dept').DataTable({
            scrollY: 400,
            scrollX: true,
            scrollXInner: "100%",
            scrollCollapse: true,
            select: 'single',
            paging: false,
            fixedColumns:
                {
                    leftColumns: 1
                },
            columnDefs: [
                {
                    targets: 0,
                    // visible: false,
                    searchable: false
                },
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
                    action: function(e, dt, button, config) {
                        let $form = document.searchForm;
                        $form.action = '<c:url value='/mes/base/dept/list/excel.json'/>';
                        $form.submit();
                    }
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
                        let $form = document.deptForm;
                        let title = $form.orgnztNm.value;
                        $("#remove-modal-alert .modal-title").text(title);
                        $("#remove-modal-alert").modal();
                    }
                },
            ]
        });

        $('#dt-dept tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/dept/detail.json'/>",
                data:{'id' : id},
                dataType:'json',
                success:function (result){
                    let data = result.data;
                    let $form = document.deptForm;

                    $form.orgnztId.value = data.orgnztId;
                    $form.orgnztNm.value = data.orgnztNm;
                    $form.orgnztDc.value = data.orgnztDc;

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
            $form.action = '<c:url value='/mes/base/dept/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정/삭제 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.deptForm;
        let id = $form.orgnztId.value;


        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/base/dept/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/base/dept/edit.do'/>';
            }
        } else if (target == 'delete') {
            $form.action = '<c:url value='/mes/base/dept/remove.do'/>';
        }
        $form.submit();
    }
</script>