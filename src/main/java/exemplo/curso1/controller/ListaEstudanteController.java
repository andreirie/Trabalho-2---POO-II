package exemplo.curso1.controller;

import exemplo.curso1.model.Estudante;
import exemplo.curso1.dao.EstudanteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ListaEstudanteController {

    @FXML
    private TableView<Estudante> tableEstudantes;

    @FXML
    private TableColumn<Estudante, String> colNome;

    @FXML
    private TableColumn<Estudante, Integer> colIdade;

    @FXML
    private Button btnEditar;

    private EstudanteDAO estudanteDAO = new EstudanteDAO();

    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));

        carregarEstudantes();
    }

    private void carregarEstudantes() {
        try {
            List<Estudante> estudantes = estudanteDAO.buscarTodos();
            ObservableList<Estudante> estudantesList = FXCollections.observableArrayList(estudantes);
            tableEstudantes.setItems(estudantesList);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar estudantes.");
            alert.show();
        }
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) {
        Estudante estudanteSelecionado = tableEstudantes.getSelectionModel().getSelectedItem();
        if (estudanteSelecionado != null) {
            abrirTelaEdicao(estudanteSelecionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um estudante para editar.");
            alert.show();
        }
    }

    private void abrirTelaEdicao(Estudante estudante) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/exemplo/curso1/view/AlteraEstudante.fxml"));
            Parent root = loader.load();

            AlteraEstudanteController controller = loader.getController();
            controller.setEstudante(estudante);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Estudante");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao abrir a tela de edição.");
            alert.show();
        }
    }


    @FXML
    private void btnVoltarOnAction(ActionEvent event) {
        Stage stageAtual = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
