<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-19
  Time: 오후 4:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready(function() {
        $('#dt-client').DataTable({
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

        $('#dt-client-member').DataTable({
            scrollY: 400,
            scrollX: true,
            scrollXInner: "100%",
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
            ]
        });
    });
</script>