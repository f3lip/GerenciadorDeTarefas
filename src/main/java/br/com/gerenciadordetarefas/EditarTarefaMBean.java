package br.com.gerenciadordetarefas;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.gerenciadordetarefas.Tarefa;
import java.io.Serializable;

@SuppressWarnings("unused")
@Named 
@ViewScoped
public class EditarTarefaMBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarefa tarefa = new Tarefa();
	private TarefaDAO dao = new TarefaDAO();
	
	@PostConstruct
	public void init() {
		editarTarefa();
	}
	
	public void getTarefaById(String id) {
		this.tarefa = dao.getTarefaById(id);
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	public String editarTarefa() {
		FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = 
	         fc.getExternalContext().getRequestParameterMap();
	      String idTarefa =  params.get("idTarefa");
	      System.out.println(idTarefa);
		return "editar.xhtml";
	}

}
