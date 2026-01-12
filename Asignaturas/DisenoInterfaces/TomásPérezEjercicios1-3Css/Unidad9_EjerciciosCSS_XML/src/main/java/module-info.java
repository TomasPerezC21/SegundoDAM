module org.example.unidad9_ejercicioscss_xml {
    requires javafx.controls;
    requires javafx.fxml;

    // --- Paquete raíz (seguramente ya tenías esto) ---

    // --- AÑADE ESTAS LÍNEAS PARA ARREGLAR EL ERROR DEL EJERCICIO 1 ---

    // Esto permite a JavaFX ver tu clase y ejecutarla (Arregla el error "does not export")
    exports org.example.unidad9_ejercicioscss_xml.flowpanecss;

    // Esto permite a JavaFX cargar el FXML correctamente
    opens org.example.unidad9_ejercicioscss_xml.flowpanecss to javafx.fxml;


    // --- PREPARA YA EL TERRENO PARA LOS SIGUIENTES EJERCICIOS ---

   exports org.example.unidad9_ejercicioscss_xml.layoutCompleto;
   opens org.example.unidad9_ejercicioscss_xml.layoutCompleto to javafx.fxml;

   exports org.example.unidad9_ejercicioscss_xml.layoutAnterior;
   opens org.example.unidad9_ejercicioscss_xml.layoutAnterior to javafx.fxml;
}