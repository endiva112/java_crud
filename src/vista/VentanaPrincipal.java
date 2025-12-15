package vista;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Game Store");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void mostrarPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }
}