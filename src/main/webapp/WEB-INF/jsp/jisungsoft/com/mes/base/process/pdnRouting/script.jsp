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
    let dtSubProcess = '';
    let dtPdNum = '';
    $(document).ready(function() {
        let collArr = new Array();
        let col = {};

        dtPdNum = $('#dt-pdnum').DataTable({
            "scrollY": 800,
            scrollX: true,
            scrollCollapse: true,
            fixedColumns: false,
            paging: false,
            select: 'single',
        });

        dtSubProcess = $('#dt-sub-process').DataTable({
            "scrollY": 300,
            "scrollX": false,
            scrollCollapse: true,
            fixedColumns: false,
            paging: false,
            select: false,
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "1%"
                },
                {targets: 1, width: "10%"},
                {targets: 2, width: "5%"},
                {targets: 3, width: "5%"},
                {targets: 4, width: "4%"},
                {targets: 5, width: "5%"},
                {targets: 6, width: "10%"},
            ]
        });

        $('#dt-pdnum tbody').on('click', 'tr', function(event) {
            let dataset = event.currentTarget.dataset;
            let $form = document.pdnumbyroutgForm;

            $form.pdnumNum.value = dataset.pdnumnm;
            $form.pdnumNm.value = dataset.pdnumnm;
            $form.pdnumId.value = dataset.pdnumid;
        });

        $(document).on("click","#pbr-check-all",function(){
            let fieldCount = dtSubProcess.column(0).data().length;
            if ($("#pbr-check-all").is(":checked")) {
                for (let i=0; i<fieldCount; i++) {
                    $("#pbrCheck_"+i).prop("checked", true);
                }
            } else {
                for (let i=0; i<fieldCount; i++) {
                    $("#pbrCheck_"+i).prop("checked", false);
                }
            }
        });
    });

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = '';
        if (target == 'save') {
            $form = document.pdnumbyroutgForm;
            $form.action = '<c:url value='/mes/base/process/pdnumRouting/add.do'/>';
        } else if (target == 'delete') {
            $form = document.pdnumbyroutgForm;
            $form.action = '<c:url value='/mes/base/process/pdnumRouting/remove.do'/>';
        }
        $form.submit();
    }

    function fn_searchFormData(obj) {
        let formData = $("[name='pdnumbyroutgForm']").serialize();
        let selectedArr = dtPdNum.rows('.selected').data()[0];
        if (isEmpty(selectedArr)) {
            alert('<spring:message code="required.pdnumbyroutgForm.pdnumId"/>')
            return;
        }
        $.ajax({
            type:'post',
            async:false,
            url:"<c:url value='/mes/base/process/pdnumRouting/list.json'/>",
            data:formData,
            dataType:'json',
            success:function (result) {
                //테이블 초기화
                dtSubProcess.clear().draw();

                let $form = document.pdnumbyroutgForm;
                let dataList = result.data;
                for (let i=0; i<dataList.length; i++) {
                    let data = dataList[i];
                    fn_fieldAdd(data, $form);
                }

                $("#pbr-check-all").prop("checked", true);
            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        })
    }

    function fn_fieldAdd(data, form) {
        let fieldCount = dtSubProcess.column(0).data().length;
        let selectedArr = dtPdNum.rows('.selected').data()[0];
        if (isEmpty(selectedArr)) {
            alert('<spring:message code="required.pdnumbyroutgForm.pdnumId"/>')
            return;
        }

        dtSubProcess.row.add([
            '<div class="custom-control custom-checkbox"><input type="checkbox" name="pdnumroutgList['+fieldCount+'].pbrCheck" value="Y" id="pbrCheck_'+fieldCount+'" class="form-control-input"/></div>'+
            '<input type="hidden" name="pdnumroutgList['+fieldCount+'].pbrId">',
            '<select class="form-control form-control-sm" name="pdnumroutgList['+fieldCount+'].pmCode">'+
            '<c:forEach var="code" items="${processCodeList}" varStatus="status">'+
            '<option value="${code.code}">${code.codeNm}</option>'+
            '</c:forEach>'+
            '</select>',
            '<input type="text" class="form-control form-control-sm" name="pdnumroutgList['+fieldCount+'].pmSeq" value="0" onkeypress="return fn_press(event, "number");"/>',
            '<div class="custom-control custom-checkbox"><input type="checkbox" name="pdnumroutgList['+fieldCount+'].pbrPfmpoint" value="Y" id="pbrPfmpoint" class="form-control-input"/></div>',
            '<input type="text" class="form-control form-control-sm" name="pdnumroutgList['+fieldCount+'].pbrReadtm" value="0" onkeypress="return fn_press(event, "number");"/>',
            '<input type="text" class="form-control form-control-sm" name="pdnumroutgList['+fieldCount+'].pbrSmpreadtm" value="0" onkeypress="return fn_press(event, "number");"/>',
            '<input type="text" class="form-control form-control-sm" name="pdnumroutgList['+fieldCount+'].pbrDesc"/>',
        ]).draw(false);

        if (!isEmpty(data)) {
            form['pdnumroutgList['+fieldCount+'].pbrId'].value = data.pbrId;
            form['pdnumroutgList['+fieldCount+'].pmCode'].value = data.pmCode;
            form['pdnumroutgList['+fieldCount+'].pmSeq'].value = data.pmSeq;
            form['pdnumroutgList['+fieldCount+'].pbrReadtm'].value = data.pbrReadtm;
            form['pdnumroutgList['+fieldCount+'].pbrSmpreadtm'].value = data.pbrSmpreadtm;
            form['pdnumroutgList['+fieldCount+'].pbrDesc'].value = data.pbrDesc;
            form['pdnumroutgList['+fieldCount+'].pbrCheck'].checked = true;
            if (!isEmpty(data.pbrPfmpoint) && data.pbrPfmpoint == 'Y') {
                form['pdnumroutgList['+fieldCount+'].pbrPfmpoint'].checked = true;
            }
        }
    }

    function fn_fieldDelete() {
        dtSubProcess.row().remove().draw();
    }
</script>