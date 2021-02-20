package sample.Onglet1;

import ecole.Annee;
import ecole.Classe;
import ecole.Ecole;
import ecole.Matiere;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final transient Ecole isen = Ecole.getInstance();
    @FXML
    private transient ComboBox<Annee> selectionAnnee;

    @FXML
    private transient ComboBox<Matiere> selectionMatiere;

    @FXML
    private transient CheckBox addCheckBox;

    @FXML
    private transient BarChart<String, Number> chart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {

        for (Annee annee : Annee.values()) {
            selectionAnnee.getItems().add(annee);
        }
        for (Matiere matiere : Matiere.values()) {
            selectionMatiere.getItems().add(matiere);
        }

    }

    @FXML
    private void addBtn() {//NOPMD
        displayChart(addCheckBox.isSelected());
    }

    @FXML
    private void clearBtn() {//NOPMD//NOPMD
        chart.getData().clear();
    }

    private void displayChart(final boolean add) {
        if (!add) {
            chart.getData().clear();
            chart.getData().clear();
        }

        if (selectionMatiere.getValue() != null && selectionAnnee.getValue() != null) {

            final XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName(selectionMatiere.getValue().toString());

            ArrayList<Classe> classes = new ArrayList<>(isen.getListeDesClasse());
            classes.removeIf(classe -> (classe.getAnnee() != selectionAnnee.getValue()));

            classes.forEach((Classe classe) -> {
                dataSeries1.getData().add(new XYChart.Data(classe.getNomClasse(), classe.getMoyenneMatiere(selectionMatiere.getValue())));
            });


            chart.getData().add(dataSeries1);
        }
    }
}
