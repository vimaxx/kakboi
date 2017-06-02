<%@page import="Model.User"%>
<%@page import="Model.Page"%>

<%
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    if( loggedUser == null ) {
        response.sendRedirect("login");
        return;
    }
    Page p = Page.BM_MAIN_PAGE;
    request.setAttribute("page", p);
%>
<jsp:include page="header.jsp"/>

<!-- Simple panel -->
<div class="panel panel-flat">
    <div class="panel-heading">
        <h5 class="panel-title">Simple panel</h5>
        <div class="heading-elements">
            <ul class="icons-list">
                <li><a data-action="collapse"></a></li>
                <li><a data-action="close"></a></li>
            </ul>
        </div>
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
<!-- /simple panel -->


<!-- Table -->
<div class="panel panel-flat">
    <div class="panel-heading">
        <h5 class="panel-title">Basic table</h5>
        <div class="heading-elements">
            <ul class="icons-list">
                <li><a data-action="collapse"></a></li>
                <li><a data-action="close"></a></li>
            </ul>
        </div>
    </div>

    <div class="panel-body">
        Starter pages include the most basic components that may help you start your development process - basic grid example, panel, table and form layouts with standard components. Nothing extra.
    </div>

    <div class="table-responsive">
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Username</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Eugene</td>
                    <td>Kopyov</td>
                    <td>@Kopyov</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Victoria</td>
                    <td>Baker</td>
                    <td>@Vicky</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>James</td>
                    <td>Alexander</td>
                    <td>@Alex</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Franklin</td>
                    <td>Morrison</td>
                    <td>@Frank</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<!-- /table -->


<!-- Grid -->
<div class="row">
    <div class="col-md-6">

        <!-- Horizontal form -->
        <div class="panel panel-flat">
            <div class="panel-heading">
                <h5 class="panel-title">Horizontal form</h5>
                <div class="heading-elements">
                    <ul class="icons-list">
                        <li><a data-action="collapse"></a></li>
                        <li><a data-action="close"></a></li>
                    </ul>
                </div>
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="#">
                    <div class="form-group">
                        <label class="control-label col-lg-2">Text input</label>
                        <div class="col-lg-10">
                            <input type="text" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2">Password</label>
                        <div class="col-lg-10">
                            <input type="password" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2">Select</label>
                        <div class="col-lg-10">
                            <select name="select" class="form-control">
                                <option value="opt1">Basic select</option>
                                <option value="opt2">Option 2</option>
                                <option value="opt3">Option 3</option>
                                <option value="opt4">Option 4</option>
                                <option value="opt5">Option 5</option>
                                <option value="opt6">Option 6</option>
                                <option value="opt7">Option 7</option>
                                <option value="opt8">Option 8</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2">Textarea</label>
                        <div class="col-lg-10">
                            <textarea rows="5" cols="5" class="form-control" placeholder="Default textarea"></textarea>
                        </div>
                    </div>

                    <div class="text-right">
                        <button type="submit" class="btn btn-primary">Submit form <i class="icon-arrow-right14 position-right"></i></button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /horizotal form -->

    </div>

    <div class="col-md-6">

        <!-- Vertical form -->
        <div class="panel panel-flat">
            <div class="panel-heading">
                <h5 class="panel-title">Vertical form</h5>
                <div class="heading-elements">
                    <ul class="icons-list">
                        <li><a data-action="collapse"></a></li>
                        <li><a data-action="close"></a></li>
                    </ul>
                </div>
            </div>

            <div class="panel-body">
                <form action="#">
                    <div class="form-group">
                        <label>Text input</label>
                        <input type="text" class="form-control">
                    </div>

                    <div class="form-group">
                        <label>Select</label>
                        <select name="select" class="form-control">
                            <option value="opt1">Basic select</option>
                            <option value="opt2">Option 2</option>
                            <option value="opt3">Option 3</option>
                            <option value="opt4">Option 4</option>
                            <option value="opt5">Option 5</option>
                            <option value="opt6">Option 6</option>
                            <option value="opt7">Option 7</option>
                            <option value="opt8">Option 8</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Textarea</label>
                        <textarea rows="4" cols="4" class="form-control" placeholder="Default textarea"></textarea>
                    </div>

                    <div class="text-right">
                        <button type="submit" class="btn btn-primary">Submit form <i class="icon-arrow-right14 position-right"></i></button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /vertical form -->

    </div>
</div>
<!-- /grid -->

<jsp:include page="footer.jsp"/>