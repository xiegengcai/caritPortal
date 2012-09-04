<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="7" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.contact_us"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<div class="ym-g45 ym-gl">
									<iframe width="425" height="450" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://ditu.google.cn/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=22.675426,114.006431&amp;aq=&amp;sll=35.86166,104.195397&amp;sspn=41.040725,86.572266&amp;brcurrent=3,0x34030a94dd8813e5:0x768320300ebc7a43,0,0x315285f132af5c3f:0x2ed41c6f09259f29%3B5,0,0&amp;ie=UTF8&amp;ll=22.675426,114.006431&amp;spn=0.005771,0.010568&amp;t=m&amp;z=14&amp;output=embed"></iframe><br /><small><a href="http://ditu.google.cn/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;q=22.675426,114.006431&amp;aq=&amp;sll=35.86166,104.195397&amp;sspn=41.040725,86.572266&amp;brcurrent=3,0x34030a94dd8813e5:0x768320300ebc7a43,0,0x315285f132af5c3f:0x2ed41c6f09259f29%3B5,0,0&amp;ie=UTF8&amp;ll=22.675426,114.006431&amp;spn=0.005771,0.010568&amp;t=m&amp;z=14" style="color:#0000FF;text-align:left">View Larger Map</a></small>
								</div>
								<div class="ym-g55 ym-gl">
									<div class="contact-us">
									<h3><spring:message code="title.contact_us"/></h3>
									<h4><spring:message code="title.address"/><spring:message code="global.contact_us.address"/></h4>
									<h4><spring:message code="title.telephone"/><spring:message code="global.contact_us.telephone"/></h4>
									<h4><spring:message code="title.fax"/><spring:message code="global.contact_us.fax"/></h4>
									<h4><spring:message code="title.postcode"/><spring:message code="global.contact_us.postcode"/></h4>
									</div>
								</div>
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