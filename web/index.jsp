
<%@page import="org.cagnosolutions.module.util.DetectMobile"%>

<%
    DetectMobile detector = new DetectMobile(request.getHeader("User-Agent"), request.getHeader("Accept"));

    if (detector.detectMobileQuick()) {
        response.sendRedirect("mobile-index.jsf");
    } else {
        response.sendRedirect("index.jsf");
    }
%>