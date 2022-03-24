var fonts = ['Source Sans Pro', '맑은 고딕', '돋움', '궁서', '굴림', '굴림체', '궁서체', 'Nanum Gothic', '바탕', '바탕체', '새굴림', 'HY견고딕', 'HY견명조', 'HY궁서B', 'HY그래픽M',
    'HY목각파임B', 'HY신명조', 'HY얕은샘물M', 'HY엽서L', 'HY엽서M', 'HY중고딕', 'HY헤드라인M'];
var isSumImageAtt = false;

$(document).ready(function(){

    $(window).on("beforeunload", function(){
        if(isSumImageAtt){
            if (!isSummernoteEmpty()) {
                return "이 페이지를 벗어나면 작성된 내용은 저장되지 않습니다.";
            }
        }
    });
    $(".button").click(function(e) {
        isSumImageAtt = false;
    });
    console.log($(window).on("beforeunload"));
});

function isSummernoteEmpty() {
    return $('.summernote').summernote('isEmpty');
}

$(function () {
    /*function fn_setSummernote() {*/
    var summ = $(".summernote").summernote();
    $(".summernote").summernote('destroy');

    $(".summernote").summernote({
        minHeight: null,                   // 최소 높이
        maxHeight: null,                   // 최대 높이
        //focus: true,                     // 에디터 로딩후 포커스를 맞출지 여부
        lang: "ko-KR",					   // 한글 설정
        placeholder: '내용을 입력해 주세요!', //placeholder 설정
        tabsize: 1,
        height: 650,                       // 에디터 높이
        popover: {
            image: [
                ['imageResize', ['resizeFull', 'resizeHalf', 'resizeQuarter', 'resizeNone']],
                ['float', ['floatLeft', 'floatRight', 'floatNone']],
                ['remove', ['removeMedia']],
                ['custom', ['imageTitle']],
            ],
        },
        toolbar: [
            // [groupName, [list of button]]
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['bold', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['map', 'picture', 'link', 'video']],
            ['view', ['fullscreen', 'help']]
                ['misc', ['emoji']]
        ],
        fontNames: fonts.sort(),
        fontNamesIgnoreCheck: ['Nanum Gothic', 'Source Sans Pro'], //없는 폰트 추가
        fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
        prettifyHtml: false,
        mageTitle: {
            specificAltField: true,
        },
        callbacks: {
            onInit: function() {
                console.log('Summernote is launched');
            },
            onImageUpload: function (files, editor, welEditable) {
                console.log(files);
                sendFile(files, this);
            },
            onMediaDelete: function (files, editor, welEditable) {
                var imageUrl = $(files[0]).attr('src');
                delFile(imageUrl);
            },
            onChange: function (files, editor, welEditable) {
                $( '#pClass' ).keydown(function(event) {

                    //백스페이스 키의 keyCode 는 8 입니다
                    if ( event.keyCode === 8 ) {
                        console.log("backspace");
                    };
                });

            }
        }
    });

    //bootstrap 적용분에만 추가
    var link = document.location.href;
    if(link.indexOf("/cms") > 0){
        $(".dropdown-toggle").each(function(i,obj){
          if($(obj).attr("aria-label") != "More Color") {
                $(obj).find("span").removeClass("note-icon-caret");
            }
          })
    }
});


/* summernote file upload */
function sendFile(files, el) {
    var form_data = new FormData();
    for (var i = 0; i < files.length; i++) {
        form_data.append('files', files[i]);
    };


    $.ajax({
        type: "POST",
        data: form_data,
        url: "/cmm/summernote/upload.json",
        async: true,
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        dataType: 'json',
        processData: false,
        success: function (data) {
            if (data.result == "success") {
                var list = data.uploadList;
                if (list.length > 0) {
                    isSumImageAtt = true;
                    for (var image of list) {
                        var query = "fileNm="+image.streFileNm+"&ext="+image.fileExtsn+"&orgFileNm="+image.orignlFileNm;
                        $(el).summernote('insertImage', "/cmm/summernote/photoView.json?"+query);
                    }
                }
            } else {
                switch (parseInt(data.msg)) {
                    case 100:
                        alert("이미지 파일이 아닙니다. (jpeg, jpg, gif,  png 확장자만 올리실 수 있습니다.");
                        break;
                    case 200:
                        alert("권한이 없습니다. 관리자에게 문의하세요.");
                        break;
                }
            }
        }
    });
}

/* delete file */
/* 삭제 시 키보드 처리는 인식 안됨. 클릭 후 휴지통 버튼 클릭 시 타는 로직임 */
function delFile(src) {
    var srcTemp = src.split("=");
    var fileInfo = srcTemp[1].split("&");

    $.ajax({
        data: {"atchFileId" : fileInfo[0], "fileSn" : srcTemp[2]},
        type: "POST",
        url: "/cmm/summernote/delete.json",
        cache: false,
        success: function(data) {

        }
    });
}
