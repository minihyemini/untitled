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
        let pdnumDt;
        let imageFileDt;
        let atchFileDt;

        pdnumDt = $('#dt-pdnum').DataTable({
            scrollY: 300,
            scrollX: true,
            scrollCollapse: true,
            select: 'single',
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    width: "10%"
                },
            ],
            lengthChange: false,
        });

        imageFileDt = $('#dt-imagefile').DataTable({
            scrollY: 300,
            scrollX: true,
            scrollCollapse: true,
            select: 'single',
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    // width: "10%"
                },
            ],
            lengthChange: false,
        });

        atchFileDt = $('#dt-atchFile').DataTable({
            scrollY: 300,
            scrollX: true,
            scrollCollapse: true,
            select: 'single',
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    // width: "10%"
                },
            ],
            lengthChange: false,
        });

        $('#dt-pdnum tbody').on('click', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $.ajax({
                type:"post",
                async:false,
                url:"<c:url value='/mes/base/pdnum/detail.json'/>",
                data:{'pdnumId' : id},
                dataType:'json',
                success:function (result){
                    //테이블 초기화
                    imageFileDt.clear().draw();
                    atchFileDt.clear().draw();
                    $("#img-view").attr("src", "");

                    let data = result.data;
                    let $form = document.pdNumForm;
                    $form.pdnumId.value = data.pdnumId;

                    let atchList = data.atchFileList;
                    if (!isEmpty(atchList)) {
                        atchList.forEach(function (el){
                            atchFileDt.row.add([
                                '<button type="button" class="btn btn-xs btn-outline-secondary waves-effect waves-themed" data-id="'+el.atchFileId+'" data-sn="'+el.fileSn+'" onclick="fn_gloDownFile(this);">'+ el.orignlFileNm+'</button>',
                                el.fileSize,
                                el.creatDt,
                                '<button type="button" class="btn btn-xs btn-danger waves-effect waves-themed" data-id="'+el.atchFileId+'" data-sn="'+el.fileSn+'" onclick="fn_gloDeleteFile(this);"><spring:message code="button.delete"/></button>',
                            ]).draw(false);
                        });
                    }

                    let imgList = data.imgFileList;
                    if (!isEmpty(imgList)) {
                        imgList.forEach(function (el){
                            imageFileDt.row.add([
                                '<button type="button" class="btn btn-xs btn-outline-secondary waves-effect waves-themed" data-id="'+el.atchFileId+'" data-sn="'+el.fileSn+'" onclick="fn_gloImageView(this);">'+
                                el.orignlFileNm+'</button>',
                                el.fileSize,
                                el.creatDt,
                            ]).draw(false);
                        });
                    }
                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert('<spring:message code="fail.user.connectFail"/>');
                }
            });
        });
    });

    /*페이지 이동 처리*/
    function fn_getPage(obj) {
        let $form = '';
        let target = $(obj).data("target");

        if (target == 'search') {
            $form = document.searchForm;
            $form.action = '<c:url value='/mes/base/pdnum/list.do'/>';
        }

        $form.submit();
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let $form = document.pdNumForm;
        let id = $form.pdnumId.value;


        if (target == 'save') {
            if (isEmpty(id)) {
                alert('품번을 선택해주세요.');
                return;
            } else {
                $form.action = '<c:url value='/mes/base/pdnum/doc/edit.do'/>';
            }
        } else if (target == 'delete') {

        }
        $form.submit();
    }
</script>