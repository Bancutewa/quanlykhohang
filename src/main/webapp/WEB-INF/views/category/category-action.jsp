<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
          <form:form modelAttribute="modelForm" action="/category/save" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <form:hidden path="createDate"/>
            <form:hidden path="activeFlag"/>

            <div class="form-group row">
              <label class="col-md-3 col-form-label" for="code">Code <span class="required">*</span></label>
              <div class="col-md-6">
                <form:input path="code" class="form-control" disabled="${viewOnly}"/>
                <div class="text-danger">
                  <form:errors path="code" cssClass="help-block"></form:errors>
                </div>
              </div>
            </div>

            <div class="form-group row">
              <label class="col-md-3 col-form-label" for="name">Name <span class="required">*</span></label>
              <div class="col-md-6">
                <form:input path="name" class="form-control" disabled="${viewOnly}"/>
                <div class="text-danger">
                  <form:errors path="name" cssClass="help-block"></form:errors>
                </div>
              </div>
            </div>

            <div class="form-group row">
              <label class="col-md-3 col-form-label" for="description">Description</label>
              <div class="col-md-6">
                <form:input path="description" class="form-control" disabled="${viewOnly}"/>
                <div class="text-danger">
                  <form:errors path="description" cssClass="help-block"></form:errors>
                </div>
              </div>
            </div>

            <div class="ln_solid"></div>

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
