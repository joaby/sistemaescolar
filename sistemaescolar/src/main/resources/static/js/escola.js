/**
Sistema escolar
 **/

// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");
	
// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");
	
// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
	if (mySidebar.style.display === 'block') {
	   mySidebar.style.display = 'none';
	   overlayBg.style.display = "none";
    } else {
	   mySidebar.style.display = 'block';
	   overlayBg.style.display = "block";
	}
}
	
// Close the sidebar with the close button
function w3_close() {
	mySidebar.style.display = "none";
	overlayBg.style.display = "none";
}

function exibeAluno(event){
	var btn = event.target;
	var linha = btn.parentNode.parentNode.childNodes;
	var p = document.querySelectorAll("#infoAluno > p");
	var i;
	var resultado = [];
	for(i = 0; i < linha.length-6; i++){
		if(i % 2 == 1){
			resultado.push(linha[i].innerHTML);	
		}
	}
	for(i = 0; i < resultado.length; i++){
		p[i].innerHTML = resultado[i];
	}
	
	document.getElementById('modalExibicaoAluno').style.display='block'
}