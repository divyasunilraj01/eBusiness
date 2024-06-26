<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Divya Sunil Raj https://www.linkedin.com/in/divya-sunilraj-3bb66b124">
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Категории кофе || Alex Coffee">
        <title>A1 Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
    <jsp:include page="/WEB-INF/views/other/admin_navbar.jsp"/>
    <div class="container-fluid">
        <section id="categories">
            <div class="row admin-page">
                <c:set var="categories_length" value="${fn:length(categories)}"/>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Categories</span>
                            <c:if test="${categories_length eq 0}">
                                <span class="color-red"> - Items!</span><br>
                                <a href="<c:url value="/admin/category/add"/>" title="Add New Item">
                                    <button class="btn btn-success" type="submit">Add</button>
                                </a>
                            </c:if>
                        </b>
                    </div>
                </div>
                <c:if test="${categories_length gt 0}">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                        <table class="table">
                            <tr>
                                <th>Name</th>
                                <td class="hidden-xs"><b>URL</b></td>
                                <th>
                                    Action
                                    <a href="<c:url value="/admin/category/add"/>" title="Add New Category">
                                        <button class="btn btn-success" type="submit">Add</button>
                                    </a>
                                    <a href="<c:url value="/admin/category/delete_all"/>"
                                       title="Delete Categories">
                                        <button class="btn btn-danger" type="submit">Delete ВСЕ</button>
                                    </a>
                                </th>
                            </tr>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td>
                                        <a href="<c:url value="/category/${category.url}"/>"
                                           title="Go To Category ${category.title}">${category.title}</a>
                                    </td>
                                    <td class="hidden-xs">${category.url}</td>
                                    <td>
                                        <a href="<c:url value="/admin/category/view/${category.id}"/>"
                                           title="View Category ${category.title}">
                                            <button class="btn btn-info" type="submit">View</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/edit/${category.id}"/>"
                                           title="Edit Category ${category.title}">
                                            <button class="btn btn-success" type="submit">Edit</button>
                                        </a>
                                        <a href="<c:url value="/admin/category/delete/${category.id}"/>"
                                           title="Delete Category ${category.title}">
                                            <button class="btn btn-danger" type="submit">Delete</button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
            </div>
        </section>
    </div>
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.appear.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput.min.js"/>"></script>
    </body>
    </html>
</compress:html>

<%-- Yurii Salimov (yuriy.alex.salimov@gmail.com) --%>
