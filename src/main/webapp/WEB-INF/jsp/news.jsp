<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       $(function (){
    	   $('#catalog-container li').each(function(i){
    		   $(this).click(function(){
	    		   location.href='/${language}/news_list?type='+this.id;
    		   });
    	   });
       });
       </script>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="3" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.news"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid">
								<div class="ym-g18 ym-gl catalog-container">
									<h3><spring:message code="Menu.news"/></h3>
									<ul id="catalog-container">
										<li id=""><spring:message code="title.news.all" /></li>
										<li id="0"><spring:message code="title.news.company" /></li>
										<li id="1"><spring:message code="title.news.industry" /></li>
									</ul>
								</div>
								<div class="ym-g81 ym-gl news-list">
									<h3 id="catalog_title" style="text-align: center;">${news.title}</h3>
									<div class="ym-grid ym-gl date">
									<spring:message code="title.news.date"/><fmt:formatDate value="${news.createTime}" type="both" pattern="yyyy-MM-dd"/>
									</div>
									<div class="content">
										${news.content}
									</div>
									<div class="ym-grid ym-gl prev-next">
									<c:if test="${not empty prevNews}">
									<label><spring:message code="title.prev"/><a href="/${language}/news/${prevNews.id}">${prevNews.title}</a></label>
									</c:if>
									<c:if test="${not empty nextNews}">
									<label><spring:message code="title.next"/><a href="/${language}/news/${nextNews.id}">${nextNews.title}</a></label>
									</c:if>
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