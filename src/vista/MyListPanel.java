package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class MyListPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JPanel listaPanelContainer;

    public MyListPanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Colores de la paleta
        Color mainBG = new Color(34, 34, 59);
        Color contrastBG = new Color(74, 78, 105);
        Color panelBG = new Color(242, 233, 228);
        Color mainText = new Color(255, 255, 255);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);

        setLayout(new BorderLayout());

        // Usar la NavBar reutilizable (Cabecera) - Mantener en NORTH, pero ajustar si es necesario
        add(new Cabecera(controlador), BorderLayout.NORTH);

        // Panel central con contrastBG
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(contrastBG);
        centerPanel.setLayout(new GridBagLayout());

        // Panel interno con panelBG para la lista
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setBackground(panelBG);
        mainCenterPanel.setPreferredSize(new Dimension(800, 600)); // Ajustar tamaño similar
        mainCenterPanel.setLayout(new BorderLayout());

        // Lista - Mantener BoxLayout Y_AXIS para scroll vertical
        listaPanelContainer = new JPanel();
        listaPanelContainer.setBackground(panelBG);
        listaPanelContainer.setLayout(new BoxLayout(listaPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listaPanelContainer);
        scrollPane.setBackground(panelBG);
        mainCenterPanel.add(scrollPane, BorderLayout.CENTER);

        centerPanel.add(mainCenterPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarLista(List<Juego> juegos) {
        // Colores de la paleta
        Color panelBG = new Color(242, 233, 228);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);

        listaPanelContainer.removeAll();

        if (juegos.isEmpty()) {
            JLabel emptyLabel = new JLabel("Tu lista está vacía");
            emptyLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
            emptyLabel.setForeground(secondaryText);
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listaPanelContainer.add(emptyLabel);
        } else {
            for (int i = 0; i < juegos.size(); i++) {
                final int index = i;
                Juego juego = juegos.get(i);

                // Tarjeta principal: GridLayout 1x2 (A: foto, B: info y botones)
                JPanel juegoPanel = new JPanel(new GridLayout(1, 2));
                juegoPanel.setBackground(panelBG);
                juegoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                juegoPanel.setPreferredSize(new Dimension(600, 150)); // Tamaño fijo para cada tarjeta
                juegoPanel.setMaximumSize(new Dimension(600, 150));

                // Panel A: Foto (izquierda)
                JPanel leftPanel = new JPanel();
                leftPanel.setBackground(panelBG);
                leftPanel.setLayout(new GridBagLayout()); // Centrar la imagen


                String rutaImagen = "/imagenes/" + juego.getImage();
                ImageIcon gameIcon = new ImageIcon(ProductPanel.class.getResource(rutaImagen));
                Image img = gameIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                gameIcon = new ImageIcon(img);
                JLabel lblImage = new JLabel(gameIcon);
                leftPanel.add(lblImage);

                // Panel B: Derecha - GridLayout 2x1 (C: info, D: botones)
                JPanel rightPanel = new JPanel(new GridLayout(2, 1));
                rightPanel.setBackground(panelBG);

                // Panel C: Info del producto
                JPanel infoPanel = new JPanel();
                infoPanel.setBackground(panelBG);
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

                JLabel lblNombre = new JLabel(juego.getName());
                lblNombre.setFont(new Font("Roboto", Font.BOLD, 18));
                lblNombre.setForeground(secondaryText);
                lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);

                JLabel lblPrecio = new JLabel("$" + juego.getPrice());
                lblPrecio.setFont(new Font("Roboto", Font.PLAIN, 16));
                lblPrecio.setForeground(secondaryText);
                lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

                infoPanel.add(lblNombre);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(lblPrecio);

                // Panel D: Botones
                JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                buttonsPanel.setBackground(panelBG);

                JButton btnVer = new JButton("Ver");
                btnVer.setFont(new Font("Roboto", Font.BOLD, 14));
                btnVer.setForeground(secondaryText);
                btnVer.setBackground(buttonBG);
                btnVer.setFocusPainted(false);
                btnVer.setBorderPainted(false);
                btnVer.setOpaque(true);
                btnVer.addActionListener(e -> controlador.mostrarProduct(juego));

                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.setFont(new Font("Roboto", Font.BOLD, 14));
                btnEliminar.setForeground(secondaryText);
                btnEliminar.setBackground(buttonBG);
                btnEliminar.setFocusPainted(false);
                btnEliminar.setBorderPainted(false);
                btnEliminar.setOpaque(true);
                btnEliminar.addActionListener(e -> controlador.eliminarJuegoDeLista(index));

                buttonsPanel.add(btnVer);
                buttonsPanel.add(btnEliminar);

                rightPanel.add(infoPanel);
                rightPanel.add(buttonsPanel);

                juegoPanel.add(leftPanel);
                juegoPanel.add(rightPanel);

                listaPanelContainer.add(juegoPanel);
                listaPanelContainer.add(Box.createVerticalStrut(10)); // Espacio entre tarjetas
            }
        }

        listaPanelContainer.revalidate();
        listaPanelContainer.repaint();
    }
}
