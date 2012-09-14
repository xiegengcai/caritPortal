<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/WEB-INF/jsp/commons/easyui.jsp"%>
		<script type="text/javascript" src="${ctx}/resources/public/scripts/utils.js?v1.0" ></script>
		<script type="text/javascript" src="${ctx}/resources/public/scripts/common.js?v1.0" ></script>
		<script type="text/javascript">
		$(function(){
			$('#status').combobox({
				data:statusList,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('#status_edit').combobox({
				data:statusList,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('#type').combobox({
				data:types,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('#type_edit').combobox({
				data:types,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('.combobox-f').each(function(){
				$(this).combobox('clear');
			});
			// 初始化
			$('#tt').datagrid({
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function() {
						$('#editWin').window({title:'新增'+winTitle,iconCls:'icon-add'});
						$('#editForm').form('clear');
						$('#editForm textarea').val('');
						$('#id').val('');
						$('#editWin').window('open');
						$('#editWin').show();
						$('.validatebox-tip').remove();
					}
				}, '-', {
					text :'删除',
					iconCls:'icon-remove',
					handler:delM
				} ]
			});
			$('#edit_submit_media').click(function(){
				$('#editForm').form({
					onSubmit:function(){
						// 避免 form validate bug
						$('.combobox-f').each(function(){
							$(this).val($(this).combobox('getText'));
						});
						var b=true;
						var fileText=$('#fileText').val();
						if(fileText==''||fileText==null||fileText==undefined){
							b=false;
							$.messager.alert('错误', "请选取文件", 'error');
						}else{
							if($('#type_eidt').val()==0&&!chkFileType(fileText,'jpg|jpeg|png|gif')){
								b=false;
								$.messager.alert('提示', "选定的类别是图片，请选择 jpg|jpeg|png|gif 类型的文件", 'info');
							}
							if($('#type_eidt').val()==1&&!chkFileType(fileText,'flv')){
								b=false;
								$.messager.alert('提示', "选定的类别是FLV视频，请选择 FLV类型的文件", 'info');
							}
						}
						if(!b){return b;}
						b=$(this).form('validate');
						if(b){
							$.messager.progress({title:'请稍后',msg:'提交中...'});
						}
						return b;
				    }, 
			    	success:function(data){
			    		$.messager.progress('close');
			    		var map=$.parseJSON(data);
			    		if(map.answerCode==-1){
							$.messager.alert('错误', "编辑失败", 'error');
			    		} else if(map.answerCode>0){
							$.messager.alert('成功', "编辑成功", 'info');
				        	$('#editWin').window('close');
				        	// update rows
				        	$('#tt').datagrid('reload');
						}else{
			    			$.messager.alert('异常', "后台系统异常", 'error');
						}
				    }
				}).submit();
			});
			checkExisted($('#name_edit'),"${ctx}/common/check/media?name=");
		});
		function edit(index) {
			
		}
		function delM(){
			var ids=getIds();
			if (ids) {
				$.messager.confirm('警告','删除同时会删除关联信息，您确认要删除吗?',function(data) {
					if (data) {
						var _url=$('#tt').attr('url');
						_url=_url.substring(0,_url.indexOf('query'))+'delete?id=0&ids='+ids;
						$.messager.progress({title:'请稍后',msg:'提交中...'});
						$.ajax({
							url : _url,
							type : 'GET',
							timeout : 1000,
							error : function() {
								$.messager.alert('错误','删除失败!','error');
							},
							success : function(data) {
								$.messager.progress('close');
								if (data == -1) {
									$.messager.alert('错误','删除失败!','error');
								} else if (data > 0) {
									$.messager.alert('成功','被引用的资源需要取消引用才能删除，','info');
									// update rows
									$('#tt').datagrid('reload');
								} else {
									$.messager.alert('异常','后台系统异常','error');
								}
							}
						});
					}
				});
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要删除的记录。'
				});
			}
		}
		</script>
		<style>
		#editWin label {width: 100px;text-align: right;}
		#editWin input {width: 250px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="media"
			action="${ctx}/admin/media/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="name" path="name">名称：</form:label>
					</td>
					<td>
						<form:input path="name"/>
					</td>
					<td>
						<form:label for="url" path="url">媒体路径：</form:label>
					</td>
					<td>
						<form:input path="url"/>
					</td>
					<td>
						<form:label for="type" path="type">类型：</form:label>
					</td>
					<td>
						<form:input path="type" />
					</td>
					<td>
						<form:label for="status" path="status">状态：</form:label>
					</td>
					<td>
						<form:input path="status" />
					</td>
				</tr>
			</table>
		</form:form>
		<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="submit"
					iconCls="icon-search">查 询</a>
				<a href="javascript:void();" class="easyui-linkbutton" id="reset"
					iconCls="icon-undo">重 置</a>
			</div>
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="媒体列表" align="left"  
			idField="id" url="${ctx}/admin/media/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[10,15, 20]">
				<thead>
					<tr>
						<th field="name" width="100" align="center">名称</th>
						<th field="url" width="180" align="center">媒体路径</th>
						<th field="type" width="90" align="center" formatter="typeFormatter">类型</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="remark" width="150" align="center">备注</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="媒体" closed="true" style="width:500px;height:380px;padding:5px;" modal="true">
			<form:form modelAttribute="media" id="editForm" action="${ctx}/admin/media/save" method="post" cssStyle="padding:10px 20px;" enctype="multipart/form-data">
				<table>
					<tr>
						<td><form:label	for="name" path="name" cssClass="mustInput">标题：</form:label></td>
						<td><form:input path="name" id="name_edit" required="true" validType="length[3,100]" class="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="type" path="type" cssClass="mustInput">类型：</form:label></td>
						<td><form:input path="type" id="type_edit" required="true" cssStyle="width:255px;"/></td>
					</tr>
					<tr>
						<td><form:label	for="url" path="url" cssClass="mustInput">文件：</form:label></td>
						<td>
						<div class="fileinputs">  
							<input type="file" class="file" name="file" id="file" onchange="$('#fileText').val(this.value);"/>  
							<div class="fakefile">  
								<input id="fileText" style="width:205px;"/><button>浏览</button>
							</div>  
						</div>
						</td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" cssStyle="width:255px;"/></td>
					</tr>
					<tr><td><form:label for="remark" path="remark" cssClass="easyui-validatebox">备注：</form:label></td>
						<td><form:textarea path="remark" id="remark_edit" cssClass="easyui-validatebox" cssStyle="width:250px;height:50px;" validType="maxLength[100]"/></td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit_media" iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset" iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
	</body>
</html>