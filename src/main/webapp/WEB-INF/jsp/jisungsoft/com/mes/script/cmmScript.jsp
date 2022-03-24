<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-28
  Time: 오전 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:importAttribute name="menuData"/>

<script>
    $(document).ready(function(){
        $(":input").inputmask();

        /*데이터테이블 공통*/
        $.extend($.fn.dataTable.defaults, {
            searching: false,
            autoWidth: false,
            ordering:  false,
            lengthChange: false,
            emptyTable: '',
            scrollCollapse: true,
            paging: false,
            language: {
                infoEmpty: '',
                emptyTable: '<spring:message code="register.nodata.message"/>',
            }
        });

    });

    $.fn.datepicker.defaults.autoclose = true;
    // $.fn.datepicker.defaults.language = 'ko';
    $.fn.datepicker.defaults.format = 'yyyymmdd';
    $("#datepicker").datepicker();
    $("#datepicker2").datepicker();
    $("#datepicker3").datepicker();
    $("#datepicker4").datepicker();

    function actionLogout() {
        document.logout.action = "<c:url value='/logoutAction.do'/>";
        document.logout.submit();
    }

    //menu location
    function fn_menuAction(obj) {
        let menuId = $(obj).data("id");
        let upperMenuId = $(obj).data("upper");
        let url = $(obj).data("url");
        let target = $(obj).data("target");
        let query = $(obj).data("query");
        let form = document.menuLocationForm;
        form.target = '';

        $("input[name='menuId']").val(menuId);
        $("input[name='upperMenuNo']").val(upperMenuId);

        if (url == '/') return false;
        if (target == 'Y') form.target = '_blank';

        if (!isEmpty(query)) {
            url += "?" + query;
        }

        form.action = url;
        form.submit();
    }

    /*validation check*/
    function isEmpty(value){
        if(value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)){
            return true;
        } else {
            return false;
        }
    }

    $(document).on("keyup", "input[numberOnly]", function() {
        $(this).val($(this).val().replace(/[^0-9]/gi,""));
    });

    /*키입력 체크*/
    function fn_press(event, type) {
        const regExp = /[^0-9a-zA-Z]/g;
        const ele = event.target;

        if (type == 'number') {
            if (regExp.test(ele.value)) {
                ele.value = ele.value.replace(regExp, '');
            }
            if (event.keyCode < 48 || event.keyCode > 57) return false;
        } else if (type == 'korean') {
            if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46) return false;
        }

        return true;
    }

    /*초기화*/
    function fn_reset(form) {
        $("form[name='"+form+"']").each(function() {
            this.reset();

            $("input[type=hidden]").val('');
        });
    }

    /*국제화*/
    function fn_localeAction(obj) {
        let form = document.menuLocationForm;
        let lang = $(obj).data("lang");
        let url = '${menuData.url}';

        form.action = url+'?lang='+lang;
        form.submit();
    }

    /*IE 접속시 엣지 자동전환*/
    if(/MSIE \d|Trident.*rv:/.test(navigator.userAgent)) {
        window.location = 'microsoft-edge:' + window.location.href;

        setTimeout(function() {
            window.location = 'https://go.microsoft.com/fwlink/?linkid=2135547';
        }, 1);
    }

    /* file download */
    function fn_gloDownFile(obj){
        let id = $(obj).data('id');
        let sn = $(obj).data('sn');
        let $form = document.fileForm;
        $form.action = "<c:url value='/cmm/fileDown.do'/>";
        $form.method = "POST";
        $form.fileId.value = id;
        $form.fileSn.value = sn;
        $form.submit();
    }

    /* file delete */
    function fn_gloDeleteFile(obj){
        let id = $(obj).data('id');
        let sn = $(obj).data('sn');
        let $form = document.fileForm;
        if(confirm("삭제 하시겠습니까?")) {
            $form.action = "<c:url value='/cmm/fileDelete.do'/>";
            $form.method = "POST";
            $form.fileId.value  = id;
            $form.fileSn.value  = sn;
            $form.submit();
        }
    }

    /* file image view */
    function fn_gloImageView(obj) {
        let id = $(obj).data('id');
        let sn = $(obj).data('sn');
        $("#img-view").attr("src", "/cmm/photoView.do?atchFileId=" + id + "&sn=" + sn);
    }

    function alterSuccessClose(e) {
        console.log(e.key)
        if(e.key === "Enter") {
            document.getElementById("success-close").click();
        }
    }

    function alterClose(e) {
        if(e.key === "Enter") {
            $('input[name="closeButton"]').click();
        }
    }
</script>