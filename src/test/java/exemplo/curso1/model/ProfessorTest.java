package exemplo.curso1.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Professor professor;

    @BeforeEach
    void setUp() {
        professor = new Professor(1, "Daniel Silva", 30);
    }

    @Test
    void testGetProfessorID() {
        assertEquals(1, professor.getProfessorID(),"O ID do professor deve ser 1.");
    }

    @Test
    void testSetProfessorID() {
        professor.setProfessorID(2);
        assertNotEquals(1, professor.getProfessorID(),"O ID do professor não foi corretamente atribuído.");
    }

    @Test
    void testGetAndSetNome() {
        professor.setNome("João Pereira");
        assertEquals("João Pereira", professor.getNome(),"O nome não foi corretamente atribuído.");
    }

    @Test
    void testGetAndSetIdade() {
        professor.setIdade(50);
        assertEquals(50, professor.getIdade(),"O valor da idade não foi corretamente atribuído.");
    }

    @Test
    void testProfessorConstructor() {
        Professor professor1 = new Professor(2,"José Carvalho",60);
        assertNotNull(professor1, "O objeto Professor não deve ser null após a criação.");
    }

    @Test
    void testToString() {
        String expected = "Professor{professorID=1, nome='Daniel Silva', idade=30}";
        assertEquals(expected, professor.toString(),"O método toString() não retornou o valor esperado.");
    }
}
