<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var baseUrl=app.name+'/common/query/news?language=${language}';
       var url;
       $(function (){
    	   $('#catalog-container li').each(function(i){
    		   $(this).click(function(){
	    		   $('#catalog-container li').removeClass('curr').eq(i).addClass('curr');
	    		   $('#catalog_title').html($(this).html());
	    		   url=baseUrl+'&type='+this.id;
	    		   doPage(1);
    		   });
    	   });
    	   if('${param.type}'){
    		   $('#catalog-container li').eq('${param.type}').click();
    	   }else{
	    	   $('#catalog-container li:first').click();
    	   }
       });
       
       function doPage(page){
    	   $.getJSON(url+'&page='+page+'&rows=10', function(data) {
				if(data.rows){
					$('#news-list').empty();
					$.each(data.rows, function(i,o){
						var $li=$('<li><span>'+o.createTime+'</span><label>'+o.title+'</label><a href="${ctx}/${language}/news/'+o.id+'"><spring:message code="title.read.more"/></a></li>').click(function(){
							location.href='${ctx}/'+o.language+'/news/'+o.id;
						});
						$('#news-list').append($li);
					});
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
									<h3><spring:message code="Menu.news"/></h3>
									<ul id="catalog-container">
										<li id=""><spring:message code="title.news.all" /></li>
										<li id="0"><spring:message code="title.news.company" /></li>
										<li id="1"><spring:message code="title.news.industry" /></li>
									</ul>
								</div>
								<div class="ym-g81 ym-gl news-list">
									<div>
										<h3 id="catalog_title"></h3>
										<ul id="news-list">
										</ul>
									</div>
									<div id="pageDiv" class="ym-g90 ym-gl"><label class="cruLabel"><span class="selected">1</span><span onclick="doPage(2)">2</span><span onclick="doPage(3)">3</span><span onclick="doPage(4)">4</span></label></div>
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