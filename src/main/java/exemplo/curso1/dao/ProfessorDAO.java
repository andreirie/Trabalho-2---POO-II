package exemplo.curso1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exemplo.curso1.model.*;

public class ProfessorDAO implements IProfessor, IConst {

    /* Refatoração 8
   Autor: André
   Uso de Replace Magic Numbers para criar constantes contendo as consultas SQL
   Objetivo: deixar o código claro e melhorar manuteniblidade
    */
    private static final String INSERT_SQL = "INSERT INTO professor (nome, idade) VALUES (?, ?)";
    private static final String UPDATE_SQL = "UPDATE professor SET nome = ?, idade = ? WHERE professor_id = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM professor";
    private static final String DELETE_SQL = "DELETE FROM professor WHERE professor_id = ?";

    // Método para obter a conexão com o banco
    private Connection getConnection() throws SQLException {
        return Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
    }

    // Método para inserir um professor
    public void inserir(Professor professor) throws SQLException {
        try (Connection conexao = getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(INSERT_SQL)) {
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2, professor.getIdade());
            pstmt.executeUpdate();
        }
    }

    // Método para atualizar um professor
    public void atualizar(Professor professor) throws SQLException {
        try (Connection conexao = getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(UPDATE_SQL)) {
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2, professor.getIdade());
            pstmt.setInt(3, professor.getProfessorID());
            pstmt.executeUpdate();
        }
    }

    // Método para buscar todos os professores
    public List<Professor> buscarTodos() throws SQLException {
        List<Professor> professors = new ArrayList<>();

        try (Connection conexao = getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setProfessorID(rs.getInt("professor_id"));
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professors.add(professor);
            }
        }
        return professors;
    }

    // Método para excluir um professor
    public void excluir(int id) throws SQLException {
        try (Connection conexao = getConnection();
             PreparedStatement pstmt = conexao.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao excluir o professor.", e);
        }
    }
}
