import java.io.*;
import java.util.*;

public class Doctor {

    private String id;
    private String nombre;
    private String especialidad;

    private static final String FILE_PATH = "db/doctores.csv";
    private static List<Doctor> doctores = new ArrayList<>();

    public Doctor(String id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }

    public static void mostrarDoctores() {

        System.out.println("\n==================== LISTA DE DOCTORES ====================");

        if (doctores.isEmpty()) {
            System.out.println("(No hay doctores registrados)");
            return;
        }
        for (Doctor d : doctores)
            System.out.println("- ID: " + d.id + " | Nombre: " + d.nombre + " | Especialidad: " + d.especialidad);
    }

    public static void agregarDoctor(Doctor d) {
        doctores.add(d);
        guardarEnArchivo();
    }

    public static boolean eliminarDoctor(String id) {
        boolean removed = doctores.removeIf(d -> d.id.equals(id));
        if (removed) guardarEnArchivo();
        return removed;
    }

    public static Doctor buscarPorId(String id) {
        for (Doctor d : doctores)
            if (d.id.equals(id)) return d;
        return null;
    }

    public static void cargarDesdeArchivo() {

        doctores.clear();

        File archivo = new File(FILE_PATH);

        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",", 3);

                if (partes.length == 3)
                    doctores.add(new Doctor(partes[0], partes[1], partes[2]));
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer doctores: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Doctor d : doctores)
                pw.println(d.id + "," + d.nombre + "," + d.especialidad);
        }
        catch (IOException e) {
            System.out.println("Error al guardar doctores: " + e.getMessage());
        }
    }
}