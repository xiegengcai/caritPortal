<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!-- 页眉 -->
<header>
	<div class="ym-wrapper">
		<div class="ym-wbox header-top">
			<img alt="" src="/resources/public/images/logo.png">
			<div class="top-right">
				<form class="ym-searchform">
				<c:if test="${language eq en}">
					<a href="https://twitter.com/caritglobal" class="twitter-follow-button" data-show-count="false" data-size="large" data-show-screen-name="false">Follow @caritglobal</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script></c:if><input class="ym-searchfield" type="search" placeholder="Search..." /><input class="ym-searchbutton" type="submit" value="" />
				</form>
				<div class="language">
				<label><spring:message code="title.language"/></label>
				<select id="language" onchange="location.href='${ctx}/'+this.value;">
					<c:forEach items="${supportLanguages}" var="lan">
					<option value="${lan.isoCode}" <c:if test="${language eq lan.isoCode}">selected="selected"</c:if>><spring:message code="language.support.${lan.isoCode}"/></option>
					</c:forEach>
				</select>
				</div>
			</div>
		</div>
	</div>
</header>
<nav id="nav">
	<div class="ym-wrapper">
		<div class="ym-hlist">
			<ul>
			
			<c:forEach items="${menuTree}" var="menu" varStatus="stat">
				<c:choose>
				  <c:when test="${param.menuIndex eq stat.count}"><li class="active"><strong><spring:message code="Menu.${menu.code}"/></strong></li></c:when>
				  <c:otherwise><li><a href="<c:if test="${menu.code == 'bbs'}">${menu.url}</c:if><c:if test="${menu.code != 'bbs'}">/${language}${menu.url}</c:if>"><spring:message code="Menu.${menu.code}"/></a></li></c:otherwise>
				</c:choose>
			</c:forEach>
			</ul>
			<!-- 
			<form class="ym-searchform">
				<input class="ym-searchfield" type="search" placeholder="Search..." />
				<input class="ym-searchbutton" type="submit" value="Search" />
			</form>
			 -->
		</div>
	</div>
</nav>