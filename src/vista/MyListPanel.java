package vista;

import controlador.ControladorPrincipal;
import modelo.Juego;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyListPanel extends JPanel {
    private ControladorPrincipal controlador;
    private JPanel listaPanelContainer;

    public MyListPanel(ControladorPrincipal controlador) {
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

        // Lista
        listaPanelContainer = new JPanel();
        listaPanelContainer.setLayout(new BoxLayout(listaPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listaPanelContainer);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void cargarLista(List<Juego> juegos) {
        listaPanelContainer.removeAll();

        if (juegos.isEmpty()) {
            listaPanelContainer.add(new JLabel("Tu lista está vacía"));
        } else {
            for (int i = 0; i < juegos.size(); i++) {
                final int index = i;
                Juego juego = juegos.get(i);

                JPanel juegoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                juegoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                JLabel lblNombre = new JLabel(juego.getName() + " - $" + juego.getPrice());
                JButton btnVer = new JButton("Ver");
                btnVer.addActionListener(e -> controlador.mostrarProduct(juego));

                JButton btnEliminar = new JButton("Eliminar");
                btnEliminar.addActionListener(e -> controlador.eliminarJuegoDeLista(index));

                juegoPanel.add(lblNombre);
                juegoPanel.add(btnVer);
                juegoPanel.add(btnEliminar);

                listaPanelContainer.add(juegoPanel);
            }
        }

        listaPanelContainer.revalidate();
        listaPanelContainer.repaint();
    }
}