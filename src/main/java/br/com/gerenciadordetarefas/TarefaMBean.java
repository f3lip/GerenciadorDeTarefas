package br.com.gerenciadordetarefas;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.gerenciadordetarefas.Tarefa;

@SuppressWarnings("unused")
@Named
@RequestScoped
public class TarefaMBean {

	private Tarefa tarefa = new Tarefa();
	private TarefaDAO dao = new TarefaDAO();
	private List<Tarefa> listaDeTarefas;
	private boolean editable;
	
	
	public void cadastrar() {
		tarefa.setStatus("Em andamento");
		dao.cadastrarTarefa(tarefa);
		tarefa = new Tarefa();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Tarefa cadastrada com sucesso."));
	}
	
	public void listarTarefas(){
		listaDeTarefas = dao.listarTarefas(tarefa);
	}
	
	public List<Tarefa> getListaDeTarefas(){
		return listaDeTarefas;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public void excluirTarefa() {
		tarefa.setStatus("Excluída");
		dao.atualizarTarefa(tarefa);
	}
	
	public void concluirTarefa() {
		tarefa.setStatus("Concluída");
		dao.atualizarTarefa(tarefa);
	}
	
	public void atualizarTarefa() {
		dao.atualizarTarefa(tarefa);
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
