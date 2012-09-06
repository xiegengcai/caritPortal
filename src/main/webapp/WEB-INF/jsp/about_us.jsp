<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="2" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.about_us"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g52 ym-gl">
									<p><spring:message code="global.about_us"/></p>
									<h3><spring:message code="global.about_us.rd.title"/></h3>
									<p><spring:message code="global.about_us.rd.content.1"/></p>
									<p><spring:message code="global.about_us.rd.content.2"/></p>
									<p><spring:message code="global.about_us.rd.content.3"/></p>
								</article>
								<article class="ym-g45 ym-gl">
									<ul id="about-us-img">
									<li><img src="/resources/public/images/about_us_1.png"/></li>
									<li><img src="/resources/public/images/about_us_2.png"/></li>
									<li><img src="/resources/public/images/about_us_3.png"/></li>
									<li><img src="/resources/public/images/about_us_4.png"/></li>
									<li class="last"><img src="/resources/public/images/about_us_5.png"/></li>
									</ul>
								</article>
							</div>
	
						</div>
					</div>
				</section>
	
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
    </body>
</html>