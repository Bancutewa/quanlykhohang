<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 17/12/2024
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
    <div class="menu_section">
        <h3>General</h3>
        <ul class="nav side-menu">
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                <div class="menu_section">
                    <ul class="nav side-menu">
                        <c:forEach items="${menuSession}" var="menu">
                            <li id="${menu.idMenu}">
                                <a><i></i> ${menu.name} <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <c:forEach items="${menu.child}" var="child">
                                        <li id="${child.idMenu}">
                                            <a href="<c:url value='${child.url}'/>">${child.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>



        <%--    <c:out value="${menuSession}" />--%>



</ul>
    </div>

</div>
<!-- /sidebar menu -->