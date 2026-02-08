package org.example.tema5.AreaChart;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

public class AreaChartController {
    @FXML
    private AreaChart<String, Integer> areaChart;
    @FXML
    private StackedAreaChart<String, Integer> stackedAreaChart;


    @FXML
    private void initialize() {
        initAreaChart();
    }

    private void initAreaChart() {
        XYChart.Series<String, Integer> seriesApril = new XYChart.Series<>();
        XYChart.Series<String, Integer> seriesMayo = new XYChart.Series<>();
        seriesApril.setName("Abril");
        seriesApril.getData().add(new XYChart.Data<String, Integer>("1", 4));
        seriesApril.getData().add(new XYChart.Data<String, Integer>("3", 6));
        seriesApril.getData().add(new XYChart.Data<String, Integer>("6", 15));
        seriesApril.getData().add(new XYChart.Data<String, Integer>("9", 8));
        seriesApril.getData().add(new XYChart.Data<String, Integer>("11", 10));
        seriesApril.getData().add(new XYChart.Data<String, Integer>("13", 20));

        seriesMayo.setName("Mayo");
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("1", 8));
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("3", 2));
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("6", 5));
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("9", 8));
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("11", 18));
        seriesMayo.getData().add(new XYChart.Data<String, Integer>("13", 15));

        areaChart.getData().add(seriesApril);
        areaChart.getData().add(seriesMayo);
    }


}
