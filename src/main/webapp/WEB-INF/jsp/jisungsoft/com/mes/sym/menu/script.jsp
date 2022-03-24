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

<%--fancytree--%>
<script src="<c:url value='/js/vendor/jquery-ui.js'/>"></script>
<script src="<c:url value='/plugins/fancytree/jquery.fancytree-all.js'/>"></script>
<script src="<c:url value='/plugins/fancytree/jquery.cookie.js'/>"></script>
<script src="<c:url value='/plugins/fancytree/extensions/jquery.fancytree.persist.js'/>"></script>
<script src="<c:url value='/plugins/jquery-ui-contextmenu/jquery.ui-contextmenu.js'/>"></script>

<script>

    $(document).ready(function() {

        /* tree list*/
        $("#tree").fancytree({
            clickFolderMode : 2,
            source : {
                url : '<c:url value="/mes/sym/menu/menuView.json"/>',
                cache : true,
                data : {'type':'root'}
            },
            extensions : [ "persist" ],
            persist : {
                expandLazy : true,
                store : "auto"
            },
            createNode: function(event, data) {

            },
            loadChildren: function(event, data) {
                // var children = data.node.getChildren();
                // for (var i = 0; i < children.length; i++) {
                //     if (children[i].data.menuNo == '0') {
                //         children[i].data.icon = "/images/egovframework/com/cmm/utl/menu_base.gif";
                //         children[i].renderTitle();
                //     }
                // }
            },
            lazyLoad : function(event, data) {
                let node = data.node;
                let mode = node.data.mode;

                if (mode == "category") {
                    data.result = {
                        url : '<c:url value="/mes/sym/menu/menuView.json?type=category"/>',
                        cache : true,
                        data : {
                            menuNo : node.data.menuNo,
                            category : node.data.category,
                            menuLv : node.data.menuLv
                        }
                    }
                } else if (mode == "menu") {
                    data.result = {
                        url : '<c:url value="/mes/sym/menu/menuView.json?type=menu"/>',
                        cache : true,
                        data : {
                            menuNo : node.data.menuNo,
                            category : node.data.category,
                            subCnt : node.data.subCnt,
                            menuLv : node.data.menuLv
                        }
                    }
                }
            }
        });

        /* menu box */
        $("#tree").contextmenu({
            delegate: "span.fancytree-title",
//      menu: "#options",
            menu: [
                // {title: "메뉴폴더생성", cmd: "create-dir"},
                {title: "메뉴생성", cmd: "create"},
                {title: "수정", cmd: "update"},
                {title: "삭제", cmd: "delete"}
            ],
            beforeOpen: function(event, ui) {
                let tree = $("#tree").fancytree("getTree");
                tree.visit(function(node) {
                    $("#tree").contextmenu("showEntry", "create", true);
                    // $("#tree").contextmenu("showEntry", "create-dir", true);

                    node.setSelected(false);
                    node.setActive(false);

                    if (node.span.lastChild.lastChild == ui.target[0]) {
                        // $("#tree").contextmenu("showEntry", "create", true);
                        // $("#tree").contextmenu("showEntry", "create-dir", true);
                        // $("#tree").contextmenu("enableEntry", "create", node.isFolder());
                        // $("#tree").contextmenu("enableEntry", "create-dir", node.isFolder());
                        $("#tree").contextmenu("enableEntry", "delete", !node.isFolder());
                        node.setActive(true);
                    }
                });
            },
            select: function(event, ui) {
                let tree = $("#tree").fancytree("getTree");
                tree.visit(function(node) {
                    if (node.span.lastChild.lastChild == ui.target[0]) {
                        let data = node.data;

                        fn_directDataAction(data, ui);
                    }
                });
            }
        });

        function fn_directDataAction(data, ui) {
            let programNm;
            let menuNo      = data.menuNo;
            let upperMenuNo = data.upperMenuNo;
            let category = data.category;
            let menuLv = data.menuLv;
            let form       = document.menuForm;

            $("#saveBtn").data('target', 'Insert');
            form.menuNo.value = menuNo;
            form.menuLv.value = menuLv;
            form.upperMenuNo.value = upperMenuNo;
            form.menuCategory.value = category;
            form.relateImageNm.value = '/';
            form.relateImagePath.value = '/';
            form.menuOrdr.value = 0;
            form.menuNm.value = "";
            form.menuDc.value = "";
            $("select[name='useAt']").val("N").attr("selected", "selected");
            $("input:checkbox[name='authorCode']").attr("checked", "");

            if (ui.cmd == 'create') {
                // programNm = 'newMenu';
                // $("input[name='progrmFileNm']").val(programNm);

            } else if (ui.cmd == 'update') {
                var dataId = menuNo;
                $("#saveBtn").data('target', 'save');

                $.ajax({
                    type:"POST",
                    async:false,
                    url:"<c:url value='/mes/sym/menu/menuForm.json'/>",
                    data:{menuNo:dataId},
                    dataType:'json',
                    success: function (data) {
                        fn_dataInput(data);

                    }, error:function (jqXHR, textStatus, errorThrown) {
                        alert("실패하였습니다. 관리자에게 문의하세요.");
                    }
                });
            } else if (ui.cmd == 'delete') {
                if(!confirm("정말로 삭제하시겠습니까?")) {
                    return false;
                }
                var dataId = menuNo;

                $.ajax({
                    type:"POST",
                    url:"<c:url value='/mes/sym/menu/upperMenuByPk.json'/>",
                    data:{menuNo:dataId},
                    dataType:'json',
                    success: function (data) {
                        if (data.result > 0) {
                            alert("하위 메뉴를 확인해주세요.");
                            return false;
                        }

                        var form        = document.menuForm;
                        form.action = "<c:url value='/mes/sym/menu/remove.do'/>";
                        form.submit();

                    }, error:function (jqXHR, textStatus, errorThrown) {
                        alert("실패하였습니다. 관리자에게 문의하세요.");
                    }
                });
            }
        };
    });

    function fn_pageAction(obj) {
        let dataId     = $(obj).data("id");
        let dataType     = $(obj).data("type");

        if (dataType == 'Detail') {
            $("input[name='progrmFileNm']").val(dataId);
            popupClose(event);

            return false;
        }

        $.ajax({
            type:"POST",
            async:false,
            url:"<c:url value='/mes/sym/menu/menuForm.json'/>",
            data:{menuNo:dataId},
            dataType:'json',
            success: function (data) {
                fn_dataInput(data);

            }, error:function (jqXHR, textStatus, errorThrown) {
                alert("실패하였습니다. 관리자에게 문의하세요.");
            }
        });
    }

    function fn_dataInput(data) {
        let form       = document.menuForm;
        form.menuNm.value = data.menuNm;
        form.menuNo.value = data.menuNo;
        form.upperMenuNo.value = data.upperMenuNo;
        form.menuLv.value = data.menuLv;
        form.progrmFileNm.value = data.progrmFileNm;
        form.progrmId.value = data.progrmId;
        form.relateImageNm.value = data.relateImageNm;
        form.relateImagePath.value = data.relateImagePath;
        form.menuOrdr.value = data.menuOrdr;
        form.menuDc.value = data.menuDc;
        form.menuCategory.value = data.menuCategory;
        form.useAt.value = data.useAt;
        form.targetAt.value = data.targetAt;
        console.log(data.menuNo + " : " + data.upperMenuNo)
        var menuCreatList = data.menuCreatList

        $("input:checkbox[name='authorCode']").each(function() {
            this.checked = false;
        });

        for(let i in menuCreatList) {
            let code = menuCreatList[i].authorCode;
            $("input:checkbox[name='authorCode']").each(function() {
                if (this.value == code) {
                    this.checked = true;
                }
            });
        }
    }

    /*등록/수정 처리*/
    function fn_dataAction(obj) {
        let target = $(obj).data("target");
        let form       = document.menuForm;
        let url        = '';

        if (target == "Insert") {
            url = "<c:url value='/mes/sym/menu/add.do'/>";

            form.action = url;
            form.submit();
        } else if (target == "save") {
            url = "<c:url value='/mes/sym/menu/edit.do'/>";

            form.action = url;
            form.submit();
            /*
            form = $("#menuForm").serialize();

            $.ajax({
                type:"POST",
                async:false,
                url:"<c:url value='/mes/sym/menu/edit.json'/>",
                data:form,
                dataType:'json',
                success: function (data) {
                    alert(data.resultMsg);
                    $('#save-modal-alert').modal('hide');

                }, error:function (jqXHR, textStatus, errorThrown) {
                    alert("실패하였습니다. 관리자에게 문의하세요.");
                }
            });
            */
        }
    }

    function fn_searchMenuProgramData(obj) {
        let formData = $("[name='searchClCodeForm']").serialize();

        $.ajax({
            type:"POST",
            async:false,
            url:"<c:url value='/mes/sym/menu/program/list.json'/>",
            data:formData,
            dataType:'json',
            success: function (result) {
                let dataResult = result.data;

                fn_jsTableData(dataResult);
            }, error:function (jqXHR, textStatus, errorThrown) {
                alert("실패하였습니다. 관리자에게 문의하세요.");
            }
        });
    };

    function fn_jsTableData(obj) {
        let tbList = document.querySelector("#menuProgram-dataTable-body");
        let tbody = "<tbody>";

        /*tbody*/
        for (let i=0; i<obj.length; i++) {
            let result = obj[i];
            let progrmFileNm = isEmpty(result.progrmFileNm) ? '' : result.progrmFileNm;
            let progrmKoreanNm = isEmpty(result.progrmKoreanNm) ? '' : result.progrmKoreanNm;
            let progrmDc = isEmpty(result.progrmDc) ? '' : result.progrmDc;
            let progrmCode = isEmpty(result.progrmCode) ? '' : result.progrmCode;
            

            tbody += "<tr onclick='fn_selectSubTableList(this);' data-id='"+result.progrmId+"' data-name='"+result.progrmFileNm+"'>";
            tbody += "<td>"+result.progrmFileNm+"</td>";
            tbody += "<td>"+result.progrmKoreanNm+"</td>";
            tbody += "<td>"+result.progrmDc+"</td>";
            tbody += "<td>"+result.progrmCode+"</td>";
            tbody += "<td>"+result.url+"</td>";
            tbody += "</tr>";
        }
        tbody += "</tbody>";

        tbList.innerHTML = tbody;
    }

    function fn_selectSubTableList(obj) {
        let id = $(obj).data("id");
        let name = $(obj).data("name");
        let $form = document.menuForm;
        $form.progrmId.value = id;
        $form.progrmFileNm.value = name;
        $('#menuProgram-list-modal').modal('hide');
    }
</script>