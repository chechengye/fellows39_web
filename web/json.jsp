<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/16
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSON使用</title>
</head>
    <!--JS默认支持json格式语法-->
    <script>
        // 1、{key:value , key1:value1}
        /* class Dog{
            private String name;
            private String age;
        }*/
        var dog = {"name":"旺财" , "age":3};
        alert(dog.name)
        //2、[{key:value ,key1:value1} , {key2:value2 , key3:value3}]
        var dogs = [{"name":"小黑","age":1} , {"name":"小黄" , "age":2}];
        for (var i = 0 ; i<dogs.length ; i++){
            alert(dogs[i].name)
        }
        //3、{key:[{},{}]}
        var json = {"baobao":[{"name":"建宁","height":168},{"name":"阿珂","height":165}]};
        alert(json.baobao[1].height)
        //4、{key1:[],key2:[] , key3:[]}
        var json1 = {"baobao":[{"name":"建宁","height":168},{"name":"阿珂","height":165}],"huangdi":[{"name":"妃子","height":168},{"name":"皇后","height":165}]};
        alert("4 = " + json1.huangdi[1].name)
        //5、{key:value , key1:{} , key2:[{},{}]}
        var jspn2 = {"code":200 , "orders":[{"ono":"ddgshgh12343","createTime":"2020-11-19"},{"ono":"dfg4343","createTime":"2020-11-20"}] , "message":"成功"}
        alert(jspn2.orders[0].ono)
    </script>
<body>

</body>
</html>
