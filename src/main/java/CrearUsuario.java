import java.sql.*;
import java.util.Scanner;
public class CrearUsuario {
    public static void main(String[] args) {
        try {
            Conexion conexion = new Conexion();

            // 1. Conectar a la base de datos
            Connection conn = conexion.Conexion();

            // 2. Obtener los datos del nuevo usuario ingresados por consola
            Scanner scannerUser = new Scanner(System.in);
            System.out.println("Ingrese el nombre del usuario:");
            String nombres = scannerUser.nextLine();
            System.out.println("Ingrese la fecha de nacimiento del usuario (formato: yyyy-mm-dd):");
            String fechaNacimientoStr = scannerUser.nextLine();
            Date fechaNacimiento = Date.valueOf(fechaNacimientoStr);
            System.out.println("Ingrese el correo electrónico del usuario:");
            String correo = scannerUser.nextLine();
            System.out.println("Ingrese una clave para el usuario:");
            String clave = scannerUser.nextLine();

            // 3. Insertar el nuevo usuario en la tabla USUARIO
            String sql = "INSERT INTO usuario (nombres, fecha_nacimiento, correo, clave) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nombres);
            pstmt.setDate(2, fechaNacimiento);
            pstmt.setString(3, correo);
            pstmt.setString(4, clave);
            int filasAfectadas = pstmt.executeUpdate();

            // 4. Mostrar el número de filas afectadas
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int IDUsuario = rs.getInt(1);
                System.out.println(filasAfectadas + " fila(s) afectada(s)");
                System.out.println("El nuevo usuario que se creó fue: " + IDUsuario + ". " + nombres + " | " + fechaNacimiento + " |  " + correo + "| " + clave);
            }

            // 5. Cerrar la conexión y el PreparedStatement
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
    }
}

