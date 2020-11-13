<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <title>第二个springmvc项目</title>
    <script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
    <!-- 加入一个base标签，是htm语言中的标签。
         表示当前页面中访问地址的基地址你的页面中所有没有"开头的地址，
         都是以base标签中的地址为参考地址 -->

    <base href="<%=basePath%>">
</head>
<body>
<body>
    1.测试返回String类型表示逻辑视图：(需要视图解析器）
    <form action="returnString-view.do" method="post">
        姓名：<input type="text" name="name"><br/>
        年龄：<input type="text" name="age"><br/>
        <input type="submit" value="提交参数">
    </form>
    2.测试返回String类型表示逻辑视图：(不需要视图解析器）
    <form action="returnString-view2.do" method="post">
        姓名：<input type="text" name="name"><br/>
        年龄：<input type="text" name="age"><br/>
        <input type="submit" value="提交参数">
    </form>
    3.发起ajax请求 返回值可以是void Object String<br/>
    <button id="btn">发起ajax请求</button>
    <p><a href="${pageContext.request.contextPath}/test/testpath.do">发起test/testpath.do 绝对路径 请求</a></p>
    <p><a href="test/testpath.do">发起test/testpath.do 相对路径 请求</a></p>
</body>
</body>
<script type="text/javascript">
    $(function () {
        $("button").click(function () {
            $.ajax({
                url:"returnString-ajax.do",
                data:{
                    name:"zhangsan",
                    age:20
                },
                type:"post",
                //dataType:"json",
                success:function (resp) {
                    //alert(resp.name + "  " + resp.age);
                    /*$.each(resp,function (i, n) {
                        alert(n.name + " " +n.age);
                    })*/
                    alert(resp);
                }
            })
        })
    })
</script>
</html>
