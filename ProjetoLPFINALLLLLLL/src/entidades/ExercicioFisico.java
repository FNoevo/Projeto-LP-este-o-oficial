package entidades;

import javax.persistence.Entity;

@Entity
public class ExercicioFisico extends Tarefa {

	public ExercicioFisico(String descricao,String tipo, String horaInicio,String horaTermino,boolean check,int id) {
		super(descricao,"Exercício Físico",horaInicio,horaTermino, check, id);
	}
	public ExercicioFisico() {
	}
}