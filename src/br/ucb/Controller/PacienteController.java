package br.ucb.Controller;

import br.ucb.pojo.Paciente;
import br.ucb.hibernate.PacienteHibernate;

public class PacienteController {
	public static void inclusaoDePaciente(Paciente registro) throws Exception {
		PacienteHibernate pacienteHBR = new PacienteHibernate();
		if (pacienteHBR.inclui(registro))
			System.out.println("Inserido: " + registro);
	}
	
	public static void exclusaoPaciente(Paciente registro) throws Exception {
		PacienteHibernate pacHBR = new PacienteHibernate();
		if(pacHBR.exclui(registro))
			System.out.println("Excluído: " + registro);
	}
	
	public static void alteraPaciente(Paciente registro) throws Exception {
		PacienteHibernate pacHBR = new PacienteHibernate();
		if(pacHBR.altera(registro))
			System.out.println("Alterado: " + registro);
		
	}
	
	public static Paciente consultaPaciente(Paciente registro) throws Exception {
		PacienteHibernate pacHBR = new PacienteHibernate();
		registro = pacHBR.consulta(registro);
		if (registro.getIdPaciente() > 0)
			System.out.println("Consulta: " + registro);
		return registro;
	}
	
	public static void listagemPacientes() throws Exception {
		PacienteHibernate pacHBR = new PacienteHibernate();
		System.out.println("*** LISTANDO TUDO ***");
		for (Paciente p : pacHBR.listaTudo())
			System.out.println(p);
	}

	public static void listagemPacientes(int inicio, int fim) throws Exception {
		PacienteHibernate pacHBR = new PacienteHibernate();
		System.out.println("*** LISTANDO SUBCONJUNTO PAGINADO ***");
		for (Paciente p : pacHBR.pagina(inicio, fim))
			System.out.println(p);
	}
	
	public static void main(String[] args) throws Exception {
		//PacienteHibernate pacHBR = new PacienteHibernate();
		Paciente p = new Paciente();
		p.setIdPaciente(1);
		p.setNomePaciente("Gabriela");
		p.setIdade(30);
		p.setSexo("Feminino");
		p.setEndereco("Rua dois");
		
		inclusaoDePaciente(p);
		listagemPacientes();
	}
}
