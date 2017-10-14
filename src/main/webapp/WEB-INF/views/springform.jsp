<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/11
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>form test</title>
</head>
<form:form>
<br>
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

<br>
<% /*
<form:form modelAttribute="contentModel" method="post">

    input 标签：<form:input path="username"/><br/>
    password 标签：<form:password path="password"/><br/>
    绑定boolean的checkbox 标签：<br/>
    <form:checkbox path="testBoolean"/><br/>
    绑定Array的checkbox 标签：<br/>
    <form:checkbox path="testArray" value="arrayItem 路人甲"/>arrayItem 路人甲
    <form:checkbox path="testArray" value="arrayItem 路人乙"/>arrayItem 路人乙
    <form:checkbox path="testArray" value="arrayItem 路人丙"/>arrayItem 路人丙
    <form:checkbox path="testArray" value="arrayItem 路人丁"/>arrayItem 路人丁<br/>
    绑定Array的checkboxs 标签：<br/>
    <form:checkboxes path="selectArray" items="${contentModel.testArray}"/><br/>
    绑定Map的checkboxs 标签：<br/>
    <form:checkboxes path="selectIds" items="${contentModel.testMap}"/><br/>
    绑定Integer的radiobutton 标签：<br/>
    <form:radiobutton path="radiobuttonId" value="0"/>0
    <form:radiobutton path="radiobuttonId" value="1"/>1
    <form:radiobutton path="radiobuttonId" value="2"/>2<br/>
    绑定Map的radiobuttons 标签：<br/>
    <form:radiobuttons path="selectId" items="${contentModel.testMap}"></form:radiobuttons><br/>
    绑定Map的select 标签：<br/>
    <form:select path="selectId" items="${contentModel.testMap}"/><br/>
    不绑定items数据直接在form:option添加的select 标签：<br/>
    <form:select path="selectId">
        <option>请选择人员</option>
        <form:option value="1">路人甲</form:option>
        <form:option value="2">路人乙</form:option>
        <form:option value="3">路人丙</form:option>
    </form:select><br/>
    不绑定items数据直接在html的option添加的select 标签：<br/>
    <form:select path="selectId">
        <option>请选择人员</option>
        <option value="1">路人甲</option>
        <option value="2">路人乙</option>
        <option value="3">路人丙</option>
    </form:select><br/>
    用form:option绑定items的select 标签：<br/>
    <form:select path="selectId">
        <option/>请选择人员
        <form:options items="${contentModel.testMap}"/>
    </form:select><br/>
    textarea 标签：
    <form:textarea path="remark"/><br/>

    <input type="submit" value="Submit" />

</form:form>
*/%>


Hello 填写表单

<form:form method="POST" commandName="user">
     Name:<form:input path="name"/>
<br>
     Pass:<form:password path="pass"/>
    <input type="submit" value="="register">
</form:form>


</form:form>
</html>
