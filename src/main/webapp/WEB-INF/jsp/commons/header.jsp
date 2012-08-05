<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!-- 页眉 -->
<ul class="ym-skiplinks">
	<li><a class="ym-skip" href="#nav">Skip to navigation (Press Enter)</a></li>
	<li><a class="ym-skip" href="#main">Skip to main content (Press Enter)</a></li>
</ul>
<header>
	<div class="ym-wrapper">
		<div class="ym-wbox">
			<h1>Project Name</h1>
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
				  <c:otherwise><li><a href="${menu.url}"><spring:message code="Menu.${menu.code}"/></a></li></c:otherwise>
				</c:choose>
			</c:forEach>
			</ul>
			<form class="ym-searchform">
				<input class="ym-searchfield" type="search" placeholder="Search..." />
				<input class="ym-searchbutton" type="submit" value="Search" />
			</form>
		</div>
	</div>
</nav>