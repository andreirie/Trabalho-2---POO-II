package exemplo.curso1.controller;

import exemplo.curso1.model.Professor;
import exemplo.curso1.dao.ProfessorDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlteraProfessorController {

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField txtIdadEst;

    @FXML
    private Button btnConf;

    private Professor professor;

    public void setprofessor(Professor professor) {
        this.professor = professor;
        txtNomEst.setText(professor.getNome());
        txtIdadEst.setText(String.valueOf(professor.getIdade()));
    }

    @FXML
    private void btnConfOnAction() {
        try {
            String nome = txtNomEst.getText();
            int idade = Integer.parseInt(txtIdadEst.getText());

            if (nome.isEmpty()) {
                throw new IllegalArgumentException("O nome não pode estar vazio.");
            }

            professor.setNome(nome);
            professor.setIdade(idade);

            ProfessorDAO professorDAO = new ProfessorDAO();
            professorDAO.atualizar(professor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "professor atualizado com sucesso.");
            alert.show();
            fecharJanela();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Idade inválida. Por favor, insira um número.");
            alert.show();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao atualizar professor.");
            alert.show();
            e.printStackTrace();
        }

    }

    @FXML
    void btnVoltarOnAction(ActionEvent event) {
        // Obtém o stageAtual (janela que será fechada)
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Fecha o Stage atual
        stageAtual.close();
    }

    private void fecharJanela() {
        Stage stage = (Stage) btnConf.getScene().getWindow();
        stage.close();
    }
}
