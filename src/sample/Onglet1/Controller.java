package sample.Onglet1;

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
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final transient Client client = Client.getInstance();
    @FXML
    private transient ComboBox<String> selectionAnnee;

    @FXML
    private transient ComboBox<String> selectionMatiere;

    @FXML
    private transient BarChart<String, Number> chart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {

        JSONArray matieres = new JSONArray(client.askServerArray("getMatiere()"));
        JSONArray annees = new JSONArray(client.askServerArray("getAnnee()"));

        for (int i = 0; i < annees.length(); i++) {
            selectionAnnee.getItems().add(annees.get(i).toString());
        }
        for (int i = 0; i < matieres.length(); i++) {
            selectionMatiere.getItems().add(matieres.get(i).toString());
        }

    }

    @FXML
    private void displayChart() {
        JSONArray classesJSON = client.askServerArray("getClasse()");
        chart.getData().clear();
        chart.getData().clear();

        if (selectionMatiere.getValue() != null && selectionAnnee.getValue() != null) {

            final XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(selectionMatiere.getValue().toString());

            ArrayList<JSONObject> classes = new ArrayList<>();
            for (int i = 0; i < classesJSON.length(); i++) {
                if (classesJSON.get(i).toString().contains(selectionAnnee.getValue())) {
                    classes.add((JSONObject) classesJSON.get(i));
                }
            }

            for (JSONObject classe : classes) {
                Double sum = 0.0;
                JSONArray noteJson = (JSONArray) classe.getJSONObject("listeNoteClasse").get(selectionMatiere.getValue());
                for (int i =0; i < noteJson.toList().size(); i++) {
                    sum += noteJson.getDouble(i);
                }

                dataSeries.getData().add(new XYChart.Data(classe.getString("nomClasse"), sum/ noteJson.toList().size()));
            }



            chart.getData().add(dataSeries);
        }
    }
}
