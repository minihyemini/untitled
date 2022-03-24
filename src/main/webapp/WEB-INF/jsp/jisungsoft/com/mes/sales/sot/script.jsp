<%--
  Created by IntelliJ IDEA.
  User: febsn
  Date: 2022-02-18
  Time: 오후 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(document).ready(function() {
        $('#dt-client').DataTable({
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

        $('#dt-sot').DataTable({
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

        $('#dt-sot tbody').on('dblclick', 'tr', function(event) {
            let id = event.currentTarget.dataset.id;

            $('#sot-detail-modal-lg-center').modal('show');
        });
    });
</script>