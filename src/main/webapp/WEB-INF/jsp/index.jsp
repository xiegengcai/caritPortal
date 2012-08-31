<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
       <script type="text/javascript">
       var maxLen=210;
       var t = n = 0, count;
       $(function (){
    	   $('.content').each(function(){
    		   <c:if test="${language eq 'cn' || language eq 'tw'}">
    		   maxLen=110;
    		   </c:if>
    		   $(this).html(textOverflow($(this).html(),maxLen));
    	   });
    	   count=$("#banner_list a").length;
    	   $("#banner_list a:not(:first-child)").hide();
    	   $("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt'));
    	   $("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")});
    	   $("#banner li").click(function() {
    		   var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4
    		   n = i;
    		   if (i >= count) return;
    		   $("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt'));
    		   $("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")})
    		   $("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000);
    		   $('#banner').css('background','');
    		   $(this).toggleClass("on");
    		   $(this).siblings().removeAttr("class");
    	   });
    	   t = setInterval("showAuto()", 3000);
    	   $("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 4000);});
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
					<div id="banner_bg"></div>  <!--标题背景-->
					<div id="banner_info"></div> <!--标题-->
				    <ul>
				        <li class="on">1</li>
				        <li>2</li>
				        <li>3</li>
				        <li>4</li>
				    </ul>
				   <div id="banner_list">
				        <c:forEach items="${topImages}" var="img" begin="0" end="4">
				        <a href="${img.href}" target="_blank"><img src="${img.url}" title="${img.name}" alt="${img.name}" /></a>
				        </c:forEach>
					</div>
				</div>
				<section class="box news-box">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g55 ym-gl">
									<div class="ym-gbox-left">
										<h4 class="newsTitle"><spring:message code="title.latest.news"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${lastestNews}" var="news" varStatus="stat">
											<li><a href="#">${news.title}</a><div class="content">${news.content}</div></li>
										</c:forEach>
										</ul>
										<a class="ym-button ym-next" href="${ctx}/${language}/news_list"><spring:message code="botton.read.more"/></a> </div>
								</article>
								<article class="ym-g45 ym-gl">
									<div class="ym-gbox">
										<h4 class="newsTitle"><spring:message code="title.hot.products"/></h4>
										<ul class="indexNewsContainer">
										<c:forEach items="${products}" var="p" varStatus="stat" begin="0" end="1">
											<li><a href="${ctx}/${language}/product/${p.id}">${p.title}</a><div class="content">${p.content}</div><img class="thumb" src="${p.thumb}"></li>
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