package entidades;

import java.util.List;

import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.persistence.EntityManagerFactory;

public class UserService {
	protected EntityManagerFactory em;
	
	public UserService(EntityManager entityManager) {
		this.em = (EntityManagerFactory) entityManager;
	}
	
	public User updateUser(String nome, String email, String senha,int id, List<Tarefa> tarefasNao) {	
		User u = ((EntityManager) em).find(User.class, id);
		if (u == null) {
			u = new User();
			((EntityManager) em).persist(u);
		}
		u.setId(id);
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senha);
		u.getTarefasNao().clear();
		return u;
	}
		
	public void removeUser(int id) {
		User l = findUser(id);
		if(l != null) 
			((EntityManager) em).remove(l);
		return;
	}
	
	public User findUser(int id) {
		return ((EntityManager) em).find(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Query qd = ((EntityManager) em).createQuery("Select a from User a");
		return qd.getResultList();
	}

}

