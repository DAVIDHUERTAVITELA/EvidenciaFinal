import java.io.*;
import java.util.*;

public class Cita {

    private String id;
    private String fechaHora;
    private String motivo;
    private String doctorId;
    private String pacienteId;

    private static final String FILE_PATH = "db/citas.csv";
    private static List<Cita> citas = new ArrayList<>();

    public Cita(String id, String fechaHora, String motivo, String doctorId, String pacienteId) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctorId = doctorId;
        this.pacienteId = pacienteId;
    }

    public static void mostrarCitas() {

        System.out.println("\n==================== LISTA DE CITAS ====================");

        if (citas.isEmpty()) {
            System.out.println("(No hay citas registradas)");
            return;
        }
        for (Cita c : citas) {

            //Mostrar nombre de doctor y paciente, si existen
            Doctor d = Doctor.buscarPorId(c.doctorId);
            Paciente p = Paciente.buscarPorId(c.pacienteId);

            String nombreDoc = (d != null) ? d.getNombre() : ("(Doctor " + c.doctorId + " no encontrado)");
            String nombrePac = (p != null) ? p.getNombre() : ("(Paciente " + c.pacienteId + " no encontrado)");

            System.out.println("- ID: " +c.id+ " | Fecha: " +c.fechaHora+ " | Motivo: " +c.motivo+ " | Doctor: " +nombreDoc+ " | Paciente: " +nombrePac);
        }
    }

    public static void agregarCita(Cita c) {
        citas.add(c);
        guardarEnArchivo();
    }

    public static boolean eliminarCita(String id) {

        boolean removed = citas.removeIf(c -> c.id.equals(id));

        if (removed) guardarEnArchivo();
        return removed;
    }

    public static void cargarDesdeArchivo() {

        citas.clear();

        File archivo = new File(FILE_PATH);

        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",", 5);

                if (partes.length == 5)
                    citas.add(new Cita(partes[0], partes[1], partes[2], partes[3], partes[4]));
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer citas: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Cita c : citas)
                pw.println(c.id + "," + c.fechaHora + "," + c.motivo + "," + c.doctorId + "," + c.pacienteId);
        }
        catch (IOException e) {
            System.out.println("Error al guardar citas: " + e.getMessage());
        }
    }
}