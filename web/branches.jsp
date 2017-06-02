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
    <div id="mapCol" class="col-md-8">
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
        <div class="panel panel-flat hidden" id="branchDetail<%=i%>">
            <div class="panel-heading">
                <h5 class="panel-title"><%=b.getBranchName()%></h5>
            </div>

            <div class="panel-body">
                <h6 class="text-semibold">Branch Descriptions</h6>
                <p class="content-group">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                <h6 class="text-semibold">More Information</h6>
                <p class="content-group">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                <h6 class="text-semibold">Eveb More Information</h6>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
            </div>
        </div>

        <%
            }
        %>
    </div>

</div>

<div id="branchContainer" class="container-fluid">
    <%
        for (int i = 0; i < branches.size(); i++) {
            Branch b = branches.get(i);
    %>
    <div id='branchRow<%=i%>' class="row hidden">
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

    $(function () {
        var data = [];

    <%
        for (int i = 0; i < branches.size(); i++) {
            Branch b = branches.get(i);
    %>
        data.push({
            latLng: [<%=b.getLatitude()%>, <%=b.getLongitude()%>],
            name: '<%=b.getBranchName()%>'}
        );
    <%
        }
    %>


        initMap(data);

    });
</script>
<jsp:include page="footer.jsp"/>
