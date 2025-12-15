package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
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

        // Panel superior (navegación)
        JPanel topPanel = new JPanel();
        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(e -> controlador.mostrarHome());
        JButton btnMyList = new JButton("Mi Lista");
        btnMyList.addActionListener(e -> controlador.mostrarMyList());
        JButton btnAccount = new JButton("Cuenta");
        btnAccount.addActionListener(e -> controlador.mostrarAccount());

        topPanel.add(btnHome);
        topPanel.add(btnMyList);
        topPanel.add(btnAccount);

        add(topPanel, BorderLayout.NORTH);

        // Panel central (filtro + juegos)
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Filtro (solo visual por ahora)
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