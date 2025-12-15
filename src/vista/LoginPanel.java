package vista;

import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPanel(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Colores
        Color mainBG = new Color(34, 34, 59);
        Color contrastBG = new Color(74, 78, 105);
        Color panelBG = new Color(242, 233, 228);
        Color mainText = new Color(255, 255, 255);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);

        // Logo
        URL url = LoginPanel.class.getResource("/logo.png");
        ImageIcon loginIcon = new ImageIcon(url);
        Image img = loginIcon.getImage().getScaledInstance(220, 40, Image.SCALE_SMOOTH);
        loginIcon = new ImageIcon(img);

        setLayout(new BorderLayout());

        // Componentes principales
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //region PANELES PRINCIPALES
        topPanel.setBackground(mainBG);
        topPanel.setPreferredSize(new Dimension(0, 72));
        topPanel.setLayout(new GridLayout(1, 2));

        centerPanel.setBackground(contrastBG);
        centerPanel.setLayout(new GridBagLayout());

        bottomPanel.setBackground(mainBG);
        bottomPanel.setPreferredSize(new Dimension(0, 144));
        bottomPanel.setLayout(new GridLayout(1, 2));
        //endregion

        //region TOP PANEL
        JPanel leftTopPanel = new JPanel();
        JPanel rightTopPanel = new JPanel();

        leftTopPanel.setLayout(new GridLayout(1, 2));
        JPanel subLogo = new JPanel();
        JPanel subLogoUseless = new JPanel();

        subLogo.setBackground(mainBG);
        subLogoUseless.setBackground(mainBG);

        subLogo.setLayout(new GridBagLayout());
        JLabel logo = new JLabel(loginIcon);
        subLogo.add(logo);

        leftTopPanel.add(subLogo);
        leftTopPanel.add(subLogoUseless);

        leftTopPanel.setBackground(mainBG);
        rightTopPanel.setBackground(mainBG);

        topPanel.add(leftTopPanel);
        topPanel.add(rightTopPanel);
        //endregion

        //region CENTER PANEL
        JPanel mainCenterPanel = new JPanel(new GridLayout(1, 2));
        mainCenterPanel.setBackground(panelBG);
        mainCenterPanel.setPreferredSize(new Dimension(800, 400));

        // PANEL IZQUIERDO (Formulario)
        JPanel leftCenterPanel = new JPanel();
        leftCenterPanel.setBackground(panelBG);
        leftCenterPanel.setLayout(new BoxLayout(leftCenterPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Iniciar sesión");
        title.setFont(new Font("Roboto", Font.BOLD, 32));
        title.setForeground(secondaryText);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel email = new JLabel("Correo electrónico");
        email.setFont(new Font("Roboto", Font.PLAIN, 16));
        email.setForeground(secondaryText);
        email.setAlignmentX(Component.CENTER_ALIGNMENT);

        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(360, 40));

        JLabel password = new JLabel("Contraseña");
        password.setFont(new Font("Roboto", Font.PLAIN, 16));
        password.setForeground(secondaryText);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(360, 40));

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Roboto", Font.BOLD, 20));
        loginButton.setForeground(secondaryText);
        loginButton.setBackground(buttonBG);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(true);
        loginButton.setPreferredSize(new Dimension(360, 56));
        loginButton.setMaximumSize(new Dimension(360, 56));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ACCIÓN DEL BOTÓN
        loginButton.addActionListener(e -> {
            String emailText = emailField.getText();
            String passwordText = new String(passwordField.getPassword());
            controlador.intentarLogin(emailText, passwordText);
        });

        leftCenterPanel.add(Box.createVerticalStrut(30));
        leftCenterPanel.add(title);
        leftCenterPanel.add(Box.createVerticalStrut(40));
        leftCenterPanel.add(email);
        leftCenterPanel.add(Box.createVerticalStrut(8));
        leftCenterPanel.add(emailField);
        leftCenterPanel.add(Box.createVerticalStrut(20));
        leftCenterPanel.add(password);
        leftCenterPanel.add(Box.createVerticalStrut(8));
        leftCenterPanel.add(passwordField);
        leftCenterPanel.add(Box.createVerticalStrut(30));
        leftCenterPanel.add(loginButton);

        // PANEL DERECHO (QR)
        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setBackground(panelBG);
        rightCenterPanel.setLayout(new BoxLayout(rightCenterPanel, BoxLayout.Y_AXIS));

        JLabel qrImage = new JLabel(new ImageIcon("recursos/imagenes/logo.png"));
        qrImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel qrText = new JLabel("Usa el QR para registrarte (obviamente, no funciona)");
        qrText.setFont(new Font("Roboto", Font.PLAIN, 14));
        qrText.setForeground(secondaryText);
        qrText.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightCenterPanel.add(Box.createVerticalGlue());
        rightCenterPanel.add(qrImage);
        rightCenterPanel.add(Box.createVerticalStrut(12));
        rightCenterPanel.add(qrText);
        rightCenterPanel.add(Box.createVerticalGlue());

        mainCenterPanel.add(leftCenterPanel);
        mainCenterPanel.add(rightCenterPanel);

        centerPanel.add(mainCenterPanel);
        //endregion

        //region BOTTOM PANEL
        JPanel leftBottomPanel = new JPanel();
        JPanel rightBottomPanel = new JPanel();

        leftBottomPanel.setBackground(mainBG);
        leftBottomPanel.setLayout(new GridBagLayout());
        rightBottomPanel.setBackground(mainBG);
        rightBottomPanel.setLayout(new GridBagLayout());

        // Izquierda (Crear cuenta)
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(mainBG);
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));

        JLabel question = new JLabel("¿Aún no tienes cuenta?");
        question.setForeground(mainText);
        question.setFont(new Font("Roboto", Font.PLAIN, 26));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton goToRegister = new JButton("Crear una cuenta");
        goToRegister.setFont(new Font("Roboto", Font.BOLD, 22));
        goToRegister.setForeground(secondaryText);
        goToRegister.setBackground(buttonBG);
        goToRegister.setFocusPainted(false);
        goToRegister.setBorderPainted(false);
        goToRegister.setOpaque(true);
        goToRegister.setPreferredSize(new Dimension(360, 64));
        goToRegister.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ACCIÓN DEL BOTÓN
        goToRegister.addActionListener(e -> controlador.mostrarRegister());

        leftColumn.add(question);
        leftColumn.add(Box.createVerticalStrut(12));
        leftColumn.add(goToRegister);

        leftBottomPanel.add(leftColumn);

        // Derecha (Slogan)
        JLabel catchphrase = new JLabel("Juega más, paga menos");
        catchphrase.setForeground(mainText);
        catchphrase.setFont(new Font("Roboto", Font.BOLD, 32));
        rightBottomPanel.add(catchphrase);

        bottomPanel.add(leftBottomPanel);
        bottomPanel.add(rightBottomPanel);
        //endregion

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void limpiarCampos() {
        emailField.setText("");
        passwordField.setText("");
    }
}