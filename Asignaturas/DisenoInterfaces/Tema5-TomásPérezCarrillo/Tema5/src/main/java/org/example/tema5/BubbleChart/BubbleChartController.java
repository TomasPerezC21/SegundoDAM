package org.example.tema5.BubbleChart;

import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BubbleChartController {

    @FXML
    private BubbleChart <Integer, Integer> bubbleChart;

    @FXML
    NumberAxis xAxis;

    @FXML
    NumberAxis yAxis;

    @FXML
    private void initialize(){
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, " "));

        XYChart.Series<Integer, Integer> series1 = new XYChart.Series<Integer, Integer>();
        series1.setName("Producto 1");
        series1.getData().add(new XYChart.Data<Integer, Integer>(3,35,2));
        series1.getData().add(new XYChart.Data<Integer, Integer>(4,25,1.3));
        bubbleChart.getData().add(series1);

        XYChart.Series<Integer, Integer> series2 = new XYChart.Series<Integer, Integer>();
        series2.setName("Producto 2");
        series2.getData().add(new XYChart.Data<Integer, Integer>(10,20,2));
        series2.getData().add(new XYChart.Data<Integer, Integer>(30,10,4.5));
        bubbleChart.getData().add(series2);
    }
}
