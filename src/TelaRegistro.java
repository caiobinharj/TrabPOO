import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaRegistro extends JFrame {
    private Sistema sistema;
    private Usuario novoUsuario;

    public TelaRegistro(Sistema sistema) {
        this.sistema = sistema;
        this.novoUsuario = new Usuario();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Registro de Novo Usuário");

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        mainPanel.setBackground(new Color(200, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = Color.WHITE;

        JTextField loginField = createStyledTextField();
        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setFont(labelFont);
        senhaField.setForeground(Color.BLACK);

        JTextField nomeField = createStyledTextField();
        JSpinner idadeSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
        JComboBox<String> sexoCombo = new JComboBox<>(new String[]{"Masculino", "Feminino", "Prefiro não declarar"});
        JTextField cidadeField = createStyledTextField();
        JTextField prefMusicalField = createStyledTextField();
        JCheckBox bebeCheck = new JCheckBox();
        JCheckBox fumaCheck = new JCheckBox();
        JTextField orientacaoField = createStyledTextField();
        JTextField hobbiesField = createStyledTextField();
        JCheckBox trabalhaCheck = new JCheckBox();
        JCheckBox faculdadeCheck = new JCheckBox();
        JComboBox<String> periodoCombo = new JComboBox<>(new String[]{"Diurno", "Noturno"});
        JCheckBox exercitaCheck = new JCheckBox();

        JTextArea descricaoArea = new JTextArea(4, 20);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        descricaoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoArea.setBackground(new Color(220, 50, 50));
        descricaoArea.setForeground(Color.WHITE);
        descricaoArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        JScrollPane descricaoScroll = new JScrollPane(descricaoArea);
        descricaoScroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        mainPanel.add(createStyledLabel("Login:", labelFont, textColor));
        mainPanel.add(loginField);
        mainPanel.add(createStyledLabel("Senha:", labelFont, textColor));
        mainPanel.add(senhaField);
        mainPanel.add(createStyledLabel("Nome:", labelFont, textColor));
        mainPanel.add(nomeField);
        mainPanel.add(createStyledLabel("Idade:", labelFont, textColor));
        mainPanel.add(idadeSpinner);
        mainPanel.add(createStyledLabel("Sexo:", labelFont, textColor));
        mainPanel.add(sexoCombo);
        mainPanel.add(createStyledLabel("Cidade:", labelFont, textColor));
        mainPanel.add(cidadeField);
        mainPanel.add(createStyledLabel("Preferência Musical:", labelFont, textColor));
        mainPanel.add(prefMusicalField);
        mainPanel.add(createStyledLabel("Bebe?", labelFont, textColor));
        mainPanel.add(bebeCheck);
        mainPanel.add(createStyledLabel("Fuma?", labelFont, textColor));
        mainPanel.add(fumaCheck);
        mainPanel.add(createStyledLabel("Orientação:", labelFont, textColor));
        mainPanel.add(orientacaoField);
        mainPanel.add(createStyledLabel("Hobbies:", labelFont, textColor));
        mainPanel.add(hobbiesField);
        mainPanel.add(createStyledLabel("Trabalha?", labelFont, textColor));
        mainPanel.add(trabalhaCheck);
        mainPanel.add(createStyledLabel("Faculdade?", labelFont, textColor));
        mainPanel.add(faculdadeCheck);
        mainPanel.add(createStyledLabel("Período:", labelFont, textColor));
        mainPanel.add(periodoCombo);
        mainPanel.add(createStyledLabel("Se exercita?", labelFont, textColor));
        mainPanel.add(exercitaCheck);
        mainPanel.add(createStyledLabel("Descrição:", labelFont, textColor));
        mainPanel.add(descricaoScroll);

        JButton registrarButton = createStyledButton("Registrar");
        registrarButton.addActionListener(e -> {
            novoUsuario.setLogin(loginField.getText());
            novoUsuario.setSenha(new String(senhaField.getPassword()));
            novoUsuario.setNome(nomeField.getText());
            novoUsuario.setIdade((Integer) idadeSpinner.getValue());
            novoUsuario.setCidade(cidadeField.getText());
            novoUsuario.setPrefMusical(prefMusicalField.getText());
            novoUsuario.setBebe(bebeCheck.isSelected());
            novoUsuario.setFuma(fumaCheck.isSelected());
            novoUsuario.setOrientacaoSexual(orientacaoField.getText());
            novoUsuario.setHobbies(hobbiesField.getText());
            novoUsuario.setTrabalha(trabalhaCheck.isSelected());
            novoUsuario.setFaculdade(faculdadeCheck.isSelected());
            novoUsuario.setPeriodo(periodoCombo.getSelectedItem().toString().charAt(0));
            novoUsuario.setExercita(exercitaCheck.isSelected());
            novoUsuario.setDescricao(descricaoArea.getText());
            novoUsuario.salvarUsuario();
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso!");
            new TelaInicial().setVisible(true);
            dispose();
        });

        JButton voltarButton = createStyledButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaInicial().setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 0, 0));
        buttonPanel.add(registrarButton);
        buttonPanel.add(voltarButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(Color.BLACK);
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        return field;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));

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
}
