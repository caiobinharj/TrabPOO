import javax.swing.*;
import java.util.*;
import java.awt.*;

public class TelaMatches extends JFrame {
    private Sistema sistema;

    public TelaMatches(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Seus Matches");
        setSize(300, 400);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ArrayList<Usuario> matches = sistema.getMatches();

        if (matches.isEmpty()) {
            mainPanel.add(new JLabel("Você ainda não tem matches"));
        } else {
            for (Usuario match : matches) {
                JPanel matchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                matchPanel.setBorder(BorderFactory.createEtchedBorder());

                JLabel nomeLabel = new JLabel(match.getNome());
                JButton conversarButton = new JButton("Conversar");

                conversarButton.addActionListener(e -> {
                    new TelaConversa(sistema, match).setVisible(true);
                });

                matchPanel.add(nomeLabel);
                matchPanel.add(conversarButton);
                mainPanel.add(matchPanel);
            }
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaUsuario(sistema).setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
