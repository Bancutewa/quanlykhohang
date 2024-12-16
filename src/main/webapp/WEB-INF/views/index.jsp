<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->

    <link href="<c:url value="/resources/vendors/bootstrap/dist/css/bootstrap.min.css"/>" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:url value="/resources/vendors/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:url value="/resources/vendors/nprogress/nprogress.css"/>" rel="stylesheet">
    <!-- jQuery custom content scroller -->
    <link href="<c:url value="/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css"/>" rel="stylesheet"/>

    <!-- Custom Theme Style -->
    <link href="<c:url value="/resources/build/css/custom.min.css"/>" rel="stylesheet">
  </head>

  <body class="nav-md footer_fixed">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Inventory Management</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="/quanlykhohang/resources/images/user.png" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>D khai</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="index.html">Dashboard</a></li>
                      <li><a href="index2.html">Dashboard2</a></li>
                      <li><a href="index3.html">Dashboard3</a></li>
                    </ul>
                  </li>


                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <div class="nav toggle">
                  <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                </div>
                <nav class="nav navbar-nav">
                <ul class=" navbar-right">
                  <li class="nav-item dropdown open" style="padding-left: 15px;">
                    <a href="javascript:;" class="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                      <img src="images/img.jpg" alt="">John Doe
                    </a>
                    <div class="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item"  href="javascript:;"> Profile</a>
                        <a class="dropdown-item"  href="javascript:;">
                          <span class="badge bg-red pull-right">50%</span>
                          <span>Settings</span>
                        </a>
                    <a class="dropdown-item"  href="javascript:;">Help</a>
                      <a class="dropdown-item"  href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                    </div>
                  </li>
<%--            hòm thư--%>
<%--                  <li role="presentation" class="nav-item dropdown open">--%>
<%--                    <a href="javascript:;" class="dropdown-toggle info-number" id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">--%>
<%--                      <i class="fa fa-envelope-o"></i>--%>
<%--                      <span class="badge bg-green">6</span>--%>
<%--                    </a>--%>
<%--                    <ul class="dropdown-menu list-unstyled msg_list" role="menu" aria-labelledby="navbarDropdown1">--%>
<%--                      <li class="nav-item">--%>
<%--                        <a class="dropdown-item">--%>
<%--                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>--%>
<%--                          <span>--%>
<%--                            <span>John Smith</span>--%>
<%--                            <span class="time">3 mins ago</span>--%>
<%--                          </span>--%>
<%--                          <span class="message">--%>
<%--                            Film festivals used to be do-or-die moments for movie makers. They were where...--%>
<%--                          </span>--%>
<%--                        </a>--%>
<%--                      </li>--%>
<%--                      <li class="nav-item">--%>
<%--                        <a class="dropdown-item">--%>
<%--                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>--%>
<%--                          <span>--%>
<%--                            <span>John Smith</span>--%>
<%--                            <span class="time">3 mins ago</span>--%>
<%--                          </span>--%>
<%--                          <span class="message">--%>
<%--                            Film festivals used to be do-or-die moments for movie makers. They were where...--%>
<%--                          </span>--%>
<%--                        </a>--%>
<%--                      </li>--%>
<%--                      <li class="nav-item">--%>
<%--                        <a class="dropdown-item">--%>
<%--                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>--%>
<%--                          <span>--%>
<%--                            <span>John Smith</span>--%>
<%--                            <span class="time">3 mins ago</span>--%>
<%--                          </span>--%>
<%--                          <span class="message">--%>
<%--                            Film festivals used to be do-or-die moments for movie makers. They were where...--%>
<%--                          </span>--%>
<%--                        </a>--%>
<%--                      </li>--%>
<%--                      <li class="nav-item">--%>
<%--                        <a class="dropdown-item">--%>
<%--                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>--%>
<%--                          <span>--%>
<%--                            <span>John Smith</span>--%>
<%--                            <span class="time">3 mins ago</span>--%>
<%--                          </span>--%>
<%--                          <span class="message">--%>
<%--                            Film festivals used to be do-or-die moments for movie makers. They were where...--%>
<%--                          </span>--%>
<%--                        </a>--%>
<%--                      </li>--%>
<%--                      <li class="nav-item">--%>
<%--                        <div class="text-center">--%>
<%--                          <a class="dropdown-item">--%>
<%--                            <strong>See All Alerts</strong>--%>
<%--                            <i class="fa fa-angle-right"></i>--%>
<%--                          </a>--%>
<%--                        </div>--%>
<%--                      </li>--%>
<%--                    </ul>--%>
<%--                  </li>--%>
                </ul>
              </nav>
            </div>
          </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
          <div class="">
            <div class="page-title">
              <div class="title_left">
<%--                <h3>Fixed Footer <small> Just add class <strong>footer_fixed</strong></small></h3>--%>
              </div>
            </div>
          </div>
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="<c:url value="/resources/vendors/jquery/dist/jquery.min.js"/>"></script>
    <!-- Bootstrap -->
   <script src="<c:url value="/resources/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"/>"></script>
    <!-- FastClick -->
    <script src="<c:url value="/resources/vendors/fastclick/lib/fastclick.js"/>"></script>
    <!-- NProgress -->
    <script src="<c:url value="/resources/vendors/nprogress/nprogress.js"/>"></script>
    <!-- jQuery custom content scroller -->
    <script src="<c:url value="/resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"/>"></script>

    <!-- Custom Theme Scripts -->
    <script src="<c:url value="/resources/build/js/custom.min.js"/>"></script>
  </body>
</html>