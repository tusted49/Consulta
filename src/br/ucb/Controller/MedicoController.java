package br.ucb.Controller;

import br.ucb.hibernate.MedicoHibernate;
import br.ucb.pojo.Medico;

public class MedicoController {

	public static void inclusaoMedico(Medico registro) throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		if (medHBR.incluir(registro))
			System.out.println("Inserido: " + registro);
	}

	public static void exclusaoMedico(Medico registro) throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		if(medHBR.exclui(registro))
			System.out.println("Excluído: " + registro);
	}

	public static void alteraMedico(Medico registro) throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		if(medHBR.altera(registro))
			System.out.println("Alterado: " + registro);
	}

	public static Medico consultaMedico(Medico registro) throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		registro = medHBR.consulta(registro);
		if(registro.getIdMedico() > 0)
			System.out.println("Consulta: " + registro);
		return registro;
	}

	public static void listagemMedicos() throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		System.out.println("*** LISTANDO TUDO ***");
		for (Medico m : medHBR.listaTudo())
			System.out.println(m);
	}

	public static void listagemMedicos(int inicio, int fim) throws Exception {
		MedicoHibernate medHBR = new MedicoHibernate();
		System.out.println("*** LISTANDO SUBCONJUNTO PAGINADO ***");
		for (Medico m : medHBR.pagina(inicio, fim))
			System.out.println(m);
	}

	public static void main(String[] args) throws Exception {
		Medico m1 = new Medico();
		m1.setIdMedico(1);
		m1.setNome("Antonio da silva neto");
		
		Medico m2 = new Medico();
		m2.setIdMedico(2);
		m2.setNome("Eduardo Augusto Maduro Gaudino");
		
		Medico m3 = new Medico();
		m3.setIdMedico(3);
		m3.setNome("Luiza Arantes");
		
		
		inclusaoMedico(m1);
		inclusaoMedico(m2);
		inclusaoMedico(m3);
		listagemMedicos();
		
	}
}