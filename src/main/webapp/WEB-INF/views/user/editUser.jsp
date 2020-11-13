<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020-11-13
  Time: 16:29
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
<%@include file="/WEB-INF/views/menu.jsp" %>
    <div class="workingroom">
        <div class="addOrEdit" >
            <form action="${pageContext.request.contextPath}/user/updateUser.do" method="post">
                用户名: <input type="text" name="name" value="${user.name}"> <br><br>
                密码: <input type="password" name="password" value="" placeholder="留空就表示不修改密码"> <br><br>
                配置角色:<br>
                <div style="text-align:left;width:300px;margin:0px auto;padding-left:50px">
                    <c:forEach items="${rs}" var="r">
                        <c:set var="hasRole" value="false" />
                        <c:forEach items="${currentRoles}" var="currentRole">
                            <c:if test="${r.id==currentRole.id}">
                                <c:set var="hasRole" value="true" />
                            </c:if>
                        </c:forEach>
                        <input type="checkbox"  ${hasRole?"checked='checked'":"" } name="roleIds" value="${r.id}"> ${r.name}<br>
                    </c:forEach>
                </div>
                <br>
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="修改">
            </form>
        </div>
</div>
</body>
</html>