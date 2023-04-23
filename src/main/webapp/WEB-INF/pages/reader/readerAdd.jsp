<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Add readers</title>
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
    <div class="layui-form-item">
        <label class="layui-form-label required">Reader Card Number</label>
        <div class="layui-input-block">
            <input type="text" name="readerNumber" lay-verify="required" lay-reqtext="Reader card number cannot be empty" placeholder="Please enter reader card number" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">Username</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" lay-reqtext="Username cannot be empty" placeholder="Please enter username" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Real Name</label>
        <div class="layui-input-block">
            <input type="text" name="realName" lay-verify="required" lay-reqtext="Real name cannot be empty" placeholder="Please enter real name" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Gender</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="male" title="male" checked="checked"/>
            <input type="radio" name="sex" value="female" title="female" />
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Date of Birth</label>
        <div class="layui-input-block">
            <input type="text" name="birthday" id="date" lay-verify="required"  class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Contact Information</label>
        <div class="layui-input-block">
            <input type="text" name="tel" lay-verify="required"  class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Email Address</label>
        <div class="layui-input-block">
            <input type="text" name="email" autocomplete="off"  class="layui-input">
        </div>
    </div>
    <%--
        Add address and registration date
    --%>
    <div class="layui-form-item">
        <label class="layui-form-label required">Address</label>
        <div class="layui-input-block">
            <input type="text" name="address" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">Registration Date</label>
        <div class="layui-input-block">
            <input type="text" name="registerDate" id="date2" lay-verify="required"  class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">Confirm Save</button>
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

        /**
         * 登记日期
         */
        //日期
        laydate.render({
            elem: '#date2',
            trigger:'click'
        });

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            var datas=data.field;//form单中的数据信息
            //向后台发送数据提交添加
            $.ajax({
                url:"addReaderSubmit",
                type:"POST",
                //data:datas,
                contentType:'application/json',
                data:JSON.stringify(datas),
                success:function(result){
                    if(result.code==0){//如果成功
                        layer.msg('Add successfully',{
                            icon:6,
                            time:500
                        },function(){
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg("Add failure");
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>

