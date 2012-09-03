package br.ucb.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import br.ucb.pojo.Paciente;

@SuppressWarnings("deprecation")
public class PacienteHibernate {
	private AnnotationConfiguration ac;
	private SessionFactory factory;
	private Session session;
	
	public PacienteHibernate() {
		ac = new AnnotationConfiguration();
		ac.configure();
		factory = ac.buildSessionFactory();
		session = factory.openSession();
	}

	public void destroy(){
		session.close();
	}
	
	public boolean inclui(Paciente registro) throws HibernateException, ConstraintViolationException {
		try {
			session.beginTransaction();
			session.save(registro);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ConstraintViolationException("Falha de inclusão: Objeto já existente", null, "Registro Duplicado.");
		} catch (HibernateException e) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de inclusão no BD: ", e);
			
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean exclui(Paciente registro) throws HibernateException, ObjectNotFoundException {
		try {
			session.beginTransaction();
			session.delete(registro);
			session.getTransaction().commit();
		} catch (ObjectNotFoundException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado.", 
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de exclusão no BD: ", e);
		} finally {
			session.close();
		}
		return true;
	}

	public boolean altera(Paciente registro) throws HibernateException, ObjectNotFoundException {
		try {
			session.beginTransaction();
			session.update(registro);
			session.getTransaction().commit();
		} catch (StaleStateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado. ",
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de alteração no BD: ", e);
		} finally {
			session.close();
		}
		return true;
	}
	
	public Paciente consulta(Paciente registro) throws HibernateException, ObjectNotFoundException {
		try {
			registro = (Paciente) session.get(Paciente.class, registro.getIdPaciente());
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ", 
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return registro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> listaTudo() throws HibernateException {
		List<Paciente> listagem;
		try {
			listagem = session.createCriteria(Paciente.class).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}	
		return listagem;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> pagina(int inicio, int quantia) throws HibernateException {
		List<Paciente> listagem;
		try { 
			listagem = session.createCriteria(Paciente.class)
					.setMaxResults(quantia).setFirstResult(inicio).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> listaPaciente(String nomePaciente) throws HibernateException {
		List<Paciente> listagem;
		try {
			listagem = session
					.createCriteria(Paciente.class)
					.add(Restrictions
							.like("nome", nomePaciente + "%")).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

}

	