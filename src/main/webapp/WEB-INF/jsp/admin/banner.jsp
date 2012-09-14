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
		var updateId;
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
			checkExisted($('#name_edit'),"${ctx}/common/check/media?name=");
			//uploadForm
			// 初始化
			$('#ttt').datagrid({
				width:'100%',
				method:'get',
				toolbar:[{
					text:'选择',
					iconCls:'icon-search',
					handler:select
				}],
				onDblClickRow:select,
				onLoadSuccess:function(){
					// clear selected
					$('#ttt').datagrid('clearSelections');
					$('.datagrid-header-check input[type=checkbox]').attr('checked',false);
				}
			});
			$('#submit_media').click(function(){
				//先取得 datagrid 的查询参数 
				var params = $('#ttt').datagrid('options').queryParams;
				//自动序列化表单元素为JSON对象
		        var fields =$('#mediaSearchForm').serializeArray();   
		        $.each( fields, function(i, field){
		            params[field.name] = field.value; //设置查询参数  
		        });
		        //设置好查询参数 reload 一下就可以了
		        $('#ttt').datagrid('reload'); 
			});
			$('#reset_media').click(function(){$('#mediaSearchForm').form('clear')});
			$('#upload_reset').click(function(){
				$('#uploadForm').form('clear');
				$('#uploadForm textarea').val('');
			});
			$('#upload_submit').click(function(){
				$('#uploadForm').form({
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
						}else if(!chkFileType(fileText,'jpg|jpeg|png|gif')){
							b=false;
							$.messager.alert('提示', "选定的类别是图片，请选择 jpg|jpeg|png|gif 类型的文件", 'info');
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
				        	$('#mediaWin').window('close');
				        	$('#'+updateId).val(map.url).removeClass('validatebox-invalid');
						}else{
			    			$.messager.alert('异常', "后台系统异常", 'error');
						}
				    }
				}).submit();
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
				$('#name_edit').val(m.name);
				$('#image_edit').val(m.image);
				$('#thumb_edit').val(m.thumb);
				$('#href_edit').val(m.href);
				$('#displayIndex_edit').numberspinner('setValue',m.displayIndex);
				$('#status_edit').combobox('setValue',m.status);
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
		
		function media(target){
			updateId=target;
			$('div.validatebox-tip').remove();
			$('#mediaWin').window('open');
			$('#mediaWin').show();
			return false;
		}
		function select(){
			var m = $('#ttt').datagrid('getSelected');
			$('#'+updateId).val(m.url).removeClass('validatebox-invalid');
			$('#mediaWin').window('close');
		}
		</script>
		<style>
		#editWin label {width: 100px;text-align: right;}
		#editWin input {width: 250px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="banner"
			action="${ctx}/admin/banner/query"
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
						<form:label for="href" path="href">链接：</form:label>
					</td>
					<td>
						<form:input path="href"/>
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
			<table id="tt" style="height: auto;" iconCls="icon-blank" title="广告列表" align="left"  
			idField="id" url="${ctx}/admin/banner/query" pagination="true" rownumbers="true"
			fitColumns="true" pageList="[10,15, 20]">
				<thead>
					<tr>
						<th field="name" width="100" align="center">名称</th>
						<th field="image" width="150" align="center">大图</th>
						<th field="thumb" width="150" align="center">缩略图</th>
						<th field="href" width="150" align="center">链接</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="displayIndex" width="60" align="center">顺序</th>
						<th field="remark" width="150" align="center">备注</th>
						<th field="updateTime" width="90" align="center">更新时间</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="editWin" class="easyui-window" title="广告" closed="true" style="width:500px;height:380px;padding:5px;" modal="true">
			<form:form modelAttribute="banner" id="editForm" action="${ctx}/admin/banner/save" method="post" cssStyle="padding:10px 20px;" enctype="multipart/form-data">
				<table>
					<tr>
						<td><form:label	for="name" path="name" cssClass="mustInput">标题：</form:label></td>
						<td><form:input path="name" id="name_edit" required="true" validType="length[3,100]" cssClass="easyui-validatebox"/></td>
					</tr>
					<tr>
						<td><form:label	for="image" path="image" cssClass="mustInput">大图：</form:label></td>
						<td><form:input path="image" id="image_edit" required="true" cssClass="easyui-validatebox" cssStyle="width:220px"/><span class="spanBtn" onclick="media('image_edit')">浏览</span></td>
					</tr>
					<tr>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">缩略图：</form:label></td>
						<td><form:input path="thumb" id="thumb_edit" required="true" cssClass="easyui-validatebox" cssStyle="width:220px"/><span class="spanBtn" onclick="media('thumb_edit')">浏览</span></td>
					</tr>
					<tr>
						<td><form:label	for="href" path="href" cssClass="mustInput">链接：</form:label></td>
						<td><form:input path="href" id="href_edit" required="true" cssClass="easyui-validatebox" validType="url"/></td>
					</tr>
					<tr>
						<td><form:label for="displayIndex" path="displayIndex" cssClass="mustInput">顺序：</form:label></td>
						<td><form:input path="displayIndex" id="displayIndex_edit" cssClass="easyui-numberspinner" min="1" max="1000" required="true" cssStyle="width:255px"/></td>
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
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit" iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset" iconCls="icon-undo">重 置</a>
				</div>
			</form:form>
		</div>
		
		<div id="mediaWin" class="easyui-window" title="媒体库" closed="true" style="width:600px;height:480px;padding:5px;" modal="true">
			<div id="tabs" class="easyui-tabs" style="width:575px;height:435px;">  
			    <div title="媒体库" style="padding:20px;">
			    <form action="${ctx}/admin/media/query?type=0" method="get" id="mediaSearchForm">
					<table>
						<tr>
							<td>
								<label>名称：</label>
							</td>
							<td>
								<input type="text" name="name"/>
							</td>
						</tr>
					</table>
				</form>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:void(0);" class="easyui-linkbutton" id="submit_media"
						iconCls="icon-search">查 询</a>
					<a href="javascript:void();" class="easyui-linkbutton" id="reset_media"
						iconCls="icon-undo">重 置</a>
				</div>
				<table id="ttt" style="height: auto;" iconCls="icon-blank" title="媒体列表" align="left"  
				idField="id" url="${ctx}/admin/media/query?type=0" pagination="true" rownumbers="true" singleSelect="true"
				fitColumns="true" pageList="[10,15]">
					<thead>
						<tr>
							<th field="name" width="100" align="center">名称</th>
							<th field="url" width="100" align="center">媒体路径</th>
							<th field="type" width="100" align="center" formatter="typeFormatter">类别</th>
						</tr>
					</thead>
				</table>
			    </div>  
			    <div title="本地上传" style="padding:20px;">  
			        <form id="uploadForm" action="${ctx}/admin/media/save" method="post" cssStyle="padding:10px 20px;" enctype="multipart/form-data">
					<table>
						<tr>
							<td><label	for="name" class="mustInput">标题：</label></td>
							<td><input name="name" id="name_edit" required="true" validType="length[3,100]" class="easyui-validatebox" style="width: 250px;"/></td>
						</tr>
						<tr>
							<td><label	for="url" class="mustInput">文件：</label></td>
							<td>
							<div class="fileinputs">  
								<input type="file" class="file" name="file" id="file" onchange="$('#fileText').val(this.value);"/>  
								<div class="fakefile">  
									<input id="fileText" style="width:205px;"/><button>浏览</button>
								</div>  
							</div>
							</td>
						</tr>
						<tr><td><label for="remark" class="easyui-validatebox">备注：</label></td>
							<td><textarea name="remark" id="remark_edit" class="easyui-validatebox" style="width:250px;height:50px;" validType="maxLength[100]"></textarea></td>
						</tr>
					</table>
					<input type="hidden" name=status value="1"/>
					<input type="hidden" name="type" value="0"/>
					<div style="text-align: center; padding: 5px;">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="upload_submit" iconCls="icon-save">保 存</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="upload_reset" iconCls="icon-undo">重 置</a>
					</div>
				</form>
			    </div>  
			</div>
		</div>
	</body>
</html>