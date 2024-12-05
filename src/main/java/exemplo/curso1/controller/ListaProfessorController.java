package exemplo.curso1.controller;

import exemplo.curso1.model.Professor;
import exemplo.curso1.dao.ProfessorDAO;
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

public class ListaProfessorController {

    @FXML
    private TableView<Professor> tableprofessors;

    @FXML
    private TableColumn<Professor, Integer> colId;

    @FXML
    private TableColumn<Professor, String> colNome;

    @FXML
    private TableColumn<Professor, Integer> colIdade;

    @FXML
    private Button btnEditar;

    private ProfessorDAO professorDAO = new ProfessorDAO();

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("professorID"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));

        carregarprofessors();
    }

    private void carregarprofessors() {
        try {
            List<Professor> professors = professorDAO.buscarTodos();
            ObservableList<Professor> professorsList = FXCollections.observableArrayList(professors);
            tableprofessors.setItems(professorsList);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao carregar professors.");
            alert.show();
        }
    }

    @FXML
    private void btnEditarOnAction(ActionEvent event) {
        Professor professorSelecionado = tableprofessors.getSelectionModel().getSelectedItem();
        if (professorSelecionado != null) {
            abrirTelaEdicao(professorSelecionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione um professor para editar.");
            alert.show();
        }
    }

    private void abrirTelaEdicao(Professor professor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/exemplo/curso1/view/Alteraprofessor.fxml"));
            Parent root = loader.load();

            AlteraProfessorController controller = loader.getController();
            controller.setprofessor(professor);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar professor");
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
