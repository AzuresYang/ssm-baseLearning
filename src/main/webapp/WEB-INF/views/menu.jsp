<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/26
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>大厅</title>
</head>
<style>
    p{
        font-family: "华文楷体";
        font-size:10px;
        font-color:blue;
    }
</style>
<body>
<h1 align="center">这里是大厅，你要做点什么？</h1>
<div style="text-align: right" >
<p>你好！${activeUser.username}</p>
</div>
<a href="/item/list">查询商品</a>
</body>
</html>
