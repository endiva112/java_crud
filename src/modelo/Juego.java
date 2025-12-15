package modelo;

import java.io.Serializable;

public class Juego implements Serializable {
    private String name;
    private double price;
    private String image; // ruta de la imagen

    public Juego(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}