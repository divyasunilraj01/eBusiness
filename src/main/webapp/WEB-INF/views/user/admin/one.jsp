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
        <meta name="title" content="${user.name} | ${user.role.description} || A1 Coffee">
        <title>${user.name} | ${user.role.description} || A1 Coffee</title>
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
        <section id="product">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow"><b><span class="color-brown">${user.name}</span></b></div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <table class="table">
                        <tr>
                            <th>Name:</th>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <th>Role:</th>
                            <td>
                                <c:choose>
                                    <c:when test="${user.role eq admin_role}">
                                        <span class="color-red">${user.role.description}</span>
                                    </c:when>
                                    <c:when test="${user.role eq manager_role}">
                                        <span class="color-green">${user.role.description}</span>
                                    </c:when>
                                    <c:otherwise>${user.role.description}</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>Name:</th>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <th>Password:</th>
                            <td>${user.password}</td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td>
                                <a href="mailto:${user.email}" title="Email" target="_blank">${user.email}</a>
                            </td>
                        </tr>
                        <tr>
                            <th>Phone:</th>
                            <td>${user.phone}</td>
                        </tr>
                        <c:if test="${not empty user.instagramid}">
                            <tr>
                                <th>Instagram Id:</th>
                                <td>
                                    <a href="https://${user.instagramid}" title="Instagram Id"
                                       target="_blank">${user.instagramid}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.facebook}">
                            <tr>
                                <th>Facebook:</th>
                                <td>
                                    <a href="https://${user.facebook}" title="Facebook"
                                       target="_blank">${user.facebook}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.skype}">
                            <tr>
                                <th>Skype:</th>
                                <td>
                                    <a href="skype:${user.skype}?call" title="Skype"
                                       target="_blank">${user.skype}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty user.description}">
                            <tr>
                                <th>Description:</th>
                                <td>${user.description}</td>
                            </tr>
                        </c:if>
                        <tr>
                            <th></th>
                            <td>
                                <a href="<c:url value="/admin/user/edit/${user.id}"/>"
                                   title="Edit Information ${user.username}">
                                    <button class="btn btn-success" type="submit">Edit</button>
                                </a>
                                <a href="<c:url value="/admin/user/delete/${user.id}"/>"
                                   title="Delete Information ${user.username}">
                                    <button class="btn btn-danger btn-mg" type="submit">Delete</button>
                                </a>
                                <a href="<c:url value="/admin/user/all"/>" title="Return to user list">
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


