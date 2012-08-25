var app={name:''};

/**
 * 生成分页导航
 * @param page 当前页
 * @param total 总页数
 */
function pagination(page,total){
	var html='';
	if(page!=1&&total>10){//当前页不是第一页时生成首页和上一页   
		html+='<span onclick="doPage(1)">首页</span>'
			+'<span onclick="doPage('+(page-1)+')">« 上一页</span>';
	}
	if(total<=10){ // 最多10页
		html+='<label class="cruLabel">';
		for(var i=1;i<=total;i++){
			if(i==page){
				html+='<span class="selected">'+i+'</span>';
			}else{
				html+='<span onclick="doPage('+i+')">'+i+'</span>';
			}
		}
		html+='</label>';
	} else {//多于10页
		var label=total%10;
		var curLabel=page%10;
		if(label==0){
			label=total/10;
		} else {
			label=total/10+1;
		}
		if(curLabel==0){
			curLabel=page/10;
		} else {
			curLabel=page/10+1;
		}
		for(var i=1;i<=label;i++){
			if(i==curLabel){
				html+='<label id="p_'+i+'" class="cruLabel">';
			}else{
				html+='<label id="p_'+i+'">';
			}
			for(var j=1;j<=10;j++){
				if(i>1){
					html+='<span onclick="doPageLabel("p_"'+(i-1)+')">«</span>'
				}
				if(j==page){
					html+='<span class="selected">'+j+'</span>';
				}else{
					html+='<span onclick="doPage('+j+')">'+j+'</span>';
				}
				if(i<label){
					html+='<span onclick="doPageLabel("p_"'+(i+1)+')">»</span>'
				}
			}
			html+='</label>';
		}
	}
	if(total>10&&page!=total){//当前页不是最后页时生成下一页 末页
		html+='<span onclick="doPage('+(page+1)+')">下一页 »</span>'
			+'<span onclick="doPage('+total+')">末页</span>'
	}
	$('#pageDiv').empty().html(html);
}
function doPageLabel(id){
	$('label[id^=p_]').hide();
	$('#'+id).show();
	$('#pageDiv label[id^=p_]').removeClass('cruLabel').addClass('hideLabel');  
    $('#'+target).removeClass('hideLabel').addClass('cruLabel');  
}

/**
 * 检测邮箱合法性
 * @param v
 * @returns
 */
function chkEmail(v) {
	if (v == null || $.trim(v)=='') {
		return false;
	}
	var pattern = /^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
	return pattern.test(v);
}
/**
 * 检查密码合法性
 * @param val
 * @returns
 */
function chkPwd(val){
	if(val==null || $.trim(val)=='') {
		return false;
	}
	var pwdRe=new RegExp('^[a-zA-Z0-9!@#$%^&*()_]{6,18}$');
	return pwdRe.test(val);
}

/**
 * 检查身份证号码，支持X
 * @param num
 * @returns {Boolean}
 */
function isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1);
	var error;
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		//error = "输入身份证号码长度不对！";
		//alert(error);
		//frmAddUser.txtIDCard.focus();
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			//error = "错误的身份证号码！.";
			//alert(error);
			//frmAddUser.txtIDCard.focus();
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		//check date
		var date8 = idNumber.substring(6, 14);
		if (checkDate(date8) == false) {
			//error = "身份证中日期信息不正确！.";
			//alert(error);
			return false;
		}
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = 12 - lngProduct % 11;
		switch (intCheckDigit) {
		case 10:
			intCheckDigit = 'X';
			break;
		case 11:
			intCheckDigit = 0;
			break;
		case 12:
			intCheckDigit = 1;
			break;
		}
		// check last digit
		if (varArray[17].toUpperCase() != intCheckDigit) {
			//error = "身份证效验位错误!正确为： " + intCheckDigit + ".";
			//alert(error);
			return false;
		}
	} else { //length is 15
	//check date
		var date6 = idNumber.substring(6, 12);
		if (checkDate(date6) == false) {
			//alert("身份证日期信息有误！.");
			return false;
		}
	}
	//alert ("Correct.");
	return true;
}

/**
 * 检查日期合法性， yyyyMMdd格式
 * @param date
 */
