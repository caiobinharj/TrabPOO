import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaConversa extends JFrame {
    private Sistema sistema;
    private Usuario outroUsuario;

    public TelaConversa(Sistema sistema, Usuario outroUsuario) {
        this.sistema = sistema;
        this.outroUsuario = outroUsuario;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Conversa com " + outroUsuario.getNome());
        setSize(400, 600);

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.RED);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(new Color(255, 230, 230));
        chatArea.setForeground(Color.BLACK);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel messagePanel = new JPanel(new BorderLayout(5, 0));
        messagePanel.setBackground(Color.RED);

        JTextField messageField = new JTextField();
        messageField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton sendButton = new JButton("Enviar");
        sendButton.setBackground(Color.WHITE);
        sendButton.setForeground(Color.BLACK);
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));

        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        ArrayList<String[]> mensagens = sistema.getConversas(outroUsuario);
        for (String[] msg : mensagens) {
            chatArea.append(msg[0] + ": " + msg[1] + "\n");
        }

        ActionListener enviarMensagem = e -> {
            String mensagem = messageField.getText().trim();
            if (!mensagem.isEmpty()) {
                sistema.salvarConversa(sistema.getUsuarioLogado().getLogin(),
                        outroUsuario.getLogin(),
                        mensagem);
                chatArea.append(sistema.getUsuarioLogado().getNome() + ": " + mensagem + "\n");
                messageField.setText("");
            }
        };

        sendButton.addActionListener(enviarMensagem);
        messageField.addActionListener(enviarMensagem);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }
}
