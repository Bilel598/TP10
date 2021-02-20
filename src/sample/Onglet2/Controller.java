package sample.Onglet2;

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
    private transient ComboBox<Matiere> selectionMatiere;

    @FXML
    private transient ComboBox<Number> selectionNote;

    @FXML
    private transient BarChart<String, Number> barChart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        for (Annee annee : Annee.values()) {
            selectionAnnee.getItems().add(annee);
        }

        for (Matiere matiere : Matiere.values()) {
            selectionMatiere.getItems().add(matiere);
        }
        selectionNote.getItems().addAll(1,2);
        if (selectionMatiere.getValue() == (Matiere.SPORT) || selectionMatiere.getValue() == (Matiere.MUSIQUE)) {
            selectionNote.getItems().add(3);
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
                for(Eleve eleve: classe.getListeEleve()) {
                    dataSeries.getData().add(new XYChart.Data(classe.getNomClasse(), eleve.getListeNote().get(selectionMatiere.getValue()).get((selectionNote.getValue().intValue()))));
                }
            });
            barChart.getData().add(dataSeries);
        }
    }
}
