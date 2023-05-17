import java.sql.*;

public class Conexion {
    public static   Connection Conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/" + "bdep2grupal";
        return DriverManager.getConnection(url, "root", "root");
    }
    public  static Statement statement() throws SQLException {
        return Conexion().createStatement();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection con = Conexion();
        Statement statement = con.createStatement();
        //un query para verificar si funciona o no la conexion
        String mostrarUsuarios = "SELECT * FROM usuario";
        ResultSet rs = statement.executeQuery(mostrarUsuarios);
        if (rs.next()){
            System.out.println("__________");
            System.out.println("conexion correcta");
            System.out.println("__________");
        }
        rs.close();
        statement.close();
        con.close();
    }


}
