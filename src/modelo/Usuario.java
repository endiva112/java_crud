package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String email;
    private String password;
    private String avatar; // ruta de la imagen
    private List<Juego> miListaDeJuegos;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
        this.avatar = null; // por defecto sin avatar
        this.miListaDeJuegos = new ArrayList<>();
    }

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public List<Juego> getMiListaDeJuegos() { return miListaDeJuegos; }

    // Lógica de negocio
    public boolean tieneJuegoEnLista(Juego juego) {
        return miListaDeJuegos.stream()
                .anyMatch(j -> j.getName().equals(juego.getName()));
    }

    public void agregarJuegoALista(Juego juego) {
        if (!tieneJuegoEnLista(juego)) {
            miListaDeJuegos.add(juego);
        }
    }

    public void eliminarJuegoDeLista(Juego juego) {
        miListaDeJuegos.removeIf(j -> j.getName().equals(juego.getName()));
    }

    public void eliminarJuegoDeLista(int index) {
        if (index >= 0 && index < miListaDeJuegos.size()) {
            miListaDeJuegos.remove(index);
        }
    }
}