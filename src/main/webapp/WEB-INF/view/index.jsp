<%--
  Created by IntelliJ IDEA.
  User: maiha
  Date: 19.03.2019
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
    <style><%@include file="css/bootstrap.min.css"%></style>
    <script><%@include file="js/popper.min.js"%></script>
    <script><%@include file="js/jquery-3.3.1.slim.min.js"%></script>
    <script><%@include file="js/bootstrap.min.js"%></script>
</head>
<body id="body">

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
                <form action="/role/roles">
                    <input class="btn btn-info" type="submit" value="Roles">
                </form>
            </li>
        </ul>
        <form class="form-inline my-2 my-md-0" method="GET" action="/user/search">
            <input class="form-control" type="text" name="name" id="fio" placeholder="FIO">
            <input class="form-control" type="text" name="age" id="age" placeholder="Age">
            <input class="form-control" type="text" name="role" id="role" placeholder="Role">
            <input class="form-control btn btn-primary" type="submit" value="Search"/>
        </form>
    </div>
</nav>

<div class="container">
    <h3>All Users</h3>
    <table class="table" border="2">
        <tr>
            <th> Name</th>
            <th>Surname</th>
            <th> Age</th>
            <th>Phone</th>
            <th> Login</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <th><c:out value="${user.name}"/></th>
            <th><c:out value="${user.surname}"/></th>
            <th><c:out value="${user.age}"/></th>
            <th><c:out value="${user.phone}"/></th>
            <th><c:out value="${user.login}"/></th>
            <th><c:out value="${user.role}"/></th>
            <th>
                <div class="dropdown">
                    <a class=" dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false">...</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <form class="dropdown-item" method="post" onsubmit="remove()" action="/user/delete">
                            <input type="number" hidden name="id" value="${user.id}"/>
                            <input class="col-md-10 btn btn-danger" type="submit" value="Delete"/>
                        </form>
                        <form class="dropdown-item" method="post" action="/user/edit">
                            <input type="number" hidden name="id" value="${user.id}"/>
                            <input class="col-md-10 btn btn-primary" type="submit" value="Edit"/>
                        </form>
                    </div>
                </div>
            </th>
            </c:forEach>
    </table>
    <table>
        <tr>
            <th>
                <form action="/user/regpage" method="get">
                    <input class="btn btn-primary" type="submit" value="Add"/>
                </form>
            </th>
        </tr>

    </table>
    <table>
        <tr>
            <th>
                <script type="text/javascript">
                    <!--
                    function getCookie(byname)	// возвращает по имени значение, здесь не используется
                    {
                        byname = byname + "=";
                        nlen = byname.length;
                        fromN = document.cookie.indexOf(byname) + 0;
                        if ((fromN) != -1) {
                            fromN += nlen
                            toN = document.cookie.indexOf(";", fromN) + 0;
                            if (toN == -1) {
                                toN = document.cookie.length;
                            }
                            return unescape(document.cookie.substring(fromN, toN));
                        }
                        return null;
                    }

                    function parseCookie()   // Разделение cookie
                    {
                        var cookieList = document.cookie.split("; ");
                        // Массив для каждого cookie в cookieList
                        var cookieArray = new Array();
                        for (var i = 0; i < cookieList.length; i++) {
                            // Разделение пар имя-значение.
                            var name = cookieList[i].split("=");
                            // Декодирование и добавление в cookie-массив.
                            cookieArray[unescape(name[0])] = unescape(name[1]);
                        }
                        return cookieArray;
                    }

                    function setCookie(visits) {
                        // Сохранение числа посещений.
                        document.cookie = "visits=" + visits + ";";
                    }

                    if ("" == document.cookie) { // Инициализация cookie.
                        setCookie(1);
                        document.write("<p>This is your first visit</p>");
                    } else {
                        var cookies = parseCookie();
                        // Вывод приветствия, числа посещений и увеличение числа посещений на 1.
                        document.write("<p>Nice to see you again, Num of your visits - " +
                            cookies.visits++ + " !</p>");
                        // Обновление cookie.
                        setCookie(isNaN(cookies.visits) ? 1 : cookies.visits);
                    }
                    //-->
                </script>
            </th>
        </tr>
    </table>
    <%= new java.util.Date()%>
</div>
</body>
</html>