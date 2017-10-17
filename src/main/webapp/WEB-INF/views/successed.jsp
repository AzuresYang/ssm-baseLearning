<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/12
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head>
    <title>succeed</title>
</head>
<body>
${message} succeed !
<br>
Return:<a href = <%= request.getHeader("Referer")%>> Last Page</a>
</body>
</html>
