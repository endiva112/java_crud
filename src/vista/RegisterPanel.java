package vista;

import controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RegisterPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox acceptTermsCheckBox;

    public RegisterPanel(ControladorPrincipal controlador) {
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
        URL url = RegisterPanel.class.getResource("/imagenes/logo.png");
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

        JLabel title = new JLabel("Crear cuenta");
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

        // Checkbox términos
        acceptTermsCheckBox = new JCheckBox("Acepto términos y condiciones");
        acceptTermsCheckBox.setFont(new Font("Roboto", Font.PLAIN, 14));
        acceptTermsCheckBox.setForeground(secondaryText);
        acceptTermsCheckBox.setBackground(panelBG);
        acceptTermsCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Roboto", Font.BOLD, 20));
        registerButton.setForeground(secondaryText);
        registerButton.setBackground(buttonBG);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setOpaque(true);
        registerButton.setPreferredSize(new Dimension(360, 56));
        registerButton.setMaximumSize(new Dimension(360, 56));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ACCIÓN DEL BOTÓN
        registerButton.addActionListener(e -> {
            String emailText = emailField.getText();
            String passwordText = new String(passwordField.getPassword());
            boolean acceptTerms = acceptTermsCheckBox.isSelected();
            controlador.intentarRegistro(emailText, passwordText, acceptTerms);
        });

        leftCenterPanel.add(Box.createVerticalStrut(30));
        leftCenterPanel.add(title);
        leftCenterPanel.add(Box.createVerticalStrut(30));
        leftCenterPanel.add(email);
        leftCenterPanel.add(Box.createVerticalStrut(8));
        leftCenterPanel.add(emailField);
        leftCenterPanel.add(Box.createVerticalStrut(20));
        leftCenterPanel.add(password);
        leftCenterPanel.add(Box.createVerticalStrut(8));
        leftCenterPanel.add(passwordField);
        leftCenterPanel.add(Box.createVerticalStrut(20));
        leftCenterPanel.add(acceptTermsCheckBox);
        leftCenterPanel.add(Box.createVerticalStrut(20));
        leftCenterPanel.add(registerButton);

        // PANEL DERECHO (Info)
        JPanel rightCenterPanel = new JPanel();
        rightCenterPanel.setBackground(panelBG);
        rightCenterPanel.setLayout(new BoxLayout(rightCenterPanel, BoxLayout.Y_AXIS));

        JLabel infoText = new JLabel("<html><center>Únete a nuestra comunidad<br>de jugadores</center></html>");
        infoText.setFont(new Font("Roboto", Font.BOLD, 24));
        infoText.setForeground(secondaryText);
        infoText.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightCenterPanel.add(Box.createVerticalGlue());
        rightCenterPanel.add(infoText);
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

        // Izquierda (Ya tengo cuenta)
        JPanel leftColumn = new JPanel();
        leftColumn.setBackground(mainBG);
        leftColumn.setLayout(new BoxLayout(leftColumn, BoxLayout.Y_AXIS));

        JLabel question = new JLabel("¿Ya tienes cuenta?");
        question.setForeground(mainText);
        question.setFont(new Font("Roboto", Font.PLAIN, 26));
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton goToLogin = new JButton("Iniciar sesión");
        goToLogin.setFont(new Font("Roboto", Font.BOLD, 22));
        goToLogin.setForeground(secondaryText);
        goToLogin.setBackground(buttonBG);
        goToLogin.setFocusPainted(false);
        goToLogin.setBorderPainted(false);
        goToLogin.setOpaque(true);
        goToLogin.setPreferredSize(new Dimension(360, 64));
        goToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ACCIÓN DEL BOTÓN
        goToLogin.addActionListener(e -> controlador.mostrarLogin());

        leftColumn.add(question);
        leftColumn.add(Box.createVerticalStrut(12));
        leftColumn.add(goToLogin);

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
        acceptTermsCheckBox.setSelected(false);
    }
}