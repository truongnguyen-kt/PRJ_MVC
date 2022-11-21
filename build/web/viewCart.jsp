<%-- 
    Document   : viewCart
    Created on : Oct 14, 2022, 9:05:07 AM
    Author     : 12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page import="java.util.Map"%>--%>
<%--<%@page import="truongnhl.product.ProductDTO"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Mart</title>
    </head>
    <body>
        <h1>Web Mart</h1>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}" />
            <c:if test="${not empty items}">
                <form action="cart">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Sku</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Action</th>
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
                                        <input type="checkbox" name="chkItem" value="${dto.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="6">
                                    <a href="showProduct">
                                        Add More Item to Your Cart
                                    </a>
                                </td>
                                <td>
                                    <input type="submit"
                                           value="Remove Selected Items" 
                                           name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <input style="text-align: center" type="submit" value="Checkout Selected Items" name="btAction" />
                </form>
            </c:if>

            <!-- EMPTY ITEMS -->
            <c:if test="${empty items}">
                <h2>
                    No item exited in your cart!
                </h2>
                <a href="showProduct">Add More Books to Your Cart</a>
            </c:if>
        </c:if>

        <c:if test="${empty cart}">
            <h1>No cart is existed!</h1>
            <a href="showProduct">Click Here To Go Shopping</a>
        </c:if>

    </body>
    <%--
    <body>
        <h1>Web Mart</h1>
        <%
            //1. Cust goes to his/her cart placement
            if (session != null) {
                //2. Cust takes his/her cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. System traverse items to show
        %>
        <form action="cart">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                            int value = items.get(key);
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= value%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key%>" />
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="showProduct">Add More Items to Your Cart!</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Item" name="btAction" />
                        </td>
                    </tr>
                </tbody>

            </table>
            <input type="submit" value="Check Out" name="btAction" />
        </form>

        <%
                        return;
                    }// end item has existed
                }// end cart has existed
            }// end session has existed
        %>
        <h2>
            No cart is existed!!!
        </h2>       
        <a href="showProduct">Add Item to Your Cart</a>
    </body>
    --%>
</html>
