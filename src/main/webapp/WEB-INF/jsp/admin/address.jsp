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
			$('#language').combobox({
				data:supportLanguages,
				editable:false,
				valueField:'isoCode',
				textField:'name'
			});
			$('#language_edit').combobox({
				data:supportLanguages,
				editable:false,
				valueField:'isoCode',
				textField:'name'
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
				$('#address_edit').val(m.address);
				$('#telephone_edit').val(m.telephone);
				$('#fax_edit').val(m.fax);
				$('#postalcode_edit').val(m.postalcode);
				$('#language_edit').combobox('setValue',m.language);
				$('#status_edit').combobox('setValue',m.status);
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
		#editWin label {width: 100px;text-align: right;}
		#editWin input {width: 250px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="address"
			action="${ctx}/admin/address/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="language" path="language">语言：</form:label>
					</td>
					<td>
						<form:input path="language"/>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="地址列表" align="left"  
			idField="id" url="${ctx}/admin/address/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[10,15, 20]">
				<thead>
					<tr>
						<th field="language" width="100" align="center" formatter="lanFormatter">语言</th>
						<th field="address" width="150" align="center">地址</th>
						<th field="telephone" width="150" align="center">联系电话</th>
						<th field="fax" width="150" align="center">传真</th>
						<th field="postalcode" width="90" align="center">邮政编码</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="地址" closed="true" style="width:500px;height:380px;padding:5px;" modal="true">
			<form:form modelAttribute="address" id="editForm" action="${ctx}/admin/address/save" method="post" cssStyle="padding:10px 20px;" enctype="multipart/form-data">
				<table>
					<tr>
						<td><form:label	for="language" path="language" cssClass="mustInput">语言：</form:label></td>
						<td><form:input path="language" id="language_edit" required="true" class="easyui-validatebox" cssStyle="width:255px;"/></td>
					</tr>
					<tr>
						<td><form:label	for="address" path="address" cssClass="mustInput">地址：</form:label></td>
						<td><form:input path="address" id="address_edit" required="true" cssClass="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td><form:label	for="telephone" path="telephone" cssClass="mustInput">电话：</form:label></td>
						<td><form:input path="telephone" id="telephone_edit" required="true" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="fax" path="fax" cssClass="mustInput">传真：</form:label></td>
						<td><form:input path="fax" id="fax_edit" required="true" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label for="postalcode" path="postalcode" cssClass="mustInput">邮政编码：</form:label></td>
						<td><form:input path="postalcode" id="postalcode_edit" required="true" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" cssStyle="width:255px;"/></td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit" iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset" iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
		
	</body>
</html>