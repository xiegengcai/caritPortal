var app={name:''};
var winTitle;
var catalogs;
var genderList=[{'code':0, 'value':'女'},{'code':1, 'value':'男'},{'code':2, 'value':'保密'}];
var statusList=[{'code':0, 'value':'停用'},{'code':1, 'value':'启用'}]
var languages; // 所有语言
var supportLanguages; // 已经支持的语言
// 扩展
$.extend($.fn.validatebox.defaults.rules, {  
	minLength:{validator: function(v, p){return getStrLen($.trim(v))>p[0];},message: '最少输入{0}个字符(一个中文两个字符)'},
	maxLength:{validator: function(v, p){return getStrLen($.trim(v))<p[0];},message: '最多输入{0}个字符(一个中文两个字符)'},
	CHS:{validator:function(v, p){return /^[\u0391-\uFFE5]+$/.test(v);},message:'请输入汉字'},
	ZIP:{validator:function(v, p){return /^[1-9]\d{5}$/.test(v);},message:'邮政编码不存在'},
	QQ:{validator:function(v, p){return /^[1-9]\d{4,10}$/.test(v);},message:'QQ号码不正确'},
	mobile:{validator:function(v, p){return /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/.test(v);},message:'手机号码不正确'},
	loginName:{validator:function(v, p) {return /^[\u0391-\uFFE5\w]+$/.test(v);},message:'登录名称只允许汉字、英文字母、数字及下划线。'},
	safepass:{validator:function(v, p) {return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(v));},message:'密码由字母和数字组成，至少6位'},
	equalTo:{validator:function(v, p){return v == $(p[0]).val();},message:'两次输入的字符不一至'},
	number:{validator:function(v, p) {return /^\d+$/.test(v);},message:'请输入数字'},
	gRemote:{validator:function(v,p){
		var data={};
		data[p[1]]=v;
		var _368=$.ajax({url:p[0],dataType:"json",data:data,async:false,cache:false,type:"get"}).responseText;
		return _368==0;
	},message:'重复！请修正此值'}
});
var KE=KindEditor;

