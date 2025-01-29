import javax.swing.*;
import javax.swing.border.LineBorder;
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
        setSize(350, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 0, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ArrayList<Usuario> matches = sistema.getMatches();

        if (matches.isEmpty()) {
            JLabel noMatchLabel = new JLabel("Você ainda não tem matches", SwingConstants.CENTER);
            noMatchLabel.setForeground(Color.WHITE);
            noMatchLabel.setFont(new Font("Arial", Font.BOLD, 16));
            mainPanel.add(noMatchLabel);
        } else {
            for (Usuario match : matches) {
                JPanel matchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                matchPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
                matchPanel.setBackground(new Color(255, 100, 100));
                matchPanel.setPreferredSize(new Dimension(280, 60));

                JLabel nomeLabel = new JLabel(match.getNome());
                nomeLabel.setForeground(Color.WHITE);
                nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));

                JButton perfilButton = createStyledButton("Perfil");
                JButton conversarButton = createStyledButton("Conversar");

                conversarButton.addActionListener(e -> new TelaConversa(sistema, match).setVisible(true));
                perfilButton.addActionListener(e -> new TelaPerfil(sistema, match).setVisible(true));

                matchPanel.add(nomeLabel);
                matchPanel.add(conversarButton);
                matchPanel.add(perfilButton);
                mainPanel.add(Box.createVerticalStrut(5));
                mainPanel.add(matchPanel);
            }
        }

        JButton voltarButton = createStyledButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaUsuario(sistema).setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 0, 0));
        buttonPanel.add(voltarButton);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30));

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
