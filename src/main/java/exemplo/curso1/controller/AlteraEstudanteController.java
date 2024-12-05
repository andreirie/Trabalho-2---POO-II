package exemplo.curso1.controller;

import exemplo.curso1.model.Estudante;
import exemplo.curso1.dao.EstudanteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlteraEstudanteController {

    @FXML
    private TextField txtNomEst;

    @FXML
    private TextField txtIdadEst;

    @FXML
    private Button btnConf;

    private Estudante estudante;

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
        txtNomEst.setText(estudante.getNome());
        txtIdadEst.setText(String.valueOf(estudante.getIdade()));
    }

    @FXML
    private void btnConfOnAction() {
        try {
            String nome = txtNomEst.getText();
            int idade = Integer.parseInt(txtIdadEst.getText());

            if (nome.isEmpty()) {
                throw new IllegalArgumentException("O nome não pode estar vazio.");
            }

            estudante.setNome(nome);
            estudante.setIdade(idade);

            EstudanteDAO estudanteDAO = new EstudanteDAO();
            estudanteDAO.atualizar(estudante);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Estudante atualizado com sucesso.");
            alert.show();
            fecharJanela();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Idade inválida. Por favor, insira um número.");
            alert.show();
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro ao atualizar estudante.");
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
