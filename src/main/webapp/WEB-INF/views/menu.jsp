<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/user/listUser.do">用户管理</a>
        <a href="${pageContext.request.contextPath}/user/listRole.do">角色管理</a>
        <a href="${pageContext.request.contextPath}/user/listPermission.do">权限管理</a>
        <br>
    </div>
</body>
</html>
