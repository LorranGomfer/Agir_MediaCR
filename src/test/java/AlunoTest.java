import modelos.Aluno;
import modelos.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("2024001");
    }

    @Test
    void deveCalcularCRCorretamenteComPesosDiferentes() {

        Disciplina d1 = new Disciplina("MAT01", 60, 40.0,100);
        Disciplina d2 = new Disciplina("FIS01", 60, 80.0,100);

        aluno.addDisciplina("MAT01", d1);
        aluno.addDisciplina("FIS01", d2);

        double crEsperado = 60.0;
        assertEquals(crEsperado, aluno.calcularCR(), 0.0001, "O cálculo do CR ponderado falhou!");
    }

    @Test
    void naoDeveSobrescreverDisciplinaJaExistente() {
        Disciplina d1 = new Disciplina("PROG1", 60, 40.0,100);
        Disciplina d2 = new Disciplina("PROG1", 60, 80.0,100);

        aluno.addDisciplina("PROG1", d1);
        aluno.addDisciplina("PROG1", d2);

        assertEquals(1, aluno.mapDisciplinas().size(), "O Map não deveria ter duplicatas.");
        assertEquals(40.0, aluno.getDisciplina("PROG1").getNota(), "A nota original foi sobrescrita!");
    }

    @Test
    void deveVerificarSeDisciplinaExiste() {
        Disciplina d1 = new Disciplina("EDO", 60, 80.0,100);
        aluno.addDisciplina("EDO", d1);

        assertTrue(aluno.existDisciplina("EDO"));
        assertFalse(aluno.existDisciplina("HISTORIA"));
    }

    @Test
    void deveManterMatriculaCorreta() {
        assertEquals("2024001", aluno.getMatricula());
    }
}