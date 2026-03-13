import modelos.Disciplina;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DisciplinaTest {

    @Test
    void deveCriarDisciplinaComDadosCorretos() {
        String cod = "MEC001";
        double nota = 9.5;
        int ch = 60;
        int periodo = 20251;

        Disciplina disciplina = new Disciplina(cod, ch,nota , periodo);

        assertAll("Verificando atributos da disciplina",
                () -> assertEquals(cod, disciplina.getCodDisciplina(), "Código incorreto"),
                () -> assertEquals(nota, disciplina.getNota(), "Nota incorreta"),
                () -> assertEquals(ch, disciplina.getCargaHoraria(), "Carga horária incorreta"),
                () -> assertEquals(periodo, disciplina.getSemestre(), "Ano/Semestre incorreto")
        );
    }

    @Test
    void devePermitirAlterarNotaESemestre() {
        Disciplina disciplina = new Disciplina("TEMP", 30, 0.0, 0);

        disciplina.setNota(8.0);
        disciplina.setSemestre(20261);

        assertEquals(8.0, disciplina.getNota());
        assertEquals(20261, disciplina.getSemestre());
    }
}