package br.ucb.pojo;

import javax.persistence.*;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table (name = "consulta")
public class Consulta {
	
	@IndexColumn (name = "idconsulta_UNIQUE")
	@Id
	@Column (name = "idconsulta", nullable = true)
	private Integer  idConsulta;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpaciente", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Paciente idPaciente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmedico", insertable = true, updatable = true)
	@Fetch(FetchMode.JOIN)
	@Cascade(CascadeType.SAVE_UPDATE)
	private Medico 	 idMedico;
	
	@Column (name = "descricao", nullable = true)
	private String 	 descricao;
	
	public Consulta() {
		this.idConsulta = null;
		this.idPaciente = null;
		this.idMedico   = null;
		this.descricao  = null;
	}
	
	@Override
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + ", idPaciente="
				+ idPaciente + ", idMedico=" + idMedico + ", descricao="
				+ descricao + "]";
	}
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	public Paciente getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Paciente idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Medico getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Medico idMedico) {
		this.idMedico = idMedico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
