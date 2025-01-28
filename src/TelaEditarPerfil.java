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
        JComboBox<String> periodoCombo = new JComboBox<>(new String[]{"D", "N"});
        periodoCombo.setSelectedItem(String.valueOf(usuario.getPeriodo()));
        JCheckBox exercitaCheck = new JCheckBox("", usuario.getExercita());
        JTextArea descricaoArea = new JTextArea(usuario.getDescricao(), 4, 20);

        // Adicionar campos ao painel
        mainPanel.add(new JLabel("Cidade:"));
        mainPanel.add(cidadeField);
        // ... adicionar outros campos

        JButton salvarButton = new JButton("Salvar Alterações");
        salvarButton.addActionListener(e -> {
            usuario.setCidade(cidadeField.getText());
            usuario.setPrefMusical(prefMusicalField.getText());
            // ... atualizar outros campos

            sistema.atualizarUsuario(usuario);
            JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso!");
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
