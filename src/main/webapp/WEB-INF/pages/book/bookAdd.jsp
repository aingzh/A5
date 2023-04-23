<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Add Book</title>
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
        <label class="layui-form-label required">Book Id</label>
        <div class="layui-input-block">
            <input type="text" name="id" lay-verify="required" lay-reqtext="Book id cannot be empty" placeholder="Please enter book id" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">Book Name</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" lay-reqtext="Book name cannot be empty" placeholder="Please enter book name" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">Book Number</label>
        <div class="layui-input-block">
            <input type="text" name="isbn" lay-verify="required" lay-reqtext="Book number cannot be empty" placeholder="Please enter book number" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Book Category</label>
        <div class="layui-input-block">
            <select name="typeId" id="typeId" lay-verify="required">
                <option value="">Please select</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Book Author</label>
        <div class="layui-input-block">
            <input type="text" name="author" lay-verify="required"  class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Book Publisher</label>
        <div class="layui-input-block">
            <input type="text" name="publish" lay-verify="required"  class="layui-input" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Book Language</label>
        <div class="layui-input-block">
            <input type="text" name="language" autocomplete="off"  class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label required">Book Price</label>
        <div class="layui-input-block">
            <input type="number" name="price" autocomplete="off"  class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">Publication Date</label>
        <div class="layui-input-block">
            <input type="text" name="pubDate" id="date" lay-verify="date" autocomplete="off" class="layui-input">
        </div>
    </div>

    <%--
        Add book status
    --%>
    <div class="layui-form-item">
        <label class="layui-form-label required">Is it lent out?</label>
        <div class="layui-input-block">
            <input type="radio" name="status"  value=0 title="Not lent out"  ${"0" eq info.status ?"checked='checked'":''} />
            <input type="radio" name="status"  value=1 title="Lent out"  ${"1" eq info.status ?"checked='checked'":''} />
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">Book Introduction</label>
        <div class="layui-input-block">
            <textarea name="introduction" class="layui-textarea" placeholder="Please enter introduction information"></textarea>
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

//Date
        laydate.render({
            elem: '#date',
            trigger:'click'
        });

//Get book type data dynamically
        $.get("findAllList",{},function (data) {
            var list=data;
            var select=document.getElementById("typeId");
            if(list!=null|| list.size()>0){
                for(var c in list){
                    var option=document.createElement("option");
                    option.setAttribute("value",list[c].id);
                    option.innerText=list[c].name;
                    select.appendChild(option);
                }
            }
            form.render('select');
        },"json")

//Submit listener
        form.on('submit(saveBtn)', function (data) {
            var datas=data.field;//Data information in the form
            //Send data to the background to submit the addition
            $.ajax({
                url:"addBookSubmit",
                type:"POST",
                data:datas,
                success:function(result){
                    if(result.code==0){//If successful
                        layer.msg('Added successfully',{
                            icon:6,
                            time:500
                        },function(){
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg("Add failed");
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>

