import javax.swing.*;
public class TelaInicial extends JFrame {
    private Sistema sistema;

    public TelaInicial() {
        sistema = new Sistema();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Encontros");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Componentes de login
        JTextField loginField = new JTextField(20);
        JPasswordField senhaField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registroButton = new JButton("Registro");

        // Adicionar componentes ao painel
        mainPanel.add(new JLabel("Login:"));
        mainPanel.add(loginField);
        mainPanel.add(new JLabel("Senha:"));
        mainPanel.add(senhaField);
        mainPanel.add(loginButton);
        mainPanel.add(registroButton);

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

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}
