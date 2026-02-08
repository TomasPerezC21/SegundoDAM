package org.example.tema5.LineChartConCSSGlobal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

public class LineChartController {

    @FXML
    private LineChart lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private void initialize() {

        String[] months = DateFormatSymbols
                .getInstance(Locale.ENGLISH)
                .getShortMonths();

        ObservableList<String> monthNames = FXCollections.observableArrayList(
                Arrays.copyOf(months, months.length - 1)
        );

        xAxis.setCategories(monthNames);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias 2018");

        series.getData().add(new XYChart.Data<>("Jan", 23));
        series.getData().add(new XYChart.Data<>("Feb", 14));
        series.getData().add(new XYChart.Data<>("Mar", -15));
        series.getData().add(new XYChart.Data<>("Apr", 24));
        series.getData().add(new XYChart.Data<>("May", -34));
        series.getData().add(new XYChart.Data<>("Jun", 36));
        series.getData().add(new XYChart.Data<>("Jul", 33));
        series.getData().add(new XYChart.Data<>("Aug", 45));
        series.getData().add(new XYChart.Data<>("Sep", -10));
        series.getData().add(new XYChart.Data<>("Oct", 15));
        series.getData().add(new XYChart.Data<>("Nov", 12));
        series.getData().add(new XYChart.Data<>("Dec", -30));

        lineChart.getData().add(series);
    }

}
