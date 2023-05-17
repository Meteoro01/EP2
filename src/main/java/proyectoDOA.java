import java.sql.*;
import java.util.Scanner;

public class proyectoDOA {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection("bdep2grupal", "root", "root");
        int i =0;
        boolean seguir = true;
        System.out.println("Bienvenido a Proyectos!");
        do {
            System.out.println("Seleccione el número de la operación que desea realizar");
            System.out.println("1.Crear un Proyecto");
            System.out.println("2.Ver todos los Proyecto");
            System.out.println("3.Modificar Nombre de Proyecto");
            System.out.println("4.Modificar Descripción de Proyecto");
            System.out.println("5.Eliminar un Proyecto");
            System.out.println("6.Salir del modulo");
            Scanner scanner = new Scanner(System.in);

            i = scanner.nextInt();

            switch (i) {
                case 6:
                    System.out.println("Saliendo del modulo");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    seguir = false;
                    break;
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Ingrese el ID del usuario");
                    int idUsuario = scanner1.nextInt();
                    scanner1.nextLine();//LIMPIAR SCANNER
                    System.out.println("Ingrese el Nombre del Nuevo Proyecto");
                    String nombreProyecto = scanner1.nextLine();
                    System.out.println("Ingrese la Despcripción del Nuevo Proyecto");
                    String descripcionProyecto = scanner1.nextLine();

                    Statement statement = connection.createStatement();
                    String query = "SELECT MAX(IdProyecto) FROM proyectos";
                    ResultSet resultSet = statement.executeQuery(query);

                    int ultimoId = 0;
                    if (resultSet.next()) {
                        ultimoId = resultSet.getInt(1);
                    }

                    int nuevoId = ultimoId + 1;


                    String queryStr1 = "INSERT INTO proyectos VALUES(" + nuevoId + "," + idUsuario + ",'" + nombreProyecto + "','" + descripcionProyecto + "')";

                    int rs1 = insertData(queryStr1, connection);
                    System.out.println("Numero de Filas afectadas:" + rs1);
                    break;


                case 2:
                    String consulta = "SELECT idProyecto, idUsuario,TituloProyecto,DescripcionProyecto FROM proyectos";
                    PreparedStatement sentencia = connection.prepareStatement(consulta);
                    ResultSet resultado = sentencia.executeQuery();

                    System.out.printf(" %-15s %-15s %-15s %-55s \n", "idProyecto", "idUsuario", "TituloProyecto", "DescripcionProyecto");
                    while (resultado.next()) {
                        String idProyecto = resultado.getString("idProyecto");
                        String idUsuarioc = resultado.getString("idUsuario");
                        String TituloProyecto = resultado.getString("TituloProyecto");
                        String DescripcionProyecto = resultado.getString("DescripcionProyecto");
                        System.out.printf("%-15s %-15s %-15s %-55s\n", idProyecto, idUsuarioc, TituloProyecto, DescripcionProyecto);
                        System.out.println("--------------------------------------------------------------------------------------------");
                    }
                    break;
                case 3:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Ingrese el id del Proyecto");
                    int idmodificar = scanner2.nextInt();
                    scanner2.nextLine(); //LIMPIAR SCANNER
                    System.out.println("Ingrese el nuevo Nombre del Proyecto");
                    String columnamodificar = scanner2.nextLine();

                    String queryStr3 = "UPDATE proyectos SET TituloProyecto=? where IdProyecto=?";

                    int rs3 = updateData(queryStr3, connection, columnamodificar, idmodificar);
                    System.out.println("Numero de Filas afectadas:" + rs3);
                    break;
                case 4:
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.println("Ingrese el id del Proyecto");
                    idmodificar = scanner4.nextInt();
                    scanner4.nextLine(); //LIMPIAR SCANNER
                    System.out.println("Ingrese la Nueva Descripción del Proyecto");
                    columnamodificar = scanner4.nextLine();

                    String queryStr4 = "UPDATE proyectos SET DescripcionProyecto=? where IdProyecto=?";

                    int rs4 = updateData(queryStr4, connection, columnamodificar, idmodificar);
                    System.out.println("Numero de Filas afectadas:" + rs4);
                    break;
                case 5:
                    Scanner scanner5 = new Scanner(System.in);
                    System.out.println("Ingrese el id a borrar");
                    int idborrar = scanner5.nextInt();
                    scanner5.nextLine();
                    String queryStr5 = "DELETE FROM proyectos where IdProyecto=?";
                    int rs5 = deleteData(queryStr5, connection, idborrar);

                    System.out.println("Numero de Filas afectadas:" + rs5);
                    break;
                default:

                    System.out.println("Debe seleccionar una opción");
                    System.out.println("----------------------------");
                    System.out.println("----------------------------");
                    break;
            }
        } while(seguir);



    }
    //Metodo para conectarse a BD
    public static Connection getConnection(String db, String user, String pass) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/" + db + "?user=" + user + "&password=" + pass;
        return DriverManager.getConnection(url);
    }

    public static int updateData(String sql, Connection connection,String columnamodificar, int idmodificar ) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,columnamodificar);
        pstmt.setInt(2,idmodificar);
        return pstmt.executeUpdate();
    }
    public static int deleteData(String sql, Connection connection,int idborrar ) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1,idborrar);
        return pstmt.executeUpdate();
    }

    public static int insertData(String sql, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public static ResultSet selectData(String sql,Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

}
