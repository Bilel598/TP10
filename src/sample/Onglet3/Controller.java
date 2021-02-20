package sample.Onglet3;

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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Controller implements Initializable {//NOPMD


    private final transient Ecole isen = Ecole.getInstance();
    @FXML
    private transient ComboBox<Matiere> selectionMatiere;
    @FXML
    private transient BarChart<String, Number> barChart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        for (Matiere matiere : Matiere.values()) {
            selectionMatiere.getItems().add(matiere);
        }
    }

    @FXML
    private void displayChart() {

        barChart.getData().clear();


        if (selectionMatiere.getValue() != null) {
            final XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(selectionMatiere.getValue().toString());


            for (Annee annee : Annee.values()) {
                ArrayList<Classe> classes = new ArrayList<>(isen.getListeDesClasse());
                classes.removeIf(classe -> (classe.getAnnee() != annee));

                final List<Double> moyennes = new ArrayList<>();
                double moyenne = classes.stream().mapToDouble(classe -> classe.getMoyenneMatiere(selectionMatiere.getValue())).average().getAsDouble();

                dataSeries.getData().add(new XYChart.Data(annee.toString(), moyenne));
            }
            barChart.getData().add(dataSeries);
        }
    }
}
