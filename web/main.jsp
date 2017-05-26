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

            <div class="svg-center" id="rounded_progress_multiple"></div>
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

                <ul class="media-list" id="notifactionList">
                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-pink text-pink btn-flat btn-rounded btn-icon btn-xs"><i class="icon-statistics"></i></a>
                        </div>

                        <div class="media-body">
                            Stats for July, 6: 1938 orders, $4220 revenue
                            <div class="media-annotation">2 hours ago</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>

                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-success text-success btn-flat btn-rounded btn-icon btn-xs"><i class="icon-checkmark3"></i></a>
                        </div>

                        <div class="media-body">
                            Invoices <a href="#">#4732</a> and <a href="#">#4734</a> have been paid
                            <div class="media-annotation">Dec 18, 18:36</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>

                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-primary text-primary btn-flat btn-rounded btn-icon btn-xs"><i class="icon-alignment-unalign"></i></a>
                        </div>

                        <div class="media-body">
                            Affiliate commission for June has been paid
                            <div class="media-annotation">36 minutes ago</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>

                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-warning-400 text-warning-400 btn-flat btn-rounded btn-icon btn-xs"><i class="icon-spinner11"></i></a>
                        </div>

                        <div class="media-body">
                            Order <a href="#">#37745</a> from July, 1st has been refunded
                            <div class="media-annotation">4 minutes ago</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>

                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-teal-400 text-teal btn-flat btn-rounded btn-icon btn-xs"><i class="icon-redo2"></i></a>
                        </div>

                        <div class="media-body">
                            Invoice <a href="#">#4769</a> has been sent to <a href="#">Robert Smith</a>
                            <div class="media-annotation">Dec 12, 05:46</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-teal-400 text-teal btn-flat btn-rounded btn-icon btn-xs"><i class="icon-redo2"></i></a>
                        </div>

                        <div class="media-body">
                            Invoice <a href="#">#4769</a> has been sent to <a href="#">Robert Smith</a>
                            <div class="media-annotation">Dec 12, 05:46</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-teal-400 text-teal btn-flat btn-rounded btn-icon btn-xs"><i class="icon-redo2"></i></a>
                        </div>

                        <div class="media-body">
                            Invoice <a href="#">#4769</a> has been sent to <a href="#">Robert Smith</a>
                            <div class="media-annotation">Dec 12, 05:46</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>
                    <li class="media">
                        <div class="media-left">
                            <a href="#" class="btn border-teal-400 text-teal btn-flat btn-rounded btn-icon btn-xs"><i class="icon-redo2"></i></a>
                        </div>

                        <div class="media-body">
                            Invoice <a href="#">#4769</a> has been sent to <a href="#">Robert Smith</a>
                            <div class="media-annotation">Dec 12, 05:46</div>
                        </div>

                        <div class="media-right media-middle">
                            <ul class="icons-list">
                                <li>
                                    <a href="#"><i class="icon-arrow-right13"></i></a>
                                </li>
                            </ul>
                        </div>
                    </li>
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
        data.push( random(60, 90) );
    <%
            }
    %>

        branchProductivityBar('#branchProductivityGraph', 400, data, name);


        roundedProgressMultiple("#rounded_progress_multiple", 140);
        roundedProgressMultiple("#rounded_progress_multiple2", 140);
        roundedProgressMultiple("#rounded_progress_multiple3", 140);
        customerSatisfactionLine('#customerSatisfactionGraph', 255); // initialize chart
    });
</script>

<jsp:include page="footer.jsp"/>
