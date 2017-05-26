<%@page import="Model.Employee"%>
<%@page import="Model.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="Model.Page"%>

<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    if (loggedUser == null) {
//        response.sendRedirect("login");
//        return;
    }
    Page p = Page.BRANCH_PAGE;
    request.setAttribute("page", p);

    ArrayList<Branch> branches = (ArrayList<Branch>) request.getAttribute("branches");
    if (branches == null) {
        branches = new ArrayList<>();
    }
%>
<jsp:include page="header.jsp"/>

<div class="row">
    <div class="col-md-8">
        <div class="panel panel-flat">
            <div class="panel-heading">
                <h5 class="panel-title">Branches</h5>
            </div>

            <div class="panel-body">
                <div id="branchMap" class="map-container" style='height: 600px'></div>
            </div>
        </div>
    </div>

    <div id='branchDetails' class="col-md-4">
        <%
            for (int i = 0; i < branches.size(); i++) {
                Branch b = branches.get(i);
        %>
        <div class="panel panel-flat" id="branchDetail<%=b.getBranchID()%>">
            <div class="panel-heading">
                <h5 class="panel-title"><%=b.getBranchName()%></h5>
            </div>

            <div class="panel-body">
                <h6 class="text-semibold">Start your development with no hassle!</h6>
                <p class="content-group">Common problem of templates is that all code is deeply integrated into the core. This limits your freedom in decreasing amount of code, i.e. it becomes pretty difficult to remove unnecessary code from the project. Limitless allows you to remove unnecessary and extra code easily just by removing the path to specific LESS file with component styling. All plugins and their options are also in separate files. Use only components you actually need!</p>

                <h6 class="text-semibold">What is this?</h6>
                <p class="content-group">Starter kit is a set of pages, useful for developers to start development process from scratch. Each layout includes base components only: layout, page kits, color system which is still optional, bootstrap files and bootstrap overrides. No extra CSS/JS files and markup. CSS files are compiled without any plugins or components. Starter kit was moved to a separate folder for better accessibility.</p>

                <h6 class="text-semibold">How does it work?</h6>
                <p>You open one of the starter pages, add necessary plugins, uncomment paths to files in components.less file, compile new CSS. That's it. I'd also recommend to open one of main pages with functionality you need and copy all paths/JS code from there to your new page, it's just faster and easier.</p>
            </div>
        </div>

        <%
            }
        %>
    </div>

</div>

<div id="branchContainer" class="container">
    <%
        for (int i = 0; i < branches.size(); i++) {
            Branch b = branches.get(i);
    %>
    <div id='branchRow<%=b.getBranchID()%>' class="row">
        <div class="panel panel-flat">
            <div class="panel-heading">
                <h5 class="panel-title">Employees</h5>
            </div>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Full Name</th>
                            <th>Position</th>
                            <th>Email</th>
                            <th>Overall Productivity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (int j = 0; j < b.getEmployees().size(); j++) {
                                Employee e = b.getEmployees().get(j);
                        %>
                        <tr>
                            <td><%=e.getEmployeeID()%></td>
                            <td><%=e.getEmployeeName()%></td>
                            <td><%=e.getPositionName()%></td>
                            <td><%=e.getEmail()%></td>
                            <td><%=e.getProductivity()%></td>
                        </tr>
                        <%
                            }
                        %>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>
<!-- Table -->
<!-- /table -->



<script type="text/javascript" src="js/jvectormap.min.js"></script>
<script type="text/javascript" src="js/jquery-jvectormap-au-mill.js"></script>
<script type="text/javascript" src="js/page_branches.js"></script>
<script>

</script>
<jsp:include page="footer.jsp"/>
