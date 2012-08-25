<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
    <head>
       <%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
    </head>
    <body>
    	<jsp:include page="/WEB-INF/jsp/commons/header.jsp" flush="true">
    		<jsp:param value="2" name="menuIndex"/>
    	</jsp:include>
        <div id="main">
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<div class="ym-grid current-position"><a href="/${language}"><spring:message code="Menu.home"/></a> - <spring:message code="Menu.about_us"/></div>
				<section class="box" style="margin: 0 auto">
					<div class="ym-grid linearize-level-1">
						<div class="ym-grid ym-gl">
	
							<div class="ym-grid linearize-level-2">
								<article class="ym-g55 ym-gl">
									<p>
									CARIT Electronics Technology Co., Ltd. was established in early 2010,which is a dynamic, young company, mainly engaged in car DVD, GPS navigation,  digital TV in car, Car PC and other  car multimedia product development, manufacturing sales and service of modern high-tech enterprise, the company has strong R & D team and sales team, who has rich in Win CE, LINUX and Android, DSP systems development experience, adhering to the "innovation, standardization, efficiency, progress and harmony" purpose, to become a global high-end automotive supplier of intelligent multimedia products, we maintain good working cooperation with FREESCALE, SAMSUNG, ZORAN, ST, TI, FAIRCHILD, DVS and other suppliers. Currently, company provides complete sophisticated automotive electronics/GPS navigation/digital product solutions to many manufacturing companies, the design of products with high quality, innovative design, characterized by won customers’ trust.
									</p>
									<h3>R&D Power</h3>
									<p>
									There are nearly 60 staffs in CARIT, including more than 30 R & D team. With strong R & D capabilities and deep accumulation of automotive multimedia industry, CARIT builds strategic cooperative partnership with Freescale of United States, dedicated to the smart car multimedia product development.
									</p>
									<p>CARIT smart car multimedia information system used Android and Win CE software platform ,FREESCALE CONTEX A8 core I.MX51, I.MX53 and I.MX61 hardware platform, STM8 MCU and UBLOX GPS module, program performance is stability and system integration is high which is leading the industry trend .</p>
									<p>3G internet access is fast and smooth, 3D photo navigation is convenient and accurate, voice recognition and Bluetooth function is novel and unique, media player supporting RMVB, DIVX, XVID MP4 H.264 and other audio and video formats, progressive scan flowing freely, the maximum resolution up to 1024*768dpi, 5.1 channel audio output which can be achieved by monitoring quality effects, user interface is realistic and novel with rich colors. Selection of all the chips used cars regulatory standards, product quality has reached the level of the depot before loading.</p>
								</article>
								<article class="ym-g45 ym-gl">
									
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