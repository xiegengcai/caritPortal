<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       $(function (){
    	   var imgs='${product.picture}'.split(';');
    	   $.each(imgs,function(){
    		   $('.product-img-list').append('<li><img src="'+this+'"/></li>');
    	   });
       });
       </script>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="4" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <a href="/${language}/products"><spring:message code="Menu.products"/></a> - ${product.title}</div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid product-container">
						<img class="product-img" alt="${product.title}" src="${product.thumb}"/>
						<div class="product-content">
							<h3><spring:message code="title.product.name"/>${product.title}</h3>
							${product.content}
						</div>
						<div class="ym-gl product-detail">
							<div class="ui-title"><spring:message code="title.ui.screenshot"/></div>
							<ul class="product-img-list">
							</ul>
						</div>
					</div>
				</section>
	
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
    </body>
</html>