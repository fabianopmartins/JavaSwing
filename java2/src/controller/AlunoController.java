package controller;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import models.Aluno;

public class AlunoController {

	private Aluno aluno;

	private EntityManagerFactory emf;
	private EntityManager em;
	private Query q;

	public AlunoController() {
		emf = Persistence.createEntityManagerFactory("aluno");
		em = emf.createEntityManager();
	}

	public void salvar(Aluno aluno) {
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		emf.close();
	}

	public void remover(Aluno aluno) {
		em.getTransaction().begin();
		q = em.createNativeQuery("DELETE aluno FROM aluno WHERE id =" + aluno.getId());
		q.executeUpdate();
		em.getTransaction().commit();
		emf.close();
	}

	public void removeAll(String dados) {
		em.getTransaction().begin();
		for(int i = 0; i < 100; i++) {
		q = em.createNativeQuery("DELETE aluno FROM aluno WHERE id= " + dados);
		}
		q.executeUpdate();
		em.getTransaction().commit();
		emf.close();
	}
	
	public Aluno buscaPorId(Integer id) {
		em.getTransaction().begin();
		aluno = em.find(Aluno.class, id);
		return aluno;
	}

	public Aluno buscaPorNome(String nome) {
		em.getTransaction().begin();
		q = em.createQuery("SELECT aluno FROM Aluno aluno WHERE nome = :nome");
		q.setParameter("nome", nome);
		emf.close();
		return aluno = (Aluno) q.getSingleResult();
	}
}