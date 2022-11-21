<%-- 
    Document   : createNewAccount
    Created on : Oct 25, 2022, 7:33:13 AM
    Author     : 12345
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
        <!--<link rel="stylesheet" href="css/logincss.css" type="text/css"/>-->

    </head>
    <body>
            <h1 class="color" >Create new Account</h1>
            <form action="createNewAccount" method="POST">
                <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>

                <label for="username">  Username* </label>
                <input type="text" id="username" name="txtUsername" value="${param.txtUsername}" /> (6 - 20 chars)<br/>
                <c:if test="${not empty errors.usernameLengthError}">
                    <font color="red">
                    ${errors.usernameLengthError}
                    </font><br/>
                </c:if>

                <label for="password">  Password* </label>
                <input type="password" id="password" name="txtPassword" value="" /> (6 - 30 chars)<br/>
                <c:if test="${not empty errors.passwordLengthError}">
                    <font color="red">
                    ${errors.passwordLengthError}
                    </font><br/>
                </c:if>

                <div class="mid">
                    <label for="confirm">  Confirm* </label>
                    <input type="password" id="confirm" name="txtConfirm" value="" /><br/>
                    <c:if test="${not empty errors.confirmNotMatch}">
                        <font color="red">
                        ${errors.confirmNotMatch}
                        </font><br/>
                    </c:if>
                </div>

                <label for="fullname"> Full name* </label>
                <input type="text" id="fullname" name="txtFullname" value="${param.txtFullname}" />(2 - 50 chars)<br/>
                <c:if test="${not empty errors.fullnameLengthError}">
                    <font color="red">
                    ${errors.fullnameLengthError}
                    </font><br/>
                </c:if>

                <input class="setsize" type="submit" value="Create New Account" name="btAction" />
                <input class="setsize" type="reset" value="Reset" />
            </form>
    </body>
</html>
