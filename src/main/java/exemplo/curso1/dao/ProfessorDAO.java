package exemplo.curso1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exemplo.curso1.model.*;

public class ProfessorDAO implements IProfessor, IConst {
    private String sql;

    public void inserir(Professor professor) throws SQLException {
        sql = "INSERT INTO professor (nome,idade) VALUES (?,?)";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2,professor.getIdade());
            pstmt.executeUpdate();
        }
    }

    public void atualizar(Professor professor) throws SQLException {
        String sql = "UPDATE professor SET nome = ?, idade = ? WHERE professor_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, professor.getNome());
            pstmt.setInt(2, professor.getIdade());
            pstmt.setInt(3, professor.getProfessorID());
            pstmt.executeUpdate();
        }
    }


    public List<Professor> buscarTodos() throws SQLException {
        sql = "SELECT * FROM professor";
        List<Professor> professors = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

    public void excluir(int id) throws SQLException {
        sql = "DELETE FROM professor WHERE professor_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
