<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var baseUrl=app.name+'/common/query/product/${language}';
       var url;
       $(function (){
    	   $('#catalog-container li').each(function(i){
    		   $(this).click(function(){
	    		   $('#catalog-container li').removeClass('curr').eq(i).addClass('curr');
	    		   $('#catalog_title').html($(this).html());
	    		   url=baseUrl+'?catalogId='+this.id;
	    		   doPage(1);
    		   });
    	   });
    	   $('#catalog-container li:first').click();
       });
       
       function doPage(page){
    	   $.getJSON(url+'&page='+page+'&rows=12', function(data) {
				if(data.rows){
					var html='';
					$.each(data.rows, function(i,o){
						html+='<li><a href="/${language}/product/'+o.id+'"><img src="'+o.thumb+'"/></a><a href="/${language}/'+o.id+'"><span>'+o.title+'</span></a></li>';
					});
					$('#products-list').html(html);
					if(data.totalPage>1){
						pagination(page, data.totalPage);
					} else {
						$('#pageDiv').empty();
					}
				}
			});
       }
       </script>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="4" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.products"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid">
						<div class="ym-grid ym-gl">
							<div class="ym-grid">
								<div class="ym-g18 ym-gl catalog-container">
									<h3>
										<spring:message code="title.catalog" />
									</h3>
									<ul id="catalog-container">
										<li id=""><spring:message code="Catalog.all" /></li>
										<c:forEach items="${catalogList}" var="catalog">
											<li id="${catalog.id}"><spring:message code="Catalog.${catalog.catalogCode}" /></li>
										</c:forEach>
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
									<div id="pageDiv" class="ym-g90 ym-gl"></div>
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