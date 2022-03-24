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
        let table = $('#dt-basic').DataTable({
            "scrollY": 400,
            scrollX: true,
            scrollCollapse: true,
            fixedColumns:   false,
            paging: false,
            select: 'single',
        });

        $('#dt-basic tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;
            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/process/routing/detail.json'/>",
                data:{'code' : id},
                dataType:'json',
                success:function (result){
                    let data = result.data;
                    let $form = document.detailCodeForm;

                    $form.codeId.value = data.codeId;
                    $form.code.value = data.code;
                    $form.codeNm.value = data.codeNm;
                    $form.codeDc.value = data.codeDc;
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
            $form.action = '<c:url value='/mes/base/process/routing/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.detailCodeForm;
        if (target == 'save') {
            $form.action = '<c:url value='/mes/base/process/routing/add.do'/>';
        } else if (target == 'delete') {
            $form.action = '<c:url value='/mes/base/process/routing/remove.do'/>';
        }
        $form.submit();
    }
</script>