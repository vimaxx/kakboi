<%@page import="Model.Page"%>
<%
    Page p = (Page) request.getAttribute("page");
    if( p == null ) {
        p = Page.fromString(request.getParameter("page"));
    }
%>

<!-- Page header -->
<div class="page-header page-header-default">
    <div class="page-header-content">
        <div class="page-title">
            <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold"><%=p.getTitle()%></span></h4>
        </div>
    </div>

    <div class="breadcrumb-line">
        
        <ul class="breadcrumb">
        <%
        for( int i = 0; i < p.getBreadCrumbs().size(); i++ ) {
            Page b = p.getBreadCrumbs().get(i);
        %>
            <li><a href="<%=b.getJspLink()%>"><i class="icon-home2 position-left"></i> <%=b.getTitle()%></a></li>
        <%
        }
        %>
            <li class="active"><%=p.getTitle()%></li>
        </ul>

<!--        <ul class="breadcrumb-elements">
            <li><a href="#"><i class="icon-comment-discussion position-left"></i> Link</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="icon-gear position-left"></i>
                    Dropdown
                    <span class="caret"></span>
                </a>

                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="#"><i class="icon-user-lock"></i> Account security</a></li>
                    <li><a href="#"><i class="icon-statistics"></i> Analytics</a></li>
                    <li><a href="#"><i class="icon-accessibility"></i> Accessibility</a></li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="icon-gear"></i> All settings</a></li>
                </ul>
            </li>-->
        </ul>
    </div>
</div>
<!-- /page header -->