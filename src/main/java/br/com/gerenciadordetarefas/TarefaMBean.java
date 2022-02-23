package br.com.gerenciadordetarefas;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.gerenciadordetarefas.Tarefa;
import net.bootsfaces.utils.FacesMessages;

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
	
	public String excluirTarefa() {
		tarefa.setStatus("Excluída");
		dao.excluirTarefa(tarefa);
		return null;

	}
	
	public void concluirTarefa() {
		tarefa.setStatus("Concluída");
		dao.concluirTarefa(tarefa);
	}
	
	public void atualizarTarefa() {
		dao.atualizarTarefa(tarefa);
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
