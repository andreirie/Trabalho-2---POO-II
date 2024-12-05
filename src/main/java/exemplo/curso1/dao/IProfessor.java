package exemplo.curso1.dao;

import java.sql.SQLException;
import java.util.List;

import exemplo.curso1.model.Professor;

/* Refatoração 7
   Autor: André
   Uso de Remove Redundant Modifier para remover modificadores desnecessários
   Objetivo: deixar o código claro e melhorar manuteniblidade
*/
public interface IProfessor {

    void inserir(Professor professor) throws SQLException;

    void atualizar(Professor professor) throws SQLException;

    List<Professor> buscarTodos() throws SQLException;

    void excluir(int id) throws SQLException;
}
