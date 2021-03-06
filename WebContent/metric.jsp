<%@page import="org.visminer.web.servlet.IndexServlet"%>
<%@page import="org.visminer.web.main.Viz"%>
<%@page import="org.visminer.web.javascript.ExportToJavascript"%>
<%@page import="org.visminer.model.MetricValue"%>
<%@page import="java.util.Collections"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ExportToJavascript js = new ExportToJavascript();
PrintWriter writer = response.getWriter(); 
writer.println("<script type='text/javascript' src='js/jquery-2.1.1.min.js'></script>"); 
String charts = js.exportAllCharts((String)request.getAttribute("metricName").toString(),(String)request.getAttribute("metricDescription"),(String)request.getAttribute("values"),(String)request.getAttribute("greater"),(String)request.getAttribute("selectedChart"));
writer.println(charts);
%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualization</title>
<%@include file='stylesheet.jsp' %>
<%@include file='script.jsp' %>
<script type="text/javascript">
$(document).ready(function(){
	$("#chartSelect > option").each(function() {
	    if($(this).val() == '<%= request.getAttribute("selectedChart") %>'){
	    	$(this).prop("selected",true);
	    }; 
	});
	$("#relatedTo > option").each(function() {
	    if($(this).val() == '<%= request.getAttribute("relatedto") %>'){
	    	$(this).prop("selected",true);
	    }; 
	});
	$("#metric > option").each(function() {
	    if($(this).val() == '<%= request.getAttribute("metricName") %>'){
	    	$(this).prop("selected",true);
	    }; 
	});
});
</script>
<style>
	.node {
	  border: solid 1px white;
	  font: 10px sans-serif;
	  line-height: 12px;
	  overflow: hidden;
	  position: absolute;
	  text-indent: 2px;
	}
</style>
</head>
<body>
	<%@include file='top.jsp' %>
	<div class="container" id="content" style="margin-top: 80px;">
        <div class="row" id="top">
			<div class="col-md-12" style="margin-top: 0px;">
           		<section id="metrics" class="col-md-3">
					<div class="inputs">
						<h4>Related to:</h4>
						<select id="relatedTo" name="relatedto" onchange="change()">
							<option value="file">File</option>
							<option value="tag">Tag</option>
						</select>
						<h4>Graphics:</h4>
						<select id="chartSelect" name="graphic" onchange="change();">
							<option value="bubbleChart">Bubble Chart</option>
							<option value="histogramChart">Histogram Chart</option>
							<option value="treemapChart">Treemap Chart</option>
							<!--<option value="">-->
						</select>
					</div>
					<div>
						<div>
							<h4>List of implemented metrics:</h4>
						</div>
						<select id="metric" name="metric" onchange="change();">
							<c:forEach items="${metrics}" var="metric">
					        	<option value="${metric.name}">${metric.name} - ${metric.description}</option>
					    	</c:forEach>
						</select>
					</div>
				</section>
				<section id="chart" class="col-md-9"></section>
           </div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
</body>
</html>