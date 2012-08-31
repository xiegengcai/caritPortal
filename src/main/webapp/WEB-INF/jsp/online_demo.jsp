<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="5" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.online_demo"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid online-demo">
						<div class="ym-grid ym-gl">
							<object width="100%" height="100%" id="FPlayer"
								data="http://swf.ws.126.net/v/ljk/shareplayer/ShareFlvPlayer.swf"
								type="application/x-shockwave-flash">
								<param value="true" name="allowFullScreen">
								<param value="always" name="allowscriptaccess">
								<param value="http://swf.ws.126.net/v/ljk/shareplayer/ShareFlvPlayer.swf" allownetworking="all" name="movie">
								<param value="pltype=6&topicid=0085&vid=V89745ME0&sid=V5HPAEV5H&threadListPath=http://comment.v.163.com/videonews_bbs/89745ME0008535RB.html&advxml=http://v.163.com/special/008547FN/vo_zixun.xml" &coverpic=http://vimg1.ws.126.net/image/snapshot/2012/8/M/J/V89745GMJ.jpg" name="flashvars">
							</object>
						</div>
					</div>
				</section>
	
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
    </body>
</html>