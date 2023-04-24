<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Modify staff</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <input type="hidden" name="id"   value="${info.id}">
    <div class="layui-form-item">
        <label class="layui-form-label required">Employee Card Number</label>
        <div class="layui-input-block">
            <input type="text" name="workerNumber" lay-reqtext="Employee Card Number cannot be empty" value="${info.workerNumber}" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">Username</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-reqtext="Username cannot be empty" value="${info.username}" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Avatar</label>
        <div class="layui-input-block">
            <input type="text" name="avatar" value="${info.avatar}" autocomplete="off" class="layui-input">
        </div>
    </div>

    <%--
        New Address
    --%>
    <div class="layui-form-item">
        <label class="layui-form-label required">Address</label>
        <div class="layui-input-block">
            <input type="text" name="address" value="${info.address}" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Real Name</label>
        <div class="layui-input-block">
            <input type="text" name="realName" lay-reqtext="Real Name cannot be empty"  value="${info.realName}" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Gender</label>
        <div class="layui-input-block">
            <input type="radio" name="sex"  value="male" title="male"  ${"male" eq info.sex ?"checked='checked'":''} />
            <input type="radio" name="sex"  value="female" title="female"  ${"female" eq info.sex ?"checked='checked'":''} />
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Date of Birth</label>
        <div class="layui-input-block">
            <input type="text" name="birthday" id="date" lay-verify="required"  value="<fmt:formatDate value='${info.birthday}' pattern='yyyy-MM-dd'/>" class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Contact Number</label>
        <div class="layui-input-block">
            <input type="text" name="tel" lay-verify="required"  class="layui-input" value="${info.tel}" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Email Address</label>
        <div class="layui-input-block">
            <input type="text" name="email" autocomplete="off" value="${info.email}"  class="layui-input">
        </div>
    </div>

    <%--
        New Resignation Status
    --%>
    <div class="layui-form-item">
        <label class="layui-form-label required">Resignation Status</label>
        <div class="layui-input-block">
            <input type="radio" name="status"  value=0 title="Resigned"  ${"0" eq info.status ?"checked='checked'":''} />
            <input type="radio" name="status"  value=1 title="Employed"  ${"1" eq info.status ?"checked='checked'":''} />
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">Confirm Changes</button>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form','laydate'], function () {
        var form = layui.form,
            layer = layui.layer,
            laydate=layui.laydate,
            $ = layui.$;

        //日期
        laydate.render({
            elem: '#date',
            trigger:'click'
        });

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var datas=data.field;//form单中的数据信息
            //向后台发送数据提交添加
            $.ajax({
                url:"updateWorkerSubmit",
                type:"POST",
                //data:datas,
                contentType:'application/json',
                data:JSON.stringify(datas),
                success:function(result){
                    if(result.code==0){//如果成功
                        layer.msg('Modified successfully',{
                            icon:6,
                            time:500
                        },function(){
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg("Modification failure");
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>

