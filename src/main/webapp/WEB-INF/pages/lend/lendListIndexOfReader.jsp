<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Lending management</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layuimini-main">
            <div class="demoTable">
                <div class="layui-form-item layui-form ">
                    Book name
                    <div class="layui-inline">
                        <input class="layui-input" name="name" id="name" autocomplete="off">
                    </div>
                    Return type
                    <div class="layui-inline">
                        <select class="layui-input" name="type" id="backType">
                            <option value=""></option>
                            <option value="0">Return normally</option>
                            <option value="1">Delayed return</option>
                            <option value="2">Damaged return</option>
                            <option value="4">Overdue return</option>
                            <option value="3">Lost</option>
                        </select>
                    </div>
                    Book Type
                    <div class="layui-inline">
                        <select class="layui-input" name="status" id="status">
                            <option value=""></option>
                            <option value="0">Not Borrowed</option>
                            <option value="1">Borrowed</option>
                        </select>
                    </div>
                    <button class="layui-btn" data-type="reload">search</button>
                </div>
            </div>
        </div>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> Borrow </button>
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="back"> Return </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            {{# if(d.backDate==null){ }}
                <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">Abnormal Return</a>
            {{# } }}
        </script>

    </div>
</div>
<script src="${pageContext.request.contextPath}/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/lendListAllOfReader',//查询借阅图书记录
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: 'Tips',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                //{field: 'id', width: 100, title: 'ID', sort: true},
                {templet: '<div><a href="javascript:void(0)" style="color:#00b7ee" lay-event="bookInfoEvent">{{d.bookInfo.name}}</a></div>',
                    width: 100, title: 'Book Name'},
                {templet: '<div>{{d.readerInfo.readerNumber}}</div>', width: 120, title: 'Borrower Card'},
                {templet: '<div><a href="javascript:void(0)" style="color:#00b7ee" lay-event="readerInfoEvent">{{d.readerInfo.realName}}</a></div>',
                    width: 100, title: 'Borrower'},
                // {templet: '<div>{{d.reader.name}}</div>', width: 80, title: '借阅人'},
                {templet:"<div>{{layui.util.toDateString(d.lendDate,'yyyy-MM-dd HH:mm:ss')}}</div>", width: 160, title: '借阅时间'},
                {field: 'backDate', width: 160, title: 'Return Time'},
                {title:"Return Type",minWidth: 120,templet:function(res){
                        if(res.backType=='0'){
                            return '<span class="layui-badge layui-bg-green">Normal Return</span>'
                        }else if(res.backType=='1'){
                            return '<span class="layui-badge layui-bg-gray">Delayed Return</span>'

                        }else if(res.backType=='2') {
                            return '<span class="layui-badge layui-bg-yellow">Damaged Return</span>'
                        }else if(res.backType=='3'){
                            return '<span class="layui-badge layui-bg-green">Lost Book</span>'
                        } else if (res.backType=='4') {
                            return '<span class="layui-badge layui-bg-black">Overdue Return</span>'
                        } else{
                            return '<span class="layui-badge layui-bg-red">Borrowing</span>'
                        }
                    }},
                {title: 'operation', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line',
            id:'testReload'
        });


        var $ = layui.$, active = {
            reload: function(){
                var name = $('#name').val();
                var backType = $('#backType').val();
                var status = $('#status').val();
                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        name: name,
                        backType:backType,
                        status:status
                    }
                }, 'data');
            }
        };
     

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /**
         * tool操作栏监听事件
         */
        table.on('tool(currentTableFilter)', function (obj) {
            var data=obj.data;
            if (obj.event === 'edit') {  // 监听添加操作
                var index = layer.open({
                    title: 'Exceptional Return',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/excBackBook?id='+data.id+"&bookId="+data.bookId,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('Are you sure you want to delete?', function (index) {
                    //调用删除功能
                    //获取记录信息的id集合
                    deleteInfoByIds(data.id,data.bookId,index);
                    layer.close(index);
                });
            }else if( obj.event === 'bookInfoEvent') {//书的借阅线
                  //获取书的id
                  var bid=data.bookId;
                  queryLookBookList("book",bid);
            }else{//读者借阅线
                //获取读者的id
                var rid=data.readerId;
                queryLookBookList("user",rid);
            }
        });

        /**
         * 借阅线打开内容
         */
        function queryLookBookList(flag,id){
            var index = layer.open({
                title: 'Borrowing Timeline',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['60%', '60%'],
                content: '${pageContext.request.contextPath}/queryLookBookList?id='+id+"&flag="+flag,
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        }




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
            //拼接id
            return arr.join(",");
        };


        /**
         * 获取选中记录的中图书id集合*/
        function getCheackBookId(data){
            var arr=new Array();
            for(var i=0;i<data.length;i++){
                arr.push(data[i].bookId);
            }
            //拼接id
            return arr.join(",");
        };

        /**
         * 提交删除功能
         */
        function deleteInfoByIds(ids ,bookIds,index){
            //向后台发送请求
            $.ajax({
                url: "deleteLendListByIds",
                type: "POST",
                data: {ids: ids,bookIds:bookIds},
                success: function (result) {
                    if (result.code == 0) {//如果成功
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
         * 提交还书功能
         */
        function backBooksByIds(ids ,bookIds,index){
            //向后台发送请求
            $.ajax({
                url: "backLendListByIds",
                type: "POST",
                data: {ids, bookIds:bookIds},
                success: function (result) {
                    if (result.code == 0) {//如果成功
                        layer.msg('Book returned successfully', {
                            icon: 6,
                            time: 500
                        }, function () {
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("Failed to return book");
                    }
                }
            })
        };
        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: 'Borrowing Management',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '${pageContext.request.contextPath}/addLendListOfReader',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }else if (obj.event === 'back'){//还书操作
                //获取选中的记录信息
                //获取选中的记录信息
                var checkStatus=table.checkStatus(obj.config.id);
                //debugger;
                var data=checkStatus.data;

                if(data.length==0){//如果没有选中信息
                    layer.msg("Please select the record information to borrow and return");
                }else{
                    //获取记录信息的id集合
                    var ids=getCheackId(data);//借阅记录的id集合
                    var bookIds=getCheackBookId(data);//图书的id集合
                    layer.confirm('Are you sure to return the book', function (index) {
                        //调用还书功能
                        backBooksByIds(ids, bookIds,index);
                        layer.close(index);
                    });
                }
            } else if (obj.event === 'delete') {
                 /*
                   1、提示内容，必须删除大于0条
                   2、获取要删除记录的id信息
                   3、提交删除功能 ajax
                 */
                 //获取选中的记录信息
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("Please select the record information to be deleted");
                }else{
                    //获取记录信息的id集合
                    var ids=getCheackId(data);//借阅记录的id集合
                    var bookIds=getCheackBookId(data);//图书的id集合
                    layer.confirm('Are you sure to delete?', function (index) {
                        //调用删除功能
                        deleteInfoByIds(ids,bookIds,index);
                        layer.close(index);
                    });
                }
            }
        });

    });
</script>

</body>
</html>
