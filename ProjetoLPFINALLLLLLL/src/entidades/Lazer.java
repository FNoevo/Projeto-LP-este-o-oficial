package entidades;

import javax.persistence.Entity;

@Entity
public class Lazer extends Tarefa {
	
	public Lazer() {
		tipo="Lazer";
	}
}

