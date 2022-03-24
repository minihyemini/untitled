<%--
  Created by IntelliJ IDEA.
  User: Hole
  Date: 2022-02-04
  Time: 오후 6:54
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
        $('#dt-basic').DataTable({
            "scrollY": 320,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            fixedColumns:
                {
                    leftColumns: 2
                },
        });
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = '';
        let target = $(obj).data("target");
        if (target == 'addForm') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/sym/code/detail/addForm.do'/>';
        } else if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/sym/code/detail/list.do'/>';
        } else if (target == 'list') {
            $form = document.detailCodeAddForm;
            $form.action = '<c:url value='/mes/sym/code/detail/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = '';
        if (target == 'saveAdd') {
            $form = document.detailCodeAddForm;
            $form.action = '<c:url value='/mes/sym/code/detail/add.do'/>';
        } else if (target == 'saveEdit') {
            $form = document.detailCodeEditForm;
            $form.action = '<c:url value='/mes/sym/code/detail/edit.do'/>';
        } else if (target == 'delete') {
            $form = document.detailCodeEditForm;
            $form.action = '<c:url value='/mes/sym/code/detail/remove.do'/>';
        }
        $form.submit();
    }

    function fn_getEditFormData(obj) {
        let id = $(obj).data("id");

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/sym/code/detail/detail.json'/>",
            data:{'code' : id},
            dataType:'json',
            success:function (result){
                let data = result.data;
                let $form = document.detailCodeEditForm;

                $form.codeId.value = data.codeId;
                $form.code.value = data.code;
                $form.codeNm.value = data.codeNm;
                $form.codeDc.value = data.codeDc;
                $form.useAt.value = data.useAt;

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }

    function fn_selectCode(obj) {
        let id = $(obj).data("id");
        let $form = document.detailCodeAddForm;
        $form.codeId.value = id;
        $('#code-list-modal').modal('hide');
    }

    function fn_searchCodeData(obj) {
        var formData = $("[name='searchCodeForm']").serialize();

        $.ajax({
            type:"POST",
            async:false,
            url:"<c:url value='/mes/sym/code/code/list.json'/>",
            data:formData,
            dataType:'json',
            success: function (result) {
                let dataResult = result.data;
                let tbList = document.querySelector("#code-dataTable-body");
                let tbody = "<tbody>";
                /*tbody*/
                for (var i=0; i<dataResult.length; i++) {
                    let result = dataResult[i];
                    let lastUpdusrUserId = isEmpty(result.lastUpdusrUserId) ? '' : result.lastUpdusrUserId;
                    let lastUpdtPnttm = isEmpty(result.lastUpdtPnttm) ? '' : result.lastUpdtPnttm;

                    tbody += "<tr onclick='fn_selectCode(this);' data-id='"+result.codeId+"'>";
                    tbody += "<td>"+result.codeId+"</td>";
                    tbody += "<td>"+result.codeIdNm+"</td>";
                    tbody += "<td>"+result.frstRegisterUserId+"</td>";
                    tbody += "<td>"+result.frstRegistPnttm+"</td>";
                    tbody += "<td>"+lastUpdusrUserId+"</td>";
                    tbody += "<td>"+lastUpdtPnttm+"</td>";
                    tbody += "</tr>";
                }
                tbody += "</tbody>";

                tbList.innerHTML = tbody;

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert("실패하였습니다. 관리자에게 문의하세요.");
            }
        });
    }
</script>