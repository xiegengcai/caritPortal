<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var maxLen=210;
       $(function (){
    	   $('.content').each(function(){
    		   <c:if test="${language eq 'cn' || language eq 'tw'}">
    		   maxLen=110;
    		   </c:if>
    		   $(this).html(textOverflow($(this).html(),maxLen));
    	   });
       });
       </script>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="1" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid slides-container">图片轮播区</div>
				<section class="box">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g55 ym-gl">
									<div class="ym-gbox-left">
										<h4 class="newsTitle"><spring:message code="title.latest.news"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${lastestNews}" var="news" varStatus="stat">
											<li><a href="#">${news.title}</a>
											<div class="content">
											${news.content}
											</div></li>
										</c:forEach>
										</ul>
										<a class="ym-button ym-next" href="#"><spring:message code="botton.read.more"/></a> </div>
								</article>
								<article class="ym-g45 ym-gl">
									<div class="ym-gbox">
										<h4 class="newsTitle"><spring:message code="title.hot.products"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${companyNewsList}" var="news" varStatus="stat">
											<li><a href="#">${news.title}</a></li>
										</c:forEach>
										</ul>
										<a class="ym-button ym-next" href="#"><spring:message code="botton.read.more"/></a> </div>
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