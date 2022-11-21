<%-- 
    Document   : checkout
    Created on : Oct 30, 2022, 7:11:31 PM
    Author     : 12345
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CHECKOUT PAGE</title>
    </head>
    <body>
        <c:set var="list" value="${sessionScope.CHECKOUT_ITEMS}" />

        <c:if test="${not empty list}">
            <form action="confirm">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Sum</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${list}" varStatus="counter">
                            <c:set var="dto" value="${item.key}" />
                            <c:set var="quantity" value="${item.value}" />
                            <c:set var="price" value="${dto.price}" />

                            <c:set var="sum" value="${quantity * price}"/>

                            <c:set var="total" value="${total + quantity * price}" />

                            <tr>
                                <td style="text-align: center">
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.sku}
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td style="text-align: center">
                                    ${quantity}
                                </td>
                                <td>
                                    <fmt:formatNumber value="${price}" maxFractionDigits="1"/>
                                </td>
                                <td>
                                    <fmt:formatNumber value="${sum}" maxFractionDigits="1"/>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" style="text-align: right">
                                Total
                            </td>
                            <td>
                                <fmt:formatNumber value="${total}" maxFractionDigits="1"/>
                                <input type="hidden" name="txtTotal" value="${total}" />
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" value="Check Out"/>
            </form>
        </c:if>

        <c:if test="${empty list}">
            <h2>No Selected Item for CheckOut!</h2>
            <a href="viewCartPage">Go Back To Your Cart</a> 
        </c:if>
        <%--
        <h1>Checkout</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="cart">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>SKU</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Remove</th>
                                <th>Check Out</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <c:set var="dto" value="${item.key}" />
                                <c:set var="quantity" value="${item.value}" />
                                <tr>
                                    <td style="text-align: center">
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.sku}
                                    </td>
                                    <td>
                                        ${dto.name}
                                    </td>
                                    <td>
                                        ${dto.description}
                                    </td>
                                    <td style="text-align: center">
                                        ${quantity}
                                    </td>
                                    <td>
                                        ${dto.price}
                                    </td>
                                    <td style="text-align: center">
                                        <input type="checkbox" name="chkItem" 
                                               value="${dto.sku}" />
                                    </td>
                                    <td style="text-align: center">
                                        <input type="checkbox" name="chkCheckOut" 
                                               value="${dto.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="6">
                                    <a href="showProduct">
                                        Add More Books to Your Cart
                                    </a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Check Out Selected Items" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </c:if>
        </c:if>
        --%>
    </body>
</html>
