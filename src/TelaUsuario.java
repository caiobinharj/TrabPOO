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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 0, 0)); // Fundo vermelho do painel

        // Criar botões estilizados
        JButton procurarMatchButton = createStyledButton("Procurar Match");
        JButton editarPerfilButton = createStyledButton("Editar Perfil");
        JButton matchesButton = createStyledButton("Matches");
        JButton logoutButton = createStyledButton("Logout");

        mainPanel.add(procurarMatchButton);
        mainPanel.add(editarPerfilButton);
        mainPanel.add(matchesButton);
        mainPanel.add(logoutButton);

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
        button.setPreferredSize(new Dimension(200, 50));

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
