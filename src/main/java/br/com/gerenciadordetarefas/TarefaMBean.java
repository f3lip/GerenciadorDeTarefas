package br.com.gerenciadordetarefas;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Named;

import br.com.gerenciadordetarefas.Tarefa;

@SuppressWarnings("unused")
@Named
@RequestScoped
public class TarefaMBean {

	private Tarefa tarefa = new Tarefa();
	private TarefaDAO dao = new TarefaDAO();
	private List<Tarefa> listaDeTarefas;
	
	
	
	public void cadastrar() {
		tarefa.setStatus("Em andamento");
		dao.cadastrarTarefa(tarefa);
		tarefa = new Tarefa();
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
		tarefa.setStatus("Exclu�da");
		dao.atualizarStatus(tarefa);
	}
	
	public void concluirTarefa() {
		tarefa.setStatus("Conclu�da");
		dao.atualizarStatus(tarefa);
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}