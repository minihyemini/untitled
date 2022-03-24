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
        $('#dt-client').DataTable({
            scrollY: 400,
            scrollX: true,
            scrollXInner: "120%",
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
            lengthChange: false,
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
                        $form.action = '<c:url value='/mes/base/client/list/excel.json'/>';
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
                        let $form = document.clientForm;
                        let title = $form.cltNm.value;
                        $("#remove-modal-alert .modal-title").text(title);
                        $("#remove-modal-alert").modal();
                    }
                },
            ]
        });

        $('#dt-client tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/client/detail.json'/>",
                data:{'id' : id},
                dataType:'json',
                success:function (result){
                    let data = result.data;
                    let $form = document.clientForm;

                    $form.cltId.value = data.cltId;
                    $form.cltType.value = data.cltType;
                    $form.cltCode.value = data.cltCode;
                    $form.cltNm.value = data.cltNm;
                    $form.cltOwnrnm.value = data.cltOwnrnm;
                    $form.cltBusstype.value = data.cltBusstype;
                    $form.cltBussnum.value = data.cltBussnum;
                    $form.cltCprtnum.value = data.cltCprtnum;
                    $form.cltZip.value = data.cltZip;
                    $form.cltAddr.value = data.cltAddr;
                    $form.cltDetailAddr.value = data.cltDetailAddr;
                    $form.cltTelno.value = data.cltTelno;
                    $form.cltFaxnum.value = data.cltFaxnum;
                    $form.cltEmail.value = data.cltEmail;
                    $form.cltSetupdt.value = data.cltSetupdt;
                    $form.cltDlbegindt.value = data.cltDlbegindt;
                    $form.cltDlendt.value = data.cltDlendt;
                    $form.cltDeadlndt.value = data.cltDeadlndt;

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
            $form.action = '<c:url value='/mes/base/client/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정/삭제 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.clientForm;
        let id = $form.cltId.value;


        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/base/client/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/base/client/edit.do'/>';
            }
        } else if (target == 'delete') {
            $form.action = '<c:url value='/mes/base/client/remove.do'/>';
        }
        $form.submit();
    }

    /*Daum address API*/
    function execDaumPostcode() {
        let zipId = 'cltZip';
        let adresId = 'cltAddr';
        let detailAdresId = 'cltDetailAddr';

        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    addr += extraAddr;

                } else {
                    addr += "";
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById(zipId).value = data.zonecode;
                document.getElementById(adresId).value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById(detailAdresId).focus();
                close();
            }
        }).open();
    }
</script>