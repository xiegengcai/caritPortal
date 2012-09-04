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
			$('#isoCode').combobox({
				data:languages,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('#isoCode_edit').combobox({
				data:languages,
				editable:false,
				valueField:'code',
				textField:'value',
				onSelect:function(rec){
					$('#name_edit').val(rec.value);
				}
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
				//$('#field_edit').val(m.field);
				$('#status_edit').combobox('setValue',m.status);
				$('#isoCode_edit').combobox('setValue',m.isoCode);
				$('#configKey_edit').val(m.configKey);
				$('#id').val(m.id);
				$('#editWin').show();
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}

		</script>
		<style>
		#editWin label {width: 140px;text-align: right;}
		#editWin input {width: 210px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="language"
			action="${ctx}/admin/language/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="isoCode" path="isoCode">名称：</form:label>
					</td>
					<td>
						<form:input path="isoCode"/>
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
			idField="id" url="${ctx}/admin/language/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[ 5, 10]">
				<thead>
					<tr>
						<th field="isoCode" width="100" align="center">ISO代码</th>
						<th field="name" width="100" align="center">名称</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="createTime" width="90" align="center">创建时间</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="支持语言" closed="true" style="width:500px;height:250px;padding:5px;" modal="true">
			<form:form modelAttribute="language" id="editForm" action="${ctx}/admin/language/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="isoCode" path="isoCode" cssClass="mustInput">名称：</form:label></td>
						<td><form:input path="isoCode" id="isoCode_edit" required="true" cssStyle="width:217px"/></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" cssStyle="width:217px"/></td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<form:hidden path="name" id="name_edit"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit"
						iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset"
						iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
	</body>
</html>