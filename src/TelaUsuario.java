import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaUsuario extends JFrame {
    private Sistema sistema;

    public TelaUsuario(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Área do Usuário");
        setSize(400, 600);
        getContentPane().setBackground(new Color(200, 0, 0)); // Fundo vermelho vibrante

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(200, 0, 0)); // Fundo vermelho do painel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Criar botões estilizados
        JButton procurarMatchButton = createStyledButton("Procurar Match");
        JButton editarPerfilButton = createStyledButton("Editar Perfil");
        JButton matchesButton = createStyledButton("Matches");
        JButton logoutButton = createStyledButton("Logout");

        gbc.gridy = 0;
        mainPanel.add(procurarMatchButton, gbc);
        gbc.gridy = 1;
        mainPanel.add(editarPerfilButton, gbc);
        gbc.gridy = 2;
        mainPanel.add(matchesButton, gbc);
        gbc.gridy = 3;
        mainPanel.add(logoutButton, gbc);

        // Ações dos botões
        procurarMatchButton.addActionListener(e -> {
            new TelaProcurarMatch(sistema).setVisible(true);
            dispose();
        });

        editarPerfilButton.addActionListener(e -> {
            new TelaEditarPerfil(sistema).setVisible(true);
            dispose();
        });

        matchesButton.addActionListener(e -> {
            new TelaMatches(sistema).setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            sistema.logout();
            new TelaInicial().setVisible(true);
            dispose();
        });

        add(mainPanel);
        setLocationRelativeTo(null);  // Centraliza a tela na tela principal
    }

    // Método para criar botões estilizados
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0)); // Cor de fundo vermelha
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(160, 90));

        // Efeito hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 0, 0)); // Efeito hover (vermelho mais claro)
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(180, 0, 0)); // Cor original após sair do hover
            }
        });

        return button;
    }
}
