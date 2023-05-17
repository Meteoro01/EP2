import java.sql.*;
import java.util.Scanner;

public class Tarea {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Elija una opción:");
            System.out.println("1. Crear tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Editar tarea");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea pendiente

            switch (opcion) {
                case 1:
                    CrearTarea();
                    break;
                case 2:
                    ListarTarea();
                    break;
                case 3:
                    Editar();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }

            System.out.println("---------");
        }

        scanner.close();


    }



    public static void CrearTarea() throws SQLException, ClassNotFoundException {
        // 1. Conectar a la base de datos
        Connection conn = Conexion.Conexion();


        // 2. Obtener los datos de la nueva tarea ingresados por consola
        Scanner scanner = new Scanner(System.in);

        System.out.println("Desea manipular los proyectos antes de crear una tarea?,\n" +
                " recuerda para crear una tareas tienes que saber el id de su proyecto o dejarla en null 1=si 2=no :");
        int listarcrear = scanner.nextInt();
        if (listarcrear == 1) {
            proyectoDOA proyectoDOA = new proyectoDOA();
            String[] args = new String[0];
            proyectoDOA.main(args);
        }

        System.out.println("Ingrese el ID del proyecto:");
        int idProyecto = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("Ingrese el ID del usuario:");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente
        System.out.println("Ingrese el título de la tarea:");
        String tituloTarea = scanner.nextLine();
        System.out.println("Ingrese la descripción de la tarea:");
        String descripcionTarea = scanner.nextLine();
        System.out.println("Ingrese la fecha de vencimiento de la tarea (formato: yyyy-mm-dd):");
        String fechaVencimiento = scanner.nextLine();


        Statement statement = conn.createStatement();
        String query = "SELECT MAX(idTarea) FROM tareas";
        ResultSet resultSet = statement.executeQuery(query);

        int ultimoId = 0;
        if (resultSet.next()) {
            ultimoId = resultSet.getInt(1);
        }

        int nuevoIdTarea = ultimoId + 1;
        // 3. Insertar la nueva tarea en la tabla Tareas
        String sql = "INSERT INTO Tareas (IdTarea, IdProyecto, IdUsuario,  TituloTarea, DescripcionTarea, FechaVencimiento, Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, nuevoIdTarea);
        pstmt.setInt(2, idProyecto);
        pstmt.setInt(3, idUsuario);
        pstmt.setString(4, tituloTarea);
        pstmt.setString(5, descripcionTarea);
        pstmt.setString(6, fechaVencimiento);
        pstmt.setString(7, "En progreso");
        int filasAfectadas = pstmt.executeUpdate();

        // 4. Mostrar el número de filas afectadas
        System.out.println(filasAfectadas + " fila(s) afectada(s)");

        // 5. Cerrar la conexión y el PreparedStatement
        pstmt.close();
        conn.close();
    }
    public static void Editar() throws SQLException {
        Statement statement = Conexion.statement();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Deseas listar las Tareas?\n1 = si\n2 = no");
        int verificar = scanner2.nextInt();
        if(verificar==1){
            ListarTarea();
        }
        System.out.println("ingresa el ID:");
        String ID = scanner2.nextLine();
        System.out.println("¿Que Columna desea editar?:");
        String columna = scanner2.nextLine();
        System.out.println("Nuevo Dato:");
        String nuevoValor = scanner2.nextLine();
        EditarTarea(statement, columna, nuevoValor, ID);
    }
    public static void EditarTarea(Statement statement, String columna, String nuevoValor, String ID) throws SQLException {
        String query = "UPDATE Tarea SET " + columna + " = '" + nuevoValor + "' WHERE ID = " + ID;
        System.out.println("Consulta SQL: " + query);
        int rsRead = statement.executeUpdate(query);
        if (rsRead > 0) {
            System.out.println("Los datos se actualizaron correctamente.");
        }else {
            System.out.println("Hubo un error");
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
    public static void VistaTarea() throws SQLException {
        Statement statement = Conexion.statement();
        String query = "SELECT\n" +
                "    t.IdTarea,\n" +
                "    t.IdProyecto,\n" +
                "    p.TituloProyecto,\n" +
                "    t.IdUsuario,\n" +
                "    u.Nombres,\n" +
                "    t.TituloTarea,\n" +
                "    t.DescripcionTarea,\n" +
                "    t.FechaVencimiento,\n" +
                "    t.Estado\n" +
                "FROM\n" +
                "    Tareas t\n" +
                "    INNER JOIN Proyectos p ON t.IdProyecto = p.IdProyecto\n" +
                "    INNER JOIN usuario u ON t.IdUsuario = u.IdUsuario;";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.printf(" %-13s %-13s %-23s %-13s %-23s %-30s %-33s %-13s\n",
                "IdTarea", "IdProyecto", "TituloProyecto", "IdUsuario", "Nombres", "TituloTarea", "DescripcionTarea", "FechaVencimiento", "Estado");

        while (resultSet.next()) {
            String idTarea = resultSet.getString("IdTarea");
            String idProyecto = resultSet.getString("IdProyecto");
            String tituloProyecto = resultSet.getString("TituloProyecto");
            String idUsuario = resultSet.getString("IdUsuario");
            String nombres = resultSet.getString("Nombres");
            String tituloTarea = resultSet.getString("TituloTarea");
            String descripcionTarea = resultSet.getString("DescripcionTarea");
            String fechaVencimiento = resultSet.getString("FechaVencimiento");
            String estado = resultSet.getString("Estado");

            System.out.printf(" %-13s %-13s %-23s %-13s %-23s %-30s %-33s %-13s\n",
                    idTarea, idProyecto, tituloProyecto, idUsuario, nombres, tituloTarea, descripcionTarea, fechaVencimiento, estado);
            System.out.println("--------------------------------------------------------------------------------------------");
        }

        statement.close();

    }

    public static void ListarTarea() throws SQLException {
        Statement statement = Conexion.statement();
        String query = "select * from tareas";
        ResultSet resultSet = statement.executeQuery(query);

        System.out.printf(" %-13s %-13s %-30s %-33s %-13s\n",
                "IdUsuario", "TituloTarea", "DescripcionTarea", "FechaVencimiento", "Estado");

        while (resultSet.next()) {



            String idUsuario = resultSet.getString("IdUsuario");

            String tituloTarea = resultSet.getString("TituloTarea");
            String descripcionTarea = resultSet.getString("DescripcionTarea");
            String fechaVencimiento = resultSet.getString("FechaVencimiento");
            String estado = resultSet.getString("Estado");

            System.out.printf(" %-13s %-13s %-30s %-33s %-13s\n",
                     idUsuario, tituloTarea, descripcionTarea, fechaVencimiento, estado);
            System.out.println("--------------------------------------------------------------------------------------------");
        }

        statement.close();
    }

}



