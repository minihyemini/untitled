<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-16
  Time: 오후 5:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready(function() {
        $('#dt-basic').DataTable({
            "scrollY": 300,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            select: 'single',
            /*
            fixedColumns:
                {
                    leftColumns: 2
                },
            */
        });

        $('#dt-basic3').DataTable({
            "scrollY": 300,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            select: 'single',
            /*
            fixedColumns:
                {
                    leftColumns: 2
                },
            */
        });

        $('#dt-basic2').DataTable({
            "scrollY": 500,
            "scrollX": true,
            scrollCollapse: true,
            fixedColumns:   true,
            paging: false,
            columnDefs: [
                {
                    "targets": 0,
                    // visible: false,
                    searchable: false,
                    width: "5%"
                },
                {targets: 1, width: "15%"},
                {targets: 2, width: "15%"},
                {targets: 3, width: "10%"},
                {targets: 4, width: "10%"},
                {targets: 5, width: "10%"},
                {targets: 6, width: "10%"},
                {targets: 7, width: "10%"},
            ],
            /*
            fixedColumns:
                {
                    leftColumns: 2
                },
            */
        });
    });
</script>