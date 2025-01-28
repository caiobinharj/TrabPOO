import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaProcurarMatch extends JFrame {
    private Sistema sistema;
    private Usuario usuarioAtual;

    public TelaProcurarMatch(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Procurar Match");
        setSize(400, 600);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel para exibir informações do usuário
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        JLabel fotoLabel = new JLabel();
        JLabel nomeLabel = new JLabel();
        JLabel idadeLabel = new JLabel();
        JTextArea descricaoArea = new JTextArea();
        descricaoArea.setEditable(false);

        userInfoPanel.add(fotoLabel);
        userInfoPanel.add(nomeLabel);
        userInfoPanel.add(idadeLabel);
        userInfoPanel.add(new JScrollPane(descricaoArea));

        // Botões de ação
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dislikeButton = new JButton("Dislike");
        JButton likeButton = new JButton("Like");
        JButton denunciarButton = new JButton("Denunciar");

        buttonPanel.add(dislikeButton);
        buttonPanel.add(likeButton);
        buttonPanel.add(denunciarButton);

        // Ações dos botões
        ActionListener proximoUsuario = e -> {
            usuarioAtual = sistema.getRandomUser();
            if (usuarioAtual != null) {
                nomeLabel.setText(usuarioAtual.getNome());
                idadeLabel.setText(String.valueOf(usuarioAtual.getIdade()));
                descricaoArea.setText(usuarioAtual.getDescricao());
                // Atualizar foto
            } else {
                JOptionPane.showMessageDialog(this, "Não há mais usuários disponíveis!");
            }
        };

        dislikeButton.addActionListener(proximoUsuario);

        likeButton.addActionListener(e -> {
            sistema.darLike(usuarioAtual);
            proximoUsuario.actionPerformed(null);
        });

        denunciarButton.addActionListener(e -> {
            sistema.denunciar(usuarioAtual);
            proximoUsuario.actionPerformed(null);
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaUsuario(sistema).setVisible(true);
            dispose();
        });

        mainPanel.add(userInfoPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(voltarButton, BorderLayout.NORTH);

        add(mainPanel);
        setLocationRelativeTo(null);

        // Carregar primeiro usuário
        proximoUsuario.actionPerformed(null);
    }
}