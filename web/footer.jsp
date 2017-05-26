<%@page import="Model.Page"%>
<%
    Page p = (Page) request.getAttribute("page");
    if( p == null ) {
        p = Page.fromString(request.getParameter("page"));
    }
%>

<div class="footer text-muted">
    &copy; 2017. <a href="#">Kakboi</a> by <a href="http://zeetech.ml" target="_blank">IT Hertz</a>
</div>

</div><!-- /content -->
</div><!-- /content wrapper-->
</div><!-- /page content -->
</div><!-- /page container -->

<!-- Theme JS files -->
<script type="text/javascript" src="js/nicescroll.min.js"></script>

<script type="text/javascript" src="js/app.js"></script>
<script type="text/javascript" src="js/layout_fixed_custom.js"></script>
<%=p.printExtraJS()%>

</body>
</html>
