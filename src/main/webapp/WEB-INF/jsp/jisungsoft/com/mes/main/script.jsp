<%--
  Created by IntelliJ IDEA.
  User: Park Yeon Ho
  Date: 2022-01-31
  Time: 오후 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script src="<c:url value='/js/statistics/d3/d3.js'/>"></script>
<!-- c3 charts : MIT license -->
<script src="<c:url value='/js/statistics/c3/c3.js'/>"></script>
<script src="<c:url value='/js/statistics/demo-data/demo-c3.js'/>"></script>
<script src="/js/dependency/moment/moment.js"></script>
<script src="/js/miscellaneous/fullcalendar/fullcalendar.bundle.js"></script>
<script src="/js/statistics/sparkline/sparkline.bundle.js"></script>
<script src="/js/statistics/easypiechart/easypiechart.bundle.js"></script>
<script src="/js/statistics/flot/flot.bundle.js"></script>
<script src="/js/miscellaneous/jqvmap/jqvmap.bundle.js"></script>

<c:url value=''/>
<script>
    $(document).ready(function()
    {
        /* defined datas */
        let dataTargetProfit = [
            [1354586000000, 153],
            [1364587000000, 658],
            [1374588000000, 198],
            [1384589000000, 663],
            [1394590000000, 801],
            [1404591000000, 1080],
            [1414592000000, 353],
            [1424593000000, 749],
            [1434594000000, 523],
            [1444595000000, 258],
            [1454596000000, 688],
            [1464597000000, 364]
        ]
        let pdCount = [
            [1354586000000, 53],
            [1364587000000, 65],
            [1374588000000, 98],
            [1384589000000, 83],
            [1394590000000, 980],
            [1404591000000, 808],
            [1414592000000, 720],
            [1424593000000, 674],
            [1434594000000, 23],
            [1444595000000, 79],
            [1454596000000, 88],
            [1464597000000, 36]
        ]
        let failCount = [
            [1354586000000, 647],
            [1364587000000, 435],
            [1374588000000, 784],
            [1384589000000, 346],
            [1394590000000, 487],
            [1404591000000, 463],
            [1414592000000, 479],
            [1424593000000, 236],
            [1434594000000, 843],
            [1444595000000, 657],
            [1454596000000, 241],
            [1464597000000, 341]
        ]

        var dataSales = [
            [1196463600000, 0],
            [1196550000000, 0],
            [1196636400000, 0],
            [1196722800000, 1177],
            [1196809200000, 3636],
            [1196895600000, 3575],
            [1196982000000, 2736],
            [1197068400000, 1086],
            [1197154800000, 1676],
            [1197241200000, 1205],
            [1197327600000, 1906],
            [1197414000000, 1710],
            [1197500400000, 1639],
            [1197586800000, 1540],
            [1197673200000, 1435],
            [1197759600000, 1301],
            [1197846000000, 1575],
            [1197932400000, 1481],
            [1198018800000, 1591],
            [1198105200000, 1608],
            [1198191600000, 1459],
            [1198278000000, 1234],
            [1198364400000, 1352],
            [1198450800000, 1686],
            [1198537200000, 1279],
            [1198623600000, 1449],
            [1198710000000, 1468],
            [1198796400000, 1392],
            [1198882800000, 1282],
            [1198969200000, 1208],
            [1199055600000, 1229],
            [1199142000000, 1177],
            [1199228400000, 1374],
            [1199314800000, 1436],
            [1199401200000, 1404],
            [1199487600000, 1253],
            [1199574000000, 1218],
            [1199660400000, 1476],
            [1199746800000, 1462],
            [1199833200000, 1500],
            [1199919600000, 1700],
            [1200006000000, 1750],
            [1200092400000, 1600],
            [1200178800000, 1500],
            [1200265200000, 1900],
            [1200351600000, 1930],
            [1200438000000, 1200],
            [1200524400000, 1980],
            [1200610800000, 1950],
            [1200697200000, 1900],
            [1200783600000, 1000],
            [1200870000000, 1050],
            [1200956400000, 1150],
            [1201042800000, 1100],
            [1201129200000, 1200],
            [1201215600000, 1300],
            [1201302000000, 1700],
            [1201388400000, 1450],
            [1201474800000, 1500],
            [1201561200000, 1546],
            [1201647600000, 1614],
            [1201734000000, 1954],
            [1201820400000, 1700],
            [1201906800000, 1800],
            [1201993200000, 1900],
            [1202079600000, 2000],
            [1202166000000, 2100],
            [1202252400000, 2200],
            [1202338800000, 2300],
            [1202425200000, 2400],
            [1202511600000, 2550],
            [1202598000000, 2600],
            [1202684400000, 2500],
            [1202770800000, 2700],
            [1202857200000, 2750],
            [1202943600000, 2800],
            [1203030000000, 3245],
            [1203116400000, 3345],
            [1203202800000, 3000],
            [1203289200000, 3200],
            [1203375600000, 3300],
            [1203462000000, 3400],
            [1203548400000, 3600],
            [1203634800000, 3700],
            [1203721200000, 3800],
            [1203807600000, 4000],
            [1203894000000, 4500]
        ];

        /* flot toggle example */
        let flot_toggle = function() {
            let data = [
                {
                    // label: "생산계획",
                    data: dataTargetProfit,
                    color: color.danger._500,
                    bars:
                        {
                            show: true,
                            align: "center",
                            barWidth: 30 * 30 * 60 * 1000 * 50,
                            lineWidth: 0,
                            fillColor:
                                {
                                    colors: [color.danger._900, color.danger._100]
                                }
                        },
                    highlightColor: 'rgba(255,255,255,0.3)',
                    shadowSize: 0
                },
                {
                    // label: "생산량",
                    data: pdCount,
                    color: color.info._500,
                    lines:
                        {
                            show: true,
                            lineWidth: 5
                        },
                    shadowSize: 0,
                    points:
                        {
                            show: true
                        }
                },
                {
                    label: "불량수량",
                    data: failCount,
                    color: color.success._500,
                    lines:
                        {
                            show: true,
                            lineWidth: 2
                        },
                    shadowSize: 0,
                    points:
                        {
                            show: true
                        }
                }]

            let options = {
                grid: {
                        hoverable: true,
                        clickable: true,
                        tickColor: '#f2f2f2',
                        borderWidth: 1,
                        borderColor: '#f2f2f2'
                    },
                tooltip: true,
                tooltipOpts:
                    {
                        cssClass: 'tooltip-inner',
                        defaultTheme: false
                    },
                xaxis:
                    {
                        mode: "time"
                    },
                yaxes:
                    {
                        tickFormatter: function(val, axis)
                        {
                            return "$" + val;
                        },
                        max: 1200
                    }
            };

            let plot2 = null;
            function plotNow() {
                let d = [];
                $("#js-checkbox-toggles").find(':checkbox').each(function() {
                    if ($(this).is(':checked'))
                    {
                        d.push(data[$(this).attr("name").substr(4, 1)]);
                    }
                });

                if (d.length > 0) {
                    if (plot2) {
                        plot2.setData(d);
                        plot2.draw();
                    } else {
                        plot2 = $.plot($("#flot-toggles"), d, options);
                    }
                }
            };

            $("#js-checkbox-toggles").find(':checkbox').on('change', function() {
                plotNow();
            });
            plotNow()
        }
        flot_toggle();
        /* flot toggle example -- end*/

        /* sales chart */
        var plotSales = $.plot($('#flot-sales'), [
                {
                    data: dataSales,
                }],
            {
                series:
                    {
                        lines:
                            {
                                show: true,
                                lineWidth: 1,
                                fill: true,
                                fillColor:
                                    {
                                        colors: [
                                            {
                                                opacity: 0.1
                                            },
                                            {
                                                opacity: 0.15
                                            }]
                                    }
                            },
                        points:
                            {
                                show: true
                            },
                        shadowSize: 0
                    },
                selection:
                    {
                        mode: "x"
                    },
                grid:
                    {
                        hoverable: true,
                        clickable: true,
                        tickColor: '#f2f2f2',
                        borderWidth: 1,
                        borderColor: '#f2f2f2'
                    },
                tooltip: true,
                tooltipOpts:
                    {
                        cssClass: 'tooltip-inner',
                        content: "Your sales for <span class='text-warning fw-500'>%x</span> was <span class='text-success fw-500'>$%y</span>",
                        dateFormat: "%y-%0m-%0d",
                        defaultTheme: false
                    },
                colors: [color.primary._500],
                xaxis:
                    {
                        mode: "time",
                        tickLength: 5
                    }
            });
        /* sales chart -- end */

        /*calendar */
        let todayDate = moment().startOf('day');
        let YM = todayDate.format('YYYY-MM');
        let YESTERDAY = todayDate.clone().subtract(1, 'day').format('YYYY-MM-DD');
        let TODAY = todayDate.format('YYYY-MM-DD');
        let TOMORROW = todayDate.clone().add(1, 'day').format('YYYY-MM-DD');


        let calendarEl = document.getElementById('calendar');

        let calendar = new FullCalendar.Calendar(calendarEl, {
                plugins: ['dayGrid', 'list', 'timeGrid', 'interaction', 'bootstrap'],
                themeSystem: 'bootstrap',
                timeZone: 'UTC',
                dateAlignment: "month", //week, month
                buttonText:
                    {
                        today: 'today',
                        month: 'month',
                        week: 'week',
                        day: 'day',
                        list: 'list'
                    },
                eventTimeFormat:
                    {
                        hour: 'numeric',
                        minute: '2-digit',
                        meridiem: 'short'
                    },
                navLinks: true,
                header:
                    {
                        left: 'title',
                        center: '',
                        right: 'today prev,next'
                    },
                footer:
                    {
                        left: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
                        center: '',
                        right: ''
                    },
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: YM + '-01',
                        description: 'This is a test description', //this is currently bugged: https://github.com/fullcalendar/fullcalendar/issues/1795
                        className: "border-warning bg-warning text-dark"
                    },
                    {
                        title: 'Reporting',
                        start: YM + '-14T13:30:00',
                        end: YM + '-14',
                        className: "bg-white border-primary text-primary"
                    },
                    {
                        title: 'Surgery oncall',
                        start: YM + '-02',
                        end: YM + '-03',
                        className: "bg-primary border-primary text-white"
                    },
                    {
                        title: 'NextGen Expo 2019 - Product Release',
                        start: YM + '-03',
                        end: YM + '-05',
                        className: "bg-info border-info text-white"
                    },
                    {
                        title: 'Dinner',
                        start: YM + '-12',
                        end: YM + '-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: YM + '-09T16:00:00',
                        className: "bg-danger border-danger text-white"
                    },
                    {
                        id: 1000,
                        title: 'Repeating Event',
                        start: YM + '-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: YESTERDAY,
                        end: TOMORROW,
                        className: "bg-success border-success text-white"
                    },
                    {
                        title: 'Meeting',
                        start: TODAY + 'T10:30:00',
                        end: TODAY + 'T12:30:00',
                        className: "bg-primary text-white border-primary"
                    },
                    {
                        title: 'Lunch',
                        start: TODAY + 'T12:00:00',
                        className: "bg-info border-info"
                    },
                    {
                        title: 'Meeting',
                        start: TODAY + 'T14:30:00',
                        className: "bg-warning text-dark border-warning"
                    },
                    {
                        title: 'Happy Hour',
                        start: TODAY + 'T17:30:00',
                        className: "bg-success border-success text-white"
                    },
                    {
                        title: 'Dinner',
                        start: TODAY + 'T20:00:00',
                        className: "bg-danger border-danger text-white"
                    },
                    {
                        title: 'Birthday Party',
                        start: TOMORROW + 'T07:00:00',
                        className: "bg-primary text-white border-primary text-white"
                    },
                    {
                        title: 'Gotbootstrap Meeting',
                        url: 'http://gotbootstrap.com/',
                        start: YM + '-28',
                        className: "bg-info border-info text-white"
                    }],
                viewSkeletonRender: function()
                {
                    $('.fc-toolbar .btn-default').addClass('btn-sm');
                    $('.fc-header-toolbar h2').addClass('fs-md');
                    $('#calendar').addClass('fc-reset-order')
                },

            });

        calendar.render();
    });

    /*
    var Target1 = document.getElementById("f1");
    var Target2 = document.getElementById("f2");
    var Target3 = document.getElementById("f3");
    var Target4 = document.getElementById("f4");
    var Target5 = document.getElementById("f5");
    var Target6 = document.getElementById("f6");

    function clock() {
        var time = new Date();
        var hours = time.getHours() < 10 ? '0'+time.getHours() : time.getHours();
        var minutes = time.getMinutes() < 10 ? '0'+time.getMinutes() : time.getMinutes();
        var seconds = time.getSeconds() < 10 ? '0'+time.getSeconds() : time.getSeconds();

        Target1.innerText = hours+":"+minutes+":"+seconds;
        Target2.innerText = hours+":"+minutes+":"+seconds;
        Target3.innerText = hours+":"+minutes+":"+seconds;
        Target4.innerText = hours+":"+minutes+":"+seconds;
        //비동작
        // Target5.innerText = hours+":"+minutes+":"+seconds;
        Target6.innerText = hours+":"+minutes+":"+seconds;
    }
    clock();
    setInterval(clock, 1000); // 1초마다 실행
    */
</script>