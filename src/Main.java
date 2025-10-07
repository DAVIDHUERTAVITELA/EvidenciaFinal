//DAVIDHUERTAVITELA

import java.io.File;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        inicializarArchivos();
        Usuario.cargarDesdeArchivo();
        Doctor.cargarDesdeArchivo();
        Paciente.cargarDesdeArchivo();
        Cita.cargarDesdeArchivo();

        System.out.println("====== SISTEMA DE ADMINISTRACIÓN DE CITAS ======");
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Usuario sesion = Usuario.autenticar(user, pass);

        if (sesion == null) {
            System.out.println("Acceso denegado.");
            return;
        }

        //Si es Admin, mostrar menú de admin (Gestión de usuarios)
        if (sesion.getId().equals("Admin")) {
            menuAdminGeneral();
        } else {
            menuUsuario();
        }
    }

    private static void inicializarArchivos() {

        File dbFolder = new File("db");

        if (!dbFolder.exists()) dbFolder.mkdir();

        //Asegurar existencia de archivos (si no existen se crearán vacíos)
        try {
            new File("db/usuarios.csv").createNewFile();
            new File("db/doctores.csv").createNewFile();
            new File("db/pacientes.csv").createNewFile();
            new File("db/citas.csv").createNewFile();
        }
        catch (Exception e) {
            System.out.println("Error creando archivos de datos: " + e.getMessage());
        }
    }

    //Menú para Admin: Gestión de usuarios y Acceso a submenús de entidad
    private static void menuAdminGeneral() {

        int op;

        do {
            System.out.println("\n========== MENÚ ADMIN ==========");
            System.out.println("|    1. Gestionar usuarios     |");
            System.out.println("|    2. Gestionar doctores     |");
            System.out.println("|    3. Gestionar pacientes    |");
            System.out.println("|    4. Gestionar citas        |");
            System.out.println("|    0. Salir                  |");
            System.out.println("================================");

            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> menuAdminUsuarios();
                case 2 -> subMenuDoctor();
                case 3 -> subMenuPaciente();
                case 4 -> subMenuCita();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    private static void menuAdminUsuarios() {

        int op;

        do {
            System.out.println("\n--- ADMINISTRACIÓN DE USUARIOS ---");
            System.out.println("|      1. Crear usuario          |");
            System.out.println("|      2. Eliminar usuario       |");
            System.out.println("|      3. Listar usuarios        |");
            System.out.println("|      0. Volver                 |");
            System.out.println("----------------------------------");

            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> {
                    System.out.print("Nuevo ID: ");
                    String id = sc.nextLine();

                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();

                    Usuario.agregarUsuario(new Usuario(id, pass));
                    System.out.println("Usuario creado.");
                }
                case 2 -> {
                    System.out.print("ID a eliminar: ");
                    String id = sc.nextLine();

                    if (id.equals("Admin")) {
                        System.out.println("No es posible eliminar al usuario Admin (virtual).");
                    }
                    else if (Usuario.eliminarUsuario(id)) {
                        System.out.println("Usuario eliminado.");
                    }
                    else {
                        System.out.println("Usuario no encontrado.");
                    }
                }
                case 3 -> Usuario.mostrarUsuarios();

                case 0 -> {}

                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    //Menú para usuarios regulares (pueden gestionar entidades)
    private static void menuUsuario() {

        int op;

        do {
            System.out.println("\n======== MENÚ PRINCIPAL ========");
            System.out.println("|   1. Gestionar doctores      |");
            System.out.println("|   2. Gestionar pacientes     |");
            System.out.println("|   3. Gestionar citas         |");
            System.out.println("|   4. Cerrar sesión           |");
            System.out.println("================================");

            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> subMenuDoctor();
                case 2 -> subMenuPaciente();
                case 3 -> subMenuCita();
                case 4 -> { System.out.println("Cerrando sesión..."); return; }
                default -> System.out.println("Opción no válida.");
            }
        } while (true);
    }

    //Submenús con create, list y delete
    private static void subMenuDoctor() {

        System.out.println("\n-------- DOCTORES --------");
        System.out.println("|   1. Agregar doctor    |");
        System.out.println("|   2. Mostrar doctores  |");
        System.out.println("|   3. Eliminar doctor   |");
        System.out.println("--------------------------");

        System.out.print("Opción: ");
        int op = leerEntero();

        switch (op) {
            case 1 -> {
                String id = UUID.randomUUID().toString();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Especialidad: ");
                String esp = sc.nextLine();

                Doctor.agregarDoctor(new Doctor(id, nombre, esp));
                System.out.println("Doctor agregado. ID: " + id);
            }
            case 2 -> Doctor.mostrarDoctores();

            case 3 -> {
                System.out.print("ID del doctor a eliminar: ");
                String id = sc.nextLine();

                if (Doctor.eliminarDoctor(id)) System.out.println("Doctor eliminado.");
                else System.out.println("Doctor no encontrado.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void subMenuPaciente() {

        System.out.println("\n-------- PACIENTES --------");
        System.out.println("|   1. Agregar paciente   |");
        System.out.println("|   2. Mostrar pacientes  |");
        System.out.println("|   3. Eliminar paciente  |");
        System.out.println("---------------------------");

        System.out.print("Opción: ");
        int op = leerEntero();

        switch (op) {
            case 1 -> {
                String id = UUID.randomUUID().toString();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                Paciente.agregarPaciente(new Paciente(id, nombre));
                System.out.println("Paciente agregado. ID: " + id);
            }
            case 2 -> Paciente.mostrarPacientes();

            case 3 -> {
                System.out.print("ID del paciente a eliminar: ");
                String id = sc.nextLine();

                if (Paciente.eliminarPaciente(id)) System.out.println("Paciente eliminado.");
                else System.out.println("Paciente no encontrado.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void subMenuCita() {

        System.out.println("\n--------- CITAS ---------");
        System.out.println("|   1. Crear cita       |");
        System.out.println("|   2. Mostrar citas    |");
        System.out.println("|   3. Eliminar cita    |");
        System.out.println("-------------------------");

        System.out.print("Opción: ");
        int op = leerEntero();

        switch (op) {
            case 1 -> {
                String id = UUID.randomUUID().toString();

                System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                String fechaHora = sc.nextLine();

                System.out.print("Motivo: ");
                String motivo = sc.nextLine();

                System.out.print("ID doctor: ");
                String doc = sc.nextLine();

                System.out.print("ID paciente: ");
                String pac = sc.nextLine();

                Cita.agregarCita(new Cita(id, fechaHora, motivo, doc, pac));
                System.out.println("Cita creada. ID: " + id);
            }
            case 2 -> Cita.mostrarCitas();

            case 3 -> {
                System.out.print("ID de la cita a eliminar: ");
                String id = sc.nextLine();

                if (Cita.eliminarCita(id)) System.out.println("Cita eliminada.");
                else System.out.println("Cita no encontrada.");
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}