package vista;

import controlador.ControladorPrincipal;
import modelo.Usuario;
import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel avatarLabel;
    private String avatarPath;

    public AccountPanel(ControladorPrincipal controlador) {
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
        JButton btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(e -> controlador.cerrarSesion());

        topPanel.add(btnHome);
        topPanel.add(btnMyList);
        topPanel.add(btnAccount);
        topPanel.add(btnLogout);

        add(topPanel, BorderLayout.NORTH);

        // Panel central (datos)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Avatar
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        avatarLabel = new JLabel("Sin avatar");
        avatarLabel.setPreferredSize(new Dimension(100, 100));
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(avatarLabel, gbc);

        JButton btnSeleccionarAvatar = new JButton("Seleccionar Avatar");
        btnSeleccionarAvatar.addActionListener(e -> seleccionarAvatar());
        gbc.gridy = 1;
        centerPanel.add(btnSeleccionarAvatar, gbc);

        // Email
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Email:"), gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        centerPanel.add(emailField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Nueva Password:"), gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        centerPanel.add(passwordField, gbc);

        // Botón actualizar
        JButton btnActualizar = new JButton("Actualizar Datos");
        btnActualizar.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            controlador.actualizarCuenta(email, password, avatarPath);
        });
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        centerPanel.add(btnActualizar, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void seleccionarAvatar() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            avatarPath = fileChooser.getSelectedFile().getAbsolutePath();
            avatarLabel.setText("Avatar seleccionado");
        }
    }

    public void cargarDatosUsuario(Usuario usuario) {
        emailField.setText(usuario.getEmail());
        passwordField.setText("");

        if (usuario.getAvatar() != null) {
            avatarLabel.setText("Avatar: " + usuario.getAvatar());
            avatarPath = usuario.getAvatar();
        }
    }
}