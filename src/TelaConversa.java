import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

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

        // Área de mensagens
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Campo de entrada de mensagem
        JPanel messagePanel = new JPanel(new BorderLayout(5, 0));
        JTextField messageField = new JTextField();
        JButton sendButton = new JButton("Enviar");

        messagePanel.add(messageField, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.EAST);

        // Carregar mensagens anteriores
        ArrayList<String[]> mensagens = sistema.getConversas(outroUsuario);
        for (String[] msg : mensagens) {
            chatArea.append(msg[0] + ": " + msg[1] + "\n");
        }

        // Ação de enviar mensagem
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
        messageField.addActionListener(enviarMensagem); // Permite enviar com Enter

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }
}