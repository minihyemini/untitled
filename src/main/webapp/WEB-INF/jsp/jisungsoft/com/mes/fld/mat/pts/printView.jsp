<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-03-22
  Time: 오전 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name=vs_targetSchema content="http://schemas.microsoft.com/intellisense/ie5">
    <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    <title>공정이동전표 재출력</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <style type="text/css">
        #barcode {font-weight: normal; font-style: normal; line-height:normal; sans-serif; font-size: 6pt}
    </style>

</head>
<body >
<body topmargin="5" bottommargin="0" leftmargin="5" rightmargin="0">

<div style = "padding: 1px 1px 1px 10px;">
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{font-family:Arial, sans-serif;font-size:12px;padding:7px 13px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg th{font-family:Arial, sans-serif;font-size:12px;font-weight:normal;padding:8px 13px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg .tg-nrw1{font-size:12px;text-align:center;vertical-align:center}
        .tg .tg-s6z2{font-size:13px;text-align:center}
        .tg .tg-031e{font-size:13px}
        .tg .tg-baqh{text-align:center;vertical-align:top}
        .tg .tg-wv9z{font-size:22px;text-align:center}
        .tg .tg-28r4{font-size:11px;vertical-align:top}
        .tg .tg-kr94{font-size:8px;text-align:center}
        .tg .tg-yw4l{font-size:12px;vertical-align:top}
        .tg .tg-4kyz{font-size:18px;text-align:center}
        .tg .tg-rg0h{font-size:8px;text-align:center;vertical-align:center}
    </style>
    <table class="tg">
        <tr>
            <th rowspan="2"><img src="icon1.jpg" width="70" height="40"></th>
            <th class="tg-wv9z" colspan="5" rowspan="2">공 &nbsp; 정 &nbsp; 이  &nbsp; 동 &nbsp; 전 &nbsp; 표</th>
            <th class="tg-28r4">작 성</th>
            <th class="tg-28r4" colspan="2">2022년03월22일</th>
        </tr>
        <tr>
            <th class="tg-28r4">입 고</th>
            <th class="tg-28r4" colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 년 &nbsp;&nbsp; 월 &nbsp;&nbsp; 일</th>
        </tr>
        <tr>
            <td class="tg-s6z2">품   명</td>
            <td class="tg-031e" colspan="4">RS4 REEL UPPER BKT FR,RH</td>
            <td class="tg-baqh">수  량</td>
            <td class="tg-031e" colspan="1">5,200</td>
            <td colspan="2" rowspan="4">&nbsp; &nbsp; &nbsp;
                <img id='barcode'
                     src="https://api.qrserver.com/v1/create-qr-code/?data=202202030013001PRS404-0002E1&amp;size=100x100"
                     alt=""
                     title="QRCODE"
                     width="60"
                     height="60" />
                <br><br> 202202030013001				<br> PRS404-0002E1</td>
        </tr>
        <tr>
            <td class="tg-s6z2">품  번</td>
            <td class="tg-031e" colspan="4">PRS404-0002E1</td>
            <td class="tg-s6z2">L O T</td>
            <td class="tg-031e" colspan="1">220317</td>
        </tr>
        <tr>
            <td class="tg-s6z2">설비명</td>
            <td class="tg-031e" colspan="4">300-2호기</td>
            <td class="tg-s6z2">작업자</td>
            <td class="tg-031e" colspan="1">시번틴</td>
        </tr>
        <tr>
            <td class="tg-4kyz" colspan="9">공 &nbsp; 정 &nbsp; 이 &nbsp; 동 &nbsp; 내 &nbsp; 역
                &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </td>
        </tr>
        <tr>
            <td class="tg-kr94">입고일</td>
            <td class="tg-kr94">공정명</td>
            <td class="tg-kr94">업체명</td>
            <td class="tg-rg0h">LOT NO.</td>
            <td class="tg-rg0h">입고수량/중량</td>
            <td class="tg-rg0h">작업수량/중량</td>
            <td class="tg-kr94">잔  량</td>
            <td class="tg-kr94">확  인</td>
            <td class="tg-kr94">출고일</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">도장</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>

        <tr>
            <td class="tg-nrw1" height="90" >특기사항</td>
            <td class="tg-yw4l" colspan="8" height="90"></td>

        </tr>
    </table>

    <br><br><br>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{font-family:Arial, sans-serif;font-size:12px;padding:7px 13px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg th{font-family:Arial, sans-serif;font-size:12px;font-weight:normal;padding:8px 13px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;}
        .tg .tg-nrw1{font-size:12px;text-align:center;vertical-align:center}
        .tg .tg-s6z2{font-size:13px;text-align:center}
        .tg .tg-031e{font-size:13px}
        .tg .tg-baqh{text-align:center;vertical-align:top}
        .tg .tg-wv9z{font-size:22px;text-align:center}
        .tg .tg-28r4{font-size:11px;vertical-align:top}
        .tg .tg-kr94{font-size:10px;text-align:center}
        .tg .tg-yw4l{font-size:12px;vertical-align:top}
        .tg .tg-4kyz{font-size:18px;text-align:center}
        .tg .tg-rg0h{font-size:8px;text-align:center;vertical-align:center}
    </style>

    <table class="tg">
        <tr>
            <th rowspan="2"><img src="icon1.jpg" width="70" height="40"></th>
            <th class="tg-wv9z" colspan="5" rowspan="2">공 &nbsp; 정 &nbsp; 이  &nbsp; 동 &nbsp; 전 &nbsp; 표</th>
            <th class="tg-28r4">작 성</th>
            <th class="tg-28r4" colspan="2">2022년03월22일</th>
        </tr>
        <tr>
            <th class="tg-28r4">입 고</th>
            <th class="tg-28r4" colspan="2">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 년 &nbsp;&nbsp; 월 &nbsp;&nbsp; 일</th>
        </tr>
        <tr>
            <td class="tg-s6z2">품   명</td>
            <td class="tg-031e" colspan="4">RS4 REEL UPPER BKT FR,RH</td>
            <td class="tg-baqh">수  량</td>
            <td class="tg-031e" colspan="1">5,200</td>
            <td colspan="2" rowspan="4">&nbsp; &nbsp; &nbsp;
                <img id='barcode'
                     src="https://api.qrserver.com/v1/create-qr-code/?data=202202030013001PRS404-0002E1&amp;size=100x100"
                     alt=""
                     title="QRCODE"
                     width="60"
                     height="60" />
                <br><br> 202202030013001				<br> PRS404-0002E1</td>
        </tr>
        <tr>
            <td class="tg-s6z2">품  번</td>
            <td class="tg-031e" colspan="4">PRS404-0002E1</td>
            <td class="tg-s6z2">L O T</td>
            <td class="tg-031e" colspan="1">220317</td>
        </tr>
        <tr>
            <td class="tg-s6z2">설비명</td>
            <td class="tg-031e" colspan="4">300-2호기</td>
            <td class="tg-s6z2">작업자</td>
            <td class="tg-031e" colspan="1">시번틴</td>
        </tr>
        <tr>
            <td class="tg-4kyz" colspan="9">공 &nbsp; 정 &nbsp; 이 &nbsp; 동 &nbsp; 내 &nbsp; 역
                &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp </td>
        </tr>
        <tr>
            <td class="tg-kr94">입고일</td>
            <td class="tg-kr94">공정명</td>
            <td class="tg-kr94">업체명</td>
            <td class="tg-rg0h">LOT NO.</td>
            <td class="tg-rg0h">입고수량/중량</td>
            <td class="tg-rg0h">작업수량/중량</td>
            <td class="tg-kr94">잔  량</td>
            <td class="tg-kr94">확  인</td>
            <td class="tg-kr94">출고일</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">도장</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
            <td class="tg-yw4l">&nbsp;</td>
        </tr>
        <tr>
            <td class="tg-nrw1" height="90" >특기사항</td>
            <td class="tg-yw4l" colspan="8" height="90"></td>

        </tr>
    </table>
    <script>
        window.print();
    </script>
</body>
</html>