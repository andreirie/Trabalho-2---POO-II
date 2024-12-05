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

    private int estudanteId;

    @FXML
    public void initialize() {
    }

    @FXML
    private void btnConfOnAction(ActionEvent event) {
        try {
            estudanteId = getEstudanteId();
            estudanteDAO.excluir(estudanteId);
            mostrarAlerta("Estudante removido com sucesso!", "Você clicou no botão Confirmar");
        } catch (SQLException e1) {
            mostrarAlerta("Estudante não foi removido com sucesso!", "Você clicou no botão Cancelar");
            e1.printStackTrace();
        }
    }


    private int getEstudanteId() {
        return Integer.parseInt(txtIDEst.getText());
    }


    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION, mensagem, ButtonType.OK);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.show();
    }


    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}