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
<script src="/js/datagrid/datatables/datatables.bundle.js"></script>
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
            $form.action = '<c:url value='/mes/sym/code/clcode/addForm.do'/>';
        } else if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/sym/code/clcode/list.do'/>';
        } else if (target == 'list') {
            $form = document.clCodeAddForm;
            $form.action = '<c:url value='/mes/sym/code/clcode/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = '';
        if (target == 'saveAdd') {
            $form = document.clCodeAddForm;
            $form.action = '<c:url value='/mes/sym/code/clcode/add.do'/>';
        } else if (target == 'saveEdit') {
            $form = document.clCodeEditForm;
            $form.action = '<c:url value='/mes/sym/code/clcode/edit.do'/>';
        } else if (target == 'delete') {
            $form = document.clCodeEditForm;
            $form.action = '<c:url value='/mes/sym/code/clcode/remove.do'/>';
        }
        $form.submit();
    }

    function fn_getEditFormData(obj) {
        let id = $(obj).data("id");

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/sym/code/clcode/detail.json'/>",
            data:{'clCode' : id},
            dataType:'json',
            success:function (result){
                let data = result.data;
                let $form = document.clCodeEditForm;

                $form.clCode.value = data.clCode;
                $form.clCodeNm.value = data.clCodeNm;
                $form.clCodeDc.value = data.clCodeDc;
                $form.useAt.value = data.useAt;

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }
</script>