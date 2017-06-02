<%@page import="Model.Strategy"%>
<%@page import="Model.Branch"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Goal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="Model.Page"%>

<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    if (loggedUser == null) {
//        response.sendRedirect("login");
//        return;
    }
    Page p = Page.MAIN_PAGE;
    request.setAttribute("page", p);

    ArrayList<Goal> goals = (ArrayList<Goal>) request.getAttribute("goals");
    if (goals == null) {
        goals = new ArrayList<>();
    }
    ArrayList<Branch> branches = (ArrayList<Branch>) request.getAttribute("branches");
    if (branches == null) {
        branches = new ArrayList<>();
    }
    DecimalFormat format = new DecimalFormat("$#,###k");
    String weeklyRevenue = "", monthlyRevenue = "", averageRevenue = "";

    weeklyRevenue = format.format((Number) request.getAttribute("weeklyRevenue"));
    monthlyRevenue = format.format((Number) request.getAttribute("monthlyRevenue"));
    averageRevenue = format.format((Number) request.getAttribute("averageRevenue"));
%>
<jsp:include page="header.jsp"/>

<div class="row">

    <%
        for (int i = 0; i < goals.size(); i++) {
            Goal g = goals.get(i);
            if (!g.isOngoing()) {
                continue;
            }

    %>
    <div class="col-md-3">
        <!-- Rounded progress - multiple -->
        <div class="panel panel-body text-center">
            <h6 class="text-semibold no-margin-bottom mt-5"><%=g.getGoalName()%></h6>
            <div class="text-size-small text-muted content-group-sm">Goal</div>

            <div class="svg-center" id="goalGraph<%=i%>"></div>
        </div>
        <!-- /rounded progress - multiple -->
    </div>
    <%
        }
    %>
</div>

<!-- Grid -->
<div class="row">
    <div class="col-md-6">

        <div class="panel panel-flat">
            <div class="panel-heading">
                <h6 class="panel-title">Total Revenue By Branches<a class="heading-elements-toggle"><i class="icon-more"></i></a></h6>

                <div class="heading-elements">
                    <button type="button" class="btn btn-link daterange-ranges heading-btn text-semibold">
                        <i class="icon-calendar3 position-left"></i> <span>May 26</span> <b class="caret"></b>
                    </button>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row text-center">
                    <div class="col-md-4">
                        <div class="content-group">
                            <h5 class="text-semibold no-margin"><i class="icon-calendar5 position-left text-slate"></i> <%=weeklyRevenue%></h5>
                            <span class="text-muted text-size-small">revenue weekly</span>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="content-group">
                            <h5 class="text-semibold no-margin"><i class="icon-calendar52 position-left text-slate"></i> <%=monthlyRevenue%></h5>
                            <span class="text-muted text-size-small">revenue monthly</span>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="content-group">
                            <h5 class="text-semibold no-margin"><i class="icon-cash3 position-left text-slate"></i> <%=averageRevenue%></h5>
                            <span class="text-muted text-size-small">average revenue</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="position-relative" id="incomeGraph"></div>
        </div>
    </div>

    <div class="col-md-6">

        <div class="panel panel-flat">
            <div class="panel-heading">
                <h6 class="panel-title">Notifications</h6>
            </div>

            <div class="panel-body">
                <div class="content-group-xs" id="bullets"></div>

                <ul class="media-list" id="notificationList">
                </ul>
            </div>
        </div>

    </div>
</div>
<!-- /grid -->

<div class="panel panel-flat">
    <div class="panel-heading">
        <h5 class="panel-title">Branch Productivity</h5>

        <div class="heading-elements">
            <button type="button" class="btn btn-link daterange-ranges heading-btn text-semibold">
                <i class="icon-calendar3 position-left"></i> <span>May 13 - May 28</span> <b class="caret"></b>
            </button>
        </div>
    </div>


    <div class="panel-body">

        <div class="chart-container">
            <div class="chart" id="branchProductivityGraph"></div>
        </div>
    </div>
</div>

<div class="panel panel-flat">
    <div class="panel-heading">
        <h6 class="panel-title">Customer Satisfaction</h6>
        <div class="heading-elements">
            <button type="button" class="btn btn-link daterange-ranges heading-btn text-semibold">
                <i class="icon-calendar3 position-left"></i> <span>May 13 - May 28</span> <b class="caret"></b>
            </button>
        </div>
    </div>

    <div class="content-group-sm" id="customerSatisfactionGraph"></div>
    <div id="monthly-sales-stats"></div>
</div>

<script type="text/javascript" src="js/d3.min.js"></script>
<script type="text/javascript" src="js/d3_tooltip.js"></script>
<script type="text/javascript" src="js/page_main.js"></script>

<script>
    $(function () {
        incomeGraph('#incomeGraph', 330);

        d3.csv("assets/demo_data/dashboard/traffic_sources.csv", function (error, data) {
            initIncomeGraph(data);
        });
        var seed = 1;
        function random(from, to) {
            var x = Math.sin(seed++) * 10000;
            var s = x - Math.floor(x);

            return Math.floor(s * (to - from) + from);
        }

        var data = [];
        var name = [];

    <%
        for (int i = 0; i < branches.size(); i++) {
            Branch b = branches.get(i);
    %>
        name.push("<%=b.getBranchName()%>");
        data.push(random(60, 90));
    <%
        }
    %>

        branchProductivityBar('#branchProductivityGraph', 400, data, name);

        var data2;
    <%
        for (int i = 0; i < goals.size(); i++) {
            Goal g = goals.get(i);
            if (!g.isOngoing()) {
                continue;
            }
    %>
        data2 = [];
        
        <%
            for( int j = 0 ; j < g.getStrategies().size(); j++ ) {
                Strategy s = g.getStrategies().get(j);
                %>
        data2.push({
            index: <%=j%>,
            name: "<%=s.getStrategyName() %>",
            percentage: "<%=s.getStrategyWeight() * 100%>"
        });
                <%
            }
        %>
        
        goalGraph("#goalGraph<%=i%>", 140, data2);            
    <%
        }
    %>

        customerSatisfactionLine('#customerSatisfactionGraph', 255); // initialize chart

        updateNotifications();

        setInterval(function () {
            updateNotifications();
        }, 100);
    });
</script>

<jsp:include page="footer.jsp"/>
