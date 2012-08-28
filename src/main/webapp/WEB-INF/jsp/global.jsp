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
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.global"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
							<div class="global-map">
							</div>
							<div class="ym-g50 ym-gl global-detail">
								<div class="ym-gl global-serial-number">1</div>
								<div class="ym-gr ym-g95 global-contact">
									<p><spring:message code="title.address"/><spring:message code="global.contact_us.address"/></p>
									<p><spring:message code="title.telephone"/><spring:message code="global.contact_us.telephone"/></p>
									<p><spring:message code="title.fax"/><spring:message code="global.contact_us.fax"/></p>
									<p><spring:message code="title.postcode"/><spring:message code="global.contact_us.postcode"/></p>
								</div>
							</div>
							<div class="ym-g50 ym-gl global-detail">
								<div class="global-serial-number">2</div>
								<div class="ym-gr ym-g95 global-contact">
									<p><spring:message code="title.address"/><spring:message code="global.contact_us.address"/></p>
									<p><spring:message code="title.telephone"/><spring:message code="global.contact_us.telephone"/></p>
									<p><spring:message code="title.fax"/><spring:message code="global.contact_us.fax"/></p>
									<p><spring:message code="title.postcode"/><spring:message code="global.contact_us.postcode"/></p>
								</div>
							</div>
							<div class="ym-g50 ym-gl global-detail">
								<div class="global-serial-number">3</div>
								<div class="ym-gr ym-g95 global-contact">
									<p><spring:message code="title.address"/><spring:message code="global.contact_us.address"/></p>
									<p><spring:message code="title.telephone"/><spring:message code="global.contact_us.telephone"/></p>
									<p><spring:message code="title.fax"/><spring:message code="global.contact_us.fax"/></p>
									<p><spring:message code="title.postcode"/><spring:message code="global.contact_us.postcode"/></p>
								</div>
							</div>
							<div class="ym-g50 ym-gl global-detail">
								<div class="global-serial-number">4</div>
								<div class="ym-gr ym-g95 global-contact">
									<p><spring:message code="title.address"/><spring:message code="global.contact_us.address"/></p>
									<p><spring:message code="title.telephone"/><spring:message code="global.contact_us.telephone"/></p>
									<p><spring:message code="title.fax"/><spring:message code="global.contact_us.fax"/></p>
									<p><spring:message code="title.postcode"/><spring:message code="global.contact_us.postcode"/></p>
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