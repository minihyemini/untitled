<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-25
  Time: 오후 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring-commons-validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    let tbClient;
    let tbPdnum;
        $(document).ready(function() {
            tbClient = $('#dt-client').DataTable({
                scrollY: 400,
                scrollX: true,
                scrollXInner: "100%",
                scrollCollapse: true,
                select: 'single',
                paging: false,
                columnDefs: [
                    {
                        targets: 0,
                        // visible: false,
                        searchable: false
                    },
                ]
            });

            tbPdnum = $('#dt-pdnum').DataTable({
                scrollY: 400,
                scrollX: true,
                scrollXInner: "100%",
                scrollCollapse: true,
                select: 'single',
                paging: false,
                columnDefs: [
                    {
                        targets: 0,
                        // visible: false,
                        searchable: false
                    },
                ]
            });

            $('#dt-client tbody').on('click', 'tr', function(event) {
                let cltId = event.currentTarget.dataset.clt;
                let $form = document.clientPdNumForm;
                $form.cltId.value = cltId;

                $.ajax({
                    type:"post",
                    async:false,
                    url:"<c:url value='/mes/base/client/pdnum/list.json'/>",
                    data:{'cltId' : cltId},
                    dataType:'json',
                    success:function (result){
                        let dataList = result.dataList;
                        tbPdnum.clear().draw();

                        for (let i=0; i<dataList.length; i++) {
                            let data = dataList[i];
                            fn_fieldAdd(data);
                        }

                    }, error:function (jqXHR, textStatus, errorThrown) {
                        alert('<spring:message code="fail.user.connectFail"/>');
                    }
                });
            });

            $(document).on("click","#check-pdnum-all",function(){
                if ($("#check-pdnum-all").is(":checked")) {
                    $("[name='pdnumCheck']").prop("checked", true);
                } else {
                    $("[name='pdnumCheck']").prop("checked", false);
                }
            });
            $(document).on("click","#form-check-all",function(){
                let fieldCount = tbPdnum.column(0).data().length;
                if ($("#form-check-all").is(":checked")) {
                    for (let i=0; i<fieldCount; i++) {
                        $("#formCheck_"+i).prop("checked", true);
                    }
                } else {
                    for (let i=0; i<fieldCount; i++) {
                        $("#formCheck_"+i).prop("checked", false);
                    }
                }
            });
    });

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = '';
        if (target == 'save') {
            $form = document.clientPdNumForm;
            $form.action = '<c:url value='/mes/base/client/pdnum/add.do'/>';
        }
        $form.submit();
    }

    /*품번항목 팝업오픈*/
    function fn_getPdNumData() {
        var formData = $("[name='searchPdNumForm']").serialize();
        let $form = document.clientPdNumForm;
        let cltId =  $form.cltId.value;

        if (isEmpty(cltId)) {
            $("#nodata-modal-alert").modal();
            return;
        }

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
        $("[name='pdnumCheck']").prop("checked", false);
        $("[name='formCheck']").prop("checked", true);
    }

    /*필드 추가*/
    function fn_fieldAdd(data) {
        let fieldCount = tbPdnum.column(0).data().length;
        let pcId = isEmpty(data.pcId) ? '' : data.pcId;
        let isDuplicated = false;

        if (fieldCount > 0) {
            /!*인풋시 중복 제거*!/
            let frm_data = $('[name="clientPdNumForm"]').serializeArray();
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

        if (!isDuplicated) {
            tbPdnum.row.add([
                '<div class="custom-control custom-checkbox"><input type="checkbox" name="pdnumList['+fieldCount+'].formCheck" value="Y" checked id="formCheck_'+fieldCount+'" class="form-control-input"/></div>'+
                '<input type="hidden" name="pdnumList['+fieldCount+'].pdnumId" value="'+data.pdnumId+'">'+
                '<input type="hidden" name="pdnumList['+fieldCount+'].pcId" value="'+pcId+'">',
                data.pdnumNum,
                data.pdnumNm,
                data.pdnumTypeNm,
                data.pdnumStnd,
                data.pdnumUnit
            ]).draw(false);
        }
    }
</script>