<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="col-md-12 col-sm-12  ">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Category list</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="container">
                    <a href="<c:url value="/category/add"/>" class="btn btn-app"><i class="fa fa-plus"></i>Add</a>
                </div>
                <div class="x_content">

                    <div class="table-responsive">
                        <table class="table table-striped jambo_table bulk_action">
                            <thead>
                            <tr class="headings">
                                <th class="column-title">#  </th>
                                <th class="column-title">Id </th>
                                <th class="column-title">Code </th>
                                <th class="column-title">Name </th>
                                <th class="column-title">Description </th>
                                <th class="column-title no-link last text-center" colspan="3"><span class="nobr">Action</span>
                                </th>
                                <th class="bulk-actions" colspan="7">
                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                </th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${categories}" var="category" varStatus="loop">
                                <c:choose>
                                    <c:when test="${loop.index%2==0}">
                                        <tr class="even pointer">
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="odd pointer">
                                    </c:otherwise>
                                </c:choose>
                                <td class=" ">${loop.index+1}</td>
                                <td class=" ">${category.id }</td>
                                <td class=" ">${category.code } </td>
                                <td class=" ">${category.name }</td>
                                <td class=" ">${category.description}</td>
                                <td class="text-center"><a href="<c:url value="/category/view/${category.id}"/> " class="btn btn-round btn-default">View</a></td>
                                <td class="text-center"><a href="<c:url value="/category/edit/${category.id}"/> " class="btn btn-round btn-primary">Edit</a></td>
                                <td class="text-center"><a href="javascript:void(0);" onclick="confirmDelete(${category.id})" class="btn btn-round btn-danger">Delete</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->
<script type="text/javascript">
    function confirmDelete(id) {
        if(confirm('Do you want delete this record ')) {
            window.location.href = "<c:url value="/category/delete/"/>" + id
        }
    }
</script>