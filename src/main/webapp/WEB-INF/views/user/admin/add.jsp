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
        <meta name="title" content="A1 Coffee">
        <title> A1 Coffee</title>
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
        <section id="user">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b><span class="color-green">New </span><span class="color-brown">Admin</span></b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <form action="<c:url value="/admin/user/save"/>" method="post">
                        <table class="table">
                            <tr>
                                <th>Name:</th>
                                <td>
                                    <input class="input-order" type="text" name="name" placeholder="Enter name"
                                           minlength="2" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Role:</th>
                                <td>
                                    <select class="input-order" name="role" title="Роль пользователя">
                                        <c:forEach items="${roles}" var="role">
                                            <option value="${role.description}">${role.description}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Login:</th>
                                <td>
                                    <input class="input-order" type="text" name="username" pattern="[A-Za-z0-9_]{5,50}"
                                           placeholder="Enter login, format (A-Z, a-z, 0-9, _)"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Password:</th>
                                <td>
                                    <input class="input-order" type="text" name="password" pattern="[A-Za-z0-9]{6,50}"
                                           placeholder=" enter password, format (A-Z, a-z, 0-9)"
                                           minlength="6" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Email:</th>
                                <td>
                                    <input class="input-order" type="email" name="email" pattern="[A-Za-z0-9_.@]{5,50}"
                                           placeholder=" Enter email, format (A-Z, a-z, 0-9, _, ., @)"
                                           minlength="5" maxlength="50" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Mobile:</th>
                                <td>
                                    <input class="phone input-order" type="text" name="phone"
                                           placeholder=" Phone number" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Instagram id:</th>
                                <td>
                                    <input class="input-order" type="text" name="Instagram" pattern="[a-z0-9_/.]{5,50}"
                                           placeholder=" enter instagram id, form (a-z, 0-9, _, /, .)"
                                           minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Facebook:</th>
                                <td>
                                    <input class="input-order" type="text" name="facebook" pattern="[a-z0-9_/.]{5,50}"
                                           placeholder=" enter facebook id , format (a-z, 0-9, _, /, .)"
                                           minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Skype:</th>
                                <td>
                                    <input class="input-order" type="text" name="skype" pattern="[A-Za-z0-9_.]{5,50}"
                                           placeholder=" enter Skype id, format (A-Z, a-z, 0-9, _, .)"
                                           minlength="5" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <th>Description:</th>
                                <td>
                                    <textarea class="input-order textarea" placeholder=" Enter admin description"
                                              name="description" maxlength="500"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit">Add User</button>
                                    <button class="btn btn-info" type="reset">Reset</button>
                                </td>
                            </tr>
                        </table>
                    </form>
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


