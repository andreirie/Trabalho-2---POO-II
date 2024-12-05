package exemplo.curso1.controller;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import exemplo.curso1.dao.EstudanteDAO;

public class ExcluiEstudanteController {

    @FXML
    private Button btnConf;

    @FXML
    private TextField txtIDEst;

    private EstudanteDAO estudanteDAO = new EstudanteDAO();

    @FXML
    public void initialize() {
    }

    @FXML
    private void btnConfOnAction(ActionEvent event) {
        try {
            int estId = Integer.parseInt(txtIDEst.getText());
            estudanteDAO.excluir(estId);
            mostrarAlerta("Estudante removido com sucesso!", "Você clicou no botão Confirmar");
        } catch (SQLException e1) {
            mostrarAlerta("Estudante não foi removido com sucesso!", "Você clicou no botão Cancelar");
            e1.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.show();
    }


    @FXML
    void btnVoltarOnAction(ActionEvent event) {
        // Obtém o stageAtual (janela que será fechada)
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Fecha o Stage atual
        stageAtual.close();
    }
}