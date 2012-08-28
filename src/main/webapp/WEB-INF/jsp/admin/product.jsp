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
		var contentEditer;
		$(function(){
			contentEditer=simpleEditer('content');
			$('#catalogId').combobox({
				data:catalogs,
				editable:false,
				valueField:'id',
				textField:'description'
			});
			$('#catalogId_edit').combobox({
				data:catalogs,
				editable:false,
				valueField:'id',
				textField:'description'
			});
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
			
			$('#tt').datagrid({
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function() {
						location.href=app.name+'/admin/product/edit?id=';
					}
				}, '-', {
					text:'修改',
					iconCls:'icon-edit',
					handler:edit
				}, '-', {
					text :'删除',
					iconCls:'icon-remove',
					handler:del
				} ],
				onDblClickRow:function(rowIndex){
					edit(rowIndex);
				},
				onLoadSuccess:function(){
					// clear selected
					$('#tt').datagrid('clearSelections');
					$('.datagrid-header-check input[type=checkbox]').attr('checked',false);
				}
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
				location.href=app.name+'/admin/product/edit?id='+m.id;
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}
		</script>
		<style>
		#editWin label {width: 60px;text-align: right;}
		#editWin input {width: 180px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="product"
			action="${ctx}/admin/product/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="catalogId" path="catalogId">车型：</form:label>
					</td>
					<td>
						<form:input path="catalogId" />
					</td>
					<td>
						<form:label for="language" path="language">语言：</form:label>
					</td>
					<td>
						<form:input path="language"/>
					</td>
					<td>
						<form:label for="title" path="title">标题：</form:label>
					</td>
					<td>
						<form:input path="title"/>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="产品列表" align="left"  
			idField="id" url="${ctx}/admin/product/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[ 5, 10]">
				<thead>
					<tr>
						<th field="title" width="100" align="center">标题</th>
						<th field="language" width="100" align="center" formatter="lanFormatter">语言</th>
						<th field="catalogId" width="100" align="center" formatter="catalogFormatter">车型</th>
						<th field="top" width="60" align="center" formatter="topFormatter">置顶</th>
						<th field="thumb" width="120" align="center">主图</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<!-- 
						<th field="createTime" width="90" align="center">创建时间</th>
						 -->
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
	</body>
</html>