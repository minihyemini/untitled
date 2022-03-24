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
            scrollY: 380,
            scrollX: true,
            scrollXInner: "100%",
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "25%"
                },
                {targets: 1, width: "25%"},
                {targets: 2, width: "5%"},
            ],
            select: 'single',
        });

        table.on('key', function(e, datatable, key) {
            console.log(key)
        })

        $('#dt-basic tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;
            let $form = document.fciltsPdNumForm;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/fciltsbypdnum/list.json'/>",
                data:{'pdfciltsId' : id},
                dataType:'json',
                success:function (result){
                    let dataList = result.data;
                    console.log(dataList);
                    $form.pdfciltsId.value = id;

                    let tbList = document.querySelector("#fcilts-pd-num");
                    let tbody = "<tbody>";

                    for (var i=0; i<dataList.length; i++) {
                        let result = dataList[i];
                        let input = "<input type='checkbox' name='pdnumIdArr' id='fciltsPdNum"+result.pdnumId+"' value='"+result.pdnumId+"' class='form-control-input' checked>";
                        tbody += "<tr>";
                        tbody += "<td><div class='custom-control custom-checkbox'>"+input+"</div></td>";
                        tbody += "<td>"+result.pdnumNum+"</td>";
                        tbody += "<td>"+result.pdnumNm+"</td>";
                        tbody += "</tr>";
                    }
                    tbody += "</tbody>";
                    tbList.innerHTML = tbody;

                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        });

        $('#dt-sub-basic').DataTable({
            scrollY: 325,
            scrollX: true,
            scrollXInner: "100%",
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "2%"
                },
                {targets: 1, width: "20%"},
                {targets: 2, width: "20%"},
            ]
        });

        $(document).on("click","#check-pdnum-all",function(){
            if ($("#check-pdnum-all").is(":checked")) {
                $("[name='pdnumCheck']").prop("checked", true);
            } else {
                $("[name='pdnumCheck']").prop("checked", false);
            }
        });
        $(document).on("click","#check-fciltsPdNum-all",function(){
            if ($("#check-fciltsPdNum-all").is(":checked")) {
                $("[name='pdnumIdArr']").prop("checked", true);
            } else {
                $("[name='pdnumIdArr']").prop("checked", false);
            }
        });
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = '';
        let target = $(obj).data("target");
        if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/base/fciltsByPdnum/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = '';
        if (target == 'save') {
            $form = document.fciltsPdNumForm;
            $form.action = '<c:url value='/mes/base/fciltsByPdnum/add.do'/>';
        } else if (target == 'delete') {
            $form = document.fciltsPdNumForm;
            $form.action = '<c:url value='/mes/base/fciltsByPdnum/remove.do'/>';
        }
        $form.submit();
    }

    /*품번목록 팝업*/
    function fn_getPdNumData() {
        let formData = $("[name='searchPdNumForm']").serialize();
        let $form = document.fciltsPdNumForm;
        let pdfciltsId =  $form.pdfciltsId.value;

        if (isEmpty(pdfciltsId)) {
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
                let tbList = document.querySelector("#pdnum-dataTable-body");
                let tbody = "<tbody>";

                for (let i=0; i<dataList.length; i++) {
                    let data = dataList[i];
                    let valueData = data.pdnumId + "," + data.pdnumNum + "," + data.pdnumNm
                    let input = "<input type='checkbox' name='pdnumCheck' id='fciltsPdNum"+data.pdnumId+"' value='"+valueData+"' class='form-control-input'>";
                    tbody += "<tr>";
                    tbody += "<td><div class='custom-control custom-checkbox'>"+input+"</div></td>";
                    tbody += "<td>"+data.pdnumNum+"</td>";
                    tbody += "<td>"+data.pdnumNm+"</td>";
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

    function fn_selectPdnumData(obj) {
        let strHtml = "";
        let tbList = document.querySelector("#fcilts-pd-num");
        $('#dt-sub-basic').css('display', 'block');
        $('input:checkbox[name="pdnumCheck"]').each(function() {
            if (this.checked == true) {

                const strArray = this.value.split(",");
                let id = strArray[0];
                let pdnumNum = strArray[1];
                let pdnumNm = strArray[2];
                let valueData = "<input type='checkbox' checked name='pdnumIdArr' id='fciltsPdNum"+id+"' value='"+id+"' class='form-control-input' checked>";
                strHtml += "<tr>";
                strHtml += "<td><div class='custom-control custom-checkbox'>"+valueData+"</div></td>";
                strHtml += "<td>"+pdnumNum+"</td>";
                strHtml += "<td>"+pdnumNm+"</td>";
                strHtml += "</tr>";
            }
        });
        tbList.innerHTML = strHtml;

        $('#pdnum-list-modal').modal('hide');
    }

</script>