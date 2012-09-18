<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!-- 页脚 -->
<footer>
	<div class="ym-wrapper">
		<div class="ym-wbox">
			<p>
				<spring:message code="global.copyright" />
				<label>
				<a href="https://twitter.com/caritglobal" target="_about"
					class="twitter-follow-button" data-show-count="false"
					data-size="large" data-show-screen-name="false"><img
					src="/resources/public/images/twitter.png"
					alt="Follow @caritglobal" width="20" height="20" align="absmiddle"
					style="border: none;">
				</a>
				<a href="http://www.facebook.com/pages/%E4%B8%AD%E9%80%9A%E7%A6%8F%E7%91%9E/156934537779302" target="_about"><img src="/resources/public/images/facebook.png" alt="Find Me On Facebook" width="20" height="20" align="absmiddle"></a>
				</label>
			</p>
		</div>
	</div>
</footer>
<script type="text/javascript">
	!function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (!d.getElementById(id)) {
			js = d.createElement(s);
			js.id = id;
			js.src = "//platform.twitter.com/widgets.js";
			fjs.parentNode.insertBefore(js, fjs);
		}
	}(document, "script", "twitter-wjs");
</script>