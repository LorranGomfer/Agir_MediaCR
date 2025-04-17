package modelos;

import java.util.HashMap;
import java.util.Map;

public class Curso {
    private String nome;
    private String codCurso;
    private Map<String, Aluno> alunos = new HashMap<>();

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getNome() {
       return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Aluno> mapAlunos() { //Retorna o conjunto de alunos deste curso
        return alunos;
    }

    public Aluno getAluno(String matricula) { //Retorna um unico aluno deste curso

        return alunos.get(matricula);
    }

    public void addAluno(String matricula, Aluno aluno) {
            if (!existAluno(matricula)) { //Se não existe aluno com essa matricula...

                alunos.put(matricula, aluno); //adiciona no map

            }else { //Se já existe aluno...

                for (String key : aluno.mapDisciplinas().keySet()) { // Adiciona as disciplinas
                    alunos.get(matricula).addDisciplina(key,aluno.getDisciplina(key));
                }

                //System.out.println("Sobrescrição de aluno");
            }

    }

    public boolean existAluno(String matricula) { //Verifica a existencia do aluno neste curso

        if (alunos.get(matricula) == null) return false;

        return true;
    }

    public double CalcularCR (){
        double CR = 0.0;
        int totalAlunos = alunos.size();

        for (String key : alunos.keySet()) {

            CR += alunos.get(key).calcularCR();

        }

        return CR/totalAlunos;
    }
}
