import java.io.*;
import java.util.*;

public class Usuario {

    private String id;
    private String password;

    private static final String FILE_PATH = "db/usuarios.csv";
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() { return id; }

    public static void mostrarUsuarios() {

        System.out.println("\n====== LISTA DE USUARIOS REGISTRADOS ======");

        if (usuarios.isEmpty()) {
            System.out.println("(No hay usuarios registrados)");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println("- Usuario: " + u.id);
        }
    }

    public static void agregarUsuario(Usuario u) {
        usuarios.add(u);
        guardarEnArchivo();
    }

    public static boolean eliminarUsuario(String id) {

        boolean removed = usuarios.removeIf(u -> u.id.equals(id));

        if (removed) guardarEnArchivo();
        return removed;
    }

    public static Usuario autenticar(String id, String password) {

        //Usuario Admin virtual con CREDENCIALES FIJAS
        if (id.equals("Admin") && password.equals("0000")) return new Usuario("Admin", "0000");

        for (Usuario u : usuarios)
            if (u.id.equals(id) && u.password.equals(password)) return u;
        return null;
    }

    public static void cargarDesdeArchivo() {

        usuarios.clear();

        File archivo = new File(FILE_PATH);

        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",", 2);
                if (partes.length == 2)
                    usuarios.add(new Usuario(partes[0], partes[1]));
            }
        }
        catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }
    }

    public static void guardarEnArchivo() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Usuario u : usuarios)
                pw.println(u.id + "," + u.password);
        }
        catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}