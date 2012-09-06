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
		var updateId;
		$(function(){
			contentEditer=simpleEditer('content');
			var array='${product.picture}'.split(';');
			$.each(array, function(i){
				$('#picture_'+(i+1)).val(this);
			});
			$('#catalogId_edit').combobox({
				data:catalogs,
				editable:false,
				valueField:'id',
				textField:'description'
			});
			$('#language_edit').combobox({
				data:supportLanguages,
				editable:false,
				valueField:'isoCode',
				textField:'name'
			});
			$('#status_edit').combobox({
				data:statusList,
				editable:false,
				valueField:'code',
				textField:'value'
			});
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
			$('#edit_submit_product').click(function(){
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
							location.href=app.name+'/admin/product';
						}else{
			    			$.messager.alert('异常', "后台系统异常", 'error');
						}
				    }
				}).submit();
			});
			checkExisted($('#name_edit'),"${ctx}/common/check/media?name=");
			//uploadForm
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
		function media(target){
			updateId=target;
			$('div.validatebox-tip').remove();
			$('#mediaWin').window('open');
			$('#mediaWin').show();
			return false;
		}
		function edit(){}
		function select(){
			var m = $('#ttt').datagrid('getSelected');
			$('#'+updateId).val(m.url).removeClass('validatebox-invalid');
			$('#mediaWin').window('close');
			var picture='';
			$('input[id^=picture_]').each(function(i){
				if($(this).val()){
					picture+=$(this).val();
					if(i<5){
						picture+=';';
					}
				}
			});
			$('#picture').val(picture);
		}
		</script>
		<style>
		#editForm input {width: 180px;}
		</style>
	</head>
	<body>
		<div style="width: 100%;">
		<form:form modelAttribute="product" id="editForm" action="${ctx}/admin/product/save" method="post" cssStyle="padding:10px 20px;">
				<table>
					<tr>
						<td><form:label	for="title" path="title" cssClass="mustInput">标题：</form:label></td>
						<td><form:input path="title" id="title_edit" required="true" cssClass="easyui-validatebox"/></td>
						<td><form:label	for="top" path="top" cssClass="mustInput">置顶：</form:label></td>
						<td>
							<form:select path="top" id="top_edit" cssClass="easyui-validatebox easyui-combobox" required="true" cssStyle="width:187px">
								<form:option value="0">-</form:option>
								<form:option value="1">置顶</form:option>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><form:label	for="language" path="language" cssClass="mustInput">语言：</form:label></td>
						<td><form:input path="language" id="language_edit" required="true" cssStyle="width:187px"/></td>
						<td><form:label	for="catalogId" path="catalogId" cssClass="mustInput">车型：</form:label></td>
						<td><form:input path="catalogId" id="catalogId_edit" required="true" cssStyle="width:187px"/></td>
					</tr>
					<tr>
						<td><form:label	for="mainPic" path="mainPic" cssClass="mustInput">主图：</form:label></td>
						<td><form:input path="mainPic" id="mainPic_edit" required="true" class="easyui-validatebox" cssStyle="width:137px"/><span class="spanBtn" onclick="media('mainPic_edit')">浏览</button></td>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">缩略图：</form:label></td>
						<td><form:input path="thumb" id="thumb_edit" required="true" class="easyui-validatebox" cssStyle="width:137px"/><span class="spanBtn" onclick="media('thumb_edit')">浏览</button></td>
					</tr>
					
					<tr>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图1：</form:label></td>
						<td><input type="text" id="picture_1" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_1')">浏览</span></td>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图2：</form:label></td>
						<td><input type="text" id="picture_2" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_2')">浏览</span></td>
					</tr>
					<tr>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图3：</form:label></td>
						<td><input type="text" id="picture_3" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_3')">浏览</span></td>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图4：</form:label></td>
						<td><input type="text" id="picture_4" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_4')">浏览</span></td>
					</tr>
					<tr>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图5：</form:label></td>
						<td><input type="text" id="picture_5" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_5')">浏览</span></td>
						<td><form:label	for="thumb" path="thumb" cssClass="mustInput">截图6：</form:label></td>
						<td><input type="text" id="picture_6" required="true" class="easyui-validatebox" style="width:137px"/><span class="spanBtn" onclick="media('picture_6')">浏览</span></td>
					</tr>
					<tr>
						<td><form:label	for="status" path="status" cssClass="mustInput">状态：</form:label></td>
						<td><form:input path="status" id="status_edit" required="true" cssStyle="width:187px"/></td>
					</tr>
					<tr>
						<td><form:label path="content">内容：</form:label></td>
						<td colspan="3"><form:textarea path="content" id="content_edit" cssStyle="width:750px;height:30px;" maxLen="5000" msg="内容"/></td>
					</tr>
				</table>
				<form:hidden path="id"/>
				<form:hidden path="picture"/>
				<div style="text-align: center; padding: 5px;">
					<a href="javascript:history.go(-1)" class="easyui-linkbutton" id="back"
						iconCls="icon-back">返 回</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_submit_product"
						iconCls="icon-save">保 存</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="edit_reset"
						iconCls="icon-undo">重 置</a>
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
			fitColumns="true" pageList="[ 5, 10]">
				<thead>
					<tr>
						<th field="name" width="100" align="center">名称</th>
						<th field="url" width="100" align="center">媒体路径</th>
						<th field="type" width="100" align="center" formatter="typeFormatter">类别</th>
						<th field="status" width="60" align="center" formatter="statusFormatter">状态</th>
						<th field="remark" width="150" align="center">备注</th>
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