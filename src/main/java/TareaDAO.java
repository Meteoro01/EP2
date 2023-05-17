import java.sql.*;

public class TareaDAO {
    private Connection connection;

    public TareaDAO(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection("bdep2grupal", "root", "root");
        TareaDAO tareaDAO = new TareaDAO(connection);
        tareaDAO.mostrarTareas();
        tareaDAO.insertarTarea(1, 5, "Tarea 5", "Descripción de la tarea 5");
        tareaDAO.mostrarTareas();
    }

    public static Connection getConnection(String db, String user, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + db + "?user=" + user + "&password=" + pass;
        return DriverManager.getConnection(url);
    }

    public void insertarTarea(int idProyecto, int idTarea, String tituloTarea, String descripcionTarea) {
        try {
            String sql = "INSERT INTO Tareas (IdProyecto, IdTarea, TituloTarea, DescripcionTarea) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, idProyecto);
            pstmt.setInt(2, idTarea);
            pstmt.setString(3, tituloTarea);
            pstmt.setString(4, descripcionTarea);
            pstmt.executeUpdate();
            System.out.println("Tarea insertada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar la tarea: " + e.getMessage());
        }
    }

    public void mostrarTareas() {
        try {
            String sql = "SELECT * FROM Tareas";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Tareas registradas:");
            while (rs.next()) {
                int idProyecto = rs.getInt("IdProyecto");
                int idTarea = rs.getInt("IdTarea");
                String tituloTarea = rs.getString("TituloTarea");
                String descripcionTarea = rs.getString("DescripcionTarea");
                System.out.println("Id Proyecto: " + idProyecto + ", Id Tarea: " + idTarea + ", Título: " + tituloTarea + ", Descripción: " + descripcionTarea);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las tareas: " + e.getMessage());
        }
    }
}
