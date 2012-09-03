package br.ucb.pojo;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table (name = "paciente")

public class Paciente {
	
	@IndexColumn(name = "idpaciente_UNIQUE")
	@Id
	@Column(name = "idpaciente", nullable = true)
	private Integer idPaciente;
	
	@Column(name = "nome", nullable = true)
	private String  nomePaciente;
	
	@Column(name = "idade", nullable = true)
	private Integer idade;
	
	@Column(name = "sexo", nullable = true)
	private String  sexo;
	
	@Column(name = "endereco", nullable = true)
	private String  endereco;
	
	public Paciente() {
		this.idPaciente = null;
		this.nomePaciente = null;
		this.idade = null;
		this.sexo = null;
		this.endereco = null;
	}
	
	
	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + ", nomePaciente="
				+ nomePaciente + ", idade=" + idade + ", sexo=" + sexo
				+ ", endereco=" + endereco + "]";
	}
	
	
	public Integer getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
}
