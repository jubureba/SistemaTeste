package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;


import dao.DocumentoDao;
import model.Documento;
import util.Situacao;

@ManagedBean(name = "document")
@SessionScoped
public class DocumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List list ;
	private DocumentoDao dao;

	@Inject
	private Documento doc;

	public DocumentoBean() {
	}

	public Situacao[] getSituacao() {
		return Situacao.values();
	}

	@PostConstruct
	public void init() {
		try {
			dao = new DocumentoDao();
			list = dao.listar();
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void limpar() {
		doc = new Documento();
	}

	public void save() {
		try {
			dao = new DocumentoDao();
			dao.salvar(doc);
		} catch(PersistenceException e) {
			System.out.println("EXCEÇÃO save bean");			
		} catch(NullPointerException e) {
			System.out.println("EXCEÇÃO save bean");		
		} finally {
			init();	
		}
	}

	public void update() {
		try {
			dao = new DocumentoDao();
			dao.update(doc);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			init();
		}
	}

	public void delete(int id) {
		try {
			dao.removerById(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			init();
		}
	}

	/*
	 *  SETTERS E GETTERS
	 */
	
	public Documento getDoc() {
		return doc;
	}

	public void setDoc(Documento doc) {
		this.doc = doc;
	}

	public List<Documento> getList() {
		return list;
	}

	public void setList(List<Documento> list) {
		this.list = list;
	}

	public DocumentoDao getDao() {
		return dao;
	}

	public void setDao(DocumentoDao dao) {
		this.dao = dao;
	}

}
