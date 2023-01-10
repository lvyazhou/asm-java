<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${tableNameSimple}-查看</title>
<script type="text/javascript">
	//事件
	$(document).ready(function() {
		//返回
		$('#btnback').on('click', function() {
			window.location="/${lowerName}/list";
		});
	})
</script>
</head>
<body>
	<!-- mdl -->
	<form action="" method="post" accept-charset="utf-8">
		<!-- 面板开始 -->
		<div class="panel panel-default">
			<!-- 面板头部 -->
			<div class="panel-heading">
				<button type="button" class="btn btn-danger btn-xs" id="btnback">返回</button>
			</div>

			<!-- 面板主体 -->
			<div class="panel-body">
				<div class="col-sm-12">
					${formViewInfo}
				</div>
			</div>
		</div>
		<!-- 面板结束 -->
	</form>
</body>
</html>