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
 * Realiza todas as opera��es em banco de dados referentes a um objeto "Tarefa"
 */


@SuppressWarnings("unused")
public class TarefaDAO{
	/*
	 * Realiza o cadastro da tarefa e retorna uma mensagem ao usu�rio em tela informando se a tarefa foi cadastrada, juntamente com o seu n�mero (id), 
	 * ou uma outra mensagem se n�o foi poss�vel realizar o cadastro
	 */
	public void cadastrarTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			FacesMessages.info("formCadastro:cadastro", "Info", "<strong>Tarefa n�mero " + t.getId() +" cadastrada com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("formCadastro:cadastro", "fatal", "<strong>N�o foi poss�vel realizar o cadastro.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Obt�m a lista de tarefas de acordo com o filtro selecionado, sendo poss�vel filtrar por 
	 * n�mero/id, t�tulo, respons�vel ou status, ou ainda n�o filtrar e obter a listagem de todas as tarefas,
	 * com exce��o de tarefas com situa��o de "Exclu�da" que n�o devem ser exibidas para o usu�rio.
	 */
	@SuppressWarnings("unchecked")
	public List<Tarefa> listarTarefas(Tarefa t){
		Session session = null;
		List<Tarefa> listaTarefas = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			String sql;
			sql = "SELECT * FROM tarefas WHERE 1=1 and status!='Exclu�da'";
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
	 * Realiza a edi��o de uma tarefa com base em seu id e informa ao usu�rio se a opera��o foi bem sucedida,
	 * e qual tarefa foi alterada, ou retorna uma mensagem de impedimento.
	 */
	public void atualizarTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			FacesMessages.info("**:salvar", "Info", "<strong>Tarefa n�mero " + t.getId() +" foi alterada com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:salvar", "fatal", "<strong>N�o foi poss�vel realizar a altera��o.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Realiza a exclus�o l�gica de uma tarefa com base em seu id e informa ao usu�rio se a opera��o foi bem sucedida,
	 * e qual tarefa foi exclu�da, ou retorna uma mensagem de impedimento. Por se tratar de uma exclus�o l�gica, 
	 * o status da tarefa � alterado para "Exclu�da", e ela n�o ser� mais exibida para o usu�rio, e nem ser� poss�vel realizar
	 * nenhuma outra opera��o a respeito da tarefa que foi exclu�da, por�m o registro permanece em banco de dados.
	 */
	public void excluirTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			FacesMessages.info("**:excluir", "Info", "<strong>Tarefa n�mero " + t.getId() +" foi exclu�da com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:excluir", "fatal", "<strong>N�o foi poss�vel realizar a exclus�o.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * Altera a tarefa para a situa��o de "Conclu�da" com base em seu id e informa ao usu�rio se a opera��o foi bem sucedida,
	 * e qual tarefa foi conclu�da, ou retorna uma mensagem de impedimento.
	 */
	public void concluirTarefa(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			session.close();
			FacesMessages.info("**:concluir", "Info", "<strong>Tarefa n�mero " + t.getId() +" foi conclu�da com sucesso!</strong>");
		} catch (HibernateException e){
			e.printStackTrace();
			FacesMessages.fatal("**:concluir", "fatal", "<strong>N�o foi poss�vel concluir a tarefa.</strong>");
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}
}