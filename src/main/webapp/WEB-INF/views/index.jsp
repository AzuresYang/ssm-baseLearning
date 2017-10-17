<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<body>
<h2>Hello World!</h2>

<%= new Date()%>>
<br>

<br>
<%String name = "";
    Map<String, String[]> map =  request.getParameterMap();

    for(String key: map.keySet()){
        out.println("Key:"+key);
        out.println("     " );
        String[] strs = map.get(key);
        name = key;
%>
<%
    for(String str: strs){
        out.println("--values:"+str);
%>
<br>
<%}%>
<%}%>

<br>
<br>
Return:<a href = <%= request.getHeader("Referer")%>> Last Page</a>
</body>
</body>
</html>
