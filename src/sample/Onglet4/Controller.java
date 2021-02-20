package sample.Onglet4;

import ecole.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ArrayList;
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
    private void displayChart() {

        barChart.getData().clear();

        if (selectionAnnee.getValue() != null) {

            final XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(selectionAnnee.getValue().toString());

            ArrayList<Classe> classes = new ArrayList<>(isen.getListeDesClasse());
            classes.removeIf(classe -> (classe.getAnnee() != selectionAnnee.getValue()));


            classes.forEach((Classe classe) -> {
                double moyenne = 0.0;
                for (Eleve eleve: classe.getListeEleve()) {
                    moyenne += eleve.getMoyenneGenerale();
                }
                dataSeries.getData().add(new XYChart.Data(classe.getNomClasse(), moyenne/20));

            });
            barChart.getData().add(dataSeries);
        }
    }
}
