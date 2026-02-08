module org.example.tema5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens org.example.tema5.BarChart to javafx.fxml;
    exports org.example.tema5.BarChart;

    opens org.example.tema5.AreaChart to javafx.fxml;
    exports org.example.tema5.AreaChart;

    opens org.example.tema5.BubbleChart to javafx.fxml;
    exports org.example.tema5.BubbleChart;

    opens org.example.tema5.PieChart to javafx.fxml;
    exports org.example.tema5.PieChart;

    opens org.example.tema5.ScatterChart to javafx.fxml;
    exports org.example.tema5.ScatterChart;

    opens org.example.tema5.LineChartConCSSGlobal to javafx.fxml;
    exports org.example.tema5.LineChartConCSSGlobal;
}