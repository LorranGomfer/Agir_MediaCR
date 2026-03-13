import modelos.Aluno;
import modelos.Curso;
import modelos.Disciplina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String caminhoArq = "src/main/resources/arqs/notas.csv";

        Map<String, Curso> cursos = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArq))) {
            String linha = leitor.readLine();
            while (linha != null) {

                String[] campos = linha.split(",");

                if (isNumeric(campos[0])) {

                    String matricula = campos[0];
                    String codDisciplina = campos[1];
                    String codCurso = campos[2];
                    double nota = Double.parseDouble(campos[3]);
                    int cargaHoraria = Integer.parseInt(campos[4]);
                    int anoSemestre = Integer.parseInt(campos[5]);

                    Curso novoCurso = cursos.get(codCurso);

                    if (novoCurso == null){
                        novoCurso = new Curso(codCurso);
                        cursos.put(codCurso,novoCurso);
                    }

                    Disciplina novaDisciplina = new Disciplina(codDisciplina, cargaHoraria, nota, anoSemestre);

                    Aluno novoAluno = new Aluno(matricula);
                    novoAluno.addDisciplina(codDisciplina, novaDisciplina);

                    novoCurso.addAluno(matricula,novoAluno);
                }
                linha = leitor.readLine();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        imprimirMediaCursos(cursos);
        imprimirMediaAlunos(cursos);

    }

    public static boolean isNumeric(String str) { //Testa se uma string é um valor numerico
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void imprimirMediaCursos(Map<String, Curso> cursos) {
        System.out.println("------ Média dos cursos ------ " );
        for (String key : cursos.keySet()) {
            int cr = (int)Math.round(cursos.get(key).CalcularCR()) ;
            //double cr = cursos.get(key).CalcularCR();
            System.out.println( key + ": " + cr);
        }
    }

    public static void imprimirMediaAlunos(Map<String, Curso> cursos) {
        System.out.println("------ Média dos Alunos ------ " );
        for (String key : cursos.keySet()) {
            for(String chave : cursos.get(key).mapAlunos().keySet()) {
                int cr = (int)Math.round(cursos.get(key).mapAlunos().get(chave).calcularCR());
                //double cr = cursos.get(key).mapAlunos().get(chave).calcularCR();
                System.out.println( chave + ": " + cr);
            }
        }
        }
}


