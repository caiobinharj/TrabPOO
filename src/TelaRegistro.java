import javax.swing.*;
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(235,68,71));

        // Campos de registro
        JTextField loginField = new JTextField(20);
        JPasswordField senhaField = new JPasswordField(20);
        JTextField nomeField = new JTextField(20);
        JSpinner idadeSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
        JComboBox<String> sexoCombo = new JComboBox<>(new String[]{"Masculino", "Feminino", "Prefiro não declarar"});
        JTextField cidadeField = new JTextField(20);
        JTextField prefMusicalField = new JTextField(20);
        JCheckBox bebeCheck = new JCheckBox();
        JCheckBox fumaCheck = new JCheckBox();
        JTextField orientacaoField = new JTextField(20);
        JTextField fotoField = new JTextField(20);
        JTextField hobbiesField = new JTextField(20);
        JCheckBox trabalhaCheck = new JCheckBox();
        JCheckBox faculdadeCheck = new JCheckBox();
        JComboBox<String> periodoCombo = new JComboBox<>(new String[]{"Diurno", "Noturno"});
        JCheckBox exercitaCheck = new JCheckBox();
        JTextArea descricaoArea = new JTextArea(4, 20);

        // Adicionar componentes ao painel
        mainPanel.add(new JLabel("Login:"));
        mainPanel.add(loginField);
        mainPanel.add(new JLabel("Senha:"));
        mainPanel.add(senhaField);
        mainPanel.add(new JLabel("Nome:"));
        mainPanel.add(nomeField);
        mainPanel.add(new JLabel("Idade:"));
        mainPanel.add(idadeSpinner);
        mainPanel.add(new JLabel("Sexo:"));
        mainPanel.add(sexoCombo);
        mainPanel.add(new JLabel("Cidade:"));
        mainPanel.add(cidadeField);
        mainPanel.add(new JLabel("Preferência Musical:"));
        mainPanel.add(prefMusicalField);
        mainPanel.add(new JLabel("Bebe?"));
        mainPanel.add(bebeCheck);
        mainPanel.add(new JLabel("Fuma?"));
        mainPanel.add(fumaCheck);
        mainPanel.add(new JLabel("Orientação Sexual:"));
        mainPanel.add(orientacaoField);
        mainPanel.add(new JLabel("Hobbies:"));
        mainPanel.add(hobbiesField);
        mainPanel.add(new JLabel("Trabalha?"));
        mainPanel.add(trabalhaCheck);
        mainPanel.add(new JLabel("Faculdade?"));
        mainPanel.add(faculdadeCheck);
        mainPanel.add(new JLabel("Período:"));
        mainPanel.add(periodoCombo);
        mainPanel.add(new JLabel("Se exercita?"));
        mainPanel.add(exercitaCheck);
        mainPanel.add(new JLabel("Descrição:"));
        mainPanel.add(new JScrollPane(descricaoArea));

        JButton registrarButton = new JButton("Registrar");
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registrarButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);

        //Comentário
    }
}
