package sample.Onglet4;

import ecole.Annee;
import ecole.Classe;
import ecole.Ecole;
import ecole.Matiere;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private final transient Ecole isen = Ecole.getInstance();

    @FXML
    private transient ComboBox<Annee> selectionAnnee;

    @FXML
    private transient BarChart<String, Number> barChart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        for (Annee annee : Annee.values()) {
            selectionAnnee.getItems().add(annee);
        }
    }

    @FXML
    private void displayChart() {//NOPMD

        barChart.getData().clear();

        if (selectionAnnee.getValue() != null) {

            final XYChart.Series dataSeries = new XYChart.Series();
            ArrayList<Classe> classes = new ArrayList<>(isen.getListeDesClasse());
            classes.removeIf(classe -> (classe.getAnnee() != selectionAnnee.getValue()));


            classes.forEach((Classe classe) -> {
                double moyenne = 20;
                // TODO: Recuper les moyennes generales par classe
                dataSeries.getData().add(new XYChart.Data(classe.getNomClasse(), moyenne));

            });
            barChart.getData().add(dataSeries);
        }
    }
}
