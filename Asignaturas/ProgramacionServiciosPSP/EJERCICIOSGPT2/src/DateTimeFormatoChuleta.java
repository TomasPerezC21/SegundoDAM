import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatoChuleta {

    public static void main(String[] args) {

        DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm:ss");
        String linea = LocalDateTime.now().format(FMT);
        System.out.println(linea);

    }

}
