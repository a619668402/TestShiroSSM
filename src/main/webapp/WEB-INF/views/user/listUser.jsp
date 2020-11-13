<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/static/css/style.css"/>
</head>
<body>
<div class="workingroom">
    <%@include file="/WEB-INF/views/menu.jsp" %>
    <table>
        <tr>
            <td>id</td>
            <td>用户名称</td>
            <td>用户密码</td>
            <td>加密盐</td>
            <td>角色</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${us}" var="u">
            <tr>
                <td>${u.id}</td>
                <td>${u.name}</td>
                <td>${fn:substring(u.password, 0, 5)}...</td>
                <td>${fn:substring(u.salt, 0, 5)}...</td>
                <td>
                    <c:forEach items="${user_roles[u]}" var="r">
                        ${r.name} <br>
                    </c:forEach>
                </td>
                <td><a href="${pageContext.request.contextPath}/user/editUser.do?id=${u.id}">编辑</a></td>
                <td><a href="${pageContext.request.contextPath}/user/deleteUser.do?id=${u.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>

    <div class="addOrEdit" >
        <form action="${pageContext.request.contextPath}/user/addUser.do" method="post">
            用户名: <input type="text" name="name"> <br>
            密码: <input type="password" name="password"> <br><br>
            <input type="submit" value="增加">
        </form>
    </div>
</div>
</body>
</html>
