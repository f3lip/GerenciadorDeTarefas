package br.com.gerenciadordetarefas;

import br.com.gerenciadordetarefas.Tarefa;
import conexoes.HibernateUtility;
import net.bootsfaces.utils.FacesMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
*/
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("unused")
public class TarefaDAO{
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
	
	public Tarefa getTarefaById(String id) {
		Session session = null;
		Tarefa tarefa = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			String sql;
			sql = "SELECT * FROM tarefas WHERE id=" + id;
			System.out.println(sql);
			tarefa = (Tarefa) session.createSQLQuery(sql).addEntity(Tarefa.class).getSingleResult();
		} catch (HibernateException e){
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return tarefa;
	}
}