<%--
  Created by IntelliJ IDEA.
  User: maiha
  Date: 25.03.2019
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
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
        <form class="form-inline my-2 my-md-0" method="GET" action="/user/search">
            <input class="form-control" type="text" name="name" id="name" placeholder="FIO">
            <input class="form-control" type="text" name="age" id="age" placeholder="Age">
            <input class="form-control" type="text" name="role" id="role" placeholder="Role">
            <input class="form-control btn btn-primary" type="submit" value="Search"/>
        </form>
    </div>
</nav>

<div class="container">
    <h3>Search Results</h3>
    <table class="table" border="2">
        <tr>
            <th> Name</th>
            <th>Surname</th>
            <th> Age</th>
            <th>Phone</th>
            <th> Login</th>
            <th> Password</th>
            <th>Role</th>
            <th>...</th>

            <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <th><c:out value="${user.name}"/></th>
            <th><c:out value="${user.surname}"/></th>
            <th><c:out value="${user.age}"/></th>
            <th><c:out value="${user.phone}"/></th>
            <th><c:out value="${user.login}"/></th>
            <th><c:out value="${user.password}"/></th>
            <th><c:out value="${user.role}"/></th>
            <th>
                <div class="dropdown">
                    <a class=" dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">...</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <form class="dropdown-item" method="get" onsubmit="remove()" action="/user/delete">
                            <input type="number" hidden name="id" value="${user.id}"/>
                            <input class="btn btn-danger" type="submit" name="delete" value="Delete"/>
                        </form>
                        <form class="dropdown-item" method="post" action="/user/update">
                            <input type="number" hidden name="id" value="${user.id}"/>
                            <input class="btn btn-primary" type="submit" value="Edit"/>
                        </form>
                    </div>
                </div>
            </th>
            </c:forEach>
    </table>
</div>

</body>
</html>
