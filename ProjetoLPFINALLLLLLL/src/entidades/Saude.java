package entidades;

import javax.persistence.Entity;

@Entity
public class Saude extends Tarefa {
	public Saude(String descricao,String tipo, String horaInicio,String horaTermino,boolean check,int id) {
		super(descricao,"Saúde",horaInicio,horaTermino,false,id);
	}}

