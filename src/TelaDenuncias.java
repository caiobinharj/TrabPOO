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

        List<Denuncia> denuncias = sistema.getDenuncias();

        if (denuncias == null || denuncias.isEmpty()) {
            mainPanel.add(new JLabel("Não há denúncias pendentes."));
        } else {
            for (Denuncia denuncia : denuncias) {
                JPanel denunciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                denunciaPanel.setBorder(BorderFactory.createEtchedBorder());

                JLabel usuarioDenunciadoLabel = new JLabel("Usuário: " + denuncia.getUsuarioDenunciado().getNome());
                JTextArea motivoArea = new JTextArea(3, 25);
                motivoArea.setText(denuncia.getMotivo());
                motivoArea.setEditable(false);

                JButton revisarButton = new JButton("Revisar");
                JButton excluirButton = new JButton("Excluir Denúncia");
                JButton bloquearButton = new JButton("Bloquear Usuário");

                revisarButton.addActionListener(e -> {
                    new TelaPerfil(sistema, denuncia.getUsuarioDenunciado()).setVisible(true);
                });

                excluirButton.addActionListener(e -> {
                    sistema.removerDenuncia(denuncia);
                    JOptionPane.showMessageDialog(this, "Denúncia excluída com sucesso!");
                    dispose();
                    new TelaDenuncias(sistema).setVisible(true);
                });

                denunciaPanel.add(usuarioDenunciadoLabel);
                denunciaPanel.add(new JScrollPane(motivoArea));
                denunciaPanel.add(revisarButton);
                denunciaPanel.add(excluirButton);
                denunciaPanel.add(bloquearButton);

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
