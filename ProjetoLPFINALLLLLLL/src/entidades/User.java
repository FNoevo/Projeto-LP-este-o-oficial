package entidades;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.processing.Generated;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@javax.persistence.Entity
public class User {
	@Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.TABLE)
	private int id;
	private String email;
	private String senha;
	private String nome;
	private double produtividade;
	@OneToMany(cascade= {javax.persistence.CascadeType.ALL},fetch=javax.persistence.FetchType.LAZY)
	private List<Tarefa>tarefasNao;
	private List<Tarefa>tarefasFeitas;
	
	public User() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
    	this.email=email;
    }
    public void setSenha(String senha) {
    	this.senha=senha;
    }
    public String getSenha(){
    	return senha;
    }
    public String getEmail() {
    	return email;
    }
    public void setNome(String nome) {
    	this.nome=nome;
    }
    public String getNome() {
    	return nome;
    }
    public double getProdutividade() {
    	produtividade=tarefasFeitas.size()/(tarefasFeitas.size()+tarefasNao.size());
    	return produtividade;
    }

	public List<Tarefa> getTarefasNao() {
		return tarefasNao;
	}

	public void setTarefas(List<Tarefa> tarefasNao) {
		this.tarefasNao = tarefasNao;
	}
	public List<Tarefa>getTarefasFeitas(){
		return tarefasFeitas;
	}
	public void setTarefa(List<Tarefa>tarefasFeitas) {
		this.tarefasFeitas=tarefasFeitas;
	}
	public void nomeEx(String nome) throws IllegalArgumentException, Excecoes {
		if (nome=="") {
			throw new IllegalArgumentException("Escreva o seu nome, não seja tímido");
		}
    }
	public void emailEx(String email) throws IllegalArgumentException, Excecoes {
		if (email=="") {
			throw new IllegalArgumentException("Escreva o seu email, não deixe em branco");
		}
    }
	public void senhaEx(String senha) throws IllegalArgumentException, Excecoes {
		if (senha=="") {
			throw new IllegalArgumentException("Escreva a sua senha, não deixe em branco");
		}
    }
	public void marcarTarefaComoConcluida(int i) {
        if (i >= 0 && i < tarefasNao.size()) {
            Tarefa tarefa = tarefasNao.get(i);
            tarefa.estaCheck();
            tarefasNao.remove(i);}
            tarefasFeitas.add((Tarefa) tarefasNao);
        }
	public void produtividadeFrases(double produtivade) throws IllegalArgumentException, FrasesMotivacionais {
		if (produtividade<=0.25) {
			throw new IllegalArgumentException("Vamos lá, só precisas de apanhar ar fresco e vais conseguir fazer as tarefas todas");
		}
		if (produtividade<=0.5) {
			throw new IllegalArgumentException("Melhor mas acho que consegues ainda mais");
		}
		if (produtividade<=0.75) {
			throw new IllegalArgumentException("Nossa, você é um bicharoco");
		}
		if (produtividade<=0.99) {
			throw new IllegalArgumentException("Bem jogado, cumpriste com tudo, faz um bifalhão para festejar");
		}
    }
	public static void imprimirTarefasNaoConcluidas(List<Tarefa> tarefasNao) {
        System.out.println("Tarefas não concluídas em ordem do tipo (saúde, exercício físico, trabalho e lazer):");
        tarefasNao.sort((t1, t2) -> {
            List<String> tipos = Arrays.asList("Saúde", "Exercício Físico", "Trabalho", "Lazer");
            return tipos.indexOf(t1.getTipo()) - tipos.indexOf(t2.getTipo());
        });
        }
	public static void imprimirTarefasConcluidas(ArrayList<Tarefa> tarefasFeitas) {
        System.out.println("Tarefas não concluídas em ordem do tipo (saúde, exercício físico, trabalho e lazer):");
        tarefasFeitas.sort((t1, t2) -> {
            List<String> tipos = Arrays.asList("saúde", "exercício físico", "trabalho", "lazer");
            return tipos.indexOf(t1.getTipo()) - tipos.indexOf(t2.getTipo());
        });
        }
    
}
