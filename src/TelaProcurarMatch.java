import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TelaProcurarMatch extends JFrame {
    private Sistema sistema;
    private Usuario usuarioAtual;
    private Usuario usuarioLogado;

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

        JLabel nomeLabel = new JLabel();
        JLabel idadeLabel = new JLabel();
        JLabel sexoLabel = new JLabel();
        JLabel cidadeLabel = new JLabel();
        JLabel prefMusicalLabel = new JLabel();
        JLabel bebeLabel = new JLabel();
        JLabel fumaLabel = new JLabel();
        JLabel orientacaoLabel = new JLabel();
        JLabel hobbiesLabel = new JLabel();
        JLabel trabalhaLabel = new JLabel();
        JLabel faculdadeLabel = new JLabel();
        JLabel periodoLabel = new JLabel();
        JLabel exercitaLabel = new JLabel();
        JTextArea descricaoArea = new JTextArea(4, 20);
        descricaoArea.setEditable(false);

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
        userInfoPanel.add(new JLabel("Descrição:"));
        userInfoPanel.add(new JScrollPane(descricaoArea));

        // Botões de ação
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton dislikeButton = new JButton("Dislike");
        JButton likeButton = new JButton("Like");
        JButton denunciarButton = new JButton("Denunciar");

        buttonPanel.add(dislikeButton);
        buttonPanel.add(likeButton);
        buttonPanel.add(denunciarButton);

        // Ação de mostrar o próximo usuário
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


        dislikeButton.addActionListener(proximoUsuario);

        likeButton.addActionListener(e -> {
            sistema.darLike(usuarioAtual);
            proximoUsuario.actionPerformed(null);
        });

        denunciarButton.addActionListener(e -> {
            sistema.denunciar(usuarioAtual);
            new TelaDenunciar(sistema, usuarioLogado, usuarioAtual).setVisible(true);
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