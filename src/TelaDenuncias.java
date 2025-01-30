import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaDenuncias extends JFrame {
    private Sistema sistema;

    public TelaDenuncias(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Denúncias");
        setSize(400, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List<Denuncia> denuncias = sistema.carregarDenuncias();

        if (denuncias.isEmpty()) {
            mainPanel.add(new JLabel("Não há denúncias pendentes."));
        } else {
            for (Denuncia denuncia : denuncias) {
                JPanel denunciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                denunciaPanel.setBorder(BorderFactory.createEtchedBorder());

                JLabel usuarioDenunciadoLabel = new JLabel("Denunciado: " + denuncia.getUsuarioDenunciado().getNome());
                JTextArea motivoArea = new JTextArea(3, 25);
                motivoArea.setText(denuncia.getMotivo());
                motivoArea.setEditable(false);

                JButton excluirButton = new JButton("Excluir Denúncia");
                excluirButton.addActionListener(e -> {
                    sistema.removerDenuncia(denuncia);
                    JOptionPane.showMessageDialog(this, "Denúncia removida!");
                    dispose();
                    new TelaDenuncias(sistema).setVisible(true);
                });

                denunciaPanel.add(usuarioDenunciadoLabel);
                denunciaPanel.add(new JScrollPane(motivoArea));
                denunciaPanel.add(excluirButton);

                mainPanel.add(denunciaPanel);
            }
        }

        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaModerador(sistema).setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltarButton);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }
}
