import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CentroDAO {


    public static ArrayList<Centro> obtenerCentros(Connection conex) throws SQLException {
        ArrayList<Centro> listaCentros = new ArrayList<>();

        String sentenciaSQL= "select c.*, e.NomEmp\n" +
                "from centro c, empleado e\n" +
                "where e.CodEmp = c.CodEmpDir\n";

        PreparedStatement ps = conex.prepareStatement(sentenciaSQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Centro c = new Centro(rs.getString(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            listaCentros.add(c);
        }


        return listaCentros;
    }

}
