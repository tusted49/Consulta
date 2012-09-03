package br.ucb.pojo;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table (name = "medico")
public class Medico {
	
	@IndexColumn (name = "idMedico_UNIQUE")
	@Id
	@Column (name = "idmedico")
	private Integer idMedico;
	
	@Column (name = "nome", nullable = true)
	private String nome;
	
	
	public Medico() {
		this.idMedico = null;
		this.nome = null;
	}
	
	@Override
	public String toString() {
		return "Medico [idMedico=" + idMedico + ", nome=" + nome + "]";
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	
	

}
