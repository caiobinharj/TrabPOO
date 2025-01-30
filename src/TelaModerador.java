import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TelaModerador extends JFrame {
    private Moderador moderador;
    private Sistema sistema;

    public TelaModerador(Sistema sistema) {
        this.sistema = sistema;
        this.moderador = new Moderador();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Painel do Moderador");
        setSize(400, 500);
        getContentPane().setBackground(new Color(0, 100, 200));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0, 100, 200));

        JButton listarUsuariosButton = createStyledButton("Listar Usuários");
        JButton denunciasButton = createStyledButton("Denúncias");
        JButton logoutButton = createStyledButton("Logout");

        mainPanel.add(listarUsuariosButton);
        mainPanel.add(denunciasButton);
        mainPanel.add(logoutButton);

        listarUsuariosButton.addActionListener(e -> {
            new TelaListarUsuarios(sistema).setVisible(true);
            dispose();
        });

        denunciasButton.addActionListener(e -> {
            new TelaDenuncias(sistema).setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            sistema.logout();
            new TelaInicial().setVisible(true);
            dispose();
        });

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 80, 180));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 220));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 80, 180));
            }
        });

        return button;
    }
}