<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script>
    $(function(){
        $("#bntable").jqGrid({
            url : "${path}/banner/page",
            editurl:"${path}/banner/edit",
            datatype : "json",
            rowNum : 3,
            styleUI:"Bootstrap",
            autowidth:true,
            rowList : [ 2, 4, 6 ],
            pager : '#bnpager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'ID', '标题', '图片', '描述', '状态','时间'],
            colModel : [
                {name : 'id',width : 55,hidden:true},
                {name : 'title',editable:true,width : 90},
                {name : 'imgpath',editable:true,width : 100,edittype:"file",align:"center",
                    formatter:function (cellvalue) {
                        return "<img src='${path}/upload/photo/"+cellvalue+"' style='with:10px;height:89px'/>"
                    }

                },
                {name : 'description',editable:true,width : 80,align : "right"

                },
                {name : 'status',width : 80,align:"center",
                    formatter:function (cellvalue,option,row) {
                        if(cellvalue==1){

                            return "<button class='btn btn-success' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >冻结</button>";
                        }else{
                            return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >解除冻结</button>";
                        }
                    }
                },
                {name : 'up_date',width : 150,sortable : false}
            ]

        });

        /*增删改查操作*/
        $("#bntable").jqGrid('navGrid', '#bnpager',
            {edit : true, add : true, del : true, addtext:"添加", edittext:"编辑", deltext:"删除"},
            {afterSubmit:function (data) {

                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "imgpath",
                        data:{id:data.responseText},
                        success:function(){
                            $("#bntable").trigger("reloadGrid")
                        }
                    })
                    return "ok";
                },
                closeAfterEdit:true
            },
            {

                afterSubmit:function (data) {

                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "imgpath",
                        data:{id:data.responseText},
                        success:function(){
                            $("#bntable").trigger("reloadGrid")

                        }
                    })
                    return "hello";
                },
                closeAfterAdd:true
            },
            {}
        );
    });
    //点击修改
    function change(id,value){

        if(value==1){

            $.ajax({
                url:"${path}/banner/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"2"},
                success:function(data){
                    //页面的刷新
                    $("#bntable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();

                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }else{
            $.ajax({
                url:"${path}/banner/updateStatus",
                type:"post",
                dataType:"JSON",
                data:{"id":id,"status":"1"},
                success:function(data){
                    //页面的刷新
                    $("#bntable").trigger("reloadGrid");
                    //提示框添加信息
                    $("#showMsg").html(data.message);
                    //展示错误信息
                    $("#show").show();
                    //设置提示框时间
                    setTimeout(function () {
                        //关闭提示框
                        $("#show").hide();
                    }, 3000);
                }
            });
        }

    }
</script>

<%--初始化面板--%>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>轮播图信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">轮播图信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" class="btn btn-info" >添加轮播图</button>
        <button type="button" class="btn btn-success" >修改轮播图</button>
        <button type="button" class="btn btn-warning" >删除轮播图</button>
    </div>
    <div class="nav nav-tabs">
    </div>

    <%--提示框--%>
    <div id="show" class="alert alert-warning alert-dismissible" role="alert" style="width:200px;display: none">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong id="showMsg"></strong>
    </div>

<%--初始化表单--%>
    <table id="bntable" />

    <%--定义分页工具栏--%>
    <div id="bnpager"></div>

</div>



