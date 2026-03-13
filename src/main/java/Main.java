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

                /*
                Campos:
                [0]Matricula;
                [1]Cod. Disciplina;
                [2]Cod. Curso;
                [3]Nota;
                [4]Carga Horaria;
                [5]Ano Semestre
                */

                if (isNumeric(campos[0])) { //Testa se este campo é realmente uma matricula, considerando que a matricula é um numero

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

                    //Criando disciplina
                    Disciplina novaDisciplina = new Disciplina(codDisciplina, cargaHoraria, nota, anoSemestre);

                    //Criando aluno
                    Aluno novoAluno = new Aluno(matricula);
                    novoAluno.addDisciplina(codDisciplina, novaDisciplina);

                    novoCurso.addAluno(matricula,novoAluno);
                }
                /*
                System.out.println(linha);
                String separador = " | ";
                System.out.println(matricula + separador + codDisciplina + separador + codCurso+ separador + nota+ separador + cargaHoraria+ separador + anoSemestre);
                */
                linha = leitor.readLine();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        imprimirMediaCursos(cursos);
        imprimirMediaAlunos(cursos);
      //  calcularAlunos(cursos);
      //  calcularDisciplinas(cursos);


    }

    public static void calcularCursos(Map<String, Curso> cursos) {

        int c = 0;
        //System.out.println("Cursos:");
        for (String key : cursos.keySet()) {
            c += 1;
            //System.out.println(key);
        }
        System.out.println("Quantidade de Cursos: " + c);
    }

    public static void calcularAlunos(Map<String, Curso> cursos) {

        int a = 0;
        //System.out.println("Cursos:");
        for (String key : cursos.keySet()) {
            a += cursos.get(key).mapAlunos().size();
            //System.out.println("Quantidade de Alunos do curso " + key +": " + a);
        }
        System.out.println("Quantidade de Alunos: " + a);
    }

    public static void calcularDisciplinas(Map<String, Curso> cursos) {

        int d = 0;
        //System.out.println("Cursos:");
        for (String key : cursos.keySet()) {
            for (String chave : cursos.get(key).mapAlunos().keySet()) {
                d += cursos.get(key).mapAlunos().get(chave).mapDisciplinas().size();
//                for (String chavi : cursos.get(key).mapAlunos().keySet()) {
//                    System.out.println(cursos.get(key).mapAlunos().get(chave).getDisciplina(chavi));
//                }

            }
        }
        System.out.println("Quantidade de Disciplinas: " + d);

    }

    public static void imprimirCurso(Curso curso) {
        System.out.println("Curso: " );
        int n = 1;
        for(String key: curso.mapAlunos().keySet()) {//Para cada key desse map OU Para cada aluno deste curso
           System.out.println("Aluno "+ n + " :" + key );
           n ++;
        }
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


