<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<script>
    $(function(){

        $("#abtable").jqGrid({
                url : "${path}/album/page",
                editurl:"${path}/album/edit",
                datatype : "json",
                autowidth:true,
                height : "auto",
                styleUI:"Bootstrap",
                rowNum : 3,
                rowList : [ 2, 4, 6, 30 ],
                pager : '#abpager',
                sortname : 'id',
                viewrecords : true,
                sortorder : "desc",
                multiselect : false,
                colNames : [ 'Id', '名字', '评分','作者', '播音','集数','内容','封面', '发布时间' ],
                colModel : [
                    {name : 'id',index : 'id',  width : 55},
                    {name : 'title',editable:true,index : 'invdate',width : 90},
                    {name : 'score',editable:true,index : 'invdate',width : 90},
                    {name : 'author',editable:true,index : 'name',width : 100},
                    {name : 'broadcast',editable:true,index : 'name',width : 100},
                    {name : 'count',editable:true,index : 'name',width : 100},
                    {name : 'content',editable:true,index : 'name',width : 100},
                    {name : 'cover_img',editable:true,index : 'amount',width : 80,align : "right",edittype:"file",
                        editoptions:{enctype:"multipart/from-data"},
                        formatter:function (cellvalue) {
                            return "<img src='${path}/upload/photo/"+cellvalue+"' style='with:10px;height:89px'/>"
                        }
                    },
                    {name : 'pub_date',index : 'total',width : 80,align : "right"}
                ],
                subGrid : true, //开启子表格支持
                //subgrid_id  子表格的Id,当开启子表格式,会在主表格中创建一个子表格，subgrid_id就是这个子表格的Id
                subGridRowExpanded : function(subgridId, rowId) {
                    addSubGrid(subgridId,rowId);
                },

            });
        /*增删改查操作*/
        $("#abtable").jqGrid('navGrid', '#abpager',
            {edit : true,add : true,del : true,addtext:"添加", edittext:"编辑", deltext:"删除"},
            {afterSubmit:function (data) {

                    $.ajaxFileUpload({
                        url:"${path}/album/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "cover_img",
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
                        url:"${path}/album/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "cover_img",
                        data:{id:data.responseText},
                        success:function(){
                            $("#abtable").trigger("reloadGrid")
                        }
                    })
                    return "hello";
                },
                closeAfterAdd:true


            },
            {}
        );

    });

    //创建子表单
    function addSubGrid(subgridId,rowId) {

        var subgridTableId = subgridId + "table";
        var pagerId = subgridId+"pager";

        //初始化表单,分页工具栏
        $("#" + subgridId).html("<table id='" + subgridTableId+ "'/><div id='"+ pagerId + "'/>");

        //创建表单
        $("#" + subgridTableId).jqGrid({
                //url:"/chapter/queryByPage?albumId="+rowId,
                url:"${path}/chaper/queryByPage?albumId="+rowId,
                editurl:"${path}/chaper/edit?albumId="+rowId,
                datatype : "json",
                rowNum : 2,
                rowList : [ 2, 4, 6, 30 ],
                pager : "#"+pagerId,
                sortname : 'num',
                sortorder : "asc",
                autowidth:true,
                viewrecords : true,
                height : "auto",
                styleUI:"Bootstrap",
                colNames : [ 'Id', '名字', '音频大小', '音频时长','上传时间','操作' ],
                colModel : [
                    {name : "id",  index : "num",width : 80,key : true},
                    {name : "url",editable:true,index : "item",  width : 130,edittype:"file"},
                    {name : "size",index : "qty",width : 70,align : "right"},
                    {name : "duration",index : "unit",width : 70,align : "right"},
                    {name : "up_date",index : "total",width : 70,align : "right"},
                    {
                        name: "url", align: "center",
                        formatter: function (cellVale) {
                                                alert("1111")
                            return "<a href='#' onclick='player(\"" + cellVale + "\")'><span class='glyphicon glyphicon-play-circle'/></a>&nbsp;&emsp;&emsp;" +
                                "<a href='#' onclick='down(\""+cellVale+ "\")'><span class='glyphicon glyphicon-download'/></a>";

                        }

                    }
                ]
            });

        /*子表格增删改查*/
        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId,
            {edit : true,add : true,del : true},
            {
                afterSubmit:function (data) {

                    $.ajaxFileUpload({
                        url:"${path}/chaper/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "url",
                        data:{id:data.responseText},
                        success:function(){
                            $("#abtable").trigger("reloadGrid")
                        }
                    })
                    return "ok";
                },
                closeAfterEdit:true
            },
            {
                afterSubmit:function (data) {

                    $.ajaxFileUpload({
                        url:"${path}/chaper/upload",
                        type:"post",
                        dataType:"json",
                        fileElementId: "url",
                        data:{id:data.responseText},
                        success:function(){
                            $("#abtable").trigger("reloadGrid")
                        }
                    })
                    return "hello";
                },
                closeAfterAdd:true
            },
            {}

        );
    }
    //下载
    function down(fileName){
        alert(fileName);
        location.href="${path}/chaper/down?fileName="+fileName;
    }
    //在线播放
    function player(fileName){
        $("#audioModal").modal("show");
        $("#playAudio").attr("src","${path}/upload/music/"+fileName);
    }


</script>

<div class="panel panel-success">

    <div class="panel panel-heading">专辑信息</div>

    <ul class="nav nav-tabs">
        <li class="active"><a href="#">专辑信息</a></li>
    </ul>
    <div class="panel panel-body">
        <button type="button" class="btn btn-info" >添加专辑</button>
        <button type="button" class="btn btn-success" >修改专辑</button>
        <button type="button" class="btn btn-warning" >删除专辑</button>
    </div>
    <table id="abtable" />

    <div id="abpager" />

    <div id="audioModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="playAudio" src="" controls/>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
