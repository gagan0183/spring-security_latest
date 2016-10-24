<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="secp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a href="<spring:url value="/"/>" class="navbar-brand">Kevin's Auto Service Center</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="<spring:url value="/services/"/>">Services</a></li>
				<li><a href="<spring:url value="/appointments/"/>">Appointments</a></li>
				
				<secp:authorize access="authenticated" var="authenticate"></secp:authorize>
				<c:choose>
					<c:when test="${authenticate}">
						<li>
							<p class="navbar-text">
								Welcome
								<secp:authentication property="name"/>
								<a id="logout" href="#">Logout</a>
							</p>
							<form action="<c:url value = '/logout'></c:url>" method="post" id="logout-form">
								<secp:csrfInput/>
							</form>
						</li>
					</c:when>
					<c:otherwise>
						<li><a href="<spring:url value="/login/"/>">Sign In</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>