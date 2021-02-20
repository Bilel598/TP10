package sample.Onglet4;

import Client.Client;
import ecole.*;
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
    private transient BarChart<String, Number> barChart;

    @Override
    public void initialize(final URL url, final ResourceBundle resourceBundle) {
        JSONArray annees = new JSONArray(client.askServerArray("getAnnee()"));

        for (int i = 0; i < annees.length(); i++) {
            selectionAnnee.getItems().add(annees.get(i).toString());
        }
    }

    @FXML
    private void displayChart() {
        JSONArray classesJSON = client.askServerArray("getClasse()");

        barChart.getData().clear();

        if (selectionAnnee.getValue() != null) {

            final XYChart.Series dataSeries = new XYChart.Series();
            dataSeries.setName(selectionAnnee.getValue().toString());

            ArrayList<JSONObject> classes = new ArrayList<>();
            for (int i = 0; i < classesJSON.length(); i++) {
                if (classesJSON.get(i).toString().contains(selectionAnnee.getValue())) {
                    classes.add((JSONObject) classesJSON.get(i));
                }
            }

            for (JSONObject classe : classes) {
                double moyenne = 0.0;
                JSONArray eleves = classe.getJSONArray("listeEleve");
                for (int i = 0; i < eleves.length(); i++) {
                    moyenne += eleves.getJSONObject(i).getDouble("moyenneGenerale");

                }
                dataSeries.getData().add(new XYChart.Data(classe.getString("nomClasse"), moyenne/20));

            }
            barChart.getData().add(dataSeries);
        }
    }
}
