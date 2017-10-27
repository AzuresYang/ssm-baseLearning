<%--
  Created by IntelliJ IDEA.
  User: 28029
  Date: 2017/10/26
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>Welcome</title>
</head>
<script type="text/javascript">
        onload=function(){
            setInterval(gotoUrl,1000);
        }
        var time = 3;
        function gotoUrl() {
            time--;
            if(time >0)
                document.getElementById("time").innerHTML=time;
            else
                location.href='http://localhost:8080/menu';
        }
</script>
<body>
欢迎登录!
<br>
message:${message}
<br>

等待跳转...<span id="time">3</span>
</body>
</html>
