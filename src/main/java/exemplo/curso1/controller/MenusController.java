package exemplo.curso1.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenusController {

    @FXML
    private MenuItem menuEstudanteInclui;

    @FXML
    private MenuItem menuEstudanteListaAltera;

    @FXML
    private MenuItem telaVazia;

    @FXML
    private MenuItem menuProfessorInclui;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    /* Refatoração 6
   Autor: André
   Uso de Extract Method para criar um método genérico chamado carregarTela
   Objetivo: deixar o código claro e melhorar manuteniblidade
    */
    private void carregarTela(String fxmlFile, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle(titulo);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ação para incluir estudante
    @FXML
    void incluiOnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/IncluiEstudante.fxml", "Estudante");
    }

    // Ação para incluir professor
    @FXML
    void inclui1OnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/IncluiProfessor.fxml", "Professor");
    }

    // Ação para listar e alterar estudante
    @FXML
    void listaAlteraOnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/listaEstudante.fxml", "Estudante");
    }

    // Ação para listar e alterar professor
    @FXML
    void listaAltera1OnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/ListaProfessor.fxml", "Professor");
    }

    // Ação para excluir estudante
    @FXML
    void excluiOnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/excluiEstudante.fxml", "Estudante");
    }

    // Ação para excluir professor
    @FXML
    void exclui1OnAction(ActionEvent event) {
        carregarTela("/exemplo/curso1/view/excluiProfessor.fxml", "Professor");
    }
}
