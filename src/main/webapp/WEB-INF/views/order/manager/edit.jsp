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
        <meta name="author" content="Yurii Salimov https://www.linkedin.com/in/yurii-salimov">
        <meta name="robots" content="noindex,nofollow">
        <meta name="title" content="Редактирование заказа ${order.number} || A1 Coffee">
        <title>Edit your order ${order.number} || A1 Coffee</title>
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">
        <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/style.min.css"/>" rel="stylesheet">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <%-- NAVBAR --%>
    <jsp:include page="/WEB-INF/views/other/manager_navbar.jsp"/>
        <%-- Edit order --%>
    <div class="container-fluid">
        <section id="order">
            <div class="row admin-page">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1">
                    <div class="row section-name text-shadow">
                        <b>
                            <span class="color-brown">Edit Order </span>
                            <span class="color-green">${order.number}</span>
                        </b>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 col-xl-10 col-xl-offset-1 full-cart">
                    <form action="<c:url value="/managers/order/update"/>" method="post">
                        <input type="hidden" name="id" value="${order.id}">
                        <input type="hidden" name="auth_user" value="${auth_user.id}">
                        <table class="table">
                            <tr>
                                <th>Number of Orders:</th>
                                <td>
                                    <input class="input-order" type="text" name="number" pattern="[A-Za-z0-9]{3,10}"
                                           placeholder=" Enter number, format (A-Z, a-z, 0-9)"
                                           value="${order.number}" minlength="3" maxlength="10" required>
                                </td>
                            </tr>
                            <tr>
                                <th>Status:</th>
                                <td>
                                    <select class="input-order" name="status" title="Статус заказа">
                                        <option value="${order.status.id}">${order.status.description}</option>
                                        <c:forEach items="${statuses}" var="status">
                                            <c:if test="${status.id ne order.status.id}">
                                                <option value="${status.id}">${status.description}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Date:</th>
                                <td>${order.date}</td>
                            </tr>
                            <tr>
                                <th>Customer:</th>
                                <td>
                                    <input class="input-order" type="text" name="user_name" minlength="2" maxlength="50"
                                           placeholder=" Введите имя клиента" value="${order.client.name}" required>
                                    <br><input class="input-order" name="user_email" value="${order.client.email}"
                                               placeholder=" Enter customer email, format (A-Z, a-z, 0-9, _, ., @)"
                                               pattern="[A-Za-z0-9_.@]{5,50}" minlength="5" maxlength="50" type="email">
                                    <br><input id="phone" class="input-order" type="text" name="user_phone" required
                                               placeholder=" Enter customer phone" value="${order.client.phone}">
                                </td>
                            </tr>
                            <tr>
                                <th>Delivery Address:</th>
                                <td>
                                    <input class="input-order" type="text" name="shipping-address" maxlength="100"
                                           placeholder=" Enter shipping address" value="${order.shippingAddress}">
                                </td>
                            </tr>
                            <tr>
                                <th>Shipping Details:</th>
                                <td>
                                    <input class="input-order" type="text" name="shipping-details" maxlength="100"
                                           placeholder=" Enter shipping details" value="${order.shippingDetails}">
                                </td>
                            </tr>
                            <tr>
                                <th>Feedback:</th>
                                <td>
                                <textarea class="input-order textarea" name="description"
                                          placeholder=" Коментарий" maxlength="250">${order.description}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th>Products:</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${fn:length(sale_positions) gt 0}">
                                            <c:forEach items="${sale_positions}" var="position">
                                                <a href="<c:url value="/product/${position.product.url}"/>"
                                                   title="Перейти к товару ${position.product.title}">
                                                        ${position.product.title}
                                                </a>, № ${position.product.id},
                                                <br>${position.number} x ${position.product.price} грн
                                                <br>--------------<br>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>Empty Cart!</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>Total Amount:</th>
                                <td>${order_price} INR</td>
                            </tr>
                            <tr>
                                <th></th>
                                <td>
                                    <button class="btn btn-success" type="submit"
                                            title="Update order information">Save</button>
                                    <button class="btn btn-info" type="reset"
                                            title="Reset entered data">Reset</button>
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
    </body>
    </html>
</compress:html>


