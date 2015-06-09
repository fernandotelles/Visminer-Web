<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.visminer.model.business.Committer" %>
<%@page import="org.visminer.main.Visminer" %>
<%@page import="org.visminer.model.business.Repository"%>
<%@page import="org.visminer.constant.RemoteServiceType"%>
<%@page import="org.visminer.constant.RepositoryType"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualização Para Desenvolvedores</title>

<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/d3.v3.min.js"></script>
<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<style>

.chart div {
  font: 10px sans-serif;
  background-color: steelblue;
  text-align: right;
  padding: 3px;
  margin: 1px;
  color: white;
}

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
					<c:forEach items="${committers}" var="committer">
						<dd id="id_dev"class="dev">${committer.name}</dd>
					</c:forEach>
				</dl>
			</div>
			<div class="panel-footer">
				<p>Footer</p>
			</div>
		</div>
	</div>
	<!-- Visualização -->
	<div class="container col-md-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Visualização
				</h3>
			</div>
			<div id="visu" class="panel-body">
				<div class="container col-md-12">
					<div id="chart" class="col-md-12">
					</div>
				</div>
			</div>
			<!-- <div class="panel-footer">
				<p> footer </p>
			</div> -->
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
				<p>=D</p>
			</div>
			<div class="panel-footer">
				<p> Footer </p>
			</div>
		</div>
	</div>
	
</div>

<script>
var data;
var color = d3.scale.category20c();
var temp;

var width = document.getElementById('chart').offsetWidth;

d3.json("flare10.json",function(error,json){
	if(error) 
		return console.warn(error);
	data = json;
	
	values = d3.values(data);
	var maxsize = d3.max(values);
	//console.log(maxsize);
	var x = d3.scale.linear()
	.domain([0, maxsize])
	.range([0, width]);

	d3.select("#chart")
	.selectAll("div")
		.data(values)
	.enter().append("div")
		.style("width", function(d){ return x(d) + "px"; })
		.style("background", function(d){ return color(d) })
		.style("margin","0px 0px 1px 0px")
		.text(function(d){ return d; });
});


</script>

</body>
</html>