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

        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.RED);

        JTextField cidadeField = new JTextField(usuario.getCidade(), 20);
        JTextField prefMusicalField = new JTextField(usuario.getPrefMusical(), 20);
        JCheckBox bebeCheck = new JCheckBox("Sim", usuario.getBebe());
        JCheckBox fumaCheck = new JCheckBox("Sim", usuario.getFuma());
        JTextField hobbiesField = new JTextField(usuario.getHobbies(), 20);
        JCheckBox trabalhaCheck = new JCheckBox("Sim", usuario.getTrabalha());
        JCheckBox faculdadeCheck = new JCheckBox("Sim", usuario.getFaculdade());
        JComboBox<String> periodoCombo = new JComboBox<>(new String[]{"Diurno", "Noturno"});
        periodoCombo.setSelectedItem(String.valueOf(usuario.getPeriodo()));
        JCheckBox exercitaCheck = new JCheckBox("Sim", usuario.getExercita());
        JTextArea descricaoArea = new JTextArea(usuario.getDescricao(), 4, 20);

        Color textColor = Color.WHITE;
        Font font = new Font("Arial", Font.BOLD, 14);

        for (Component c : new Component[]{cidadeField, prefMusicalField, hobbiesField, descricaoArea}) {
            c.setFont(font);
            c.setBackground(new Color(255, 235, 235));
            c.setForeground(Color.BLACK);
            ((JComponent) c).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        }

        for (JCheckBox check : new JCheckBox[]{bebeCheck, fumaCheck, trabalhaCheck, faculdadeCheck, exercitaCheck}) {
            check.setFont(font);
            check.setForeground(textColor);
            check.setBackground(Color.RED);
        }

        for (Component c : new Component[]{periodoCombo}) {
            c.setFont(font);
            c.setForeground(Color.BLACK);
        }

        mainPanel.add(createStyledLabel("Cidade:", textColor, font));
        mainPanel.add(cidadeField);

        mainPanel.add(createStyledLabel("Preferência Musical:", textColor, font));
        mainPanel.add(prefMusicalField);

        mainPanel.add(createStyledLabel("Bebe?", textColor, font));
        mainPanel.add(bebeCheck);

        mainPanel.add(createStyledLabel("Fuma?", textColor, font));
        mainPanel.add(fumaCheck);

        mainPanel.add(createStyledLabel("Hobbies:", textColor, font));
        mainPanel.add(hobbiesField);

        mainPanel.add(createStyledLabel("Trabalha?", textColor, font));
        mainPanel.add(trabalhaCheck);

        mainPanel.add(createStyledLabel("Faculdade?", textColor, font));
        mainPanel.add(faculdadeCheck);

        mainPanel.add(createStyledLabel("Período:", textColor, font));
        mainPanel.add(periodoCombo);

        mainPanel.add(createStyledLabel("Se exercita?", textColor, font));
        mainPanel.add(exercitaCheck);

        mainPanel.add(createStyledLabel("Descrição:", textColor, font));
        mainPanel.add(new JScrollPane(descricaoArea));

        JButton salvarButton = new JButton("Salvar Alterações");
        salvarButton.setFont(new Font("Arial", Font.BOLD, 16));
        salvarButton.setForeground(Color.WHITE);
        salvarButton.setBackground(new Color(50, 50, 50));
        salvarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

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
        buttonPanel.setBackground(Color.RED);
        buttonPanel.add(salvarButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JLabel createStyledLabel(String text, Color color, Font font) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        return label;
    }
}
