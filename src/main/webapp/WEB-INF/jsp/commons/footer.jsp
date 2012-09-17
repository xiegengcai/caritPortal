<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!-- 页脚 -->
<footer>
	<div class="ym-wrapper">
		<div class="ym-wbox">
			<div class="twitter">
				<span>
					<a href="https://twitter.com/caritglobal" target="_about"
						class="twitter-follow-button" data-show-count="false"
						data-size="large" data-show-screen-name="false"><img
						src="/resources/public/images/twitter.png"
						alt="Follow @caritglobal" width="20" height="20" align="absmiddle"
						style="border: none;">&nbsp;&nbsp;@caritglobal</a>
				</span>
			</div>
			<p><spring:message code="global.copyright"/></p>
		</div>
	</div>
</footer>
<script type="text/javascript">
!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
</script>