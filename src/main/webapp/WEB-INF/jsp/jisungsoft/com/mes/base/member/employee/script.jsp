<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-02-01
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
    $(document).ready(function() {
        let table = $('#dt-employee-member').DataTable({
            scrollY: 350,
            scrollX: true,
            scrollXInner: "200%",
            scrollCollapse: true,
            select: 'single',
            paging: false,
            fixedColumns:
                {
                    leftColumns: 0
                },

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
                    text: '<spring:message code="member.list.title.password"/>',
                    className: 'btn-primary btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        fn_editPasswdForm();
                    }
                },
                {
                    extend: 'selected',
                    text: '<spring:message code="button.account.unlock"/>',
                    className: 'btn-primary btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        fn_lockIncorrect();
                    }
                },
                {
                    extend: 'selected',
                    text: '<i class="fal fa-times mr-1"></i> <spring:message code="button.field.delete"/>',
                    name: 'delete',
                    className: 'btn-primary btn-sm mr-1',
                    action: function(e, dt, button, config) {
                        let $form = document.memberForm;
                        let title = $form.name.value;
                        $("#remove-modal-alert .modal-title").text(title);
                        $("#remove-modal-alert").modal();
                    }
                },
            ]
        });

        $('#dt-employee-member tbody').on('click', 'tr', function(event) {
            let data = table.row().data();
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/member/employee/detail.json'/>",
                data:{'uniqId' : id},
                dataType:'json',
                success:function (result){
                    let data = result.employee;
                    let $form = document.memberForm;
                    fn_setDataForm($form, data);

                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        })
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = document.searchForm;
        let target = $(obj).data("target");

        if (target == 'search') {
            $form.action = '<c:url value='/mes/base/member/employee/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.memberForm;
        let id =  $form.uniqId.value;
        if (target == 'save') {
            if (isEmpty(id)) {
                $form.action = '<c:url value='/mes/base/member/employee/add.do'/>';
            } else {
                $form.action = '<c:url value='/mes/base/member/employee/edit.do'/>';
            }
        } else if(target == 'savePasswd') {
            $form.action = '<c:url value='/mes/base/member/employee/pwdEdit.do'/>';
        } else if(target == 'delete') {
            $form.action = '<c:url value='/mes/base/member/employee/remove.do'/>';
        }
        $form.submit();
    }

    /**
     * 패스워드 변경처리
     */
    function fn_editPasswdAction() {
        let formData = $("#passwordEditForm").serialize();

        $.ajax({
            type:"post",
            async:false,
            url:"<c:url value='/mes/base/member/employee/pwdEdit.json'/>",
            data: formData,
            dataType:'json',
            success:function (result){
                if (isEmpty(result.errors)) {
                    alert(result.resultMessage);
                    location.href = "<c:url value='/mes/base/member/employee/list.do'/>";
                    // $('#default-changepasswd-modal-lg-center').modal('hide');
                } else {
                    let data = result.errors;
                    let $field, error, message;
                    for(let key in data) {
                        if (data.hasOwnProperty(key)) {
                            let obj = data[key];
                            error = obj['error'];
                            message = obj['errorMessage'];

                            $field = $('#passwordEditForm #'+error['field']);
                            $field.addClass('form-control is-invalid');
                            $field.siblings('.invalid-feedback').remove();
                            $field.after('<div class="invalid-feedback">'+message+'</div>');
                        }
                    }
                }

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert('<spring:message code="fail.user.connectFail"/>');
            }
        });
    }

    /**
     * 패스워드 변경 폼 로드
     */
    function fn_editPasswdForm() {
        let memberForm = document.memberForm;
        let uniqId = memberForm.uniqId.value;
        let id = memberForm.id.value;

        let $form = document.passwordEditForm;
        $form.uniqId.value = uniqId;
        $form.id.value = id;
        $("#changepasswd-modal-lg-center").modal('show');
    }

    let controls = {
        leftArrow: '<i class="fal fa-angle-left" style="font-size: 1.25rem"></i>',
        rightArrow: '<i class="fal fa-angle-right" style="font-size: 1.25rem"></i>'
    }

    function fn_setDataForm(form, data) {
        form.uniqId.value = data.uniqId;
        form.name.value = data.emplyrNm;
        form.id.value = data.emplyrId;
        form.email.value = data.emailAdres;
        form.mobileNum.value = data.mbtlnum;
        form.offmTelno.value = data.offmTelno;
        form.authorCode.value = data.authorCode;
        form.ofcpsNm.value = data.ofcpsNm;
        form.birthday.value = data.brthdy;
        form.attendanceAt.value = data.attendanceAt;
        form.foreignerAt.value = data.foreignerAt;
        form.sexdstnCode.value = data.sexdstnCode;
        form.emplyrSttusCode.value = data.emplyrSttusCode;
        form.zip.value = data.zip;
        form.address.value = data.adres;
        form.detailAddress.value = data.detailAdres;
        form.joiningDate.value = data.joiningDate;
        form.quittingDate.value = data.quittingDate;
        form.sbscrbDe.value = data.sbscrbDe;
    }

    /*로그인 인증제한 해제*/
    function fn_lockIncorrect() {
        if (!confirm("로그인인증제한을 해제 하시겠습니까?")) {
            return false;
        }

        let formData = $("[name='memberForm']").serialize();
        $.ajax({
            type:"POST",
            async:false,
            url:"<c:url value='/mes/base/member/employee/lockIncorrect.json'/>",
            data:formData,
            dataType:'json',
            success:function (result) {
                alert(result.resultMsg);
            }, error:function (jqXHR, textStatus, errorThrown) {
                alert("실패하였습니다. 관리자에게 문의하세요.");
            }
        });
    }

    /*Daum address API*/
    function execDaumPostcode() {
        let zipId = 'zip';
        let adresId = 'address';
        let detailAdresId = 'detailAddress';

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