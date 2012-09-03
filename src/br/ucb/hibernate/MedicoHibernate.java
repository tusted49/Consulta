package br.ucb.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import br.ucb.pojo.Medico;

@SuppressWarnings("deprecation")
public class MedicoHibernate {
	
	private AnnotationConfiguration ac;
	private SessionFactory factory;
	private Session session;
	
	
	public MedicoHibernate() {
		ac = new AnnotationConfiguration();
		ac.configure();
		factory = ac.buildSessionFactory();
		session = factory.openSession();
	}
	
	public void destroy() {
		session.close();
	}

	public boolean incluir(Medico registro) throws HibernateException, ConstraintViolationException {
		try {
			session.beginTransaction();
			session.save(registro);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ConstraintViolationException("Falha na inclusão: Objeto existente.", null, "Registro duplicado");
		} catch (HibernateException e) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de inclusão no BD: ", e);
		
		} finally {
			session.close();
		}
		
		
		return true;
	}
	

	public boolean exclui(Medico registro) throws HibernateException, ObjectNotFoundException {
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

	
	public boolean altera(Medico registro) throws HibernateException, ObjectNotFoundException {
		try {
			session.beginTransaction();
			session.update(registro);
			session.getTransaction().commit();
		} catch (StaleStateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ObjectNotFoundException("" +
					"Falha de consulta: Objeto não localizado.", 
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de alteração no BD", e);
		} finally {
			session.close();
		}
		
		return true;
	}
	
	public Medico consulta(Medico registro) throws HibernateException, ObjectNotFoundException {
		try {
			registro = (Medico) session.get(Medico.class, registro.getIdMedico());
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado ", 
					"ERRO! Objeto não localizado");
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD:", e);
		} finally {
			session.close();
		}
		return registro;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> listaTudo() throws HibernateException {
		List<Medico> listagem;
		try {
			listagem = session.createCriteria(Medico.class).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> pagina(int inicio, int quantia) throws HibernateException {
		List<Medico> listagem;
		try {
			listagem = session.createCriteria(Medico.class)
					.setMaxResults(quantia).setFirstResult(inicio).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
	
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<Medico> listaMedico(String nomeMedico) throws HibernateException {
		List<Medico> listagem;
		try {
			listagem = session.createCriteria(Medico.class)
					.add(Restrictions
							.like("nome", nomeMedico + "%")).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	
	}
	

}
