<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal fade modal-danger" id="alertModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title">错误</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-outline" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- loading模板 -->
<script type="text/x-ejs-template" id="overlayTmpl">
<div class="overlay">
		<i class="fa fa-refresh fa-spin"></i>
</div>
</script>