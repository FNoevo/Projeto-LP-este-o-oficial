package entidades;

import javax.persistence.Entity;

@Entity
public class Trabalho extends Tarefa {
	public Trabalho(String descricao,String tipo, String horaInicio,String horaTermino,boolean check,int id) {
		super(descricao,"Trabalho",horaInicio,horaTermino,false,id);
	}
	public Trabalho() {
	}
}
