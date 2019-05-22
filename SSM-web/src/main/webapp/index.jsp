<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/14
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/product/findAll.do">查找所有</a>
<jsp:forward page="${pageContext.request.contextPath}/pages/main.jsp"/>
</body>
</html>
