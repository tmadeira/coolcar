<%@page import="java.util.ArrayList"%>
<%@page import="coolcar.managers.ModelosManager,coolcar.modelos.Modelo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:include page="header.jsp" />

<div class="container">

	<%
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().equals("usernameCoolCar"))
		            userName = cookie.getValue();
		    }
		}
		if (userName == null)
		    response.sendRedirect("index.jsp");
	%>
	<h3>
	    Hi <%=userName%>, Login successful.
	</h3>
	<br>

</div>

<jsp:include page="footer.jsp" />