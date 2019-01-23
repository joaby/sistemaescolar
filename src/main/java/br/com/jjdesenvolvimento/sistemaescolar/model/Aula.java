package br.com.jjdesenvolvimento.sistemaescolar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Aula {
	
	@Id
	@GeneratedValue
	private Long id;
	private String assunto;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name="disciplina_id")
	private Disciplina disciplina;
	@OneToMany(mappedBy="aula")
	private List<PresencaAluno> presencaAlunos;

	public Aula() {
		this.disciplina = new Disciplina();
		this.presencaAlunos = new ArrayList<PresencaAluno>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<PresencaAluno> getPresencaAlunos() {
		return presencaAlunos;
	}

	public void setPresencaAlunos(List<PresencaAluno> presencaAlunos) {
		this.presencaAlunos = presencaAlunos;
	}
	
	

}
