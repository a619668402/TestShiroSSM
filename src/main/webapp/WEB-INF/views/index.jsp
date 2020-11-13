<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="/static/css/style.css">
    </head>
    <body>
    <div class="workingroom">
        <div class="loginDiv">

            <c:if test="${empty subject.principal}">
                <a href="${pageContext.request.contextPath}login.do">登录</a><br>
            </c:if>
            <c:if test="${!empty subject.principal}">
                <span class="desc">你好，${subject.principal}，</span>
                <a href="${pageContext.request.contextPath}/logout.do">退出</a><br>
            </c:if>
            <a href="${pageContext.request.contextPath}/menu.do">权限管理</a><span class="desc">(登录后才可以查看) </span><br>
            <a href="${pageContext.request.contextPath}/listProduct.do">查看产品</a><span class="desc">(要有查看产品权限, zhang3有，li4 有)</span><br>
            <a href="${pageContext.request.contextPath}/deleteProduct.do">删除产品</a><span  class="desc">(要有删除产品权限, zhang3有，li4 有)</span><br>
            <a href="${pageContext.request.contextPath}/deleteOrder.do">删除订单</a><span class="desc">(要有删除订单权限, zhang3有，li4没有)</span><br>
        </div>${pageContext.request.contextPath}
    </body>
</html>
