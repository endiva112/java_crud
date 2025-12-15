package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomePanel extends JPanel {
    private ControladorPrincipal controlador;
    private JPanel juegosPanelContainer;
    private JTextField filtroField;

    public HomePanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // ✅ Usar la NavBar reutilizable
        add(new Cabecera(controlador), BorderLayout.NORTH);

        // Panel central (filtro + juegos)
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Filtro
        JPanel filtroPanel = new JPanel();
        filtroPanel.add(new JLabel("Buscar:"));
        filtroField = new JTextField(20);
        filtroPanel.add(filtroField);
        centerPanel.add(filtroPanel, BorderLayout.NORTH);

        // Lista de juegos
        juegosPanelContainer = new JPanel();
        juegosPanelContainer.setLayout(new BoxLayout(juegosPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(juegosPanelContainer);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarJuegos(List<Juego> juegos) {
        juegosPanelContainer.removeAll();

        for (Juego juego : juegos) {
            JPanel juegoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            juegoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            JLabel lblNombre = new JLabel(juego.getName() + " - $" + juego.getPrice());
            JButton btnVer = new JButton("Ver detalles");
            btnVer.addActionListener(e -> controlador.mostrarProduct(juego));

            juegoPanel.add(lblNombre);
            juegoPanel.add(btnVer);

            juegosPanelContainer.add(juegoPanel);
        }

        juegosPanelContainer.revalidate();
        juegosPanelContainer.repaint();
    }
}