<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%
    String path=request.getContextPath();
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Library management</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="demoTable">
            <div class="layui-form-item layui-form ">
                Book number:
                <div class="layui-inline">
                    <input class="layui-input" name="isbn" id="isbn" autocomplete="off">
                </div>
                Book name:
                <div class="layui-inline">
                    <input class="layui-input" name="name" id="name" autocomplete="off">
                </div>
                Book classification:
                <div class="layui-inline">
                    <select id="typeId" name="typeId" lay-verify="required">
                        <option value="">Please select</option>
                    </select>
                </div>
                <button class="layui-btn" data-type="reload">Search</button>
            </div>
        </div>

        <!--Form, the data queried is displayed here-->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="update">Borrow</a>
        </script>

    </div>
</div>


<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        // Get data for book types dynamically, i.e., drop-down menu, pop-up book types
        $.get("findAllList",{},function (data) {
            var list=data;
            var select=document.getElementById("typeId");
            if(list!=null|| list.size()>0){
                for(var obj in list){
                    var option=document.createElement("option");
                    option.setAttribute("value",list[obj].id);
                    option.innerText=list[obj].name;
                    select.appendChild(option);
                }
            }
            form.render('select');
        },"json")



        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/bookAll',//Query type data
            defaultToolbar: ['filter', 'exports', 'print', {
                title: 'Tips',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                //{field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'isbn', width: 100, title: 'Book ID'},
                {field: 'name', width: 100, title: 'Book Name'},
                {templet:'<div>{{d.typeInfo.name}}</div>',width:100,title:'Book Type'},
                {field: 'author', width: 80, title: 'Author'},
                {field: 'price', width: 80, title: 'Price'},
                {field: 'language', width: 80, title: 'Language'},
                {templet:'<div>{{d.lendStatus == 0 ? "Not Borrowed" : "Borrowed" }}</div>',width:100,title:'Borrowed or Not'},
                {title: 'Operation', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--Default display 15-->
            page: true,
            skin: 'line',
            id:'testReload'
        });


        var $ = layui.$, active = {
            reload: function(){
                var name = $('#name').val();
                var isbn = $('#isbn').val();
                var typeId = $('#typeId').val();
                console.log(name)
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        name: name,
                        isbn:isbn,
                        typeId:typeId
                    }
                }, 'data');
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /**
         * Tool operation bar listener event
         */
        table.on('tool(currentTableFilter)', function (obj) {
            var data=obj.data;
            if (obj.event === 'update') {  // Listen for modification operations
                var index = layer.open({
                    title: 'Modify Book Information',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/queryBookInfoById?id='+data.id,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // Listen for delete operations
                layer.confirm('Are you sure you want to delete it?', function (index) {
                    // Call delete function
                    deleteInfoByIds(data.id,index);
                    layer.close(index);
                });
            }
        });


        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });

        /**
         * 获取选中记录的id信息
         */
        function getCheackId(data){
            var arr=new Array();
            for(var i=0;i<data.length;i++){
                arr.push(data[i].id);
            }
            //拼接id,变成一个字符串
            return arr.join(",");
        };


        /**
         * 提交删除功能
         */
        function deleteInfoByIds(ids ,index){
            //send request to backend
            $.ajax({
                url: "deleteBook",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code == 0) {//if success
                        layer.msg('Delete success', {
                            icon: 6,
                            time: 500
                        }, function () {
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("Delete failed");
                    }
                }
            })
        };


        /**
         * toolbar栏监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // Listen for add operation
                var index = layer.open({
                    title: 'Add Book',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/bookAdd',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {
                /*
                  1. Prompt content, must delete more than 0
                  2. Get the id information of the record to be deleted
                  3. Submit delete function ajax
                */
                // Get selected record information
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//If no selected information
                    layer.msg("Please select the record information to be deleted");
                }else{
                    // Get the id collection of the record information, the spliced ids
                    var ids=getCheackId(data);
                    layer.confirm('Are you sure you want to delete it?', function (index) {
                        // Call delete function
                        deleteInfoByIds(ids,index);
                        layer.close(index);
                    });
                }
            }
        });


    });
</script>

</body>
</html>
