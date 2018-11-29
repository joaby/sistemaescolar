/**
Sistema escolar
 **/

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