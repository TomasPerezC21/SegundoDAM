package org.example.tema5.ScatterChart;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class ScatterChartController {

    @FXML
    private ScatterChart<String, Double> scatterChart;


    @FXML
    private void initialize() {
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        series1.setName("Renta variable");
        series1.getData().add(new XYChart.Data<String, Double>("1", 193.2));
        series1.getData().add(new XYChart.Data<String, Double>("2", 29.6));
        series1.getData().add(new XYChart.Data<String, Double>("3", 42.3));
        series1.getData().add(new XYChart.Data<String, Double>("4", 20.3));
        series1.getData().add(new XYChart.Data<String, Double>("5", 80.2));

        scatterChart.getData().add(series1);
    }
}
