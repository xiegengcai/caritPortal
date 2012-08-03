<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>中通福瑞应用市场——后台管理</title>
		<%@ include file="/WEB-INF/jsp/commons/easyui.jsp"%>
		<script type="text/javascript" src="${ctx}/resources/public/scripts/default.js" ></script>
		<script>
		var fieldList;
		$(function(){
			var p = $('body').layout('panel','west').panel({
				onCollapse:function(){
					//alert('collapse');
				}
			});
			setTimeout(function(){
				$('body').layout('collapse','east');
			},0);
			$('#logout').click(function(){
				location.href='${ctx}/back/logout';
			});
		});
		</script>
	</head>

	<body class="easyui-layout">
		<div region="north" border="false" style="height:50px;overflow:hidden;">
			<div style="padding:10px;text-align: right;">
				<span>${adminUser.nickName}，您上次登录时间是：${adminUser.lastLoginTime}，IP地址是：${adminUser.lastLoginIp}</span>
				<button id="logout">退 出</button>
			</div>
		</div>
		<div region="west" split="true" title="导航菜单" style="width: 180px;overflow:hidden;" icon="icon-redo">
	        <div id="menu" class="easyui-accordion" fit="true" border="false">
	            <div title="系统管理" style="overflow:auto;" icon="icon-edit">
                    <ul>
                        <li>
                             <div><a target="mainFrame" href="<c:url value="/admin/user"/>">用户管理</a></div>
                             <div><a target="mainFrame" href="<c:url value="/admin/app/catalog"/>">类别管理</a></div>
                        </li>
                    </ul>
	            </div>
	            <div title="产品管理" style="padding: 10px;" icon="icon-edit">
                    <ul>
                        <li>
                            <div>
                                <a target="mainFrame" href="#">产品管理</a></div>
                        </li>
                    </ul>
	            </div>
	            <div title="关于" icon="icon-help">
	                <h4>EntWebSite Ver 1.0</h4>
	            </div>
	        </div>
	    </div>
	    <!-- 
		<div region="east" split="true" title="East" style="width:180px;padding:10px;">east region</div>
	     -->
		<div region="center" id="mainPanle" style="background: #eee;overflow:hidden;">
	        <div id="tabs" class="easyui-tabs" fit="true" border="false">
	            <div title="我的主页" style="padding: 20px;" id="home">
	            </div>
	        </div>
    	</div>
		<div region="south" border="false" style="height:65px;background:#A9FACD;padding:5px 10px;">
			<h5>版权所有</h5>
		</div>
		<noscript>
	        <div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px;
	            width: 100%; background: white; text-align: center;">
	            <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	        </div>
	    </noscript>
	</body>
</html>
