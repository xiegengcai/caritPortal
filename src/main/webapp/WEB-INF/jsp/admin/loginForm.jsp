<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
		<title>中通福瑞应用市场——后台管理</title>
		<%@ include file="/WEB-INF/jsp/commons/easyui.jsp"%>
		<script type="text/javascript">
		$(function (){
			// 检测是否是超时在iframe显示登录页
			if($('.easyui-layout', window.parent.document).html()){
				window.parent.location.reload();
			}
			$('#loginWin').window('open');
			$('#submit').click(function(){
				login();
			});
			$('#password').keydown(function(e){
				if(e.which==13){
					login();
				}
			});
			$('#reset').bind('click',function(){ $('#loginForm').form('clear');});
		});
		function login(){
			$('#loginForm').form({
		    	success:function(data){
		    		if(data==-3){
						$.messager.alert('错误', '密码错误次数太多，半小时内限制登录', 'error');
		    		}else if(data==-2){
		    			$.messager.alert('提示', '用户不存在！', 'info');
		    		}else if(data==-1){
						$.messager.alert('错误', '账号已停用！', 'error');
					}else if(data==0){
		    			$.messager.alert('提示', '密码错误！', 'info');
					}else if(data==1){
						//登录成功
						location.href='${ctx}/admin/index'
					} else {
						$.messager.alert('异常', '后台系统异常', 'error');
					}
			    }
			}).submit();
		}
		</script>
	</head>
	<body>
		<div id="loginWin" class="easyui-window" title="登录后台" closable="false" collapsible="false" minimizable="false" maximizable="false" style="width:380px;height:280px;padding:50px;">
		<form:form 
			action="${ctx}/back/login"
			method="post"  id="loginForm">
			<table>
				<tr>
					<td>
						<label for="email" path="email">邮箱：</label>
					</td>
					<td>
						<input type="text" name="email" class="easyui-validatebox" required="true" validType="email"/>
					</td>
				</tr>
				<tr>
					<td>
						<label  for="password" path="password">密码：</label>
					</td>
					<td>
						<input type="password" name="password" id="password" class="easyui-validatebox" required="true" validType="length[6,20]"/>
					</td>
				</tr>
			</table>
		</form:form>
		<div style="text-align: center; padding: 5px;">
				<a href="javascript:void(0);" class="easyui-linkbutton" id="submit"
					iconCls="icon-search">登 录</a>
				<a href="javascript:void();" class="easyui-linkbutton" id="reset"
					iconCls="icon-undo">重 置</a>
			</div>
		</div>
	</body>
</html>