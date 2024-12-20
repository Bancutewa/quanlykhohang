<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- page content -->
<div class="right_col" role="main">
    <div class="x_panel">
        <div class="x_title">
            <h2>Category List</h2>
            <div class="clearfix"></div>
        </div>
        <div class="container">
            <a href="<c:url value="/category/add"/>" class="btn btn-app"><i class="fa fa-plus"></i> Add</a>
            <!-- Search Form -->
            <form:form modelAttribute="searchForm" cssClass="form-horizontal form-label-left" servletRelativeAction="/category/list" method="post">
                <div class="form-group row">
                    <label for="id" class="col-md-3 col-form-label">ID</label>
                    <div class="col-md-6">
                        <form:input path="id" class="form-control"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="code">Code</label>
                    <div class="col-md-6">
                        <form:input path="code" class="form-control"/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-md-3 col-form-label" for="name">Name</label>
                    <div class="col-md-6">
                        <form:input path="name" class="form-control"  />
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-6 offset-md-3">
                        <button type="submit" class="btn btn-success">Search</button>
                    </div>
                </div>
            </form:form>
        </div>

        <!-- Category Table -->
        <div class="x_content">
            <div class="table-responsive">
                <table class="table table-striped jambo_table bulk_action">
                    <thead>
                    <tr class="headings">
                        <th class="column-title">#</th>
                        <th class="column-title">ID</th>
                        <th class="column-title">Code</th>
                        <th class="column-title">Name</th>
                        <th class="column-title">Description</th>
                        <th class="column-title no-link last text-center" colspan="3">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categories}" var="category" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.index % 2 == 0}">
                                <tr class="even pointer">
                            </c:when>
                            <c:otherwise>
                                <tr class="odd pointer">
                            </c:otherwise>
                        </c:choose>
                        <td>${loop.index + 1}</td>
                        <td>${category.id}</td>
                        <td>${category.code}</td>
                        <td>${category.name}</td>
                        <td>${category.description}</td>
                        <td class="text-center">
                            <a href="<c:url value='/category/view/${category.id}' />" class="btn btn-round btn-default">View</a>
                        </td>
                        <td class="text-center">
                            <a href="<c:url value='/category/edit/${category.id}' />" class="btn btn-round btn-primary">Edit</a>
                        </td>
                        <td class="text-center">
                            <a href="javascript:void(0);" onclick="confirmDelete(${category.id})" class="btn btn-round btn-danger">Delete</a>
                        </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->

<script type="text/javascript">
    function confirmDelete(id) {
        if (confirm('Do you want to delete this record?')) {
            window.location.href = "<c:url value='/category/delete/'/>" + id;
        }
    }

    $(document).ready(function () {
        processMessage();
    });

    function processMessage() {
        var msgSuccess = '${msgSuccess}';
        var msgError = '${msgError}';
        if (msgSuccess) {
            new PNotify({
                title: 'Success',
                text: msgSuccess,
                type: 'success',
                styling: 'bootstrap3'
            });
        }
        if (msgError) {
            new PNotify({
                title: 'Error',
                text: msgError,
                type: 'error',
                styling: 'bootstrap3'
            });
        }
    }
</script>
