<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/13
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<!--language : 默认是java语言，可以不写此属性，若写只能写java
   buffer="8kb" out默认的缓冲区大小 此时若将大小置为0，关闭了out缓冲区。所有的值将写入response缓冲区
session="false" : 若置为false，再主动向session域中设定值的时候，会出问题
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="error.jsp" buffer="0kb"  %>
<html>
<head>
    <title>JSP学习</title>
</head>
<body>
   <%-- <%System.out.println(i);%>--%>
    <%int i = 0 ;%>
    <%System.out.println(i);
        /*public void method2(){

        }*/
        List<String> list = new ArrayList<>();
        //int svm = 10 / 0;

    %>
   <!--HTML注释-->
    <%=i%>
    <%=6+10
    %>
   <%--JSP注释，可见范围只有jsp源码 --%>
    <%! String str = "test"; //标识成员或全局
        public void method(){
            System.out.println("method is called !!");
        }
    %>
   <%System.out.println(str);%>
   <% method(); %>
   <%--<!--静态包含-->
    <%@ include file="login.jsp"%>
    <!--动态包含-->
   <jsp:include page="login.jsp"></jsp:include>--%>
 aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    <%
        out.write("ccccccccccccccccccccccccccc");
        response.getWriter().write("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        //response.setBufferSize(32);
    %>
    <%="dddddddddddddddddddddddddddddddddd"%>
    <!--PageContext域对象-->
    <%
        //隐式对象可以直接使用
        //request.setAttribute("name" , "zhangsan");
        //pageContext.setAttribute("name" , "zhangsan" , PageContext.REQUEST_SCOPE);
        //pageContext.setAttribute("name" , "lisi" , PageContext.SESSION_SCOPE);服务端
        pageContext.setAttribute("name" , "zhaoliu" , PageContext.APPLICATION_SCOPE);
        //pageContext.setAttribute("name" , "tianqi");
        //去域中寻找key。page域 < request 域 < session 域 < application域
        System.out.println(pageContext.findAttribute("name"));

        System.out.println("name : " + pageContext.getAttribute("name" , PageContext.APPLICATION_SCOPE));
    %>

</body>
</html>
