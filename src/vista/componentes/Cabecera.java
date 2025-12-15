package vista.componentes;
import javax.swing.*;
import java.awt.*;
import controlador.ControladorPrincipal;

public class Cabecera extends JPanel {
    private ControladorPrincipal controlador;

    // Constructor básico (Home, Mi Lista, Cuenta)
    public Cabecera(ControladorPrincipal controlador) {
        this(controlador, false);
    }

    // Constructor con opción de mostrar botón de Logout
    public Cabecera(ControladorPrincipal controlador, boolean mostrarLogout) {
        this.controlador = controlador;
        inicializarComponentes(mostrarLogout);
    }

    private void inicializarComponentes(boolean mostrarLogout) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(e -> controlador.mostrarHome());

        JButton btnMyList = new JButton("Mi Lista");
        btnMyList.addActionListener(e -> controlador.mostrarMyList());

        JButton btnAccount = new JButton("Cuenta");
        btnAccount.addActionListener(e -> controlador.mostrarAccount());

        add(btnHome);
        add(btnMyList);
        add(btnAccount);

        // Si se pide, agregar botón de Logout
        if (mostrarLogout) {
            JButton btnLogout = new JButton("Cerrar Sesión");
            btnLogout.addActionListener(e -> controlador.cerrarSesion());
            add(btnLogout);
        }
    }
}