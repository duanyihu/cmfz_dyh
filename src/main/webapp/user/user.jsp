<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        $("#bntable").jqGrid({
            url : "${path}/user/page",
            editurl:"${path}/user/edit",
            datatype : "json",
            rowNum : 3,
            styleUI:"Bootstrap",
            autowidth:true,
            rowList : [ 2, 4, 6 ],
            pager : '#bnpager',
            viewrecords : true,  //是否展示总条数
            colNames : [ 'ID', '电话', '密码', '头像', '法名','名字','性别','籍贯','签名','状态','注册时间','关注上师'],
            colModel : [
                {name : 'id',width : 55,hidden:true},
                {name : 'phone',editable:true,width : 90},
                {name : 'password',editable:true,width : 90},
                {name : 'pic_img',editable:true,width : 150,edittype:"file",align:"center",
                    formatter:function (cellvalue) {
                        return "<img src='${path}/upload/photo/"+cellvalue+"' style='with:10px;height:89px'/>"
                    }

                },
                {name : 'ahama',editable:true,width : 150,align : "right"},
                {name : 'name',editable:true,width : 150,align : "right"},
                {name : 'sex',editable:true,width : 150,align : "right"},
                {name : 'city',editable:true,width : 150,align : "right"},
                {name : 'sign',editable:true,width : 150,align : "right"},
                {name : 'status',width : 150,align:"center",autowidth:true,
                    formatter:function (cellvalue,option,row) {
                        if(cellvalue==1){
                            return "<button class='btn btn-success' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >冻结</button>";
                        }else{
                            return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'  >解除冻结</button>";
                        }
                    }
                },
                {name : 'reg_date',width : 150,sortable : false},
                {name : 'guruId',width : 150,sortable : false}
            ]

        });

        /*增删改查操作*/
        $("#bntable").jqGrid('navGrid', '#bnpager',
            {edit : true, add : true, del : true, addtext:"添加", edittext:"编辑", deltext:"删除"},
            {afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/user/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "pic_img",
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
                        url:"${path}/user/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "pic_img",
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

    })
    //点击修改
    function change(id,value){

        if(value==1){

            $.ajax({
                url:"${path}/user/updateStatus",
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
                url:"${path}/user/updateStatus",
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
    $("#btn1").click(function () {
        $.ajax({
            url:"${path}/user/exportExcel",
            type:"post",
            dataType:"JSON",
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
    })
</script>

<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">用户信息</a></li>
    </ul>

    <div class="panel panel-body">
        <button type="button" class="btn btn-success" >修改用户信息</button>
        <button type="button" class="btn btn-success" onclick='exportExcel()' id="btn1">导出用户信息</button>
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