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
		var types=[{'code':'0','value':'公司新闻'}, {'code':'1','value':'业界动态'}];
		var contentEditer;
		$(function(){
			contentEditer=simpleEditer('content');
			$('#language').combobox({
				data:supportLanguages,
				editable:false,
				valueField:'id',
				textField:'name'
			});
			$('#language_edit').combobox({
				data:supportLanguages,
				editable:false,
				valueField:'id',
				textField:'name'
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
			$('.combobox-f').each(function(){
				$(this).combobox('clear');
			});
			$('#edit_submit_news').click(function(){
				$('#editForm').form({
					onSubmit:function(){
						contentEditer.sync();
						// 避免 form validate bug
						$('.combobox-f').each(function(){
							$(this).val($(this).combobox('getText'));
						});
						var b=true;
						if(getStrLen($.trim($('#content').val()))>$('#content').attr('maxLen')){
							$.messager.alert('提示', $('#content').attr('msg')+'超长，最多输入'+$('#content').attr('maxLen')+'个字符(一个中文两个字符)', 'info');
							b=false;
							return false;
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
			    		if(data==-1){
							$.messager.alert('错误', "编辑失败", 'error');
			    		} else if(data>0){
							$.messager.alert('成功', "编辑成功", 'info');
				        	$('#editVersionWin').window('close');
				        	// update rows
				        	$('#tt').datagrid('reload');
						}else{
			    			$.messager.alert('异常', "后台系统异常", 'error');
						}
				    }
				}).submit();
			});
			$('#editWin').window({onClose:function(){
				contentEditer.html('');
			}});  
		});
		function edit(index) {
			if(index>-1){//双击
				// clear selected
				$('#tt').datagrid('clearSelections');
				$('#tt').datagrid('selectRow',index); //让双击行选定
			}
			var m = $('#tt').datagrid('getSelected');
			if (m) {
				$('#editForm input').each(function(){
					$(this).removeClass('validatebox-invalid');
				});
				$('#editWin').window({title:'修改'+winTitle,iconCls:'icon-edit'});
				$('#editWin').window('open');
				// init data
				$('#title_edit').val(m.title);
				$('#type_edit').combobox('setValue',m.type);
				$('#language_edit').combobox('setValue',m.language);
				$('#status_edit').combobox('setValue',m.status);
				contentEditer.html(m.content);
				$('#id').val(m.id);
				$('#editWin').show();
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}
		
		function typeFormatter(v){
			var result='-';
			$.each(types, function(key,val) {
				if(v==val.code){
					result=val.value;
					return false;
				}
			});
			return result;
		}
		</script>
		<style>
		#editWin label {width: 80px;text-align: right;}
		#editWin input {width: 180px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="news"
			action="${ctx}/admin/news/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="title" path="title">标题：</form:label>
					</td>
					<td>
						<form:input path="title"/>
					</td>
					<td>
						<form:label for="language" path="language">语言：</form:label>
					</td>
					<td>
						<form:input path="language"/>
					</td>
					<td>
						<form:label for="type" path="type">类别：</form:label>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="支持语言列表" align="left"  
			idField="id" url="${ctx}/admin/news/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[ 5, 10]">
				<thead>
					<tr>
						<th field="title" width="100" align="center">标题</th>
						<th field="language" width="100" align="center" formatter="lanFormatter">语言</th>
						<th field="type" width="100" align="center" formatter="typeFormatter">类别</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="createTime" width="90" align="center">创建时间</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="新闻" closed="true" style="width:650px;height:480px;padding:5px;" modal="true">
			<form:form modelAttribute="news" id="editForm" action="${ctx}/admin/news/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="title" path="title" cssClass="mustInput">标题：</form:label></td>
						<td><form:input path="title" id="title_edit" required="true" validType="length[3,100]" class="easyui-validatebox"/></td>
						<td><form:label	for="type" path="type" cssClass="mustInput">类别：</form:label></td>
						<td><form:input path="type" id="type_edit" required="true" class="easyui-validatebox" cssStyle="width:185px;"/></td>
					</tr>
					<tr>
						<td><form:label	for="language" path="language" cssClass="mustInput">语言：</form:label></td>
						<td><form:input path="language" id="language_edit" required="true" class="easyui-validatebox" cssStyle="width:185px;"/></td>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" class="easyui-validatebox" cssStyle="width:185px;"/></td>
					</tr>
					<tr><td><form:label for="content" path="content" cssClass="easyui-validatebox">内容：</form:label></td>
						<td colspan="3"><form:textarea path="content" id="content_edit" cssClass="easyui-validatebox" cssStyle="width:468px;height:300px;" maxLen="5000" msg="内容"/></td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit_news" iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset" iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
	</body>
</html>