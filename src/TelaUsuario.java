import javax.swing.*;

public class TelaUsuario extends JFrame {
    private Sistema sistema;

    public TelaUsuario(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Área do Usuário");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton procurarMatchButton = new JButton("Procurar Match");
        JButton editarPerfilButton = new JButton("Editar Perfil");
        JButton matchesButton = new JButton("Matches");
        JButton logoutButton = new JButton("Logout");

        mainPanel.add(procurarMatchButton);
        mainPanel.add(editarPerfilButton);
        mainPanel.add(matchesButton);
        mainPanel.add(logoutButton);

        procurarMatchButton.addActionListener(e -> {
            new TelaProcurarMatch(sistema).setVisible(true);
            dispose();
        });

        editarPerfilButton.addActionListener(e -> {
            new TelaEditarPerfil(sistema).setVisible(true);
            dispose();
        });

        matchesButton.addActionListener(e -> {
            new TelaMatches(sistema).setVisible(true);
            dispose();
        });

        logoutButton.addActionListener(e -> {
            sistema.logout();
            new TelaInicial().setVisible(true);
            dispose();
        });

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
}