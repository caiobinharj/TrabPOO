import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaPerfil extends JFrame {
    private Sistema sistema;
    private Usuario usuario;

    public TelaPerfil(Sistema sistema, Usuario usuario) {
        this.sistema = sistema;
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Perfil de " + usuario.getNome());
        setSize(400, 600);

        // Painel principal com fundo vermelho
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 0, 0)); // Vermelho vibrante
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Estilização dos rótulos
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = Color.WHITE;

        JLabel loginLabel = createStyledLabel("Login: " + usuario.getLogin(), labelFont, textColor);
        JLabel nomeLabel = createStyledLabel("Nome: " + usuario.getNome(), labelFont, textColor);
        JLabel idadeLabel = createStyledLabel("Idade: " + usuario.getIdade(), labelFont, textColor);
        JLabel sexoLabel = createStyledLabel("Sexo: " + usuario.getSexo(), labelFont, textColor);
        JLabel cidadeLabel = createStyledLabel("Cidade: " + usuario.getCidade(), labelFont, textColor);
        JLabel prefMusicalLabel = createStyledLabel("Preferência Musical: " + usuario.getPrefMusical(), labelFont, textColor);
        JLabel bebeLabel = createStyledLabel("Bebe? " + (usuario.getBebe() ? "Sim" : "Não"), labelFont, textColor);
        JLabel fumaLabel = createStyledLabel("Fuma? " + (usuario.getFuma() ? "Sim" : "Não"), labelFont, textColor);
        JLabel orientacaoLabel = createStyledLabel("Orientação Sexual: " + usuario.getOrientacaoSexual(), labelFont, textColor);
        JLabel hobbiesLabel = createStyledLabel("Hobbies: " + usuario.getHobbies(), labelFont, textColor);
        JLabel trabalhaLabel = createStyledLabel("Trabalha? " + (usuario.getTrabalha() ? "Sim" : "Não"), labelFont, textColor);
        JLabel faculdadeLabel = createStyledLabel("Faculdade? " + (usuario.getFaculdade() ? "Sim" : "Não"), labelFont, textColor);
        JLabel periodoLabel = createStyledLabel("Período: " + usuario.getPeriodo(), labelFont, textColor);
        JLabel exercitaLabel = createStyledLabel("Se exercita? " + (usuario.getExercita() ? "Sim" : "Não"), labelFont, textColor);
        JLabel descricaoLabel = createStyledLabel("Descrição:", labelFont, textColor);

        JTextArea descricaoArea = new JTextArea(usuario.getDescricao(), 4, 20);
        descricaoArea.setEditable(false);
        descricaoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        descricaoArea.setBackground(new Color(220, 50, 50)); // Fundo vermelho escuro
        descricaoArea.setForeground(Color.WHITE);
        descricaoArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        JScrollPane descricaoScroll = new JScrollPane(descricaoArea);
        descricaoScroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Adicionar componentes ao painel principal
        mainPanel.add(loginLabel);
        mainPanel.add(nomeLabel);
        mainPanel.add(idadeLabel);
        mainPanel.add(sexoLabel);
        mainPanel.add(cidadeLabel);
        mainPanel.add(prefMusicalLabel);
        mainPanel.add(bebeLabel);
        mainPanel.add(fumaLabel);
        mainPanel.add(orientacaoLabel);
        mainPanel.add(hobbiesLabel);
        mainPanel.add(trabalhaLabel);
        mainPanel.add(faculdadeLabel);
        mainPanel.add(periodoLabel);
        mainPanel.add(exercitaLabel);
        mainPanel.add(descricaoLabel);
        mainPanel.add(descricaoScroll);

        // Botão estilizado de voltar
        JButton voltarButton = createStyledButton("Voltar");
        voltarButton.addActionListener(e -> dispose());

        // Painel do botão
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 0, 0));
        buttonPanel.add(voltarButton);

        // Adicionar tudo ao frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    // Método para criar rótulos estilizados
    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    // Método para criar botões estilizados
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));

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
}
