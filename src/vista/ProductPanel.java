package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import javax.swing.*;
import java.awt.*;

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

        // Panel central (detalles del juego)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Imagen
        imagenLabel = new JLabel("Imagen del juego");
        imagenLabel.setPreferredSize(new Dimension(200, 200));
        imagenLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        centerPanel.add(imagenLabel, gbc);

        // Nombre
        nombreLabel = new JLabel();
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 1;
        centerPanel.add(nombreLabel, gbc);

        // Precio
        precioLabel = new JLabel();
        precioLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 2;
        centerPanel.add(precioLabel, gbc);

        // Checkbox en lista
        enListaCheckBox = new JCheckBox("Agregar a mi lista");
        enListaCheckBox.addActionListener(e -> {
            if (juegoActual != null) {
                controlador.toggleJuegoEnLista(juegoActual);
            }
        });
        gbc.gridy = 3;
        centerPanel.add(enListaCheckBox, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarProducto(Juego juego, boolean enLista) {
        this.juegoActual = juego;
        nombreLabel.setText(juego.getName());
        precioLabel.setText("Precio: $" + juego.getPrice());

        // Cargar imagen
        String rutaImagen = "recursos/imagenes/" + juego.getImage(); // Ajusta la ruta según tu proyecto
        ImageIcon icon = new ImageIcon(rutaImagen);
        // Escalar imagen al tamaño del JLabel
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imagenLabel.setIcon(new ImageIcon(img));
        imagenLabel.setText(null); // quitar el texto

        // Importante: quitar el listener antes de cambiar el estado
        // para evitar que se dispare el evento
        enListaCheckBox.removeActionListener(enListaCheckBox.getActionListeners()[0]);
        enListaCheckBox.setSelected(enLista);
        enListaCheckBox.addActionListener(e -> {
            if (juegoActual != null) {
                controlador.toggleJuegoEnLista(juegoActual);
            }
        });
    }
}