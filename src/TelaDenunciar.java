import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

public class TelaDenunciar extends JFrame {
    private Sistema sistema;
    private Usuario usuarioAtual;
    private Usuario usuarioDenunciado;

    public TelaDenunciar(Sistema sistema, Usuario usuarioAtual, Usuario usuarioDenunciado) {
        this.sistema = sistema;
        this.usuarioAtual = usuarioAtual;
        this.usuarioDenunciado = usuarioDenunciado;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Denunciar Usuário");
        setSize(400, 250);
        getContentPane().setBackground(new Color(200, 0, 0));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 0, 0));

        mainPanel.add(new JLabel("Denunciando o usuário: " + usuarioDenunciado.getNome()));
        mainPanel.add(new JLabel("Motivo da denúncia:"));

        JTextArea motivoArea = new JTextArea(5, 30);
        motivoArea.setWrapStyleWord(true);
        motivoArea.setLineWrap(true);
        mainPanel.add(new JScrollPane(motivoArea));

        JButton enviarButton = createStyledButton("Enviar Denúncia");
        JButton cancelarButton = createStyledButton("Cancelar");

        enviarButton.addActionListener(e -> {
            String motivo = motivoArea.getText().trim();

            if (motivo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, descreva o motivo da denúncia!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Denuncia denuncia = new Denuncia(usuarioAtual, usuarioDenunciado, motivo);
            sistema.registrarDenuncia(usuarioAtual.getNome(), usuarioDenunciado.getNome(), motivo);
            JOptionPane.showMessageDialog(this, "Denúncia enviada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            dispose();
        });

        cancelarButton.addActionListener(e -> {
            new TelaProcurarMatch(sistema).setVisible(true);
            dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enviarButton);
        buttonPanel.add(cancelarButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(180, 0, 0));
        button.setBorder(new LineBorder(Color.WHITE, 2, true));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 40));

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
