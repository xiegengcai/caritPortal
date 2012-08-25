<title><spring:message code="global.title"/>--<spring:message code="global.subTitle"/></title>
	<meta charset="utf-8"/>
	<!-- Mobile viewport optimisation -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<%@ include file="/WEB-INF/jsp/commons/nocache.jsp"%>
	<meta name="keywords" content="<spring:message code="global.keywords"/>" />
	<meta name="description" content="<spring:message code="global.description"/>">
	<link rel="shortcut icon" href="${ctx }/resources/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${ctx }/resources/favicon.ico" type="image/x-icon">
	<link href="${ctx }/resources/public/styles/flexible-grids.css" rel="stylesheet" type="text/css"/>
	<!--[if lte IE 7]>
	<link href="${ctx }/resources/yaml/core/iehacks.css" rel="stylesheet" type="text/css" />
	<![endif]-->

	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${ctx}/resources/jquery-easyui-1.3/jquery-1.7.2.min.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/public/scripts/utils.js?v1.0" ></script>