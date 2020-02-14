<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/14
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    This is include2
    <!--静态包含 被包含的页面依次在此页面翻译后的servlet的service方法中进行输出。被包含页面并不会被翻译成servlet-->
    <%@ include file="include1.jsp"%>
</body>
</html>
