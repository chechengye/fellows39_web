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
    This is include4
    <!--动态包含 推荐使用。是在此页面翻译后的servlet的service的方法中调用了include(被包含的页面)
        ，被包含页面也要被翻译为servlet-->
    <jsp:include page="include3.jsp"></jsp:include>
    <!--内部转发-->
    <jsp:forward page="include2.jsp"></jsp:forward>

</body>
</html>
