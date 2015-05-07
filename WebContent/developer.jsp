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
					<dd id="id_dev"class="dev">Developers here</dd>
					<dd id="id_dev"class="dev">Developers here1</dd>
					<c:forEach items="${sessionScope.committers}" var="committer">
						<dd id="id_dev"class="dev">${committer.name}</dd>
					</c:forEach>
					<dd>
						<% if (request.getAttribute("committers") == null)
								System.out.println("Nao Passou");
						else
							System.out.println(request.getAttribute("committers"));
						%>
					</dd>
					
				</dl>
				<ul>
					<c:forEach items="${committers}" var="committer">
						<li id="id_dev"class="dev">${committer.name}</li>
					</c:forEach>
				</ul>
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

/* Set margin, width and height of the visualization */
var margin = {top: 40, right: 10, bottom: 10, left: 10},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;
	
	var chart = document.getElementById("chart");
	var Awidth = chart.offsetWidth - margin.left - margin.right, 
		Aheight = chart.offsetWidth - margin.top - margin.bottom;
	
var color = d3.scale.category20c();

/*Choose the visualization's layout */
var treemap = d3.layout.treemap()
.size([Awidth, Aheight])
.value(function(d) { return d.value })
/*Se for testar com o arquivo flare.json, utilizar size. */


var div = d3.select("#chart").append("div")
    .style("position", "relative")
    .style("width", (Awidth + margin.left + margin.right) + "px")
    .style("height", (Aheight + margin.top + margin.bottom) + "px")
 /* .style("left", margin.left + "px")
    .style("top", margin.top + "px"); */

/* Tooltip */

var tooltip = d3.select("#chart").append("div")
	.attr("class","tooltip")
	.style("opacity",0);
    
/* Load external data */
d3.json("flare.json", function(error,root) {		
	var node = div.datum(root).selectAll(".node")
    .data(treemap.nodes)
  .enter().append("a")
  	.attr("class","entity")
  	.attr("href",function(d){return "/" + d.name + "/";})
  	.attr("id",function(d){return d.name})
  	.append("div")
    .attr("class", "node")
    .call(position)
    .style("background", function(d) { return d.children ? color(d.name) : null; })
    .text(function(d) { return d.children ? null : d.name; })
		.on("mouseover",function(d){
			tooltip.transition()
			.duration(300)
			.style("opacity",.9)
			 tooltip.html("Nome: "+ d.name + " Profundidade: "+ d.depth)  
                .style("left", d3.mouse + "px")     
                .style("top", (d3.mouse - 28) + "px");;
		})
		.on("mouseout",function(d){
			tooltip.transition()
			.duration(300)
			.style("opacity", 0);
		});
	
	// Criar aqui o zoom através do click na função
	d3.selectAll(".node").on("click", function(){
		//d3.behavior.zoom()
	})
	
	/* Transitions */
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