package org.example.tema5.BarChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.util.Arrays;

public class BarChartController {

    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis xAxisBar;

    ObservableList<String> paisesNames = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        String [] paises = new String[]{"Austria","Brazil","Italy","USA"};
        paisesNames = FXCollections.observableArrayList();
        paisesNames.addAll(Arrays.asList(paises));

        xAxisBar.setCategories(paisesNames);

        barChart.getData().add(initDatos1());
        barChart.getData().add(initDatos2());
        barChart.getData().add(initDatos3());

    }

    private XYChart.Series<String, Double> initDatos1(){
        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<String, Double>("Austria", 25601.34));
        series1.getData().add(new XYChart.Data<String, Double>("Brazil", 34212.22));
        series1.getData().add(new XYChart.Data<String, Double>("France", 18901.33));
        series1.getData().add(new XYChart.Data<String, Double>("Italy", 12345.33));
        series1.getData().add(new XYChart.Data<String, Double>("USA", 12347.33));
        return series1;
    }

    private XYChart.Series<String, Double> initDatos2(){
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<String, Double>("Austria", 25601.34));
        series2.getData().add(new XYChart.Data<String, Double>("Brazil", 34212.22));
        series2.getData().add(new XYChart.Data<String, Double>("France", 18901.33));
        series2.getData().add(new XYChart.Data<String, Double>("Italy", 12345.33));
        series2.getData().add(new XYChart.Data<String, Double>("USA", 112345.33));
        return series2;
    }

    private XYChart.Series<String, Double> initDatos3(){
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<String, Double>("Austria", 256120.34));
        series3.getData().add(new XYChart.Data<String, Double>("Brazil", 342132.22));
        series3.getData().add(new XYChart.Data<String, Double>("France", 189422.33));
        series3.getData().add(new XYChart.Data<String, Double>("Italy", 123552.33));
        series3.getData().add(new XYChart.Data<String, Double>("USA", 112342.33));
        return series3;
    }
}
