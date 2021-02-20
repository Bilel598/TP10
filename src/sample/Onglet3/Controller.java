package sample.Onglet3;

import Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private final transient Client client = Client.getInstance();

    @FXML
    private transient ComboBox<String> selectionMatiere;
    @FXML
    private transient BarChart<String, Number> barChart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        JSONArray matieres = new JSONArray(client.askServerArray("getMatiere()"));

        for (int i = 0; i < matieres.length(); i++) {
            selectionMatiere.getItems().add(matieres.get(i).toString());
        }

    }

    @FXML
    private void displayChart() {

        barChart.getData().clear();
        JSONArray classesJSON = client.askServerArray("getClasse()");
        JSONArray anneesJson = client.askServerArray("getAnnee()");



        if (selectionMatiere.getValue() != null) {
            final XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(selectionMatiere.getValue());


            ArrayList<String> annees = new ArrayList<>();
            for (int i = 0; i < anneesJson.length(); i++) {
                annees.add(anneesJson.get(i).toString());
            }

            for (String annee : annees) {
                ArrayList<JSONObject> classes = new ArrayList<>();
                for (int i = 0; i < classesJSON.length(); i++) {
                    classes.add((JSONObject) classesJSON.get(i));
                }

                Double sum = 0.0;
                JSONArray noteJson = (JSONArray) classe.getJSONObject("listeNoteClasse").get(selectionMatiere.getValue());
                for (int i =0; i < noteJson.toList().size(); i++) {
                    sum += noteJson.getDouble(i);
                }
                final List<Double> moyennes = new ArrayList<>();
                double moyenne = classes.stream().mapToDouble(classe -> classe.getJSONObject("listeNoteClasse").get(selectionMatiere.getValue())).average().getAsDouble();


                //dataSeries.getData().add(new XYChart.Data(annee.toString(), moyenne));
            }
            barChart.getData().add(dataSeries);
        }
    }
}
