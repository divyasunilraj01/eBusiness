<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>

<compress:html removeIntertagSpaces="true">
    <!DOCTYPE HTML>
    <html lang="ru">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="author" content="Divya Sunil Raj https://www.linkedin.com/in/divya-sunilraj-3bb66b124">
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Категория ${category.title} || Alex Coffee">
        <title>Категория ${category.title} ||A1 Coffee</title>
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
        <section id="category">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Category </span>
                            <span class="color-green">"${category.title}"</span>
                        </b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Name:</th>
                            <td>${category.title}</td>
                        </tr>
                        <tr>
                            <th>URl:</th>
                            <td>
                                <a href="<c:url value="/category/${category.url}"/>"
                                   title="Go to Category ${category.title}">${category.url}</a>
                            </td>
                        </tr>
                        <tr>
                            <th>Description:</th>
                            <td>${category.description}</td>
                        </tr>
                        <tr>
                            <th>Image:</th>
                            <td>${category.photo.title}
                                <br><img src="<c:url value="/resources/img/${category.photo.smallUrl}"/>"
                                         width="75px" height="75px" alt="${category.title}">
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/category/edit/${category.id}"/>"
                                   title="РEdit Category ${category.title}">
                                    <button class="btn btn-success" type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/category/delete/${category.id}"/>"
                                   title="Delete Category ${category.title}">
                                    <button class="btn btn-danger" type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/category/all"/>" title="Вернуться к списку категорий">
                                    <button class="btn btn-info" type="submit">Back</button>
                                </a>
                            </td>
                        </tr>
                    </table>
                </div>
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


