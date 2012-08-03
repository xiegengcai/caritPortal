<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp">
    		<jsp:param value="0" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid slides-container">图片轮播区</div>
				<section class="box info">
					<div class="ym-grid linearize-level-1">
						<div class="ym-g66 ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g50 ym-gl">
									<div class="ym-gbox-left">
										<h4>Here's a box</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.</p>
										<a class="ym-button ym-next" href="#">Read More</a> </div>
								</article>
								<article class="ym-g50 ym-gr">
									<div class="ym-gbox">
										<h4>And another box</h4>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.</p>
										<a class="ym-button ym-next" href="#">Read More</a> </div>
								</article>
							</div>
	
						</div>
						<article class="ym-g33 ym-gr">
							<div class="ym-gbox-right secondary">
								<h4>This box is aligned with the sidebar</h4>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip.</p>
							</div>
						</article>
					</div>
				</section>
	
				<section class="ym-grid linearize-level-1">
					<article class="ym-g66 ym-gl content">
						<div class="ym-gbox-left ym-clearfix">
							<img src="http://dummyimage.com/240x160/cccccc/fff.png" alt="" class="float-left bordered"/>
							<p>Lorem ipsum dolor sit amet, <em>consectetuer adipiscing elit</em>. Nunc congue ipsum vestibulum libero. Aenean vitae justo. Nam eget tellus. Etiam convallis, est eu lobortis mattis, lectus tellus tempus felis, a ultricies erat ipsum at metus.</p>
							<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi et risus. Aliquam nisl. Nulla facilisi. Cras accumsan vestibulum ante. Vestibulum sed tortor. Praesent SMALL CAPS tempus fringilla elit. Ut elit diam, sagittis in, nonummy in, gravida non, nunc. Ut orci. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Nam egestas, orci eu imperdiet malesuada, nisl purus fringilla odio, quis commodo est orci vitae justo. Aliquam placerat odio tincidunt nulla. Cras in libero. Aenean rutrum, magna non tristique posuere, erat odio eleifend nisl, non convallis est tortor blandit ligula. Nulla id augue.</p>
							<p>Nullam mattis, odio ut tempus facilisis, metus nisl facilisis metus, auctor consectetuer felis ligula nec mauris. Vestibulum odio erat, fermentum at, commodo vitae, ultrices et, urna. Mauris vulputate, mi pulvinar sagittis condimentum, sem nulla aliquam velit, sed imperdiet mi purus eu magna. Nulla varius metus ut eros. Aenean aliquet magna eget orci. Class aptent taciti sociosqu ad litora.</p>
							<p>Vivamus euismod. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Suspendisse vel nibh ut turpis dictum sagittis. Aliquam vel velit a elit auctor sollicitudin. Nam vel dui vel neque lacinia pretium. Quisque nunc erat, venenatis id, volutpat ut, scelerisque sed, diam. Mauris ante. Pellentesque habitant   morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec mattis. Morbi dignissim sollicitudin libero. Nulla lorem.</p>
							<blockquote>
								<p>Integer cursus ornare mauris. Praesent nisl arcu, imperdiet eu, ornare id, scelerisque ut, nunc. Praesent sagittis erat sed velit tempus imperdiet. Ut tristique, ante in interdum hendrerit, erat enim faucibus felis, quis rutrum mauris lorem quis sem. Vestibulum ligula nisi, mattis nec, posuere et, blandit eu, ligula. Nam suscipit placerat odio. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Pellentesque tortor libero, venenatis vitae, rhoncus eu, placerat ut, mi. Nulla nulla.</p>
							</blockquote>
							<p>Maecenas vel metus quis magna pharetra fermentum. <em>Integer sit amet tortor</em>. Maecenas porttitor, pede sed gravida auctor, nulla augue aliquet elit, at pretium urna orci ut metus. Aliquam in dolor. Vestibulum ante ipsum   primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed aliquam, tellus id ornare posuere, quam nunc accumsan turpis, at   convallis tellus orci et nisl. Phasellus congue neque a lorem.</p>
							<div class="ym-grid linearize-level-2">
								<div class="ym-g50 ym-gl">
									<div class="ym-gbox-left">
										<!-- content -->
										<h6>This is a nested column</h6>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
									</div>
								</div>
								<div class="ym-g50 ym-gr">
									<div class="ym-gbox-right">
										<!-- content -->
										<h6>This is another nested column </h6>
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip  ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
									</div>
								</div>
							</div>
						</div>
					</article>
					<aside class="ym-g33 ym-gr">
						<div class="ym-gbox-right ym-clearfix">
							<h3>A Simple Sidebar </h3>
							<p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices  posuere cubilia Curae; Cras ornare mattis nunc. Mauris venenatis, pede  sed aliquet vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue.</p>
							<p>Mauris a lectus. Aliquam erat volutpat. Phasellus ultrices mi a sapien. Nunc rutrum egestas lorem. Duis ac sem sagittis elit tincidunt gravida. Mauris a lectus. Aliquam erat volutpat. Phasellus ultrices mi a sapien. Nunc rutrum egestas lorem. Duis ac sem sagittis elit tincidunt gravida.</p>
							<p class="box info">Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras ornare mattis nunc. Mauris venenatis, pede sed aliquet  vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue.</p>
							<h3>Incremental leading</h3>
							<p>Vestibulum ante ipsum  primis in faucibus orci luctus vestibulum ante ipsum primis in faucibus  orci luctus et ultrices posuere cubilia Curae; Cras ornare mattis nunc. Mauris venenatis, pede sed aliquet vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue. sed aliquet vehicula, lectus  tellus.</p>
							<p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras ornare  mattis nunc. Mauris venenatis, pede sed aliquet vehicula, lectus tellus  pulvinar neque, non cursus sem nisi vel augue. sed aliquet vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue. ipsum  primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras  ornare mattis nunc. Mauris venenatis, pede sed aliquet vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue. sed aliquet  vehicula, lectus tellus pulvinar neque, non cursus sem nisi vel augue</p>
						</div>
					</aside>
				</section>
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
    </body>
</html>
