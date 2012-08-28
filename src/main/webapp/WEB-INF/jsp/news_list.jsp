<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var baseUrl=app.name+'/common/query/news/${language}';
       var url;
       $(function (){
    	   $('#catalog-container li').each(function(i){
    		   $(this).click(function(){
	    		   $('#catalog-container li').removeClass('curr').eq(i).addClass('curr');
	    		   $('#catalog_title').html($(this).html());
	    		   url=baseUrl+'/'+this.id;
	    		   doPage(1);
    		   });
    	   });
    	   $('#catalog-container li:first').click();
       });
       
       function doPage(page){
    	   $.getJSON(url+'?page='+page+'&rows=100', function(data) {
				if(data.rows){
					var html='';
					$.each(data.rows, function(i,o){
						html+='<li><a href="#"><img src="'+o.thumb+'"/></a><a href="#"><span>'+o.title+'</span></a></li>';
					});
					$('#products-list').html(html);
					//pagination(page, data.totalPage);
				}
			});
       }
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
						<div class="ym-grid ym-gl">
							<div class="ym-grid">
								<div class="ym-g18 ym-gl catalog-container">
									<h3>
										<spring:message code="title.catalog" />
									</h3>
									<ul id="catalog-container">
										<li id="0"><spring:message code="title.company.news" /></li>
										<li id="1"><spring:message code="title.industry.news" /></li>
									</ul>
								</div>
								<div class="ym-g81 ym-gl products-list">
									<div>
										<h3 id="catalog_title"></h3>
										<ul id="products-list">
											<li><a href=""><img /></a><a href=""><span>dddddddddd</span></a></li>
											<li><a href=""><img /></a><a href=""><span>dddddddddd</span></a></li>
											<li><a href=""><img /></a><a href=""><span>dddddddddd</span></a></li>
											<li><a href=""><img /></a><a href=""><span>dddddddddd</span></a></li>
										</ul>
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