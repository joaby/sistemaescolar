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

//codigo jquery
$(document).ready(function(){
	//deleta profrofessor de uma disciplina
	$('.btn-deleta-prof').click(function(event){
		event.preventDefault();
		
		var botao = $(event.currentTarget);
		var urlDeleta = botao.attr('href');
		
		var response = $.ajax({
			url : urlDeleta,
			type : 'DELETE'
		});
		
		response.done(function(e){
			botao.parent().parent().remove();
		});
		
		response.fail(function(e){
			alert('Erro ao deletar professor!');
		});
	});
	
	//concatena usuario e tipo no login
	$("#btn-entrar").click(function(event){
		var usuario = $("#usuario").val();
		var tipo = $("#tipo").val();
		$("#usuario").val(usuario+":"+tipo);
	});
	
	//excluir escola
	$('.btn-excluir-escola').click(function(event){
		var botao = $(this);
		var nome = botao.data('nome');
		var id = botao.data('id');
		console.log(id +":"+ nome);
		var modal = $('#modal-exclusao');
		modal.show();
		var form = modal.find('form');
		form.attr('action', '/escola'+"/"+id);
		modal.find('p span').text(nome);
		
	});
	
});