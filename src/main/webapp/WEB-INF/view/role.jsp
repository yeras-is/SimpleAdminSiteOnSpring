<%--
  Created by IntelliJ IDEA.
  User: maiha
  Date: 26.03.2019
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role</title>
    <style><%@include file="css/bootstrap.min.css"%></style>
    <script><%@include file="js/popper.min.js"%></script>
    <script><%@include file="js/jquery-3.3.1.slim.min.js"%></script>
    <script><%@include file="js/bootstrap.min.js"%></script>

</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Localhost.8080</a>

    <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
            <li style="padding: 1px" class="nav-item">
                <form action="adduser" method="post">
                    <input class="btn btn-primary" type="submit" value="Add User"/>
                </form>
            </li>
            <li style="padding: 1px" class="nav-item">
                <form action="getindex" method="get">
                    <input class="btn btn-info" type="submit" value="All Users">
                </form>
            </li>
            <li style="padding: 1px" class="nav-item">
                <form action="/roles">
                    <input class="btn btn-info" type="submit" value="Roles">
                </form>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <table class="table col-md-5 align-content-center" border="2">
        <tr>
            <h3>
                <td>Name</td>
                <td>Privilege</td>
            </h3>
        </tr>
        <c:forEach var="role" items="${requestScope.roles}">
            <tr>
                <th><c:out value="${role.name}"/></th>
                <th>
            <c:forEach var="role" items="${role.privelege}">
                <div class="col-md-2">
                    <input class="form-check-input" type="checkbox" name="${role.get(role)}"
                           value="${role}">${role.get(role)}<br>
                </div>
            </c:forEach>
                    <input type="text" value="${role.privelege}">
                </th>
            </tr>
        </c:forEach>
    </table>


    <h3>Create new Role</h3>
    <table class="table col-md-2 align-content-center" border="2">
        <form action="/role/add" method="get">
            <tr>
                <th>
                    <label for="name">Name</label>
                    <input type="text" name="name" id="name">
                </th>
            </tr>
            <tr>
                <th>
                    <div class="row form-check">
                        <div class="col-md-2">
                            <input class="form-check-input" type="checkbox" name="read"
                                   value="1">Read<br>
                        </div>
                        <div class="col-md-2">
                            <input class="form-check-input" type="checkbox" name="edit"
                                   value="2">Edit<br>
                        </div>
                        <div class="col-md-2">
                            <input class="form-check-input" type="checkbox" name="write"
                                   value="3">Write<br>
                        </div>
                        <div class="col-md-2">
                            <input class="form-check-input" type="checkbox" name="delete"
                                   value="4">Delete<br>
                        </div>
                    </div>
                </th>
            </tr>
            <tr>
                <th>
                    <div class="form-check">
                        <input class="btn btn-primary" type="submit">
                    </div>
                </th>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
