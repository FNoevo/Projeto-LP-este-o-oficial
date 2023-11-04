package entidades;

import java.util.List;

import javax.management.Query;

import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	private static final String PERSISTANCE_UNIT_NAME = "LibraryJPA";
	private static EntityManagerFactory factory;
	private static javax.persistence.EntityManager em = null;
	
	public static void fill() {
		System.out.println("========");
		System.out.println("  FILL");
		System.out.println("========");
		javax.persistence.EntityManager em = getEM();
		Query q = null;
		List<User> users = null;
		List<Tarefa> tarefaNao = null;
		List<Tarefa> TarefasFeitas = null;
		
		em.getTransaction().begin();
		UserService us = new UserService(getEM());
		List<User> UserList = us.findAllUsers();
		for (User u : UserList) {
			us.removeUser(u.getId());
		}
		TarefaService ts = new TarefaService(getEM());
		List<Tarefa> TarefaNaoList = ts.findAllTarefasNao();
		for (Tarefa ts1 : TarefaNaoList) {
			ts1.removeTarefaNao(ts1.getId());
		}
		List<Tarefa> TarefasFeitaList = ts.findAllTarefasFeitas();
		for (Tarefa ts2 : TarefasFeitaList) {
			ts2.removeTarefaNao(ts2.getId());
		}
		
		em.getTransaction().commit();
		System.out.println("Cleaned DB");
		System.out.println("------------------------");
		
		em.getTransaction().begin();
		User user1=us.updateUser("Francisco", "kiko@gmail.com", "1234", 0, tarefaNao);
		User user2=us.updateUser("João", "joao@gmail.com", "1234", 0, tarefaNao);
		User user3=us.updateUser("Daniel", "daniel@gmail.com", "1234", 0, tarefaNao);
		User user4=us.updateUser("Diogo", "diogo@gmail.com", "1234", 0, tarefaNao);
		User user5=us.updateUser("Zezin", "zezin@gmail.com", "1234", 0, tarefaNao);
		
		
		Tarefa t1 = ts.updateTarefa("Futebol", "Exercício Físico", "9:00","11:00", false, 0);
		Tarefa t2 = ts.updateTarefa("Arrumar caixas", "Trabalho", "11.30","13.30", false, 0);
		Tarefa t3 = ts.updateTarefa("Dentista", "Saúde", "15:30","16:30", false, 0);
		Tarefa t4 = ts.updateTarefa("Ir ver um filme", "Lazer", "17:00","19:30", false, 0);
		Tarefa t5 = ts.updateTarefa("Lavar pratos", "Trabalho", "21.30","23:30", false, 0);
				
		user1.getTarefasNao().add(t1);
		user2.getTarefasNao().add(t2);
		user3.getTarefasNao().add(t3);
		user4.getTarefasNao().add(t4);
		user5.getTarefasNao().add(t5);
		em.getTransaction().commit();
		
		Tarefa tc1 = new Tarefa("Basquetebol","Exercício Físcico","8:00","8:30", true,0);
		Tarefa tc2 = new Tarefa("Beber um copo","Lazer","00:00","2:00", true,0);
		Tarefa tc3 = new Tarefa("Raio-x","Saúde","10:00","11:00", true,0);
		Tarefa tc4 = new Tarefa("Estacionar carros","Trabalho","10:00","12:00", true,0);
		Tarefa tc5 = new Tarefa("Golf","Exercício Físico","17:00","19:00", true,0);
				
		user1.getTarefasFeitas().add(tc1);
		user2.getTarefasFeitas().add(tc2);
		user3.getTarefasFeitas().add(tc3);
		user4.getTarefasFeitas().add(tc4);
		user5.getTarefasFeitas().add(tc5);
		em.getTransaction().commit();
		
		em.getTransaction().commit();
		
		TarefasFeitas = ((TarefaService) ts).findAllTarefasFeitas();
		System.out.println("------------------------");
		System.out.println("Tarefas Feitas table");
		for (Tarefa tf : TarefasFeitas) {
			System.out.println(tf);
		}
		
		tarefaNao = ((TarefaService) ts).findAllTarefasNao();
		System.out.println("------------------------");
		System.out.println("Tarefas não feitas table");
		for (Tarefa tn : tarefaNao) {
			System.out.println(tn);
		}
		
		users = ((UserService) us).findAllUsers();
		System.out.println("------------------------");
		System.out.println("Users table");
		for (User u : users) {
			System.out.println(u);
		}
		System.out.println("------------------------");
		System.out.println("\n\nFinished!!!");
	}
	
	public static EntityManager getEM() {
		if(em == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
			em = ((EntityManagerFactoryImpl) factory).createEntityManager();
		}
		return em;
	}
	
	public static void main(String[] args) {
		fill();
	}

}

