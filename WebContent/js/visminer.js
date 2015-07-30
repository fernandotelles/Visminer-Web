/* AJAX function to retrieve data*/


$.ajax({
	url: "getData.do",//e append a querystring para o objeto de procura;
	context: document.body,
	type: "GET",
	success: function(data){
		$(this).append(data);
	}
	
});


//Terminar a função de pegar os commits por committer
function getCommitsByCommitterID(id){
	//alert("Hello!"+ id.getAttribute('id'));
	var result;
	
	$.ajax({
		url: "getData.do",
		context: document.getElementById("hidden"),
		data: { 
			user: id.getAttribute("id")
		},
		type: "GET",
		success: function(returnSucess){
			changeColors(returnSucess);
		},
		error: function(returnError){
			//alert("Lol")
			console.log(returnError)
		}
	});
}

function changeColors(files){
	var index;
	files.trim();
	var names = files.split(",");
	
	for(index=0;index< names.length;index++){
		document.getElementById(names[i]).style.backgroundColor="#0F0";
	}
	//alert("this.grace()");
	
	//d3.select("names[i]")
}