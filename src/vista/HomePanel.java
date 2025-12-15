package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
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
        // Colores
        Color mainBG = new Color(34, 34, 59);
        Color contrastBG = new Color(74, 78, 105);
        Color panelBG = new Color(242, 233, 228);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);
        Color mainText = new Color(255, 255, 255);

        setLayout(new BorderLayout());

        // Cabecera
        add(new Cabecera(controlador), BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(contrastBG);

        // Panel de filtro
        JPanel filtroPanel = new JPanel();
        filtroPanel.setBackground(contrastBG);
        filtroPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Roboto", Font.BOLD, 18));
        lblBuscar.setForeground(mainText);

        filtroField = new JTextField(30);
        filtroField.setFont(new Font("Roboto", Font.PLAIN, 16));
        filtroField.setPreferredSize(new Dimension(300, 35));

        filtroPanel.add(lblBuscar);
        filtroPanel.add(filtroField);

        centerPanel.add(filtroPanel, BorderLayout.NORTH);

        // Container de juegos con scroll
        juegosPanelContainer = new JPanel();
        juegosPanelContainer.setBackground(contrastBG);
        juegosPanelContainer.setLayout(new BoxLayout(juegosPanelContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(juegosPanelContainer);
        scrollPane.setBackground(contrastBG);
        scrollPane.getViewport().setBackground(contrastBG);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarJuegos(List<Juego> juegos) {
        // Colores
        Color contrastBG = new Color(74, 78, 105);
        Color panelBG = new Color(242, 233, 228);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);
        Color cardBG = new Color(255, 255, 255);

        juegosPanelContainer.removeAll();

        // Agrupar de 4 en 4
        for (int i = 0; i < juegos.size(); i += 4) {
            // Panel fila con GridLayout 1x4
            JPanel filaPanel = new JPanel(new GridLayout(1, 4, 20, 0));
            filaPanel.setBackground(contrastBG);
            filaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));

            // Agregar hasta 4 tarjetas
            for (int j = 0; j < 4 && (i + j) < juegos.size(); j++) {
                Juego juego = juegos.get(i + j);

                // Tarjeta individual
                JPanel cardPanel = new JPanel();
                cardPanel.setBackground(cardBG);
                cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
                cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(170, 160, 165), 2),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));

                // Imagen
                JLabel imagenLabel = new JLabel();
                imagenLabel.setPreferredSize(new Dimension(180, 180));
                imagenLabel.setMaximumSize(new Dimension(180, 180));
                imagenLabel.setMinimumSize(new Dimension(180, 180));
                imagenLabel.setBorder(BorderFactory.createLineBorder(new Color(170, 160, 165), 1));
                imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imagenLabel.setVerticalAlignment(SwingConstants.CENTER);

                String rutaImagen = "/imagenes/" + juego.getImage();
                try {
                    URL urlImagen = HomePanel.class.getResource(rutaImagen);
                    ImageIcon icon = new ImageIcon(urlImagen);
                    Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                    imagenLabel.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    imagenLabel.setText("Sin imagen");
                    imagenLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
                }

                // Nombre
                JLabel nombreLabel = new JLabel(juego.getName());
                nombreLabel.setFont(new Font("Roboto", Font.BOLD, 16));
                nombreLabel.setForeground(secondaryText);
                nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Precio
                JLabel precioLabel = new JLabel("$" + juego.getPrice());
                precioLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
                precioLabel.setForeground(new Color(100, 150, 100));
                precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Botón
                JButton btnVer = new JButton("Ver detalles");
                btnVer.setFont(new Font("Roboto", Font.BOLD, 14));
                btnVer.setForeground(secondaryText);
                btnVer.setBackground(buttonBG);
                btnVer.setFocusPainted(false);
                btnVer.setBorderPainted(false);
                btnVer.setOpaque(true);
                btnVer.setPreferredSize(new Dimension(150, 40));
                btnVer.setMaximumSize(new Dimension(150, 40));
                btnVer.setAlignmentX(Component.CENTER_ALIGNMENT);
                btnVer.addActionListener(e -> controlador.mostrarProduct(juego));

                // Agregar componentes a la tarjeta
                cardPanel.add(imagenLabel);
                cardPanel.add(Box.createVerticalStrut(12));
                cardPanel.add(nombreLabel);
                cardPanel.add(Box.createVerticalStrut(8));
                cardPanel.add(precioLabel);
                cardPanel.add(Box.createVerticalStrut(12));
                cardPanel.add(btnVer);

                filaPanel.add(cardPanel);
            }

            // Si quedan menos de 4 juegos, rellenar con paneles vacíos
            int juegosFila = Math.min(4, juegos.size() - i);
            for (int k = juegosFila; k < 4; k++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setBackground(contrastBG);
                filaPanel.add(emptyPanel);
            }

            juegosPanelContainer.add(Box.createVerticalStrut(20));
            juegosPanelContainer.add(filaPanel);
        }

        juegosPanelContainer.add(Box.createVerticalStrut(20));

        juegosPanelContainer.revalidate();
        juegosPanelContainer.repaint();
    }
}