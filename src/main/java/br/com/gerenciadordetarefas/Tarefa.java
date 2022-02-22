package br.com.gerenciadordetarefas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "Tarefa")
@Table(name = "tarefas")
public class Tarefa {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "responsavel")
	private String responsavel;
	@Column(name = "prioridade")
	private String prioridade;
	@Column(name = "deadline")
	private Date deadline;
	@Column(name = "status")
	private String status;
	
	@Transient
	private boolean editable;
	
	public Tarefa() {

	}

	public Tarefa(String titulo, String descricao, String responsavel, String prioridade, Date deadline, String status) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.prioridade = prioridade;
		this.deadline = deadline;
		this.status = status;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public void setTitulo(String titulo){
		this.titulo = titulo;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public void setResponsavel(String responsavel){
		this.responsavel = responsavel;
	}

	public void setPrioridade(String prioridade){
		this.prioridade = prioridade;
	}

	public void setDeadline(Date deadline){
		this.deadline = deadline;
	}

	public void setStatus(String status){
		this.status = status;
	}
	
	public Integer getId() {
		return this.id;
	}

	public String getTitulo(){
		return this.titulo;
	}

	public String getDescricao(){
		return this.descricao;
	}

	public String getResponsavel(){
		return this.responsavel;
	}

	public String getPrioridade(){
		return this.prioridade;
	}

	public Date getDeadline(){
		return this.deadline;
	}

	public String getStatus(){
		return this.status;
	}
	
	
	public boolean getEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public String editTarefa(){
		if(editable) {
			setEditable(false);
		} else {
			setEditable(true);
		}
	    return null;
   }
}