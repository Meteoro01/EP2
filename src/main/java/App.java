import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean salir = false;

        while (!salir) {
            System.out.println("Elija una opción:");
            System.out.println("1. Verificar conexión");
            System.out.println("2. Manipular usuario");
            System.out.println("3. Manipular tarea");
            System.out.println("4. Manipular proyecto");
            System.out.println("5. Salir");

            try {
                int opcion = Integer.parseInt(reader.readLine());

                switch (opcion) {
                    case 1:
                        Conexion conexion = new Conexion();
                        try {
                            Conexion.main(args);
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        Scanner scanner2 = new Scanner(System.in);
                        System.out.println("Elija una opción:\n1.Crear Usuario\n2.Listar Usuario");
                        int opcionusuario = scanner2.nextInt();
                        if (opcionusuario==1){
                            CrearUsuario crearUsuario = new CrearUsuario();
                            crearUsuario.main(args);
                        }else {
                            LeerUsuariosRegistrados leerUsuariosRegistrados = new LeerUsuariosRegistrados();
                            leerUsuariosRegistrados.main(args);
                        }

                        break;
                    case 3:
                        Tarea tarea = new Tarea();
                        tarea.main(args);
                        break;
                    case 4:
                        proyectoDOA proyectoDOA = new proyectoDOA();
                        proyectoDOA.main(args);
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (IOException e) {
                System.out.println("Error al leer la entrada del usuario: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Debe ingresar un número.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            System.out.println("---------");
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

