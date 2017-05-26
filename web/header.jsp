<%@page import="Model.User"%>
<%@page import="Model.Page"%>
<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    Page p = (Page) request.getAttribute("page");
    if (p == null) {
        p = Page.fromString(request.getParameter("page"));
    }

    String title = p.getTitle();
    title = "Kakboi" + (title == null ? "" : " - " + title);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><%=title%></title>

        <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
        <link href="css/icomoon/styles.css" rel="stylesheet" type="text/css">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/core.css" rel="stylesheet" type="text/css">
        <link href="css/components.css" rel="stylesheet" type="text/css">
        <link href="css/colors.css" rel="stylesheet" type="text/css">
        <%=p.printExtraCSS()%>
        <%=p.printExtraHead()%>


        <!-- Core JS files -->
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <!-- /core JS files -->

    </head>

    <body class="fixed-sidebar-space">
        <!-- Page container -->
        <div class="page-container">

            <!-- Page content -->
            <div class="page-content">

                <%if (p != Page.LOGIN_PAGE) {%>
                <jsp:include page="sidebar.jsp"/>
                <%}%>

                <div class="content-wrapper">
                    <%if (p != Page.LOGIN_PAGE) {%>
                    <jsp:include page="pageHeader.jsp"/>
                    <%}%>

                    <div class="content">
