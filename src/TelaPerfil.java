import javax.swing.*;
import java.util.*;
import java.awt.*;

public class TelaPerfil extends JFrame{
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel loginLabel = new JLabel("Login: " + usuario.getLogin());
        JLabel nomeLabel = new JLabel("Nome: " + usuario.getNome());
        JLabel idadeLabel = new JLabel("Idade: " + usuario.getIdade());
        JLabel sexoLabel = new JLabel("Sexo: " + usuario.getSexo());
        JLabel cidadeLabel = new JLabel("Cidade: " + usuario.getCidade());
        JLabel prefMusicalLabel = new JLabel("Preferência Musical: " + usuario.getPrefMusical());
        JLabel bebeLabel = new JLabel("Bebe? " + (usuario.getBebe() ? "Sim" : "Não"));
        JLabel fumaLabel = new JLabel("Fuma? " + (usuario.getFuma() ? "Sim" : "Não"));
        JLabel orientacaoLabel = new JLabel("Orientação Sexual: " + usuario.getOrientacaoSexual());
        JLabel hobbiesLabel = new JLabel("Hobbies: " + usuario.getHobbies());
        JLabel trabalhaLabel = new JLabel("Trabalha? " + (usuario.getTrabalha() ? "Sim" : "Não"));
        JLabel faculdadeLabel = new JLabel("Faculdade? " + (usuario.getFaculdade() ? "Sim" : "Não"));
        JLabel periodoLabel = new JLabel("Período: " + usuario.getPeriodo());
        JLabel exercitaLabel = new JLabel("Se exercita? " + (usuario.getExercita() ? "Sim" : "Não"));
        JLabel descricaoLabel = new JLabel("Descrição: ");
        JTextArea descricaoArea = new JTextArea(usuario.getDescricao(), 4, 20);
        descricaoArea.setEditable(false);

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
        mainPanel.add(new JScrollPane(descricaoArea));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
