package modelos;

import java.util.HashMap;
import java.util.Map;

public class Curso {
    private String nome;
    private String codCurso;
    private Map<String, Aluno> alunos = new HashMap<>();


    public Curso (String codCurso){
        this.codCurso = codCurso;
    }
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

    public Map<String, Aluno> mapAlunos() {
        return alunos;
    }

    public Aluno getAluno(String matricula) {

        return alunos.get(matricula);
    }

    public void addAluno(String matricula, Aluno aluno) {
            if (!existAluno(matricula)) {

                alunos.put(matricula, aluno);

            }else {

                for (String key : aluno.mapDisciplinas().keySet()) {
                    alunos.get(matricula).addDisciplina(key,aluno.getDisciplina(key));
                }
            }

    }

    public boolean existAluno(String matricula) {

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
