<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id', {
            width: '700px',
            uploadJson: "${path}/editor/upload",
            filePostName: "photo",
            allowFileManager: true,
            fileManagerJson: "${path}/editor/preview",
            afterBlur:function (){  //编辑器失去焦点(blur)时执行的回调函数。
            this.sync();  //将编辑器中的内容同步到表单
        }
    });
</script>


<script>
    $(function () {
        $("#bntable").jqGrid({
            url: "${path}/article/page",
            editurl: "${path}/article/edit",
            datatype: "json",
            rowNum: 3,
            styleUI: "Bootstrap",
            autowidth: true,
            rowList: [2, 4, 6],
            pager: '#bnpager',
            viewrecords: true,  //是否展示总条数

            colNames: ['ID', '标题', '图片', '描述', '时间', '所属上师'],
            colModel: [
                {name: 'id', width: 55, hidden: true},
                {name: 'title', editable: true, width: 90},
                {
                    name: 'insert_img', editable: true, width: 100, edittype: "file", align: "center",
                    formatter: function (cellvalue) {
                        return "<img src='${path}/upload/photo/" + cellvalue + "' style='with:10px;height:89px'/>"
                    }

                },
                {
                    name: 'content', editable: true, width: 80, align: "right"

                },
                {name: 'up_date', width: 150, sortable: false},
                {name: 'guruId', width: 150, sortable: false}
            ]

        });

        /*增删改查操作*/
        $("#bntable").jqGrid('navGrid', '#bnpager',
            {edit: false, add: false, add: false, search: false, del: true, edittext: "编辑"},
            {},
            {},
            {}
        );
    });
    //点击查看文章详情
    $("#btn1").click(function () {

        var rowId= $("#bntable").jqGrid("getGridParam","selrow");
        if(rowId!=null){

            var row = $("#bntable").jqGrid("getRowData",rowId);
            $("#myModal").modal("show");
            $("#title").val(row.title);
            KindEditor.html("#editor_id",row.content);
            //添加和关闭按钮
            $("#modalFooter").html("<button type='button' onclick='updateArticle(\""+rowId+"\")' class='btn btn-default'>提交</button>" +
                "<button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");
        }else{
            alert("请选中一条数据")
        }
    });


    //点击添加文章
    $("#btn2").click(function () {

        //给表单置空
        $("#articleFrom")[0].reset();

        //给KindEditor框置空
        KindEditor.html("#editor_id","");

        //展示模态框
        $("#myModal").modal("show");
        //添加按钮
        $("#modalFooter").html("<button type='button' onclick='addArticle()' class='btn btn-default'>提交" +
            "</button><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");
    });

    /*点击添加按钮操作*/
    function addArticle(){
        //向后台提交
        $.ajax({
            url:"${path}/article/add",
            type:"post",
            dateType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){
                $("#myModal").modal('hide');//隐藏模态框
                $("#atctable").trigger("reloadGrid");//刷新jqGrid
            }
        });
    }

    //点击删除文章
    $("#btn3").click(function () {

    });

    /*修改的提交按钮*/
    function updateArticle(rowId){
        //向后台提交
        $.ajax({
            url:"${path}/article/update?articleId="+rowId,
            type:"post",
            dateType:"json",
            data:$("#articleFrom").serialize(),
            success:function(){
                $("#myModal").modal('hide');//隐藏模态框
                $("#bntable").trigger("reloadGrid");//刷新jqGrid
            }
        });

    }

</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">文章信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" id="btn1" class="btn btn-info" >查看文章</button>&emsp;
        <button type="button" id="btn2" class="btn btn-success" >添加文章</button>&emsp;
        <button type="button" id="btn3" class="btn btn-warning" >删除文章</button>&emsp;
    </div>

    <%--提示框--%>
    <div id="show" class="alert alert-warning alert-dismissible" role="alert" style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>
    <%--初始化表单--%>
    <table id="bntable"/>

    <%--定义分页工具栏--%>
    <div id="bnpager"></div>

    <%--模态框--%>
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" >
        <div class="modal-dialog" role="document" style="width: 750px">
            <div class="modal-content">

                <%--模态框的标题--%>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Modal title</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="articleFrom">

                        <%--输入框组--%>
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon1">标题</span>
                            <input id="title" type="text"  name="title" class="form-control" aria-describedby="basic-addon1">
                        </div>

                        <%--初始化富文本编辑器--%>
                        <div class="input-group">
                            <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                            </textarea>
                        </div>
                    </form>
                </div>

                <%--模态框的按钮--%>
                <div class="modal-footer" id="modalFooter">
                    <button type="button" class="btn btn-primary">提交</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div
        </div>
    </div>
</div>





