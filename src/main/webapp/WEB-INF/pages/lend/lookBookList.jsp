<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Timeline query</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>Time line of borrowing books</legend>
</fieldset>
<ul class="layui-timeline">
<c:forEach var="lend" items="${info}" varStatus="status">
    <li class="layui-timeline-item">
        <div class="layui-timeline-content layui-text">
            <div class="layui-timeline-title">
                <fmt:formatDate value="${lend.lendDate}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> <br/>
                <span style="color: red"> ${lend.readerInfo.realName}</span> borrow <span style="color: crimson"><<${lend.bookInfo.name}>></span><br/>
                <c:if test="${lend.backDate == null}">
                    unreturned
                </c:if>
                <c:if test="${lend.backDate != null}">
                    <fmt:formatDate value="${lend.backDate}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> <span style="color: green">return</span>,
                </c:if>
            </div>
        </div>
    </li>
</c:forEach>

</ul>
<script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</body>
</html>
