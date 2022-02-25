package br.com.gerenciadordetarefas;

import br.com.gerenciadordetarefas.Tarefa;
import conexoes.HibernateUtility;
import net.bootsfaces.utils.FacesMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * Realiza todas as operações em banco de dados referentes a um objeto "Tarefa"
 */


@SuppressWarnings("unused")
public class TarefaDAO{
	/*
	 * Realiza o cadastro da tarefa e retorna uma mensagem ao usuário em tela informando se a tarefa foi cadastrada, juntamente com o seu número (id), 
	 * ou uma outra mensagem se não foi possível realizar o cadastro
	 */
	public void cadastrarTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			FacesMessages.info("formCadastro:cadastro", "Info", "<strong>Tarefa número " + t.getId() +" cadastrada com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("formCadastro:cadastro", "fatal", "<strong>Não foi possível realizar o cadastro.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Obtém a lista de tarefas de acordo com o filtro selecionado, sendo possível filtrar por 
	 * número/id, título, responsável ou status, ou ainda não filtrar e obter a listagem de todas as tarefas,
	 * com exceção de tarefas com situação de "Excluída" que não devem ser exibidas para o usuário.
	 */
	@SuppressWarnings("unchecked")
	public List<Tarefa> listarTarefas(Tarefa t){
		Session session = null;
		List<Tarefa> listaTarefas = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			String sql;
			sql = "SELECT * FROM tarefas WHERE 1=1 and status!='Excluída'";
			if(t.getId() != null) {
				sql += " and id=" + t.getId();
			}
			if(!t.getTitulo().equals("")) {
				sql += " and titulo='" + t.getTitulo() + "'";
			}
			if(!t.getResponsavel().equals("0")) {
				sql += " and responsavel='" + t.getResponsavel() + "'";
			}
			if(!t.getStatus().equals("0")) {
				sql += " and status='" + t.getStatus() +"'";
			}
			listaTarefas = session.createSQLQuery(sql).addEntity(Tarefa.class).getResultList();
		} catch (HibernateException e){
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return listaTarefas;
	}
	
	/*
	 * Realiza a edição de uma tarefa com base em seu id e informa ao usuário se a operação foi bem sucedida,
	 * e qual tarefa foi alterada, ou retorna uma mensagem de impedimento.
	 */
	public void atualizarTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			FacesMessages.info("**:salvar", "Info", "<strong>Tarefa número " + t.getId() +" foi alterada com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:salvar", "fatal", "<strong>Não foi possível realizar a alteração.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Realiza a exclusão lógica de uma tarefa com base em seu id e informa ao usuário se a operação foi bem sucedida,
	 * e qual tarefa foi excluída, ou retorna uma mensagem de impedimento. Por se tratar de uma exclusão lógica, 
	 * o status da tarefa é alterado para "Excluída", e ela não será mais exibida para o usuário, e nem será possível realizar
	 * nenhuma outra operação a respeito da tarefa que foi excluída, porém o registro permanece em banco de dados.
	 */
	public void excluirTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			FacesMessages.info("**:excluir", "Info", "<strong>Tarefa número " + t.getId() +" foi excluída com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:excluir", "fatal", "<strong>Não foi possível realizar a exclusão.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Altera a tarefa para a situação de "Concluída" com base em seu id e informa ao usuário se a operação foi bem sucedida,
	 * e qual tarefa foi concluída, ou retorna uma mensagem de impedimento.
	 */
	public void concluirTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			session.close();
			FacesMessages.info("**:concluir", "Info", "<strong>Tarefa número " + t.getId() +" foi concluída com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:concluir", "fatal", "<strong>Não foi possível concluir a tarefa.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
}