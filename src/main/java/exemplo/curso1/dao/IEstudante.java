package exemplo.curso1.dao;

import java.sql.SQLException;
import java.util.List;

import exemplo.curso1.model.Estudante;

public interface IEstudante {
    public void inserir(Estudante estudante) throws SQLException;
    public void atualizar(Estudante estudante) throws SQLException;
    public List<Estudante> buscarTodos() throws SQLException;
    public void excluir(int id) throws SQLException;
}
