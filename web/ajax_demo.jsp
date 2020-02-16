<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/16
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax异步学习</title>
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        function testFn() {
            //1、创建引擎对象
           var xmlHttp = new XMLHttpRequest(); //不适合IE 5,6
            //2、绑定监听事件
            xmlHttp.onreadystatechange = function () {
                //3、确定status为200且 readystate 为4 服务端才会发回响应数据
                if(xmlHttp.status == 200 && xmlHttp.readyState == 4){
                    var text = xmlHttp.responseText;
                    alert(text);
                }
            }
            //4、建立服务请求
            xmlHttp.open("GET" ,${pageContext.request.contextPath}"/ajax?name=zhangsan" , true);
            //5、发起请求
            xmlHttp.send();
        }

        function testFnPost() {
            //1、创建引擎对象
            var xmlHttp = new XMLHttpRequest(); //不适合IE 5,6
            //2、绑定监听事件
            xmlHttp.onreadystatechange = function () {
                //3、确定status为200且 readystate 为4 服务端才会发回响应数据
                if(xmlHttp.status == 200 && xmlHttp.readyState == 4){
                    var text = xmlHttp.responseText;
                    alert(text);
                }
            }
            //4、建立服务请求
            xmlHttp.open("POST" ,${pageContext.request.contextPath}"/ajax" , false);
            //5、添加必须头
            xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            //5、发起请求
            xmlHttp.send("name=wangwu");
        }
        function testJQGetFn() {
            $.get(${pageContext.request.contextPath}"/ajax",{"name":"zhaoliu"} , function (data) {
                alert(data.person);
            } , "json");
        }
        function testJQPostFn() {
            $.post(${pageContext.request.contextPath}"/ajax",{"name":"tianqi"} , function (data) {
                alert(data.person);
            },"json");
        }
        function testJQAjaxFn() {
            //真实使用中最常用的方式
            $.ajax({
                url:${pageContext.request.contextPath}"/ajax",
                data:{"name":"huba"},
                type:"POST",
                success:function (data) {
                    alert(data.person)
                },
                dataType:"json",
                async:true
            });
        }
    </script>
</head>
<body>
   <%-- <input type="button" value="GET方式的AJAX的异步" onclick="testFn()"/>
    <input type="button" value="POST方式的AJAX的同步" onclick="testFnPost()"/>
    <input type="button" onclick="alert(123)" value="测试弹窗"/>--%>
    <!--JQuery测试ajax-->
   <input type="button" value="JQ GET方式请求" onclick="testJQGetFn()"/>
   <input type="button" value="JQ POST方式请求" onclick="testJQPostFn()"/>
   <input type="button" value="JQ AJax方式请求" onclick="testJQAjaxFn()"/>
</body>
</html>
