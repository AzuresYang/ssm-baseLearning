<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/16
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>修改商品信息</title>
</head>
<body>

<form:form modelAttribute="item" action="${pageContext.request.contextPath }/item/edit.action" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${items.id}"/>
    修改商品信息：
    <table width="100%" border=1>
    <tr>
    <td>商品名称</td>
    <td><form:input path="name" value="${item.name}"/></td>
    </tr>
    <tr>
    <td>商品价格</td>
    <td><form:input path="price" /></td>
    </tr>
        <tr>
            <td>商品生产日期</td>
            <td><form:input path="createtime" disabled="true"/></td>
        </tr>

        <tr>
            <td>商品描述</td>
            <td>
                <form:textarea rows="3" cols="30" path="detail"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <c:if test="${item.pic}!=null">
                    <img src="/pic/${item.pic}" width="100" height="100">
                    <br/>
                </c:if>
                <input type="file" name="pictureFile">
            </td>
        </tr>
    </table>
</form:form>
<c:if test="${editResult==null}">
    <c:out value="没有返回值"/>
</c:if>
<c:if test="${editResult!=null}">
    <c:if test = "${editResult== 1} ">
        <c:out value="修改成功"/>
    </c:if>

    <c:if test = "${editResult!= 1} ">
        <c:out value="修改失败"/>
    </c:if>
</c:if>
</body>
</html>