function checkDate(date){
	var today=new Date(); 
    var year=today.getFullYear();
    var month=today.getMonth()+1;
    var day=today.getDate();
    var _year=date.substring(0,4);
    var _month=date.substring(4,6);
    var _day=date.substring(6,8);
    if(_year<1900){return false;}
    if(_year<0){return false;}
    if(_month<0){return false;}
    if(_day<0){return false;}
	if(_year>year){
		return false;
	}else if(_year==year){
		if(_month>month){			
			return false;
		} else if(_month=month){
			if(_day>day){
				return false;
			}
		}
	}
	if(_day==29 && _month==2){
		 // 如果是2月份
	   if( (y / 100) * 100 == y && (y / 400) * 400 != y){
	     // 如果年份能被100整除但不能被400整除 (即闰年)
		   return true;
	   }else{
	     return false;
	   }
	}
	if(_day==30 && _month==2){
		return false;
	}
	if(_day==31){
		if(_month == 2 || _month == 4 || _month == 6 || _month == 9 || _month == 11){
		    // 2、4、6、9、11月没有31日
		   return false;
	    }
	}
	return true;
}

/**
 * 检查手机号码
 * @param s
 * @returns {Boolean}
 */
function chkMobile(s) {
	var regu = /^[1][3,5,8][0-9]{9}$/;
	var re = new RegExp(regu);
	return re.test(s);
}

/**
 * 验证中文
 * @param str
 * @returns {Boolean}
 */
function isCh(str){
	 // 正则表达式对象
	var re = new RegExp("^[\\u4e00-\\u9fa5]+$", "");
	 // 验证是否刚好匹配
	return re.test(str);
}

/**
 * 判断是否包含中文
 * @param str
 */
function hasCh(str){
	var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
	return patrn.exec(str);
}

//验证用户昵称，只能包含中文、字母
function valiNickname(nickname){
	var reg = new RegExp("[^a-zA-Z\u4E00-\u9FFF]");
	return !reg.test(nickname);
}
//获得字符串长度
function getStrLength(str){
	if(str == null){
		return 0;
	}
	var len = 0;
	var reg = /[a-zA-Z0-9]/;
	for(var i=0;i<str.length;i++){
		if(reg.test(str.charAt(i))){
			len++;
		}else{
			len = len+2;
		}
	}
	return len;
}
/**
 * 清除所有HTML标签
 * @param str
 * @returns
 */
function removeHTMLTag(str){
	var regEx=/<[^>]*>/g;
	return str.replace(regEx,'');
}
/**
 * 字符溢出处理
 * @param str
 * @param len
 * @returns
 */
function textOverflow(str, len){
	str=removeHTMLTag(str);
	if(str.length>len){
		return str.substring(0,len)+'...';
	}else{
		return str;
	}
}
/**
 * 分享到腾讯微博
 * @param _url 链接URL
 * @param _pic '图片url1|图片url2|图片url3....'
 * @param _t 内容
 */
function shareToTx(_url,_pic, _t){
	if(_url==null||_url==''){
		_url=document.location
	}
	_url = encodeURIComponent(_url);
	var _pic = encodeURI('');//（例如：var _pic='图片url1|图片url2|图片url3....）
	var metainfo = document.getElementsByTagName("meta");
	if(_t.length > 120){
		_t= _t.substr(0,117)+'...';
	}
	var _t = encodeURI(_t);
	var _u = 'http://share.v.t.qq.com/index.php?c=share&a=index&url='+_url+'&appkey=085b1bfa56e24008a8c7feba290e2324&pic='+_pic+'&assname=&title='+_t;
	window.open( _u,'', 'width=700, height=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' );
}
/**
 * 分享到QQ空间
 * @param _url
 * @param _desc
 * @param _title
 * @param _site
 * @param _pics
 */
function shareToQZone(_url, _desc, _title, _site, _pics){
	if(_url==null||_url==''){
		_url=document.location
	}
	var p = {
	url:_url,
	desc:_desc,/* 默认分享理由(可选) */
	title:_title,/* 分享标题(可选) */
	site:_site,/* 分享来源 如：腾讯网(可选) */
	pics:_pics /* 分享图片的路径(可选) */
	};
	var s = [];
	for(var i in p){
	s.push(i + '=' + encodeURIComponent(p[i]||''));
	}
	window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?'+s.join('&'));
}