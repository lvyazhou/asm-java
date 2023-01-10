<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    ${headInclude}
	<title>${tableNameSimple}-列表</title>
<script type="text/javascript">
	//事件
	$(document).ready(function() {
		//查询按钮事件
		$('#btnsearch').on('click', function() {
			//反选list中的checkbox
			$("[name='id']").removeAttr("checked");//取消全选     
			$("[name='frm']").attr("method", "post");
			$("[name='frm']").submit();
        });
		
		//增加
		$('#btnadd').on('click', function() {
			window.location="/${lowerName}/toDetail";
		});
		//修改
		$('#btnupdate').on('click', function() {
			var str="";  
			$("[name='id'][checked]").each(function(){
				str+=$(this).val()+",";
			})
			if(str==null || str==''){
				window.wxc.xcConfirm('请选择需要查看的信息!', window.wxc.xcConfirm.typeEnum.info);
				return false;
			}else{
				str = str.substring(0,str.length-1);
				var strs=new Array();
				strs = str.split(",");
				if(strs.length>1){
					window.wxc.xcConfirm('只能选择一条信息，请重新选择!', window.wxc.xcConfirm.typeEnum.info);
					return false;
				}
			}
			window.location="/${lowerName}/toDetail?id="+str;
		});
		//批量删除
		$('#btndelete').on('click', function() {
			var str="";  
			$("[name='id'][checked]").each(function(){
				str+=$(this).val()+",";  
			})  
			//如果没有选择ID
			if(str=='' || str == null){
				var txt=  "请选择需要删除的信息!";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
				return false;
			}else{
				str = str.substring(0,str.length-1);
			}
			window.location="/${lowerName}/deletes?ids="+str;
		});
		//查看
		$('#btndetail').on('click', function() {
			var str="";  
			$("[name='id'][checked]").each(function(){
				str+=$(this).val()+",";
			})
			if(str==null || str==''){
				window.wxc.xcConfirm('请选择需要查看的信息!', window.wxc.xcConfirm.typeEnum.info);
				return false;
			}else{
				str = str.substring(0,str.length-1);
				var strs=new Array();
				strs = str.split(",");
				if(strs.length>1){
					window.wxc.xcConfirm('只能选择一条信息查看，请重新选择!', window.wxc.xcConfirm.typeEnum.info);
					return false;
				}
			}
			window.location="/${lowerName}/detail?id="+str;
		});
		//日志查询
		$('#btnlogsearch').on('click', function() {
			var str=""; 
		    var storeNo="";
			$("[name='id'][checked]").each(function(){
				//storeNo = $(this).parents().find("input[name='storeNo']").val();
				str+=$(this).val()+",";
			})
			if(str==null || str==''){
				window.wxc.xcConfirm('请选择需要查看的日志信息!', window.wxc.xcConfirm.typeEnum.info);
				return false;
			}else{
				str = str.substring(0,str.length-1);
				var strs=new Array();
				strs = str.split("-");
				//alert(strs);
				if(strs.length>1){
					window.wxc.xcConfirm('只能选择一条信息查看，请重新选择!', window.wxc.xcConfirm.typeEnum.info);
					return false;
				}
			}
			window.location="/sysLogInfo/list?businessId="+str+"&changeTable=${lowerName}";
		});
	})
</script>
</head>
<!-- /Head -->
<!-- Body -->
<body>
${titleInclude}
<!--右侧内容-->
<div class="contentText">
    <!--内容区域-->
    <div class="ifCtext">
        <!--当前位置-->
        <div class="location">
            当前位置：<a href="#">${tableNameSimple}</a> > <a href="/${lowerName}/list">${tableNameSimple}</a>
        </div>


        <!--查询-->
		<form action="/${lowerName}/list" method="post" name="frm" accept-charset="utf-8">
            <div class="query">
                <div class="queryL queryLH" style="height: 120px;">
                    ${whereItems}
               </div>
              <div class="queryR">
                    <input class="Button backgreen" type="submit" value="查询">
                </div>
            </div>
        </form>

        <!--操作按钮-->
        <div class="operation">
       		<a href='#' class='btn btn-default purple btn-xs' id='btnadd' ><i class='fa fa-plus-square'></i> 增加 </a> 
		<a href='#' class='btn btn-default purple btn-xs' id='btnupdate'><i class='fa fa-plus-square'></i> 修改 </a> 
		<a href='#' class='btn btn-default orange btn-xs' id='btndelete'><i class='fa fa-trash-o'></i> 删除 </a> 
		<a href='#' class='btn btn-default purple btn-xs' id='btndetail' ><i class='fa fa-plus-square'></i> 查看 </a> 
		<a href='#' class='btn btn-default blue btn-xs' id='btnsearch' ><i class='glyphicon glyphicon-search'></i> 查询 </a>
		<a href='#' class='btn btn-default blue btn-xs' id='btnlogsearch' ><i class='glyphicon glyphicon-search'></i> 日志查询 </a>
        </div>


        <table class="tablelest" border="0" cellspacing="0" cellpadding="0">
            <thead>
            <#-- 显示列表信息 --> 
            ${heads}
            </thead>
            <tbody>
	        <#-- 显示列表内容 --> 
			<#list pageInfo.list as item>
				${items}
			</#list>
            </tbody>
        </table>
        <!--分页-->
        <div class="paging">
        ${pageInclude}
        </div>
    <#--<div class="paging">-->
    <#--</div>-->
    </div>
    <!--所有内容框结束-->
</body>
<!--  /Body -->
</html>