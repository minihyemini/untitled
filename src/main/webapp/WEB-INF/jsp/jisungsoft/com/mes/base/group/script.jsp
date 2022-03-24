<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-20
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(document).ready(function() {
        $('#dt-group').DataTable({
            "scrollY": 300,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            select: 'single',
            /*
            fixedColumns:
                {
                    leftColumns: 2
                },
            */
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
                        $form.action = '<c:url value='/mes/base/group/list/excel.json'/>';
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
                        let $form = document.groupForm;
                        let title = $form.orgnztNm.value;
                        $("#remove-modal-alert .modal-title").text(title);
                        $("#remove-modal-alert").modal();
                    }
                },
            ]
        });

        $('#dt-group tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/group/detail.json'/>",
                data:{'id' : id},
                dataType:'json',
                success:function (result){
                    let data = result.data;
                    let $form = document.groupForm;

                    $form.groupId.value = data.groupId;
                    $form.orgnztId.value = data.orgnztId;
                    $form.orgnztNm.value = data.orgnztNm;
                    $form.groupNm.value = data.groupNm;
                    $form.groupDc.value = data.groupDc;

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
            $form.action = '<c:url value='/mes/base/group/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정/삭제 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.groupForm;
        let id = $form.groupId.value;

        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/base/group/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/base/group/edit.do'/>';
            }
        } else if (target == 'delete') {
            $form.action = '<c:url value='/mes/base/group/remove.do'/>';
        }
        $form.submit();
    }

    function fn_getDeptData() {
        let $form = $("form[name=searchSubForm]").serialize() ;

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/base/dept/list.json'/>",
            data:$form,
            dataType:'json',
            success:function (result){
                let dataList = result.dataList;
                let tbList = document.querySelector("#dept-dataTable-body");
                let tbody = "<tbody>";

                for (let i=0; i<dataList.length; i++) {
                    let data = dataList[i];
                    tbody += "<tr>";
                    tbody += "<td>"+data.orgnztId+"</td>";
                    tbody += "<td>"+data.orgnztNm+"</td>";
                    tbody += "<td><a href='javascript:void(0);' class='btn btn-xs btn-default waves-effect waves-themed' data-id='"+data.orgnztId+"' data-name='"+data.orgnztNm+"' onclick='fn_selectDeptData(this);'>"
                    tbody += "<spring:message code="button.select"/></td>";
                    tbody += "</tr>";
                }
                tbody += "</tbody>";
                tbList.innerHTML = tbody;

                $("#dept-list-modal").modal();

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }

    function fn_selectDeptData(obj) {
        let id = $(obj).data("id");
        let name = $(obj).data("name");

        let $form = document.groupForm;
        $form.orgnztId.value = id;
        $form.orgnztNm.value = name;

        $("#dept-list-modal").modal('hide');
    }
</script>