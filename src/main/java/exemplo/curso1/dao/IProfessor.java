package exemplo.curso1.dao;

import java.sql.SQLException;
import java.util.List;

import exemplo.curso1.model.Professor;

public interface IProfessor {
    public void inserir(Professor professor) throws SQLException;
    public void atualizar(Professor professor) throws SQLException;
    public List<Professor> buscarTodos() throws SQLException;
    public void excluir(int id) throws SQLException;
}
