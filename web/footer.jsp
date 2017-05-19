<%@page import="Model.Page"%>
<%
    Page p = (Page) request.getAttribute("page");
    if( p == null ) {
        p = Page.fromString(request.getParameter("page"));
    }
%>

<div class="footer text-muted">
    &copy; 2015. <a href="#">Limitless Web App Kit</a> by <a href="http://themeforest.net/user/Kopyov" target="_blank">Eugene Kopyov</a>
</div>

</div><!-- /content -->
</div><!-- /content wrapper-->
</div><!-- /page content -->
</div><!-- /page container -->

<!-- Core JS files -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- /core JS files -->

<!-- Theme JS files -->
<script type="text/javascript" src="js/nicescroll.min.js"></script>

<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/layout_fixed_custom.js"></script>
<%=p.printExtraJS()%>

</body>
</html>
