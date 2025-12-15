package vista.componentes;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import controlador.ControladorPrincipal;

public class Cabecera extends JPanel {
    private ControladorPrincipal controlador;

    public Cabecera(ControladorPrincipal controlador) {
        this.controlador = controlador;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Colores
        Color mainBG = new Color(34, 34, 59);
        Color buttonBG = new Color(170, 160, 165);
        Color secondaryText = new Color(0, 0, 0);

        setBackground(mainBG);
        setPreferredSize(new Dimension(0, 72));
        setLayout(new GridLayout(1, 2));

        //region PANEL IZQUIERDO (Logo)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(1, 2));
        leftPanel.setBackground(mainBG);

        JPanel subLogo = new JPanel();
        JPanel subLogoUseless = new JPanel();

        subLogo.setBackground(mainBG);
        subLogoUseless.setBackground(mainBG);
        subLogo.setLayout(new GridBagLayout());

        // Logo
        URL urlLogo = Cabecera.class.getResource("/imagenes/logo.png");
        ImageIcon logoIcon = new ImageIcon(urlLogo);
        Image imgLogo = logoIcon.getImage().getScaledInstance(220, 40, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(imgLogo);

        JLabel logo = new JLabel(logoIcon);
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlador.mostrarHome();
            }
        });
        subLogo.add(logo);

        leftPanel.add(subLogo);
        leftPanel.add(subLogoUseless);
        //endregion

        //region PANEL DERECHO (Botones)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(mainBG);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        // Botón Mi Lista (con icono)
        JButton btnMyList = new JButton();
        URL urlShopping = Cabecera.class.getResource("/imagenes/icons/shoppingIcon.png");
        ImageIcon shoppingIcon = new ImageIcon(urlShopping);
        Image imgShopping = shoppingIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        btnMyList.setIcon(new ImageIcon(imgShopping));
        btnMyList.setBackground(buttonBG);
        btnMyList.setFocusPainted(false);
        btnMyList.setBorderPainted(false);
        btnMyList.setPreferredSize(new Dimension(50, 50));
        btnMyList.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMyList.addActionListener(e -> controlador.mostrarMyList());

        // Botón Mi Perfil (con foto y texto)
        JButton btnAccount = new JButton("Mi Perfil");
        URL urlUser = Cabecera.class.getResource("/imagenes/icons/userIcon.png");
        ImageIcon userIcon = new ImageIcon(urlUser);
        Image imgUser = userIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(imgUser);

        btnAccount.setIcon(userIcon);

        btnAccount.setFont(new Font("Roboto", Font.BOLD, 16));
        btnAccount.setForeground(secondaryText);
        btnAccount.setBackground(buttonBG);
        btnAccount.setFocusPainted(false);
        btnAccount.setBorderPainted(false);
        btnAccount.setPreferredSize(new Dimension(150, 50));
        btnAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAccount.addActionListener(e -> controlador.mostrarAccount());

        rightPanel.add(btnMyList);
        rightPanel.add(btnAccount);
        //endregion

        add(leftPanel);
        add(rightPanel);
    }
}