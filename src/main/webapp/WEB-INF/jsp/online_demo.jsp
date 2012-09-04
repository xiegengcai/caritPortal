<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
	   <script type="text/javascript" src="${ctx}/resources/public/flvPlayer/swfobject.js"></script>
	   <script type="text/javascript">
       $(function (){
    	   var s5 = new SWFObject("${ctx}/resources/public/flvPlayer/FlvPlayer201002.swf","playlist","100%","100%","7");
		   s5.addParam("allowfullscreen","true");
		   s5.addVariable("autostart","false");
		   //s5.addVariable("image","flashM-cebbank.jpg");
		   s5.addVariable("file",$('#flv_url').html());
			//s5.addVariable("width","400");
			//s5.addVariable("height","350");
		   s5.write("player");
       });
       </script>
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
						<div class="ym-grid ym-gl" id="player">
							
						</div>
					</div>
				</section>
	
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
    <div style="display: none;">
    <c:forEach items="${videoList}" var="v" begin="0" end="1">
    <span id="flv_url">${v.url}</span>
    </c:forEach>
    </div>
    </body>
</html>