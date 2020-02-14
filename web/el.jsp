<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="day04Demo.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/14
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL讲解</title>
</head>
<body>
    <%
        pageContext.setAttribute("name" , "lisi");
        request.setAttribute("name" , "zhangan");
        User u = new User();
        u.setUsername("wangwu");
        u.setEmail("wangwu@163.com");
        session.setAttribute("user" , u);
        List<User> list = new ArrayList<>();
        User u1 = new User();
        u1.setUsername("zhaoliu");
        u1.setEmail("zhaoliu@163.com");
        list.add(u);
        list.add(u1);
        application.setAttribute("list" , list);
    %>
    <!--从域中取值-->
    <!-- =request.getAttribute("name") -->
    ${requestScope.name}<br/>
    ${sessionScope.user.username}<br/>
    ${applicationScope.list[1].username}<br/>
    <!--pageContext.findAttribute("name")
        el语法，可以写在jsp文件中，任意位置
    -->
    ${name}
    <!--获取请求头中的值-->
    ${header["User-Agent"]}
    <!--获取应用的路径 request.getContextPath()-->
    ${pageContext.request.contextPath}
    <hr/>
    <!--jstl语法要与el语法配合使用-->
    <c:forEach begin="0" end="5" var="i">
        <c:if test="${i==3}">
            ${i}
        </c:if>
        <c:if test="${i != 3}">
            ${i+10}
        </c:if>
    </c:forEach>
    <hr/>
    <!--varStatus="vs" 计数操作.有一些管理系统，表示序列-->
    <c:forEach items="${list}" var="user" varStatus="vs">
        第${vs.count}个用户的邮箱为:${user.email}<br/>
    </c:forEach>
</body>
</html>
