package entidades;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.ManyToMany;


@javax.persistence.Entity
public class Tarefa implements Comparable<Tarefa>{
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.TABLE)
	private int id;
	private String descricao;
	private boolean check;
	protected String tipo;
	private String horaInicio;
	private String horaTermino;
	@ManyToMany(cascade= {javax.persistence.CascadeType.ALL},fetch=javax.persistence.FetchType.LAZY)
	private List<User>users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Tarefa() {
	}
	
	public Tarefa(String descricao, String tipo, String horaInicio, String horaTermino,boolean check,int id) {
		// TODO Auto-generated constructor stub
	}

	public void marcarCheck(boolean check) {
		this.setCheck(true);
	}
	public boolean estaCheck() {
		return isCheck();
	}
	public String getDescricao() {
		return descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraTermino() {
		return horaTermino;
	}
	public void setDescricao(String descricao) {
		this.descricao=descricao;
	}
	public void setTipo(String tipo) {
		this.tipo=tipo;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio=horaInicio;
	}
	public void setHoraTermino(String horaTermino) {
		this.horaTermino=horaTermino;
	}
	public int calcularTempoGasto() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        LocalTime inicio = LocalTime.parse(horaInicio, formatter);
	        LocalTime termino = LocalTime.parse(horaTermino, formatter);
	        long minutos = ChronoUnit.MINUTES.between(inicio, termino);
	        return (int) minutos;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
    public int compareTo(Tarefa outraTarefa) {
        return this.horaInicio.compareTo(outraTarefa.horaInicio);
    }

	public void removeTarefaNao(int id2) {
		// TODO Auto-generated method stub
		
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public static void add(Tarefa tarefa) {
		Tarefa.add(tarefa);
	}
    public void registarUser(Tarefa tarefa) {
	    Tarefa.add(tarefa);
    }
    public void descricaoEx(String descricao) throws IllegalArgumentException, Excecoes {
		if (descricao=="") {
			throw new IllegalArgumentException("Escreva uma descrição, não deixe em branco");
		}
    }
    public void tipoEx(String tipo) throws IllegalArgumentException, Excecoes {
		if (tipo=="") {
			throw new IllegalArgumentException("Escreva um tipo: Sáude,Lazer, Trabalho e Exercício Físico, não deixe em branco");
		}
    }
    public void horaInicioEx(String horaInicio) throws IllegalArgumentException, Excecoes {
		if (horaInicio=="") {
			throw new IllegalArgumentException("Escreva uma hora da forma 00:00, não deixe em branco");
		}
    }
    public void horaTerminoEx(String horaTermino) throws IllegalArgumentException, Excecoes {
		if (horaTermino=="") {
			throw new IllegalArgumentException("Escreva uma hora da forma 00:00, não deixe em branco");
		}
    }
    
}




