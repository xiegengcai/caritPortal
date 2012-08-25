<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="6" name="menuIndex"/>
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
									<img class="address-map" alt="" src="${ctx}/resources/public/images/map.png" />
								</div>
								<div class="ym-g55 ym-gl">
									<div class="contact-us">
									<h3><spring:message code="title.contact.us"/></h3>
									<h4><spring:message code="title.address"/><spring:message code="global.address"/></h4>
									<h4><spring:message code="title.telephone"/><spring:message code="global.telephone"/></h4>
									<h4><spring:message code="title.fax"/><spring:message code="global.fax"/></h4>
									<h4><spring:message code="title.postcode"/><spring:message code="global.postcode"/></h4>
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