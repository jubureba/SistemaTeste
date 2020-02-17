package model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import util.Situacao;


/**
 * The persistent class for the DOCUMENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="ID_DOC")
	@GeneratedValue( generator="seqRunId")
    @SequenceGenerator(name="seqRunId", sequenceName="SEQ_RUN_ID",  initialValue = 1, allocationSize = 1)
	private long idDoc;

	@Basic(optional = true)
	@Size(min = 1, max = 100, message = "{MSG0004}") //MENSAGEM ADICIONADA PELO .PROPERTIES
	@Column(length = 100)
	private String descricao;
	
	@Basic(optional = false)
	@Column(length = 40, unique = true)
	@Size(min = 1, max= 40, message = "{MSG0004}")
	private String nome;


    @Enumerated(EnumType.STRING)
	private Situacao situacao;

	public Documento() {
	}

	public long getIdDoc() {
		return this.idDoc;
	}

	public void setIdDoc(long idDoc) {
		this.idDoc = idDoc;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}


}