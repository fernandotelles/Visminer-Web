<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.visminer.persistence.CommitterDAO" %>
<%-- <%@page import="org.visminer.model.business.Committer" %> --%>
<%@page import="org.visminer.model.database.Committer" %>
<%@page import="org.visminer.main.Visminer" %>
<%@page import="org.visminer.model.business.Repository"%>
<%@page import="org.visminer.model.business.File"%>
<%@page import="org.visminer.constant.RemoteServiceType"%>
<%@page import="org.visminer.constant.RepositoryType"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualização de arquivos</title>

<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/d3.v3.min.js"></script>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style>

#visu {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  margin: auto;
  position: relative;
}

form {
  position: absolute;
  right: 10px;
  top: 10px;
}

.node {
  border: solid 1px white;
  font: 10px sans-serif;
  line-height: 12px;
  overflow: hidden;
  position: absolute;
  text-indent: 2px;
}

.node:hover{
	border: 1px solid #00F;
}

.entity a:link{
	display: block;
	color: #FFF;
}

.entity a:hover{
	border: 1px solid #F00;
}


.tooltip{
	background: #eee;
	box-shadow: 0 0 5px #999999;
	color: #333;
	display: none; 
	font-size: 12px;
	left: 130px;
	padding: 10px;
	position: absolute;
	text-align: center;
	top: 95px;
	width: 80px;
	/*z-index: 10;*/   
}

/* Developers */

.dev{
	
}

.dev:hover{

}

.dev

#navtop{

	margin-bottom: 0px;
}

#jumbo{
	padding: 20px 30px 20px 30px;
}


/* Buttons */

.button_files{
	width: 30%;
	margin: 0 auto;
}

</style>

</head>

<body>

<div class="col-md-12">
	<nav class="navbar navbar-inverse collapse navbar-collapse" id="navtop" >
		<div class="container-fluid" >
			<div class="navbar-header">
				<a class="navbar-brand" href="#"> Visminer </a>
			</div>
			<div class="container">
				<ul class="nav navbar-nav">
					<li><a href="metric.do">Metric</a></li>
					<li><a href="issuesStatus.do">Issues Status</a></li>
					<li><a href="issuesQuantity.do">Issues Quantity</a></li>
					<li><a href="developerVis.do">Visualizations for developers</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
	<div class="jumbotron" id="jumbo">
		<div class="">
			<h2>Hello World!</h2>
			<p> Esta é uma demonstração da visualização para desenvolvedores</p>		
		</div>
	</div>
	
	<!-- Lista dos Desenvolvedores -->
	<div class="container col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Desenvolvedores
				</h3>
			</div>
			<div class="panel-body">
				<dl> 

					
				</dl>	
			</div>
			<div class="panel-footer">
				<p>Footer</p>
			</div>
		</div>
	</div>
	<!-- Visualização -->
	<div class="container col-md-6">
		<div  id="java_files" class="container col-md-12 arquivos">
			<h3>All Java Files and metrics Here</h3>
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"> 
						Lista todos os arquivos e suas respectivas metricas
					</h3>
				</div>
				<div id="visu" class="panel-body">
					<div class="container col-md-12">
						<table>
							<th>File</th>
							<th>Metrics</th>
							<c:forEach items="${files}" var="file">
								<tr>
									<td>${file.path}</td>
									<td>[LOC,NOC,NOM]</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
					
				<div class="panel-footer">
					<div class = "button_files">
						<input type="button" value="|<" name = "first"></input>
						<input type="button" value="<<" name = "previous"></input>
						<input type="button" value=">>" name = "next"></input>
						<input type="button" value=">|" name = "last"></input>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Outro Panel
				</h3>
			</div>
			<div class="panel-body">
				<p></p>
			</div>
			<div class="panel-footer">
				<p> Footer </p>
			</div>
		</div>
	</div>
</div>

</body>
</html>