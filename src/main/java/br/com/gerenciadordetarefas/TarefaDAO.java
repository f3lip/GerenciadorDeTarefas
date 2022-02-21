package br.com.gerenciadordetarefas;

import br.com.gerenciadordetarefas.Tarefa;
import conexoes.HibernateUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
			session.close();
			System.out.println("Cadastrado");
		} catch (HibernateException e){
			e.printStackTrace();
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
			System.out.println(t.getId());
			System.out.println(t.getTitulo());
			System.out.println(t.getResponsavel());
			System.out.println(t.getStatus());
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
			System.out.println(sql);
			listaTarefas = session.createSQLQuery(sql).addEntity(Tarefa.class).getResultList();
			session.close();
			System.out.println(listaTarefas);
		} catch (HibernateException e){
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
		}
		return listaTarefas;
	}
	
	public void atualizarStatus(Tarefa t){
		Session session = null;
		try {
			session = HibernateUtility.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			session.close();
			System.out.println("Atualizado");
		} catch (HibernateException e){
			e.printStackTrace();
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
			session.close();
			System.out.println(tarefa.getTitulo());
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