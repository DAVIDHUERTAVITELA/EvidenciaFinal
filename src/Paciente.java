import java.io.*;
import java.util.*;

public class Paciente {

    private String id;
    private String nombre;

    private static final String FILE_PATH = "db/pacientes.csv";
    private static List<Paciente> pacientes = new ArrayList<>();

    public Paciente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public static void mostrarPacientes() {

        System.out.println("\n==================== LISTA DE PACIENTES ====================");

        if (pacientes.isEmpty()) {
            System.out.println("(No hay pacientes registrados)");
            return;
        }
        for (Paciente p : pacientes)
            System.out.println("- ID: " + p.id + " | Nombre: " + p.nombre);
    }

    public static void agregarPaciente(Paciente p) {
        pacientes.add(p);
        guardarEnArchivo();
    }

    public static boolean eliminarPaciente(String id) {

        boolean removed = pacientes.removeIf(p -> p.id.equals(id));

        if (removed) guardarEnArchivo();
        return removed;
    }

    public static Paciente buscarPorId(String id) {
        for (Paciente p : pacientes)
            if (p.id.equals(id)) return p;
        return null;
    }

    public static void cargarDesdeArchivo() {

        pacientes.clear();

        File archivo = new File(FILE_PATH);

        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",", 2);

                if (partes.length == 2)
                    pacientes.add(new Paciente(partes[0], partes[1]));
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer pacientes: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Paciente p : pacientes)
                pw.println(p.id + "," + p.nombre);
        }
        catch (IOException e) {
            System.out.println("Error al guardar pacientes: " + e.getMessage());
        }
    }
}