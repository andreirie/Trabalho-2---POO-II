package exemplo.curso1.controller;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import exemplo.curso1.model.Estudante;
import exemplo.curso1.dao.EstudanteDAO;

public class IncluiEstudanteController {

    @FXML
    private Button btnConf;

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField txtIdadEst;

    @FXML
    public void initialize() {
        // Inicialização, se necessário
    }

    @FXML
    private void btnConfOnAction(ActionEvent event) {
        Estudante estudante = criarEstudante();

        try {
            inserirEstudante(estudante);
            showAlert(AlertType.INFORMATION, "Estudante cadastrado com sucesso!", "Informação", "Você clicou no botão Confirmar");
        } catch (SQLException e) {
            showAlert(AlertType.ERROR, "Estudante não foi cadastrado com sucesso!", "Erro", "Ocorreu um erro ao tentar cadastrar o estudante.");
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
        fecharStage(event);
    }

    /* Refatoração 5
       Autor: André
       Uso de Extract Method para criar um método separado chamado criarEstudante
       Objetivo: deixar o código claro e melhorar manuteniblidade
    */
    private Estudante criarEstudante() {
        Estudante estudante = new Estudante();
        estudante.setNome(txtNomEst.getText());
        estudante.setIdade(Integer.parseInt(txtIdadEst.getText()));
        return estudante;
    }

    // Insere o estudante no banco de dados usando EstudanteDAO
    private void inserirEstudante(Estudante estudante) throws SQLException {
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        estudanteDAO.inserir(estudante);
    }

    // Exibe um alerta genérico com os parâmetros fornecidos
    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType, contentText, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
    }

    // Fecha o estágio atual (janela)
    private void fecharStage(ActionEvent event) {
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
