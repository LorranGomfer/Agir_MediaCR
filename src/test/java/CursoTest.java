import modelos.Aluno;
import modelos.Curso;
import modelos.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CursoTest {

    private Curso curso;

    @BeforeEach
    void setUp() {
        curso = new Curso("ENG-MEC");
    }

    @Test
    void deveAdicionarNovoAlunoComSucesso() {
        Aluno aluno = new Aluno("1001");
        curso.addAluno("1001", aluno);

        assertTrue(curso.existAluno("1001"));
        assertEquals(1, curso.mapAlunos().size());
    }

    @Test
    void deveRealizarMergeDeDisciplinasQuandoAlunoJaExiste() {

        Aluno alunoPrimeiraVez = new Aluno("1001");
        Disciplina calc1 = new Disciplina("CALC1", 30, 60.0,100);
        alunoPrimeiraVez.addDisciplina("CALC1", calc1);
        curso.addAluno("1001", alunoPrimeiraVez);

        Aluno alunoSegundaVez = new Aluno("1001");
        Disciplina fis1 = new Disciplina("FIS1", 60, 60.0,100);
        alunoSegundaVez.addDisciplina("FIS1", fis1);
        curso.addAluno("1001", alunoSegundaVez);

        assertEquals(1, curso.mapAlunos().size(), "O curso deveria ter apenas um aluno único.");


        Aluno alunoNoMapa = curso.getAluno("1001");
        assertEquals(2, alunoNoMapa.mapDisciplinas().size(), "O aluno deveria ter as duas disciplinas após o merge.");
        assertTrue(alunoNoMapa.existDisciplina("CALC1"));
        assertTrue(alunoNoMapa.existDisciplina("FIS1"));
    }

    @Test
    void deveCalcularMediaDoCursoCorretamente() {

        Aluno alunoA = new Aluno("A");
        alunoA.addDisciplina("D1", new Disciplina("D1", 40, 10.0,100));
        curso.addAluno("A", alunoA);

        Aluno alunoB = new Aluno("B");
        alunoB.addDisciplina("D2", new Disciplina("D2",40 , 6.0,100));
        curso.addAluno("B", alunoB);

        assertEquals(8.0, curso.CalcularCR(), 0.001, "A média geral do curso está incorreta.");
    }
}