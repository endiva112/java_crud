package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ProductPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JLabel nombreLabel;
    private JLabel precioLabel;
    private JLabel imagenLabel;
    private JCheckBox enListaCheckBox;
    private Juego juegoActual;

    public ProductPanel(ControladorPrincipal controlador) {
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
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(contrastBG);

        JPanel mainCenterPanel = new JPanel(new GridLayout(1, 5));
        mainCenterPanel.setBackground(panelBG);
        mainCenterPanel.setPreferredSize(new Dimension(1000, 600));

        // Panel izquierdo - IMAGEN GRANDE (4 columnas)
        JPanel imagePanel = new JPanel(new GridBagLayout());
        imagePanel.setBackground(panelBG);
        imagePanel.setLayout(new GridLayout(1, 1));

        JPanel imageContainer = new JPanel();
        imageContainer.setBackground(panelBG);
        imageContainer.setLayout(new GridBagLayout());

        imagenLabel = new JLabel("Imagen del juego");
        imagenLabel.setPreferredSize(new Dimension(480, 480));
        imagenLabel.setMaximumSize(new Dimension(480, 480));
        imagenLabel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 3));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagenLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        imagenLabel.setForeground(new Color(150, 150, 150));

        imageContainer.add(imagenLabel);
        imagePanel.add(imageContainer);

        // Panel derecho - INFO (1 columna)
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(panelBG);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        // Nombre del juego - MUY GRANDE
        nombreLabel = new JLabel("Nombre del juego");
        nombreLabel.setFont(new Font("Roboto", Font.BOLD, 36));
        nombreLabel.setForeground(secondaryText);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Línea divisoria decorativa
        JSeparator separator1 = new JSeparator();
        separator1.setMaximumSize(new Dimension(180, 2));
        separator1.setForeground(new Color(170, 160, 165));

        // Precio - GRANDE
        precioLabel = new JLabel("$0.00");
        precioLabel.setFont(new Font("Roboto", Font.BOLD, 48));
        precioLabel.setForeground(new Color(100, 150, 100));
        precioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator separator2 = new JSeparator();
        separator2.setMaximumSize(new Dimension(180, 2));
        separator2.setForeground(new Color(170, 160, 165));

        // Icono de lista
        URL urlIcon = ProductPanel.class.getResource("/imagenes/icons/listIcon.png");
        ImageIcon listIcon = null;
        if (urlIcon != null) {
            listIcon = new ImageIcon(urlIcon);
            Image imgIcon = listIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            listIcon = new ImageIcon(imgIcon);
        }

        JLabel iconLabel = new JLabel(listIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Checkbox
        enListaCheckBox = new JCheckBox("Agregar a mi lista");
        enListaCheckBox.setFont(new Font("Roboto", Font.BOLD, 18));
        enListaCheckBox.setForeground(secondaryText);
        enListaCheckBox.setBackground(panelBG);
        enListaCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        enListaCheckBox.setFocusPainted(false);
        enListaCheckBox.addActionListener(e -> {
            if (juegoActual != null) {
                controlador.toggleJuegoEnLista(juegoActual);
            }
        });

        // Label descriptivo adicional
        JLabel descLabel = new JLabel("Añade este juego");
        descLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        descLabel.setForeground(new Color(100, 100, 100));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel2 = new JLabel("a tu lista de deseos");
        descLabel2.setFont(new Font("Roboto", Font.PLAIN, 14));
        descLabel2.setForeground(new Color(100, 100, 100));
        descLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(nombreLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(separator1);
        infoPanel.add(Box.createVerticalStrut(30));
        infoPanel.add(precioLabel);
        infoPanel.add(Box.createVerticalStrut(30));
        infoPanel.add(separator2);
        infoPanel.add(Box.createVerticalStrut(40));
        infoPanel.add(iconLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(enListaCheckBox);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descLabel);
        infoPanel.add(descLabel2);
        infoPanel.add(Box.createVerticalGlue());

        // Agregar 4 veces el imagePanel (ocupar 4 columnas) y 1 vez el infoPanel
        mainCenterPanel.add(imagePanel);
        mainCenterPanel.add(imagePanel); // Columna 2
        mainCenterPanel.add(imagePanel); // Columna 3
        mainCenterPanel.add(imagePanel); // Columna 4
        mainCenterPanel.add(infoPanel);   // Columna 5

        centerPanel.add(mainCenterPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarProducto(Juego juego, boolean enLista) {
        this.juegoActual = juego;
        nombreLabel.setText(juego.getName());
        precioLabel.setText("$" + juego.getPrice());

        // Cargar imagen
        String rutaImagen = "/imagenes/" + juego.getImage();
        try {
            ImageIcon icon = new ImageIcon(ProductPanel.class.getResource(rutaImagen));
            Image img = icon.getImage().getScaledInstance(480, 480, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(img));
            imagenLabel.setText(null);
        } catch (Exception e) {
            imagenLabel.setText("Imagen no disponible");
        }

        // Actualizar checkbox
        enListaCheckBox.setSelected(enLista);
    }
}