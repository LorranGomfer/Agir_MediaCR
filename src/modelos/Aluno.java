package modelos;

import java.util.HashMap;
import java.util.Map;

public class Aluno {

    private String matricula;

    private Map<String,Disciplina> disciplinas = new HashMap<>();

    public Map<String, Disciplina> mapDisciplinas() { //Retorna o conjunto de disciplinas

        return disciplinas;
    }

    public Disciplina getDisciplina(String key) { //retorna uma unica disciplina
        return disciplinas.get(key);
    }

    public void addDisciplina(String CodDisciplina, Disciplina disciplina) {

        if (!existDisciplina(disciplina.getCodDisciplina())) { //Se não existe essa disciplina, adiciona
            disciplinas.put(CodDisciplina, disciplina);
        }//else System.out.println("Sobrescrição disciplina");


    }

    public boolean existDisciplina(String codDisciplina) { //Verifica se essa disciplina já existe neste aluno

        if (disciplinas.get(codDisciplina) == null) {
            return false;
        }
        return true;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public double calcularCR(){
        double CR ;
        double soma = 0.0;
        int chtotal = 0;

        for (String key : disciplinas.keySet()) { // Para cada disciplinas dentro desse aluno

            soma += disciplinas.get(key).getNota() * disciplinas.get(key).getCargaHoraria();
            chtotal += disciplinas.get(key).getCargaHoraria();

        }

        CR = soma / chtotal;
        return CR;
    }
}
