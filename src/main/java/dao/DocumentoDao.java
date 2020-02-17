package dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import abstracts.Constantes;
import abstracts.Msgs;
import model.Documento;

public class DocumentoDao implements Msgs, Constantes{
	
	/*
	 * Atributos
	 */

	private static EntityManager em;
	private static DocumentoDao Instance;
	static EntityManagerFactory factory;

	/*
	 * Construtor
	 */
	public DocumentoDao() {
		em = getEntityManager();
	}

	
	private static EntityManager getEntityManager() {
		factory = Persistence.createEntityManagerFactory("pscs");
		if (em == null) {
			em = factory.createEntityManager();
		}
		return em;
	}

	public List listar() {
		return em.createQuery("FROM " + Documento.class.getName() ).getResultList();
	}

	public boolean exists(String nome) {
		TypedQuery<Documento> query = getEntityManager()
				.createQuery("SELECT d FROM Documento d WHERE d.nome = '" + nome + "'", Documento.class);
		try {
			query.getSingleResult();
			return true;
		} catch (RuntimeException ex) {
			return false;
		}
	}
	
	public Long teste(String d) {
		TypedQuery<Documento> query = getEntityManager()
				.createQuery("SELECT d FROM Documento d WHERE d.nome = '" + d + "'", Documento.class);
		return query.getSingleResult().getIdDoc();	
	}

	public void salvar(Documento d) throws PersistenceException, TransactionRequiredException, RollbackException {
		try {
			if (!exists(d.getNome())) {
				em.getTransaction().begin();
				em.persist(d);
				em.getTransaction().commit();
				MSG0001(); // msg save successfully
			} else {
				MSG0003(); // msg duplicate record
			}

		} catch (Exception e) {
			em.getTransaction().rollback();
			em.close();
			factory.close();
		}
	}

	public void update(Documento d) {
		try {
			if(teste(d.getNome()) == d.getIdDoc()) {
				em.getTransaction().begin();
				em.persist(d);
				em.getTransaction().commit();
				MSG0001(); // msg save successfully
				System.out.println("TO AQUI NO UPDATE IF");
			}
			else if (!exists(d.getNome())) {
				em.getTransaction().begin();
				em.merge(d);
				em.getTransaction().commit();
				MSG0001();
				System.out.println("TO AQUI NO UPDATE ELSE IF");
			} else {
				MSG0003(); // msg duplicate record
				System.out.println("TO AQUI NO UPDATE ELSE");
			}

		} catch (Exception e) {
			em.getTransaction().rollback();
			em.close();
			factory.close();
		}
	}

	public void remover(Documento d) {
		try {
			em.getTransaction().begin();
			d = em.find(Documento.class, d.getIdDoc());
			em.remove(d);
			em.getTransaction().commit();
			MSG0001();

		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public void removerById(final long id) {
		try {
			Documento doc = getById(id);
			remover(doc);
			MSG0001();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Documento getById(final long id) {
		return em.find(Documento.class, id);
	}

	public Documento getByNome(String nome) {
		return em.find(Documento.class, nome);
	}
	
	
	/***
	 * 
	 * METODOS DA INTERFACE PARA MENSAGENS 
	 * 
	 */
	
	@Override
	public void MSG0001() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, INFO, MSG0001));	
	}

	@Override
	public void MSG0002() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, WARN, MSG0002));	
	}

	@Override
	public void MSG0003() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0003));	
	}

	@Override
	public void MSG0004() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0004));
	}

	@Override
	public void MSG0005() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0005));
	}

	@Override
	public void MSG0006() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, INFO, MSG0006));
	}

	@Override
	public void MSG0007() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0007));
		
	}

	@Override
	public void MSG0008() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0008));
		
	}

	@Override
	public void MSG0009() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0009));
		
	}

	@Override
	public void MSG0010() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0010));
		
	}

	@Override
	public void MSG0011() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0011));
		
	}

	@Override
	public void MSG0012() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0012));
		
	}

	@Override
	public void MSG0013() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR, MSG0013));
		
	}
}
