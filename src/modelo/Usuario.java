package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {
    private String email;
    private String password;
    private String avatar; // ruta de la imagen
    private List<Juego> myShoppingList;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
        this.avatar = null; // por defecto sin avatar
        this.myShoppingList = new ArrayList<>();
    }

    //region Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public List<Juego> getMyShoppingList() { return myShoppingList; }
    //endregion

    //region Lógica de negocio
    public boolean tieneJuegoEnLista(Juego juego) {
        for (Juego j : myShoppingList) {
            if (j.getName().equals(juego.getName())) {
                return true;
            }
        }
        return false;
    }

    public void agregarJuegoALista(Juego juego) {
        if (!tieneJuegoEnLista(juego)) {
            myShoppingList.add(juego);
        }
    }

    public void eliminarJuegoDeLista(Juego juego) {
        for (int i = 0; i < myShoppingList.size(); i++) {
            if (myShoppingList.get(i).getName().equals(juego.getName())) {
                myShoppingList.remove(i);
                break; // Salir del bucle, el jeugo ya está borrado de la lista
            }
        }
    }

    public void eliminarJuegoDeLista(int index) {
        if (index >= 0 && index < myShoppingList.size()) {
            myShoppingList.remove(index);
        }
    }
    //endregion
}