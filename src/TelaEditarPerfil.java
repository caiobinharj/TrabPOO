import javax.swing.*;
import java.awt.*;

public class TelaEditarPerfil extends JFrame {
    private Sistema sistema;
    private Usuario usuario;

    public TelaEditarPerfil(Sistema sistema) {
        this.sistema = sistema;
        this.usuario = sistema.getUsuarioLogado();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Editar Perfil");

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campos de edição preenchidos com dados atuais
        JTextField cidadeField = new JTextField(usuario.getCidade(), 20);
        JTextField prefMusicalField = new JTextField(usuario.getPrefMusical(), 20);
        JCheckBox bebeCheck = new JCheckBox("", usuario.getBebe());
        JCheckBox fumaCheck = new JCheckBox("", usuario.getFuma());
        JTextField hobbiesField = new JTextField(usuario.getHobbies(), 20);
        JCheckBox trabalhaCheck = new JCheckBox("", usuario.getTrabalha());
        JCheckBox faculdadeCheck = new JCheckBox("", usuario.getFaculdade());
        JComboBox<String> periodoCombo = new JComboBox<>(new String[]{"Diurno", "Noturno"});
        periodoCombo.setSelectedItem(String.valueOf(usuario.getPeriodo()));
        JCheckBox exercitaCheck = new JCheckBox("", usuario.getExercita());
        JTextArea descricaoArea = new JTextArea(usuario.getDescricao(), 4, 20);

        mainPanel.add(new JLabel("Cidade:"));
        mainPanel.add(cidadeField);

        mainPanel.add(new JLabel("Preferência Musical:"));
        mainPanel.add(prefMusicalField);

        mainPanel.add(new JLabel("Bebe?"));
        mainPanel.add(bebeCheck);

        mainPanel.add(new JLabel("Fuma?"));
        mainPanel.add(fumaCheck);

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

        JButton salvarButton = new JButton("Salvar Alterações");
        salvarButton.addActionListener(e -> {
            usuario.setCidade(cidadeField.getText());
            usuario.setPrefMusical(prefMusicalField.getText());
            usuario.setBebe(bebeCheck.isSelected());
            usuario.setFuma(fumaCheck.isSelected());
            usuario.setHobbies(hobbiesField.getText());
            usuario.setTrabalha(trabalhaCheck.isSelected());
            usuario.setFaculdade(faculdadeCheck.isSelected());
            usuario.setPeriodo(periodoCombo.getSelectedItem().toString().charAt(0));
            usuario.setExercita(exercitaCheck.isSelected());
            usuario.setDescricao(descricaoArea.getText());

            sistema.atualizarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!");
            new TelaUsuario(sistema).setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(salvarButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }
}
