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
		var topMenus={};
		$(function(){
			checkExisted($('#code_edit'),app.name+'back/check/menu?name=');
			$.ajaxSettings.async=false;
			$.getJSON(app.name+'/back/query/top_menu', function(data) {
				if(data){
					topMenus=data;
				}
			});
			$.ajaxSettings.async=true;
			$('#parentId').combobox({
				data:topMenus,
				editable:false,
				valueField:'id',
				textField:'remark'
			});
			$('#parentId_edit').combobox({
				data:topMenus,
				editable:false,
				valueField:'id',
				textField:'remark'
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
				if(m.parentId>0){
					$('#parentId_edit').combobox('setValue',m.parentId);
				}
				$('#code_edit').val(m.code);
				$('#remark_edit').val(m.remark);
				$('#url_edit').val(m.url);
				$('#level_edit').val(m.level);
				$('#displayIndex_edit').numberspinner('setValue',m.displayIndex);
				$('#id').val(m.id);
				$('#editWin').show();
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}

		function parentFormatter(v){
			var result='-';
			$.each(topMenus, function(key,val) {
				if(v==val.id){
					result=val.remark;
					return false;
				}
			});
			return result;
		}
		</script>
		<style>
		#editWin label {width: 140px;text-align: right;}
		#editWin input {width: 210px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="menu"
			action="${ctx}/admin/menu/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="parentId" path="parentId">父菜单：</form:label>
					</td>
					<td>
						<form:input path="parentId"/>
					</td>
					<td>
						<form:label for="code" path="code">菜单代码：</form:label>
					</td>
					<td>
						<form:input path="code"/>
					</td>
					<td>
						<form:label for="url" path="url">链接路径：</form:label>
					</td>
					<td>
						<form:input path="url"/>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="菜单列表" align="left"  
			idField="id" url="${ctx}/admin/menu/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[10,15, 20]" sortName="displayIndex" sortOrder="asc">
				<thead>
					<tr>
						<th field="parentId" width="100" align="center" formatter="parentFormatter">父菜单</th>
						<th field="code" width="100" align="center">菜单代码</th>
						<th field="url" width="100" align="center">链接路径</th>
						<th field="level" width="60" align="center">层级</th>
						<th field="displayIndex" width="60" align="center">顺序</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="createTime" width="90" align="center">创建时间</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="菜单" closed="true" style="width:500px;height:350px;padding:5px;" modal="true">
			<form:form modelAttribute="menu" id="editForm" action="${ctx}/admin/menu/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="parentId" path="parentId">父菜单：</form:label></td>
						<td><form:input path="parentId" id="parentId_edit" cssStyle="width:217px"/></td>
					</tr>
					<tr>
						<td><form:label	for="code" path="code"  cssClass="mustInput">菜单代码(国际化配置)：</form:label></td>
						<td><form:input path="code" id="code_edit" required="true" validType="maxLength[50]" maxLen="50" msg="菜单代码"/></td>
					</tr>
					<tr>
						<td><form:label	for="remark" path="remark"  cssClass="mustInput">菜单名称：</form:label></td>
						<td><form:input path="remark" id="remark_edit" required="true" validType="maxLength[100]" maxLen="100" msg="菜单名称"/></td>
					</tr>
					<tr>
						<td><form:label	for="url" path="url" cssClass="mustInput">链接路径：</form:label></td>
						<td><form:input path="url" id="url_edit" cssClass="easyui-validatebox" required="true" validType="maxLength[100]" maxLen="100" msg="字段描述"/></td>
					</tr>
					<tr>
						<td><form:label for="displayIndex" path="displayIndex" cssClass="mustInput">顺序：</form:label></td>
						<td><form:input path="displayIndex" id="displayIndex_edit" cssClass="easyui-numberspinner" min="1" max="1000" required="true" cssStyle="width:217px"/></td>
					</tr>
					<tr>
						<td><form:label	for="level" path="level" cssClass="mustInput">层级：</form:label></td>
						<td><form:input path="level" id="level_edit" cssClass="easyui-numberspinner" min="1" max="1000" required="true" cssStyle="width:217px"/></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td>
							<form:input path="status" id="status_edit" required="true" cssStyle="width:217px"/>
						</td>
					</tr>
				</table>
				<form:hidden path="id"/>
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