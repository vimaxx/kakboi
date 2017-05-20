<%@page import="Model.User"%>
<%@page import="Model.Page"%>
<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    Page p = (Page) request.getAttribute("page");
    if( p == null ) {
        p = Page.fromString(request.getParameter("page"));
    }
    
%>

<div class="sidebar sidebar-main sidebar-fixed">
    <div class="sidebar-content" style='top: 0px'>
        <!-- User menu -->
        <div class="sidebar-user">
            <div class="category-content">
                <div class="media">
                    <a href="logout" class="media-left"><img src="resource/image.png" class="img-circle img-sm" alt=""></a>
                    <div class="media-body">
                        <span class="media-heading text-semibold"><%=loggedUser.getFullname()%></span>
                        <div class="text-size-mini text-muted">
                            <i class="icon-pin text-size-small"></i> <%=loggedUser.getUserType()%>
                        </div>
                    </div>
                    <div class="media-right media-middle">
                        <ul class="icons-list">
                            <li>
                                <a href="#"><i class="icon-cog3"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- /user menu -->


        <!-- Main navigation -->
        <div class="sidebar-category sidebar-category-visible">
            <div class="category-content no-padding">
                <ul class="navigation navigation-main navigation-accordion">

                    
                    <li class="navigation-header"><span>Main</span> <i class="icon-menu" title="Main pages"></i></li>
                    <!-- Main -->
                    <%if("ceo".equalsIgnoreCase(loggedUser.getUserType())) {%>
                    <li <%=(p==Page.MAIN_PAGE?"class='active'":"")%> ><a href="main"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                    <li <%=(p==Page.BRANCH_PAGE?"class='active'":"")%>><a href="branches"><i class="icon-briefcase"></i><span>Branches</span></a></li>
                    <li <%=(p==Page.STRATEGY_PAGE?"class='active'":"")%>><a href="strategy"><i class="icon-paperplane"></i><span>Strategy</span></a></li>
                    <li <%=(p==Page.PERFORMANCE_PAGE?"class='active'":"")%>><a href="performances"><i class="icon-dribbble"></i><span>Performances</span></a></li>
                    <%}else{%>
                    <li <%=(p==Page.MAIN_PAGE?"class='active'":"")%> ><a href="main"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                    <li <%=(p==Page.BM_PRODUCT_PAGE?"class='active'":"")%>><a href="branches"><i class="icon-cube2"></i><span>Products</span></a></li>
                    <li <%=(p==Page.BM_EMPLOYEE_PAGE?"class='active'":"")%>><a href="strategy"><i class="icon-people"></i><span>Employee</span></a></li>
                    <li <%=(p==Page.BM_TRANSACTIONS_PAGE?"class='active'":"")%>><a href="transactions"><i class="icon-cash3"></i><span>Transactions</span></a></li>
                    <%}%>
                    <!-- /main -->

                </ul>
            </div>
        </div>
        <!-- /main navigation -->

    </div>
</div>