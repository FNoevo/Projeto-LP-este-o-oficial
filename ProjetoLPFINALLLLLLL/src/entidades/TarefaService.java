package entidades;

import java.util.List;

import javax.naming.spi.DirectoryManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.persistence.EntityManagerFactory;

public class TarefaService<EntityManagerFactory> {
	protected EntityManagerFactory em;
	
	public TarefaService(EntityManager entityManager) {
		this.em = (EntityManagerFactory) entityManager;
	}
	
	public Tarefa updateTarefa(String descricao, String tipo,String horaInicio, String horaTermino,boolean check, int id) {	
		Tarefa t = ((EntityManager) em).find(Tarefa.class, id);
		if (t == null) {
			t = new Tarefa();
			((EntityManager) em).persist(t);
		}
		t.setId(id);
		t.setDescricao(descricao);
		t.setTipo(tipo);
		t.setHoraInicio(horaInicio);
		t.setHoraTermino(horaTermino);
		t.marcarCheck(check);
		t.getUsers().clear();
		return t;
	}
		
	public void removeTarefa(int id) {
		Tarefa l = findTarefa(id);
		if(l != null) 
			((EntityManager) em).remove(l);
		return;
	}
	
	public Tarefa findTarefa(int id) {
		return ((EntityManager) em).find(Tarefa.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> findAllTarefasNao() {
		Query qd = ((EntityManager) em).createQuery("Select a from Tarefa não concluídas a");
		return qd.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Tarefa> findAllTarefasFeitas() {
		Query qd = ((EntityManager) em).createQuery("Select a from Tarefa concluídas a");
		return qd.getResultList();
	}
	}



