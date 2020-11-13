<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../static/css/style.css" />
</head>
<body>
    <div class="workingroom">

    <%@include file="/WEB-INF/views/menu.jsp" %>

    <table>
        <tr>
            <td>id</td>
            <td>权限名称</td>
            <td>权限描述</td>
            <td>权限对应的路径</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <c:forEach items="${ps}" var="p">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.desc_}</td>
                <td>${p.url}</td>
                <td><a href="${pageContext.request.contextPath}/user/editPermission.do?id=${p.id}">编辑</a></td>
                <td><a href="${pageContext.request.contextPath}/user/deletePermission.do?id=${p.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>

    <div class="addOrEdit" >
        <form action="${pageContext.request.contextPath}/user/addPermission.do" method="post">
            权限名称: <input type="text" name="name"> <br>
            权限描述: <input type="text" name="desc_"> <br>
            权限对应的url: <input type="text" name="url"> <br><br>
            <input type="submit" value="增加">
        </form>
    </div>
</div>
</body>
</html>
