<%-- 
    Document   : shoping
    Created on : Oct 17, 2022, 6:34:54 PM
    Author     : 12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="truongnhl.product.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="truongnhl.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Mart</title>
    </head>
    <body>
        <h1>Web Mart</h1>
        <c:set var="result" value="${requestScope.product}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="cart">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.name}
                                <%--<%= // dto.getName()%>--%>
                                <input type="hidden" name="cboBook" 
                                       value="${dto.sku}" />
                            </td>
                            <td>
                                ${dto.price}
                            </td>
                            <td>
                                <input type="submit" value="Add book to Your Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <form action="cart">
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>

    </c:if>
    <c:if test="${empty result}">
        <h2>
            Run out of Product!!!
        </h2>
    </c:if>
    <%--
    <form action="cart">
        <%
            ProductDAO dao = new ProductDAO();
            dao.getProduct();
            List<ProductDTO> result = dao.getProductList();
            if (result != null) {
        %>

            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (ProductDTO dto : result) {
                    %>
                <form action="cart">
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= dto.getName()%>
                            <input type="hidden" name="cboBook" 
                                   value="<%= dto.getName()%>" />
                        </td>
                        <td>
                            <%= dto.getPrice()%>
                        </td>
                        <td>
                            <input type="submit" value="Add book to Your Cart" name="btAction" />
                        </td>
                    </tr>
                </form>
                <%
                    }// end traverse result DTO list
                %>
                </tbody>
            </table>
            <%
            } else {
            %>
            <h2>
                No product in database!!!
            </h2>
            <%
                }

            %>

            <!--        <form action="DispatchController">
                        Book <select name="cboBook">
                            <option>Basic</option>
                            <option>Java</option>
                            <option>JDK</option>
                            <option>Servlet</option>
                            <option>Netbeans</option>
                            <option>TomCat</option>
                            <option>JSP</option>
                        </select><br>
                        <input type="submit" value="Add book to Your Cart" name="btAction" />
                        <input type="submit" value="View Your Cart" name="btAction" />
            
                    </form>-->
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
    --%>
</body>
</html>