var simpleEditer=function(name){
	return KE.create('textarea[name="'+name+'"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'link', 'unlink', 'image']
	});
};
$(function (){
	$.ajaxSettings.async=false;
	$.getJSON(app.name+'/back/catalogs', function(data) {
		if(data){
			catalogs=data;
		}
	});
	$.getJSON(app.name+'/back/config/languages', function(data) {
		if(data){
			languages=data;
		}
	});
	$.getJSON(app.name+'/back/query/support/languages',function(data){
		if(data){
			supportLanguages=data;
		}
	});
	$.ajaxSettings.async=true;
	winTitle=$('#editWin').window('options').title;
	// 初始化
	$('#tt').datagrid({
		width:'100%',
		method:'get',
		frozenColumns:[[{field:'ck',checkbox:true}]],
		toolbar:[{
			text:'新增',
			iconCls:'icon-add',
			handler:function() {
				$('#editWin').window({title:'新增'+winTitle,iconCls:'icon-add'});
				$('#editForm').form('clear');
				$('#editForm textarea').val('');
				$('#id').val('');
				$('#editWin').window('open');
				$('#editWin').show();
				$('.validatebox-tip').remove();
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
	$("#submit").bind("click", function(){
		//先取得 datagrid 的查询参数 
		var params = $('#tt').datagrid('options').queryParams;
		//自动序列化表单元素为JSON对象
        var fields =$('#searchForm').serializeArray();   
        $.each( fields, function(i, field){
            params[field.name] = field.value; //设置查询参数  
        });
        //设置好查询参数 reload 一下就可以了
        $('#tt').datagrid('reload'); 
	});
	$('#reset').bind('click',function(){ $('#searchForm').form('clear');});
	// edit form
	$('#edit_submit').bind('click',function(){
		$('#editForm').form({
			onSubmit:function(){
				// 避免 form validate bug
				$('.combobox-f').each(function(){
					$(this).val($(this).combobox('getText'));
				});
				var b=true;
				$('#editForm textarea').each(function(){
					if($.trim($(this).val()).length>$(this).attr('maxLen')){
						$.messager.alert('提示', $(this).attr('msg')+'超长，最多输入'+$(this).attr('maxLen')+'个字符(一个中文两个字符)', 'info');
						b=false;
						return false;
					}
				});
				if(!b){return b;}
				$('#editForm input[type=file]').each(function(){
					if($(this).val()){
						b=chkFileType($(this).val(),$(this).attr('fileType'));
						if(!b){
							$.messager.alert('提示', '请上传 '+$(this).attr('fileType')+' 类型的文件', 'info');
							b=false;
							return false;
						};
					}
				});
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
		        	$('#editWin').window('close');
		        	$('#tt').datagrid('reload');
				}else{
	    			$.messager.alert('异常', "后台系统异常", 'error');
				}
		    }
		}).submit();
	});
	$('#edit_reset').bind('click',function(){
		$('#editForm').form('clear');
	});
	$('#editWin').window({onClose:function(){
		$('div.validatebox-tip').remove();
	}});
});

function del(){
	var ids=getIds();
	if (ids) {
		$.messager.confirm('警告','删除同时会删除关联信息，您确认要删除吗?',function(data) {
			if (data) {
				var _url=$('#tt').attr('url');
				_url=_url.substring(0,_url.indexOf('query'))+'delete?ids='+ids;
				$.messager.progress({title:'请稍后',msg:'提交中...'});
				$.ajax({
					url : _url,
					type : 'GET',
					timeout : 1000,
					error : function() {
						$.messager.alert('错误','删除失败!','error');
					},
					success : function(data) {
						$.messager.progress('close');
						if (data == -1) {
							$.messager.alert('错误','删除失败!','error');
						} else if (data > 0) {
							$.messager.alert('成功','删除成功','info');
							// update rows
							$('#tt').datagrid('reload');
						} else {
							$.messager.alert('异常','后台系统异常','error');
						}
					}
				});
			}
		});
	} else {
		$.messager.show({
			title : '警告',
			msg : '请先选择要删除的记录。'
		});
	}
}

function getIds(){
	var ids=[];
	$.each($('#tt').datagrid("getSelections"),function(i,row){
		if(row.id>0){
			ids.push(row.id);
		}
	});
	return ids.join();
}

function statusFormatter(v){
	var result='-';
	$.each(statusList, function(key,val) {
		if(v==val.code){
			result=val.value;
			return false;
		}
	});
	return result;
}

function genderFormatter(v){
	var result='-';
	$.each(genderList, function(key,val) {
		if(v==val.code){
			result=val.value;
			return false;
		}
	});
	return result;
}

function lanFormatter(v){
	var result=v;
	$.each(languages, function(key,val) {
		if(v==val.code){
			result=val.value;
			return false;
		}
	});
	return result;
}

function catalogFormatter(v){
	var result='-';
	$.each(catalogs, function(key,val) {
		if(v==val.id){
			result=val.name;
			return false;
		}
	});
	return result;
}

function checkExisted(item,url){
	item.change(function(){
		if(item.val()!=''){
		$.getJSON(url+item.val(), function(data) {
			if(data>0){
				$.messager.alert('提示','重复记录，请修正!','info');
				item.val('').select();
			}
		});
		}
	});
}

//获得字符串长度
function getStrLen(str){
	if(str == null || str == ''){
		return 0;
	}
	var len = 0;
	var reg = new RegExp("^[\\u4e00-\\u9fa5]+$", "");
	for(var i=0;i<str.length;i++){
		if(reg.test(str.charAt(i))){//中文字符
			len=len+2;
		}else{
			len++;
		}
	}
	return len;
}
/**
 * 校验上传文件后缀类型是否匹配
 * @param name 文件名
 * @param types 允许的类型 jpg|png|apk "|"分隔
 */
function chkFileType(name,types){
	if(''==name){
		return true;
	}
	if(types==''){
		return true;
	}
	var tArray=types.split('|');
	var fArray=name.split('.');
	var suffix=fArray[fArray.length-1];
	for(var i in tArray){
		if(suffix.toLowerCase()==tArray[i].toLowerCase()){
			return true;
		}
	}
	return false;
}