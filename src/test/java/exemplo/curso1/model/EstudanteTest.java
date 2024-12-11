package exemplo.curso1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstudanteTest {

    private Estudante estudante;

    @BeforeEach
    void setUp() {
        estudante = new Estudante(1, "Carlos Silva", 20);
    }

    @Test
    void testGetAndSetEstudanteID() {
        assertEquals(1, estudante.getEstudanteID(), "O ID do estudante deve ser 1.");
        estudante.setEstudanteID(2);
        assertEquals(2, estudante.getEstudanteID(), "O ID do estudante não foi corretamente atribuído.");
    }

    @Test
    void testGetAndSetNome() {
        assertEquals("Carlos Silva", estudante.getNome(), "O nome inicial deve ser 'Carlos Silva'.");
        estudante.setNome("Ana Pereira");
        assertEquals("Ana Pereira", estudante.getNome(), "O nome não foi corretamente atribuído.");
    }

    @Test
    void testGetAndSetIdade() {
        assertEquals(20, estudante.getIdade(), "A idade inicial deve ser 20.");
        estudante.setIdade(25);
        assertEquals(25, estudante.getIdade(), "O valor da idade não foi corretamente atribuído.");
    }

    @Test
    void testToString() {
        String expected = "Estudante{estudanteID=1, nome='Carlos Silva', idade=20}";
        assertEquals(expected, estudante.toString(), "O método toString() não retornou o valor esperado.");
    }
}