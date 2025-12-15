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

    private List<Usuario> usersList;
    private List<Juego> gamesList;

    public GestorDatos() {
        this.usersList = new ArrayList<>();
        this.gamesList = new ArrayList<>();
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
                System.out.println("(OK) Directorio creado: " + RUTA_FICHEROS);
            }
        }
    }

    //region GESTIÓN DE JUEGOS (TXT)
    /**
     * Carga juegos desde archivo de texto
     * Formato: nombre|precio|imagen
     */
    private void cargarJuegos() {
        File archivo = new File(ARCHIVO_JUEGOS);

        if (!archivo.exists()) {
            System.out.println("(ADVERTENCIA) Archivo de juegos no existe. Creando uno nuevo...");
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

                    gamesList.add(new Juego(nombre, precio, imagen));
                }
            }
            System.out.println("(OK) Juegos cargados: " + gamesList.size() + " juegos");
        } catch (IOException e) {
            System.err.println("(ERROR) Error al cargar juegos: " + e.getMessage());
            crearArchivoJuegosPorDefecto();
        } catch (NumberFormatException e) {
            System.err.println("(ERROR) Error en formato de precio: " + e.getMessage());
        }
    }

    /**
     * Crea el archivo de juegos con datos por defecto
     */
    private void crearArchivoJuegosPorDefecto() {
        gamesList.clear();
        gamesList.add(new Juego("The Witcher 3", 39.99, "witcherImg.jpg"));
        gamesList.add(new Juego("Cyberpunk 2077", 59.99, "cyberpunkImg.jpg"));
        gamesList.add(new Juego("Red Dead Redemption 2", 49.99, "rdr2Img.jpg"));
        gamesList.add(new Juego("GTA V", 29.99, "gta5Img.jpg"));
        gamesList.add(new Juego("Minecraft", 26.99, "minecraftImg.jpg"));
        gamesList.add(new Juego("Elden Ring", 59.99, "eldenringImg.jpg"));
        gamesList.add(new Juego("Hogwarts Legacy", 69.99, "hogwartsImg.jpg"));
        gamesList.add(new Juego("Hollow Knight - Silksong", 14.99, "silksongImg.jpg"));

        guardarJuegos();
    }

    /**
     * Guarda el catálogo de juegos en formato legible
     */
    private void guardarJuegos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_JUEGOS))) {
            // Escribir cabecera explicativa
            bw.write("# Catálogo de Juegos\n");
            bw.write("# Formato: nombre|precio|imagen\n");
            bw.write("# Ejemplo: The Witcher 3|39.99|witcherImg.jpg\n");
            bw.write("# Se puede y debe editar este archivo manualmente para agregar juegos nuevos\n\n");

            // Escribir cada juego
            for (Juego juego : gamesList) {
                String linea = juego.getName() + "|" +
                        juego.getPrice() + "|" +
                        juego.getImage();
                bw.write(linea + "\n");
            }

            System.out.println("(OK) Archivo de juegos creado: " + ARCHIVO_JUEGOS);
        } catch (IOException e) {
            System.err.println("(ERROR) Error al guardar juegos: " + e.getMessage());
        }
    }
    //endregion

    //region GESTIÓN DE USUARIOS (DAT)
    /**
     * Carga usuarios desde archivo binario
     */
    private void cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);

        if (!archivo.exists()) {
            System.out.println("(ADVERTENCIA) Archivo de usuarios no existe. Creando 1 usuario de prueba...");
            crearUsuarioPrueba();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            usersList = (List<Usuario>) ois.readObject();
            System.out.println("(OK) Usuarios cargados: " + usersList.size() + " usuarios");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("(ERROR) Error al cargar usuarios: " + e.getMessage());
            crearUsuarioPrueba();
        }
    }

    /**
     * Crea un usuario de prueba por defecto
     */
    private void crearUsuarioPrueba() {
        Usuario usuarioPrueba = new Usuario("a@gmail.com", "1234");
        usersList.add(usuarioPrueba);
        guardarUsuarios();
        System.out.println("(OK) Usuario de prueba creado: a@gmail.com / 1234");
    }

    /**
     * Guarda todos los usuarios en archivo binario
     */
    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usersList);
            System.out.println("(OK) Usuarios guardados correctamente");
        } catch (IOException e) {
            System.err.println("(ERROR) Error al guardar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //endregion

    //region MÉTODOS PÚBLICOS

    /**
     * Devuelve la lista de juegos cargados en el programa
     * @return lista de obejtos de tipo Juego
     */
    public List<Juego> getGamesList() {
        return new ArrayList<>(gamesList);
    }

    /**
     * Devuelve un Usuario cuyo email y pwd
     * coincida con los registrados en el sistema
     * @param email
     * @param password
     * @return Usuario registrado en el sistema o null si no existe en los registros
     */
    public Usuario buscarUsuario(String email, String password) {
        for (Usuario u : usersList) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Devuelve si existe o no un usuario con el correo seleccionado
     * @param email
     * @return true / false
     */
    public boolean existeUsuario(String email) {
        for (Usuario u : usersList) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registra un nuevo usuario y guarda inmediatamente
     */
    public void registrarUsuario(Usuario usuario) {
        usersList.add(usuario);
        guardarUsuarios();
        System.out.println("→ Usuario registrado: " + usuario.getEmail());
    }

    /**
     * Actualiza un usuario existente y guarda inmediatamente
     */
    public void actualizarUsuario(Usuario usuario) {
        // El usuario ya está en la lista por referencia
        // Solo necesito guardar, esto machacará los datos antiguos
        guardarUsuarios();
    }
    //endregion
}