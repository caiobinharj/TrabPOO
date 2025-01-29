import javax.swing.*;
import javax.swing.border.LineBorder;
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
        setTitle("Lovelace");
        setSize(600, 600);
        setResizable(false);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200, 0, 0)); // Vermelho vibrante
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título estilizado
        JLabel titleLabel = new JLabel("Bem-vindo ao Lovelace", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Campos de login
        JTextField loginField = createRoundedTextField();
        JPasswordField senhaField = createRoundedPasswordField();
        JButton loginButton = createStyledButton("Login");
        JButton registroButton = createStyledButton("Registro");

        // Adicionando componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        mainPanel.add(createStyledLabel("Login:"), gbc);

        gbc.gridy = 2;
        mainPanel.add(loginField, gbc);

        gbc.gridy = 3;
        mainPanel.add(createStyledLabel("Senha:"), gbc);

        gbc.gridy = 4;
        mainPanel.add(senhaField, gbc);

        gbc.gridy = 5;
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

        // Adicionando painel ao frame
        add(mainPanel);
        setLocationRelativeTo(null);
    }

    // Método para criar labels estilizadas
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Método para criar campos de entrada arredondados
    private JTextField createRoundedTextField() {
        JTextField textField = new JTextField(20);
        textField.setBorder(new LineBorder(Color.WHITE, 2, true));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        return textField;
    }

    // Método para criar campos de senha arredondados
    private JPasswordField createRoundedPasswordField() {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(new LineBorder(Color.WHITE, 2, true));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        return passwordField;
    }

    // Método para criar botões estilizados
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40));

        // Efeito hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(180, 0, 0));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInicial().setVisible(true));
    }
}
