<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/10
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head>
    <title>testcontroller</title>
</head>
<body>
<%String name = "";
    Map<String, String[]> map =  request.getParameterMap();

    for(String key: map.keySet()){
        out.println("Key："+key);
        out.println("     " );
        String[] strs = map.get(key);
        name = key;
%>
<%
    for(String str: strs){
        out.println("--values："+str);
%>
<%}%>
<%}%>

<!--图片上传-->
<form action="upload_action"enctype="multipart/form-data" method="POST" >

    selectimage: <input type="file"name="file"/><br>
    <input type="submit"value="upload"/>
</form>

<form action="upload_action2"enctype="multipart/form-data" method="POST" >

    selectimage: <input type="file"name="file"/><br>
    <input type="submit"value="upload"/>
</form>
</body>
</html>
