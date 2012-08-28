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
				$('#catalogCode_edit').val(m.catalogCode);
				$('#description_edit').val(m.description);
				$('#displayIndex_edit').numberspinner('setValue',m.displayIndex);
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
		<form:form modelAttribute="catalog"
			action="${ctx}/admin/catalog/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="catalogCode" path="catalogCode">国际化配置名称：</form:label>
					</td>
					<td>
						<form:input path="catalogCode"/>
					</td>
					<td>
						<form:label for="description" path="description">描述：</form:label>
					</td>
					<td>
						<form:input path="description"/>
					</td>
					<td>
						<form:label for="displayIndex" path="displayIndex">顺序：</form:label>
					</td>
					<td>
						<form:input path="displayIndex" cssClass="easyui-numberspinner" min="1" max="1000"/>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="车系分类列表" align="left"  
			idField="id" url="${ctx}/admin/catalog/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[ 5, 10]" sortName="displayIndex" sortOrder="asc">
				<thead>
					<tr>
						<th field="catalogCode" width="100" align="center">国际化配置名称</th>
						<th field="description" width="150" align="center">描述</th>
						<th field="displayIndex" width="60" align="center">顺序</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="createTime" width="90" align="center">创建时间</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="车系分类" closed="true" style="width:500px;height:280px;padding:5px;" modal="true">
			<form:form modelAttribute="catalog" id="editForm" action="${ctx}/admin/catalog/save" method="post" cssStyle="padding:10px 20px;" enctype="multipart/form-data">
				<table>
					<tr>
						<td><form:label	for="catalogCode" path="catalogCode" cssClass="mustInput">国际化配置名称：</form:label></td>
						<td><form:input path="catalogCode" id="catalogCode_edit" required="true" validType="length[3,100]" class="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="description" path="description" cssClass="mustInput">描述：</form:label></td>
						<td><form:input path="description" id="description_edit" required="true" validType="length[3,200]" class="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="displayIndex" path="displayIndex" cssClass="mustInput">顺序：</form:label></td>
						<td>
							<form:input path="displayIndex" id="displayIndex_edit"  required="true" cssClass="easyui-numberspinner" min="1" max="1000" cssStyle="width:255px;"/>
						</td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" class="easyui-validatebox" cssStyle="width:255px;"/></td>
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