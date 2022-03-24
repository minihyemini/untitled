<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-24
  Time: 오전 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>

    function getPlcData() {
        let data = 'D704';

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/sym/plc/send.json'/>",
            data:{'data' : data},
            dataType:'json',
            success:function (result){
                // let data = result.result;
                console.log(result);

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }
</script>