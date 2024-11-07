package exemplo.curso1.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exemplo.curso1.model.*;

public class EstudanteDAO implements IEstudante, IConst {
    private String sql;

    public void inserir(Estudante estudante) throws SQLException {
        sql = "INSERT INTO estudante (nome,idade) VALUES (?,?)";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, estudante.getNome());
            pstmt.setInt(2,estudante.getIdade());
            pstmt.executeUpdate();
        }
    }

    public void atualizar(Estudante estudante) throws SQLException {
        String sql = "UPDATE estudante SET nome = ?, idade = ? WHERE estudante_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, estudante.getNome());
            pstmt.setInt(2, estudante.getIdade());
            pstmt.setInt(3, estudante.getEstudanteID());
            pstmt.executeUpdate();
        }
    }


    public List<Estudante> buscarTodos() throws SQLException {
        sql = "SELECT * FROM estudante";
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setIdade(rs.getInt("idade"));
                estudantes.add(estudante);
            }
        }
        return estudantes;
    }

    public void excluir(int id) throws SQLException {
        sql = "DELETE FROM estudante WHERE estudante_id = ?";

        try (Connection conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
