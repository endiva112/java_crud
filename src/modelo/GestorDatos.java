package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la lectura y escritura de datos en ficheros
 */
public class GestorDatos {
    private static final String RUTA_FICHEROS = "recursos/ficheros/";
    private static final String ARCHIVO_USUARIOS = RUTA_FICHEROS + "usuarios.dat";
    private static final String ARCHIVO_JUEGOS = RUTA_FICHEROS + "juegos.txt";

    private List<Usuario> usuarios;
    private List<Juego> catalogoJuegos;

    public GestorDatos() {
        this.usuarios = new ArrayList<>();
        this.catalogoJuegos = new ArrayList<>();
        crearDirectoriosSiNoExisten();
        cargarJuegos();
        cargarUsuarios();
    }

    /**
     * Crea los directorios necesarios si no existen
     */
    private void crearDirectoriosSiNoExisten() {
        File directorio = new File(RUTA_FICHEROS);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("✓ Directorio creado: " + RUTA_FICHEROS);
            }
        }
    }

    // ==================== GESTIÓN DE JUEGOS (TXT) ====================

    /**
     * Carga juegos desde archivo de texto
     * Formato: nombre|precio|imagen
     */
    private void cargarJuegos() {
        File archivo = new File(ARCHIVO_JUEGOS);

        if (!archivo.exists()) {
            System.out.println("⚠ Archivo de juegos no existe. Creando uno nuevo...");
            crearArchivoJuegosPorDefecto();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                // Ignorar líneas vacías o comentarios
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                // Parsear línea: nombre|precio|imagen
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    double precio = Double.parseDouble(partes[1].trim());
                    String imagen = partes[2].trim();

                    catalogoJuegos.add(new Juego(nombre, precio, imagen));
                }
            }
            System.out.println("✓ Juegos cargados: " + catalogoJuegos.size() + " juegos");
        } catch (IOException e) {
            System.err.println("✗ Error al cargar juegos: " + e.getMessage());
            crearArchivoJuegosPorDefecto();
        } catch (NumberFormatException e) {
            System.err.println("✗ Error en formato de precio: " + e.getMessage());
        }
    }

    /**
     * Crea el archivo de juegos con datos por defecto
     */
    private void crearArchivoJuegosPorDefecto() {
        catalogoJuegos.clear();
        catalogoJuegos.add(new Juego("The Witcher 3", 39.99, "witcher.jpg"));
        catalogoJuegos.add(new Juego("Cyberpunk 2077", 59.99, "cyberpunk.jpg"));
        catalogoJuegos.add(new Juego("Red Dead Redemption 2", 49.99, "rdr2.jpg"));
        catalogoJuegos.add(new Juego("GTA V", 29.99, "gta5.jpg"));
        catalogoJuegos.add(new Juego("Minecraft", 26.99, "minecraft.jpg"));
        catalogoJuegos.add(new Juego("Elden Ring", 59.99, "eldenring.jpg"));
        catalogoJuegos.add(new Juego("Hogwarts Legacy", 69.99, "hogwarts.jpg"));
        catalogoJuegos.add(new Juego("God of War", 49.99, "gow.jpg"));

        guardarJuegos();
    }

    /**
     * Guarda el catálogo de juegos en formato legible
     */
    private void guardarJuegos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_JUEGOS))) {
            // Escribir cabecera explicativa
            bw.write("# Catálogo de Juegos - Game Store");
            bw.newLine();
            bw.write("# Formato: nombre|precio|imagen");
            bw.newLine();
            bw.write("# Ejemplo: The Witcher 3|39.99|witcher.jpg");
            bw.newLine();
            bw.write("# Puedes editar este archivo manualmente");
            bw.newLine();
            bw.newLine();

            // Escribir cada juego
            for (Juego juego : catalogoJuegos) {
                String linea = juego.getName() + "|" +
                        juego.getPrice() + "|" +
                        juego.getImage();
                bw.write(linea);
                bw.newLine();
            }

            System.out.println("✓ Archivo de juegos creado: " + ARCHIVO_JUEGOS);
        } catch (IOException e) {
            System.err.println("✗ Error al guardar juegos: " + e.getMessage());
        }
    }

    // ==================== GESTIÓN DE USUARIOS (DAT) ====================

    /**
     * Carga usuarios desde archivo binario
     */
    private void cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);

        if (!archivo.exists()) {
            System.out.println("⚠ Archivo de usuarios no existe. Creando usuario de prueba...");
            crearUsuarioPrueba();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            usuarios = (List<Usuario>) ois.readObject();
            System.out.println("✓ Usuarios cargados: " + usuarios.size() + " usuarios");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✗ Error al cargar usuarios: " + e.getMessage());
            crearUsuarioPrueba();
        }
    }

    /**
     * Crea un usuario de prueba por defecto
     */
    private void crearUsuarioPrueba() {
        Usuario usuarioPrueba = new Usuario("a@gmail.com", "1234");
        usuarios.add(usuarioPrueba);
        guardarUsuarios();
        System.out.println("✓ Usuario de prueba creado: a@gmail.com / 1234");
    }

    /**
     * Guarda todos los usuarios en archivo binario
     */
    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
            System.out.println("✓ Usuarios guardados correctamente");
        } catch (IOException e) {
            System.err.println("✗ Error al guardar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ==================== MÉTODOS PÚBLICOS ====================

    public List<Juego> getCatalogoJuegos() {
        return new ArrayList<>(catalogoJuegos);
    }

    public Usuario buscarUsuario(String email, String password) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) &&
                        u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean existeUsuario(String email) {
        return usuarios.stream()
                .anyMatch(u -> u.getEmail().equals(email));
    }

    /**
     * Registra un nuevo usuario y guarda inmediatamente
     */
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
        System.out.println("→ Usuario registrado: " + usuario.getEmail());
    }

    /**
     * Actualiza un usuario existente y guarda inmediatamente
     */
    public void actualizarUsuario(Usuario usuario) {
        // El usuario ya está en la lista por referencia
        // Solo necesitamos guardar
        guardarUsuarios();
    }

    /**
     * Método público para guardar datos (llamar al cerrar la app)
     */
    public void guardarDatos() {
        guardarUsuarios();
        System.out.println("→ Guardado final completado");
    }
}