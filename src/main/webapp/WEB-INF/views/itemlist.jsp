<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/16
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>查询商品列表</title>
</head>
<body>

${param.message}
<br>

<form:form modelAttribute="selectItem" action="${pageContext.request.contextPath }/item/query.action" method="post" >

    <input type="hidden" name="isQuery" value="yes"/>
查询商品信息：
<table width="100%" border=1>
    <tr>
        <td>商品名称</td>
        <td><form:input path="name" /></td>
        <form:errors path="name" cssClass="error"/>
    </tr>
    <tr>
        <td>商品价格</td>
        <td><form:input path="price" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="提交"/>
        </td>
    </tr>
</table>

</form:form>
<br>
<a href="http://localhost:8080/item/list">刷新</a>
<br>
<c:if test="${param.isQuery=='yes'}">
<center>查询结果：</center>
</c:if>

<c:if test="${param.isQuery!='yes'}">
    <center>所有商品：</center>
</c:if>
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${itemsList}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><fmt:formatDate value="${item.createtime}" pattern="yyy-MM-dd HH:mm:ss"/></td>
            <td>${item.detail}</td>
            <td><a href="${pageContext.request.contextPath}/item/edit?id=${item.id}">修改</a></td>
        </tr>
        </c:forEach>
    </table>

</body>

</html>
