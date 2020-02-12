<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>
</head>
<body>

    <form method="post" action="/login">
        <span style="width: 200px ; height: 30px;background-color: red"><%=request.getAttribute("loginInfo") == null ? "" : request.getAttribute("loginInfo")%></span><br/>
        用户名:<input type="text" placeholder="请输入用户名" name="username" /><br/>
        密码: <input type="password" placeholder="请输入密码" name="password"/><br/>
        <input type="submit" value="登陆"/>
    </form>
</body>
</html>