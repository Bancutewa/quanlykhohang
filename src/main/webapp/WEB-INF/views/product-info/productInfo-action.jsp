<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="master">
  <tiles:putAttribute name="body">
    <div class="right_col" role="main">
      <div class="page-title">
        <div class="title_left">
          <h2>${titlePage}</h2>
        </div>
      </div>
      <div class="clearfix"></div>
      <div class="row">
        <div class="col-md-12">
          <div class="x_panel">
            <div class="x_content">
                <br />
                <form:form modelAttribute="modelForm" servletRelativeAction="/productInfo/save" method="post" class="form-horizontal" enctype="multipart/form-data">

                <!-- Hidden Fields -->
                <form:hidden path="id"/>
                <form:hidden path="createDate"/>
                <form:hidden path="activeFlag"/>

                <!-- Category Selection -->
                <div class="form-group">
                  <label class="col-md-3 col-form-label" for="cateId">Category <span class="required">*</span></label>
                  <div class="col-md-6">
                    <form:select path="cateId" cssClass="form-control">
                      <form:options items="${mapCategories}"/>
                    </form:select>
                    <div class="text-danger">
                      <form:errors path="cateId" cssClass="help-block"></form:errors>
                    </div>
                  </div>
                </div>

                <!-- Code Input -->
                <div class="form-group row">
                  <label class="col-md-3 col-form-label" for="code">Code <span class="required">*</span></label>
                  <div class="col-md-6">
                    <form:input path="code" class="form-control" disabled="${viewOnly}"/>
                    <div class="text-danger">
                      <form:errors path="code" cssClass="help-block"></form:errors>
                    </div>
                  </div>
                </div>

                <!-- Name Input -->
                <div class="form-group row">
                  <label class="col-md-3 col-form-label" for="name">Name <span class="required">*</span></label>
                  <div class="col-md-6">
                    <form:input path="name" class="form-control" disabled="${viewOnly}"/>
                    <div class="text-danger">
                      <form:errors path="name" cssClass="help-block"></form:errors>
                    </div>
                  </div>
                </div>

                <!-- Description Input -->
                <div class="form-group row">
                  <label class="col-md-3 col-form-label" for="description">Description</label>
                  <div class="col-md-6">
                    <form:textarea path="description" class="form-control" disabled="${viewOnly}"/>
                    <div class="text-danger">
                      <form:errors path="description" cssClass="help-block"></form:errors>
                    </div>
                  </div>
                </div>

                <!-- Image Upload -->
                <div class="form-group row">
                  <label class="col-md-3 col-form-label" for="multipartFile">Image upload</label>
                  <div class="col-md-6">
                    <form:input path="multipartFile" cssClass="form-control" type="file"/>
                    <div class="text-danger">
                      <form:errors path="multipartFile" cssClass="help-block"></form:errors>
                    </div>
                  </div>
                </div>

                <div class="ln_solid"></div>

                <!-- Submit and Reset Buttons -->
                <div class="form-group row">
                  <div class="col-md-6 offset-md-3">
                    <button class="btn btn-secondary" type="button" onclick="cancel()">Cancel</button>
                    <c:if test="${!viewOnly}">
                      <button class="btn btn-warning" type="reset">Reset</button>
                      <button type="submit" class="btn btn-success">Submit</button>
                    </c:if>
                  </div>
                </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </tiles:putAttribute>
</tiles:insertDefinition>

<script type="text/javascript">
  $(document).ready(function () {
    $('#categorylistId').addClass('current-page').siblings().removeClass('current-page');
    var parent = $('#categorylistId').parents('li');
    parent.addClass('active').siblings().removeClass('active');
    $('#categorylistId').parents().show();
  });

  function cancel() {
    window.location.href = '<c:url value="/category/list"/>';
  }
</script>
