package br.ucb.Controller;

import br.ucb.hibernate.ConsultaHibernate;
import br.ucb.hibernate.MedicoHibernate;
import br.ucb.hibernate.PacienteHibernate;
import br.ucb.pojo.Consulta;
import br.ucb.pojo.Medico;
import br.ucb.pojo.Paciente;


public class ConsultaController {
	public static void inclusaoConsulta(Consulta registro)
			throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		if (consultaHBR.incluir(registro))
			System.out.println("Inserido: " + registro);
	}

	public static void exclusaoConsulta(Consulta registro)
			throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		if (consultaHBR.excluir(registro))
			System.out.println("Excluído: " + registro);
	}

	public static void alteracaoConsulta(Consulta registro)
			throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		if (consultaHBR.alterar(registro))
			System.out.println("Alterado: " + registro);
	}
	
	public static Consulta consultaConsultas(Consulta registro)
			throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		registro = consultaHBR.consulta(registro);
		if (registro.getIdConsulta() > 0)
			System.out.println("Consulta: " + registro);
		return registro;
	}
	
	public static void listagemConsulta() throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		System.out.println("*****Listando Tudo*******");
		for (Consulta c : consultaHBR.listaTudo())
			System.out.println(c);
	}
	
	public static void listagemConsultas(int inicio, int fim)
			throws Exception {
		ConsultaHibernate consultaHBR = new ConsultaHibernate();
		System.out.println("*****Listando Subconjunto Paginado*******");
		for (Consulta c : consultaHBR.pagina(inicio, fim))
			System.out.println(c);
	}
	
	public static void main(String[] args) throws Exception {
		// Add Paciente
		PacienteHibernate pacienteHBR = new PacienteHibernate();
		Paciente e = new Paciente();
		e.setIdPaciente(1);
		e = pacienteHBR.consulta(e);
		
		// Add Medico
		MedicoHibernate medicoHBR = new MedicoHibernate();
		Medico m = new Medico();
		m.setIdMedico(1);
		m = medicoHBR.consulta(m);
		
		// Add Consulta
		Consulta registro = new Consulta();
		registro.setIdConsulta(1);
		registro.setIdMedico(m);
		registro.setIdPaciente(e);
		registro.setDescricao("Consulta número 1.");
		
		inclusaoConsulta(registro);
		
		
		listagemConsulta();
		
	}

}
