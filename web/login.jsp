
<%@page import="Model.Page"%>
<%@page import="Model.User"%>
<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    if( loggedUser == null ) {
//        response.sendRedirect("login");
//        return;
    }
    Page p = Page.LOGIN_PAGE;
    request.setAttribute("page", p);
%>
<jsp:include page="header.jsp"/>
<form action="login">
    <div class="panel panel-body login-form" style="max-width: 400px; margin: auto;">
        <div class="text-center">
            <div class="icon-object border-slate-300 text-slate-300"><i class="icon-reading"></i></div>
            <h5 class="content-group">Login to your account <small class="display-block">Enter your credentials below</small></h5>
        </div>

        <div class="form-group has-feedback has-feedback-left">
            <input type="text" name="username" class="form-control" placeholder="Username">
            <div class="form-control-feedback">
                <i class="icon-user text-muted"></i>
            </div>
        </div>

        <div class="form-group has-feedback has-feedback-left">
            <input type="password" name="password" class="form-control" placeholder="Password">
            <div class="form-control-feedback">
                <i class="icon-lock2 text-muted"></i>
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Sign in <i class="icon-circle-right2 position-right"></i></button>
        </div>

        <div class="text-center">
            <a href="login_password_recover.html">Forgot password?</a>
        </div>
    </div>
</form>
<jsp:include page="footer.jsp"/>