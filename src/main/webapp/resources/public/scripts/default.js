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
$(function () {
    InitLeftMenu();
    $('body').layout();
})

function InitLeftMenu() {
    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).text();
        var url = $(this).attr("href");
        addTab(tabTitle, url);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
}

function addTab(subtitle, url) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        });
    } else {
        $('#tabs').tabs('select', subtitle);
    }
}

function createFrame(url) {
    var s = '<iframe name="mainFrame" scrolling="no" src="' + url + '" style="width:100%;height:100%;border:0;overflow-x:hidden"></iframe>';
    return s;
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