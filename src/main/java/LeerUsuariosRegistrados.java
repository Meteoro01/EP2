import java.sql.*;

public class LeerUsuariosRegistrados {

    public static void main (String[] args) throws SQLException {
            // Llamada al método para listar
            leerRegistros();
        }
    public static void leerRegistros() throws SQLException {
        Connection conn = Conexion.Conexion();

            try (conn) {
            String consulta = "SELECT nombres, fecha_nacimiento, correo, clave FROM usuario";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombres = resultado.getString("nombres");
                String fechaNacimiento = resultado.getString("fecha_nacimiento");
                String correo = resultado.getString("correo");

                System.out.println("Nombres: " + nombres);
                System.out.println("Fecha de nacimiento: " + fechaNacimiento);
                System.out.println("Correo: " + correo);
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

