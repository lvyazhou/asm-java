<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${tableNameSimple}-增加</title>
<script type="text/javascript">
	//事件
	$(document).ready(function() {
		//返回
		$('#btnback').on('click', function() {
			window.location="/${lowerName}/list";
		});
		//增加
		$('#btnadd').on('click', function() {
			var id = $("#id").val(); //如果ID不为空，说明是修改
			if(id!=null && id!=''){
				//是修改操作
				document.frm.action = "${updateUrl}"; 
			}else{
				//是增加操作
			}
			$("[name='frm']").attr("method", "post");
			$("[name='frm']").submit();
		});
		//form表单校验
		$("#frm").validate({
			rules: {
				${ruler}	
			}
		});
	})
</script>
</head>
<body>
	<!-- mdl -->
	<form action="${addUrl}" id="frm" name="frm" method="post" accept-charset="utf-8">
		<!-- 面板开始 -->
		<div class="panel panel-default">
			<!-- 面板头部 -->
			<div class="panel-heading">
				<button type="button" class="btn btn-primary btn-xs" id="btnadd">确定</button>&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn btn-danger btn-xs">清空</button>&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-danger btn-xs" id="btnback">返回</button>
			</div>

			<!-- 面板主体 -->
			<div class="panel-body">
				<div class="col-sm-12">
					${formInfo}
				</div>
			</div>
		</div>
		<!-- 面板结束 -->
	</form>
	<!-- mdl -->
</body>
</html>