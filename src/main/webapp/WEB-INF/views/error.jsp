<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/24
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>error</title>
</head>
<body>
${message} error !
是否没有权限或者登陆失败。
<br>
Return:<a href = <%= request.getHeader("Referer")%>> Last Page</a>
</body>
</html>