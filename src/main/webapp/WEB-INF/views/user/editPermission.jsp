<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 16:35
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

    <div class="addOrEdit" >
        <form action="${pageContext.request.contextPath}/updatePermission.do" method="post">
            权限名称: <input type="text" name="name" value="${permission.name}"> <br>
            权限描述: <input type="text" name="desc_" value="${permission.desc_}"> <br>
            权限对应的url: <input type="text" name="url"  value="${permission.url}"> <br><br>
            <input type="hidden" name="id" value="${permission.id}">
            <input type="submit" value="修改">
        </form>
    </div>
</div>
</body>
</html>
