<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 16:32
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
                <td>角色名称</td>
                <td>角色描述</td>
                <td>权限</td>
                <td>编辑</td>
                <td>删除</td>
            </tr>
            <c:forEach items="${rs}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.name}</td>
                    <td>${r.desc_}</td>
                    <td>
                        <c:forEach items="${role_permissions[r]}" var="p">
                            ${p.name} <br>
                        </c:forEach>
                    </td>

                    <td><a href="${pageContext.request.contextPath}/user/editRole.do?id=${r.id}">编辑</a></td>
                    <td><a href="${pageContext.request.contextPath}/user/deleteRole.do?id=${r.id}">删除</a></td>
                </tr>
            </c:forEach>
        </table>

        <div class="addOrEdit" >
            <form action="${pageContext.request.contextPath}/user/addRole.do" method="post">
                角色名称: <input type="text" name="name"> <br>
                角色描述: <input type="text" name="desc_"> <br><br>
                <input type="submit" value="增加">
            </form>
        </div>
    </div>
</body>
</html>
