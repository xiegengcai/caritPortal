<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var maxLen=210;
       var t = n = 0, count,timeout=3000;
       $(function (){
    	   $('.content').each(function(){
    		   <c:if test="${language eq 'cn' || language eq 'tw'}">
    		   maxLen=110;
    		   </c:if>
    		   $(this).html(textOverflow($(this).html(),maxLen));
    	   });
    	   count=$("#banner_list a").length;
    	   $("#banner_list a:not(:first-child)").hide();
    	   //$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
    	   //$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")});
    	   $("#banner li").click(function() {
    		   //var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4
    		   var i = $(this).val() - 1;//获取Li元素内的值，即1，2，3，4
    		   n = i;
    		   if (i >= count) return;
    		   $("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
    		   $("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")})
    		   $("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
    		   $('#banner').css('background','');
    		   $(this).toggleClass("on");
    		   $(this).siblings().removeAttr("class");
    	   }).css('width',$('#banner').width()/count-2);
    	   t = setInterval("showAuto()", timeout);
    	   $("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", timeout);});
       });
       function showAuto(){
    	   n = n >=(count - 1) ? 0 : ++n;
    	   $("#banner li").eq(n).trigger('click');
   	   }
       </script>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="1" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid" id="banner">
				   <div id="banner_list">
				        <c:forEach items="${bannerAdList}" var="ad" begin="0" end="4">
				        <a href="${ad.href}" target="_blank"><img src="${ad.image}" title="${ad.name}" alt="${ad.name}" /></a>
				        </c:forEach>
					</div>
				    <ul id="banner-number">
				        <c:forEach items="${bannerAdList}" var="ad" begin="0" end="4" varStatus="index">
				         <li <c:if test="${index.count eq 1}">class="on"</c:if> value="${index.count}"><img alt="${ad.name}" src="${ad.thumb}"></li>
				        </c:forEach>
				    </ul>
				</div>
				<section class="box news-box">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g52 ym-gl">
									<div class="ym-gbox-left">
										<h4 class="newsTitle"><spring:message code="title.latest.news"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${lastestNews}" var="news" varStatus="stat">
											<li><a href="/${news.language}/news/${news.id}">${news.title}</a><div class="content">${news.content}</div></li>
										</c:forEach>
										</ul>
										<a class="ym-button ym-next" href="${ctx}/${language}/news_list"><spring:message code="botton.read.more"/></a> </div>
								</article>
								<article class="ym-g45 ym-gl">
									<div class="ym-gbox">
										<h4 class="newsTitle"><spring:message code="title.hot.products"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${products}" var="p" varStatus="stat" begin="0" end="1">
											<li><a href="${ctx}/${p.language}/product/${p.id}">${p.title}</a><div class="content">${p.content}</div><div class="thumb"><img src="${p.thumb}"></div></li>
										</c:forEach>
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