import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaProcurarMatch extends JFrame {
    private Sistema sistema;
    private Usuario usuarioAtual;
    private Usuario usuarioLogado;

    public TelaProcurarMatch(Sistema sistema) {
        this.sistema = sistema;
        this.usuarioLogado = sistema.getUsuarioLogado();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Procurar Match");
        setSize(400, 600);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(200, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(new Color(200, 0, 0));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color textColor = Color.WHITE;




        JLabel nomeLabel = createStyledLabel("", labelFont, textColor);
        JLabel idadeLabel = createStyledLabel("", labelFont, textColor);
        JLabel sexoLabel = createStyledLabel("", labelFont, textColor);
        JLabel cidadeLabel = createStyledLabel("", labelFont, textColor);
        JLabel prefMusicalLabel = createStyledLabel("", labelFont, textColor);
        JLabel bebeLabel = createStyledLabel("", labelFont, textColor);
        JLabel fumaLabel = createStyledLabel("", labelFont, textColor);
        JLabel orientacaoLabel = createStyledLabel("", labelFont, textColor);
        JLabel hobbiesLabel = createStyledLabel("", labelFont, textColor);
        JLabel trabalhaLabel = createStyledLabel("", labelFont, textColor);
        JLabel faculdadeLabel = createStyledLabel("", labelFont, textColor);
        JLabel periodoLabel = createStyledLabel("", labelFont, textColor);
        JLabel exercitaLabel = createStyledLabel("", labelFont, textColor);
        JLabel descricaoLabel = createStyledLabel("Descrição:", labelFont, textColor);

        JTextArea descricaoArea = new JTextArea(4, 20);
        descricaoArea.setEditable(false);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);
        descricaoArea.setFont(new Font("Arial", Font.PLAIN, 12));
        descricaoArea.setBackground(new Color(220, 50, 50));
        descricaoArea.setForeground(Color.WHITE);
        descricaoArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        JScrollPane descricaoScroll = new JScrollPane(descricaoArea);
        descricaoScroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        userInfoPanel.add(nomeLabel);
        userInfoPanel.add(idadeLabel);
        userInfoPanel.add(sexoLabel);
        userInfoPanel.add(cidadeLabel);
        userInfoPanel.add(prefMusicalLabel);
        userInfoPanel.add(bebeLabel);
        userInfoPanel.add(fumaLabel);
        userInfoPanel.add(orientacaoLabel);
        userInfoPanel.add(hobbiesLabel);
        userInfoPanel.add(trabalhaLabel);
        userInfoPanel.add(faculdadeLabel);
        userInfoPanel.add(periodoLabel);
        userInfoPanel.add(exercitaLabel);
        userInfoPanel.add(descricaoLabel);
        userInfoPanel.add(descricaoScroll);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(200, 0, 0));
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        JButton dislikeButton = createStyledButton("Dislike");
        JButton likeButton = createStyledButton("Like");
        JButton denunciarButton = createStyledButton("Denunciar");

        buttonPanel.add(dislikeButton);
        buttonPanel.add(likeButton);
        buttonPanel.add(denunciarButton);

        ActionListener proximoUsuario = e -> {
            usuarioAtual = sistema.getRandomUser();
            if (usuarioAtual != null) {
                nomeLabel.setText("Nome: " + usuarioAtual.getNome());
                idadeLabel.setText("Idade: " + usuarioAtual.getIdade());
                sexoLabel.setText("Sexo: " + usuarioAtual.getSexo());
                cidadeLabel.setText("Cidade: " + usuarioAtual.getCidade());
                prefMusicalLabel.setText("Preferência Musical: " + usuarioAtual.getPrefMusical());
                bebeLabel.setText("Bebe? " + (usuarioAtual.getBebe() ? "Sim" : "Não"));
                fumaLabel.setText("Fuma? " + (usuarioAtual.getFuma() ? "Sim" : "Não"));
                orientacaoLabel.setText("Orientação Sexual: " + usuarioAtual.getOrientacaoSexual());
                hobbiesLabel.setText("Hobbies: " + usuarioAtual.getHobbies());
                trabalhaLabel.setText("Trabalha? " + (usuarioAtual.getTrabalha() ? "Sim" : "Não"));
                faculdadeLabel.setText("Faculdade? " + (usuarioAtual.getFaculdade() ? "Sim" : "Não"));
                periodoLabel.setText("Período: " + usuarioAtual.getPeriodo());
                exercitaLabel.setText("Se exercita? " + (usuarioAtual.getExercita() ? "Sim" : "Não"));
                descricaoArea.setText(usuarioAtual.getDescricao());
            } else {
                nomeLabel.setText("Nenhum usuário disponível");
                idadeLabel.setText("");
                descricaoArea.setText("Todos os perfis disponíveis já receberam interação.");
                JOptionPane.showMessageDialog(this, "Não há mais usuários disponíveis!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        };

        dislikeButton.addActionListener(e -> {
            sistema.darDislike(usuarioAtual);
            proximoUsuario.actionPerformed(null);
        });

        likeButton.addActionListener(e -> {
            sistema.darLike(usuarioAtual);
            proximoUsuario.actionPerformed(null);
        });

        denunciarButton.addActionListener(e -> {
            sistema.denunciar(usuarioAtual);
            new TelaDenunciar(sistema, usuarioLogado, usuarioAtual).setVisible(true);
            proximoUsuario.actionPerformed(null);
        });

        JButton voltarButton = createStyledButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaUsuario(sistema).setVisible(true);
            dispose();
        });

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(200, 0, 0));
        topPanel.add(voltarButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(userInfoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);

        proximoUsuario.actionPerformed(null);
    }

    private JLabel createStyledLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
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