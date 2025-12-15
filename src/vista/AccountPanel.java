package vista;

import controlador.ControladorPrincipal;
import modelo.Usuario;
import vista.componentes.Cabecera;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class AccountPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel avatarLabel;

    public AccountPanel(ControladorPrincipal controlador) {
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

        setLayout(new BorderLayout());

        // Cabecera (sin logout)
        add(new Cabecera(controlador), BorderLayout.NORTH);

        // Panel central
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(contrastBG);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setBackground(panelBG);
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));
        mainCenterPanel.setPreferredSize(new Dimension(500, 550));

        JLabel title = new JLabel("Mi Cuenta");
        title.setFont(new Font("Roboto", Font.BOLD, 32));
        title.setForeground(secondaryText);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Avatar
        avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new Dimension(120, 120));
        avatarLabel.setMaximumSize(new Dimension(120, 120));
        avatarLabel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        URL urlAvatar = AccountPanel.class.getResource("/imagenes/icons/userIcon.png");
        ImageIcon icon = new ImageIcon(urlAvatar);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        avatarLabel.setIcon(new ImageIcon(img));

        // TODO: Implementar funcionalidad para cambiar avatar (JFileChooser)

        JLabel emailLabel = new JLabel("Correo electrónico");
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        emailLabel.setForeground(secondaryText);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(360, 40));

        JLabel passwordLabel = new JLabel("Nueva contraseña");
        passwordLabel.setFont(new Font("Roboto", Font.PLAIN, 16));
        passwordLabel.setForeground(secondaryText);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(360, 40));

        JButton btnActualizar = new JButton("Actualizar Datos");
        btnActualizar.setFont(new Font("Roboto", Font.BOLD, 18));
        btnActualizar.setForeground(secondaryText);
        btnActualizar.setBackground(buttonBG);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setOpaque(true);
        btnActualizar.setPreferredSize(new Dimension(360, 50));
        btnActualizar.setMaximumSize(new Dimension(360, 50));
        btnActualizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnActualizar.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            controlador.actualizarCuenta(email, password, null);
        });

        JButton btnEliminar = new JButton("Eliminar Cuenta");
        btnEliminar.setFont(new Font("Roboto", Font.BOLD, 18));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setBackground(new Color(200, 50, 50));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setOpaque(true);
        btnEliminar.setPreferredSize(new Dimension(360, 50));
        btnEliminar.setMaximumSize(new Dimension(360, 50));
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEliminar.addActionListener(e -> controlador.eliminarCuenta());

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Roboto", Font.BOLD, 18));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(new Color(100, 100, 100));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setOpaque(true);
        btnCerrarSesion.setPreferredSize(new Dimension(360, 50));
        btnCerrarSesion.setMaximumSize(new Dimension(360, 50));
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.addActionListener(e -> controlador.cerrarSesion());

        mainCenterPanel.add(Box.createVerticalStrut(30));
        mainCenterPanel.add(title);
        mainCenterPanel.add(Box.createVerticalStrut(20));
        mainCenterPanel.add(avatarLabel);
        mainCenterPanel.add(Box.createVerticalStrut(30));
        mainCenterPanel.add(emailLabel);
        mainCenterPanel.add(Box.createVerticalStrut(8));
        mainCenterPanel.add(emailField);
        mainCenterPanel.add(Box.createVerticalStrut(20));
        mainCenterPanel.add(passwordLabel);
        mainCenterPanel.add(Box.createVerticalStrut(8));
        mainCenterPanel.add(passwordField);
        mainCenterPanel.add(Box.createVerticalStrut(30));
        mainCenterPanel.add(btnActualizar);
        mainCenterPanel.add(Box.createVerticalStrut(15));
        mainCenterPanel.add(btnCerrarSesion);
        mainCenterPanel.add(Box.createVerticalStrut(15));
        mainCenterPanel.add(btnEliminar);

        centerPanel.add(mainCenterPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void cargarDatosUsuario(Usuario usuario) {
        emailField.setText(usuario.getEmail());
        passwordField.setText("");
    }
}