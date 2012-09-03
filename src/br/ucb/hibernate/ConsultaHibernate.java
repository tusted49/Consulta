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

import br.ucb.pojo.Consulta;

@SuppressWarnings("deprecation")
public class ConsultaHibernate {
	private AnnotationConfiguration ac;
	private SessionFactory factory;
	private Session session;
	
	public ConsultaHibernate() {
		ac = new AnnotationConfiguration();
		ac.configure();
		factory = ac.buildSessionFactory();
		session = factory.openSession();
	}
	
	public void destroy() {
		session.close();
	}
	
	public boolean incluir(Consulta registro) throws HibernateException, 
	ConstraintViolationException {
		try {
			session.beginTransaction();
			session.save(registro);
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ConstraintViolationException(
					"Falha na inclusão: Objeto já existe.", null, 
					"Registro duplicado.");
		} catch (HibernateException e) {
			if(session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new HibernateException("Falha de inclusão no BD: ", e);
		}
		finally {
		session.close();
	}
		return true;
	}

	public boolean excluir(Consulta registro) throws HibernateException,
			ObjectNotFoundException {
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

	public boolean alterar(Consulta registro) throws HibernateException,
			ObjectNotFoundException {
		try {
			session.beginTransaction();
			session.update(registro);
			session.getTransaction().commit();
		} catch (StaleStateException e) {
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			throw new ObjectNotFoundException(
					"Falha de consulta: Objeto não localizado.", 
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
	
	public Consulta consulta(Consulta registro) throws HibernateException, ObjectNotFoundException {
		try {
			registro = (Consulta) session.get(Consulta.class, registro.getIdConsulta());
			
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
	public List<Consulta> listaTudo() throws HibernateException {
		List<Consulta> listagem;
		try {
			listagem = session.createCriteria(Consulta.class).list();
		} catch(HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> pagina(int inicio, int quantia) throws HibernateException {
		List<Consulta> listagem;
		try {
			listagem = session.createCriteria(Consulta.class).setMaxResults(quantia).setFirstResult(inicio).list();
		} catch (HibernateException e){
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> listaLike(String nome) throws HibernateException {
		List<Consulta> listagem;
		try {
			listagem = session.createCriteria(Consulta.class)
					.add(Restrictions.like("nome", nome + "%")).list();
		} catch (HibernateException e) {
			throw new HibernateException("Falha de consulta no BD: ", e);
		} finally {
			session.close();
		}
		return listagem;
	}

}
