import java.sql.*;
import java.util.ArrayList;

public class Examen  {

    private static Connection conexion;
    private static String url = "jdbc:mysql://localhost:3306/examenjdbc";
    private static String usuario = "root";
    private static String password = "alumnoDAM";

    public static void main(String[] args) {

        conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión realizada correctamente.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }

        try {
            ejercicio1(conexion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ejercicio2(conexion);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void ejercicio1(Connection conexion) throws SQLException {

        CentroDAO dao = new CentroDAO();
        ArrayList<Centro> listaCentros = dao.obtenerCentros(conexion);

        System.out.println("==== Lista de Centros: ====");
        for (int i = 0; i < listaCentros.size(); i++) {
            System.out.println(listaCentros.get(i));
        }
        System.out.println();


    }

    public static void ejercicio2(Connection conex) throws SQLException {

        listadoDepartamento(conex);

    }

    private static void listadoDepartamento(Connection conex) throws SQLException {

        String funcionSQL = " { ? = call gasto_salarios_departamento(?)}";

        String sentenciaSQL1 = "select d.NomDep, e.NomEmp, d.PreAnu, d.CodDep\n" +
                "from departamento d, centro c, empleado e\n" +
                "where d.CodCen = d.CodCen\n" +
                "and d.CodEmpDir = e.CodEmp\n" +
                "group by d.NomDep\n";

        ResultSet rs;
        try (PreparedStatement pst = conex.prepareStatement(sentenciaSQL1)) {
            rs = pst.executeQuery();

            while (rs.next()) {
                double gastoSalario = llamarFuncion(funcionSQL, rs.getString(4), conex);
                System.out.println("===== DEPARTAMENTO: " + rs.getString(4) + " ====");
                System.out.println(rs.getString(1)
                        + " | " + rs.getString(2)
                        + " | Presupuesto anual: " + rs.getLong(3)
                        + " | " + "Gasto en salarios: " + gastoSalario);

                System.out.println("===== EMPLEADOS =====");
                empleadosPorDepartamento(rs.getString(4), conex);
                System.out.println();
            }
        }
        rs.close();

    }

    private static void empleadosPorDepartamento(String codDep, Connection conex) throws SQLException {

        String sentenciaSQL = "select e.NomEmp, e.SalEmp\n" +
                "from empleado e, departamento d\n" +
                "where e.CodDep = d.CodDep\n" +
                "and d.CodDep = ?\n";

        ResultSet rs;
        try (PreparedStatement pst = conex.prepareStatement(sentenciaSQL)) {
            pst.setString(1, codDep);
            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | Sueldo: " + rs.getString(2));
            }
        }
        rs.close();
    }

    private static double llamarFuncion(String funcionSQL, String codDep, Connection conex) throws SQLException {

        try (CallableStatement cs = conex.prepareCall(funcionSQL)) {
            cs.registerOutParameter(1, Types.DECIMAL);
            cs.setString(2, codDep);
            cs.execute();


            return cs.getDouble(1);
        }

    }
}
