import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class TelaInicial extends JFrame {
    private Sistema sistema;

    public TelaInicial() {
        sistema = new Sistema();
        initComponents();
    }

    private void initComponents() {
        // Configurações básicas da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Encontros");
        setPreferredSize(new Dimension(600, 600)); // Tamanho fixo da janela

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new ColorUIResource(235, 68, 71));
        mainPanel.setLayout(new GridBagLayout()); // Usando GridBagLayout para alinhamento

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente os componentes

        // Componentes de login
        JTextField loginField = new JTextField(20);
        JPasswordField senhaField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registroButton = new JButton("Registro");


        // Estilização dos botões
        styleButton(loginButton);
        styleButton(registroButton);

        // Adicionando componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(new JLabel("Login:"), gbc);

        gbc.gridy = 1;
        mainPanel.add(loginField, gbc);

        gbc.gridy = 2;
        mainPanel.add(new JLabel("Senha:"), gbc);

        gbc.gridy = 3;
        mainPanel.add(senhaField, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        mainPanel.add(loginButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(registroButton, gbc);

        // Ações dos botões
        loginButton.addActionListener(e -> {
            if (sistema.login(loginField.getText(), new String(senhaField.getPassword()))) {
                new TelaUsuario(sistema).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login inválido!");
            }
        });

        registroButton.addActionListener(e -> {
            new TelaRegistro(sistema).setVisible(true);
            dispose();
        });

        // Configurações finais
        add(mainPanel);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
    }

    // Método para estilizar botões
    private void styleButton(JButton button) {
        button.setBackground(new Color(235,68,71));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Inter", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setFocusPainted(false); // Remove borda de foco
        button.setPreferredSize(new Dimension(180, 40)); // Define tamanho fixo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}
