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
			checkExisted($('#email_edit'),app.name+'/back/check/user?name=');
			checkExisted($('#nickName_edit'),app.name+'/back/check/user?nickName=');
			$('#gender').combobox({
				data:genderList,
				editable:false,
				valueField:'code',
				textField:'value'
			});
			$('#gender_edit').combobox({
				data:genderList,
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
				$('#email_edit').val(m.email);
				$('#nickName_eidt').val(m.nickName);
				$('#password_eidt').val(m.password);
				$('#realName_eidt').val(m.realName);
				$('#status_edit').combobox('setValue',m.status);
				$('#gender_edit').combobox('setValue',m.gender);
				$('#officePhone_eidt').val(m.officePhone);
				if(m.mobile>0){
					$('#mobile_eidt').val(m.mobile);	
				}
				$('#remark_edit').val(m.remark);
				$('#id').val(m.id);
				$('#editWin').show();
			} else {
				$.messager.show({
					title : '警告',
					msg : '请先选择要修改的记录。'
				});
			}
		}
		
		function mobileFormatter(v){
			if(v>0){
				return v;
			}
			return '';
		}
		</script>
		<style>
		#editWin label {width: 80px;text-align: right;}
		#editWin input {width: 120px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="user"
			action="${ctx}/admin/user/query"
			method="get" id="searchForm">
			<table>
				<tr>
					<td>
						<form:label for="email" path="email">登录邮箱：</form:label>
					</td>
					<td>
						<form:input path="email"/>
					</td>
					<td>
						<form:label for="nickName" path="nickName">昵称：</form:label>
					</td>
					<td>
						<form:input path="nickName"/>
					</td>
					<td>
						<form:label for="gender" path="gender">性别：</form:label>
					</td>
					<td>
						<form:input path="gender" />
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
			idField="id" url="${ctx}/admin/user/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[10,15, 20]">
				<thead>
					<tr>
						<th field="email" width="100" align="center">登录邮箱</th>
						<th field="nickName" width="100" align="center">昵称</th>
						<th field="realName" width="100" align="center">真实姓名</th>
						<th field="mobile" width="100" align="center" formatter="mobileFormatter">手机号码</th>
						<th field="officePhone" width="100" align="center">办公电话</th>
						<th field="gender" width="60" align="center" formatter="genderFormatter">性别</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="remark" width="120" align="center">备注</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="用户" closed="true" style="width:600px;height:300px;padding:5px;" modal="true">
			<form:form modelAttribute="user" id="editForm" action="${ctx}/admin/user/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="email" path="email" id="emailLabel" cssClass="mustInput">邮箱：</form:label></td>
						<td><form:input path="email" id="email_edit" cssClass="easyui-validatebox" required="true" validType="email"/></td>
						<td><form:label	for="nickName" path="nickName" id="nickNameLabel" cssClass="mustInput">昵称：</form:label></td>
						<td><form:input path="nickName" id="nickName_edit" cssClass="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td><form:label	for="password" path="password" id="passwordLabel" cssClass="mustInput">密码：</form:label></td>
						<td><form:password path="password" cssClass="easyui-validatebox" validType="safepass"/></td>
						<td><form:label	for="realName" path="realName">真实姓名：</form:label></td>
						<td><form:input path="realName" id="realName_edit" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" cssStyle="width:128px;"/></td>
						<td><form:label	for="gender" path="gender" cssClass="mustInput">性别：</form:label></td>
						<td><form:input path="gender" id="gender_edit" required="true" cssStyle="width:128px;"/></td>
					</tr>
					<tr>
						<td><form:label	for="mobile" path="mobile">手机号码：</form:label></td>
						<td><form:input path="mobile" id="mobile_edit" cssClass="easyui-validatebox" validType="mobile"/></td>
						<td><form:label	for="officePhone" path="officePhone">办公电话：</form:label></td>
						<td><form:input path="officePhone" id="officePhone_edit" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr><td><form:label for="remark" path="remark" cssClass="easyui-validatebox">备注：</form:label></td>
						<td colspan="3"><form:textarea path="remark" id="remark_edit" cssClass="easyui-validatebox" cssStyle="width:380px;height:50px;" validType="maxLength[100]" maxLen="100" msg="备注"/></td>
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