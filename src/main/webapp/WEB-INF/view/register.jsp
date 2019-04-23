<%--
  Created by IntelliJ IDEA.
  User: maiha
  Date: 25.03.2019
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <style>
        <%@include file="css/bootstrap.min.css" %>
    </style>
    <script>
        <%@include file="js/jquery-1.12.1.min.js" %>
    </script>
    <script>
        <%@include file="js/jquery.validate.min.js" %>
    </script>

</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Localhost.8080</a>
    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
            <li style="padding: 1px" class="nav-item">
                <form action="/user/regpage" method="post">
                    <input class="btn btn-primary" type="submit" value="Add User"/>
                </form>
            </li>
            <li style="padding: 1px" class="nav-item">
                <form action="/index" method="get">
                    <input class="btn btn-info" type="submit" value="All Users">
                </form>
            </li>
            <li style="padding: 1px" class="nav-item">
                <form action="/roles">
                    <input class="btn btn-info" type="submit" value="Roles">
                </form>
            </li>
        </ul>
        <form class="form-inline my-2 my-md-0" method="GET" action="/search">
            <input class="form-control" type="text" name="fio" placeholder="FIO">
            <input class="form-control" type="text" name="age" placeholder="Age">
            <input class="form-control" type="text" name="role" placeholder="Role">
            <input class="form-control btn btn-primary" type="submit" value="Search"/>
        </form>
    </div>
</nav>


<div class="container align-content-center">
    <h3>Registration form</h3>
    <form action="/user/register" method="post" id="loginform">
        <div class="row container-fluid">
            <label class="col-md-1 " for="name">Name</label>
            <input class=" col-md-3" type="text" name="name" id="name">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="surname">Surname</label>
            <input class=" col-md-3" type="text" name="surname" id="surname">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="login">Login</label>
            <input class=" col-md-3" type="text" name="login" id="login">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="password">Password</label>
            <input class=" col-md-3" type="password" name="password" id="password">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="phone">Phone</label>
            <input class=" col-md-3" type="text" maxlength="15" name="phone" id="phone">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="age">Age</label>
            <input class=" col-md-3" type="text" maxlength="3" name="age" id="age">
        </div>
        <div class="row container-fluid">
            <label class="col-md-1" for="role">Role</label>
            <select class="col-md-3" name="role" id="role" form="loginform">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role.name}"><c:out value="${role.name}"/></option>
                </c:forEach>
            </select>
        </div>
        <input class=" col-md-1 btn btn-info" type="submit" value="Register">
        <input type="text" name="actual" value="true" hidden>
    </form>
    <div class="row">
        <a class="col-md-3" href="/index"><input class=" col-md-3 btn btn-primary" src="/index" type="submit"
                                                 value="Back"></a>
    </div>
    <script>
        $(document).ready(function () {
            $("#loginform").validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 4,
                        maxlength: 16,
                    },
                    surname: {
                        required: true,
                        minlength: 4,
                        maxlength: 16,
                    },
                    login: {
                        required: true,
                        minlength: 4,
                        maxlength: 16,
                    },
                    password: {
                        required: true,
                        minlength: 6,
                        maxlength: 16,
                    },
                    phone: {
                        required: true,
                        minlength: 6,
                        maxlength: 16,
                        digits: true,
                    },
                    age: {
                        required: true,
                        minlength: 2,
                        digits: true,
                    },
                },
                messages: {
                    name: {
                        required: "This field is required",
                        minlength: "Min length is 4",
                        maxlength: "Max length is 15",
                    },
                    surname: {
                        required: "This field is required",
                        minlength: "Min length is 4",
                        maxlength: "Max length is 15",
                    },
                    login: {
                        required: "This field is required",
                        minlength: "Min length is 4",
                        maxlength: "Max length is 15",
                    },
                    password: {
                        required: "This field is required",
                        minlength: "Min length is 6",
                        maxlength: "Max length is 15",
                    },
                    phone: {
                        required: "This field is required",
                        minlength: "Min length is 6",
                        maxlength: "Max length is 15",
                        digits: "Only digits",
                    },
                    age: {
                        required: "This field is required",
                        minlength: "Min length is 2",
                        digits: "Only digits",
                    },
                }
            });
        });
    </script>
</div>
</body>
</html>
