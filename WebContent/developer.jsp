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
<script src="js/visminer.js"></script>

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
	text-decoration: underline;
	color: black;
	font-size: 18px;
	cursor: pointer;
}

.dev{
	text-transform: capitalize;
}

dd{
	height: 26px;
}

#navtop{

	margin-bottom: 0px;
}

#jumbo{
	padding: 10px 20px;
	margin: 0px 0px 20px 0px;
}


#devPanel{
	padding: 0px;
}

#devPanel-body{
	overflow: scroll;
}

#visPanel{
	padding: 0px;
}

#visPanel-body{

}

#statsPanel{
	visibility: hidden;
	display: none;
}

.panel-pin{
	position: relative;
	height: 1px;
	
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
	<div id="devPanel" class="container col-md-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Desenvolvedores
				</h3>
			</div>
			<div id="devPanel-body" class="panel-body panel-pin">
				<dl> 
					<c:forEach items="${committers}" var="committer">
						<dd id="${committer.name}"class="dev" onclick="getCommitsByCommitterID(this)">${committer.name}</dd>
					</c:forEach>
				</dl>
			</div>
			<!-- <div class="panel-footer">
				<p>Footer</p>
			</div> -->
		</div>
	</div>
	<!-- Visualização -->
	<div class="container col-md-10">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Visualização
				</h3>
			</div>
			<div id="visu" class="panel-body panel-pin">
				
			</div>
			<!-- <div class="panel-footer">
				<p> footer </p>
			</div> -->
		</div>
	</div>
	<div id="hidden"></div>
	<!-- Painel de estatisticas -->
	<div id="statsPanel" class="container col-md-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title"> 
					Outro Panel
				</h3>
			</div>
			<div class="panel-body panel-pin ">
				<p>=D</p>
			</div>
			<!-- <div class="panel-footer">
				<p> Footer </p>
			</div> -->
		</div>
	</div>
	
</div>

<script>
/* var data;
var color = d3.scale.category20c();
var temp;

var width = document.getElementById('chart').offsetWidth;
 */

 
/* var margin = {top: 40, right: 10, bottom: 10, left: 10},
width = 960 - margin.left - margin.right,
height = 500 - margin.top - margin.bottom; */

var altura = window.innerHeight - document.getElementById("navtop").offsetHeight - 
document.getElementById("jumbo").offsetHeight - document.getElementsByClassName("panel-heading")[0].offsetHeight - 
45;

//document.getElementById("visu").style.height = altura;
var panels = document.getElementsByClassName("panel-pin");

var i;

for(i = 0; i < panels.length; i++){
	panels[i].style.height = altura +"px";
}

var largura = document.getElementById("visu").offsetWidth;
 
var color = d3.scale.category20c();

var treemap = d3.layout.treemap()
.size([largura - 30, altura -30])
.sticky(true)
.value(function(d) { return d.size; });

var div = d3.select("#visu").append("div")
.style("position", "relative")
.style("width", largura + "px")
.style("height", altura + "px")
.attr("class","container col-md-12");
//.style("left", margin.left + "px")
//.style("top", margin.top + "px");

d3.json("flare.json", function(error, root) {
if (error) throw error;

var node = div.datum(root).selectAll(".node")
  .data(treemap.nodes)
.enter().append("div")
  .attr("class", "node")
  .attr("id",function(d){return d.children ? null : d.name;})
  .call(position)
  .style("background", function(d) { return d.children ? color(d.name) : null; })
  .style("cursor","pointer")
  .text(function(d) { return d.children ? null : d.name; });

d3.selectAll("input").on("change", function change() {
var value = this.value === "count"
    ? function() { return 1; }
    : function(d) { return d.size; };

node
    .data(treemap.value(value).nodes)
  .transition()
    .duration(1500)
    .call(position);
});
});

function position() {
this.style("left", function(d) { return d.x + "px"; })
  .style("top", function(d) { return d.y + "px"; })
  .style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
  .style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
}

</script>

</body>
</html>