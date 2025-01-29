import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TelaListarUsuarios extends JFrame {
    private Sistema sistema;
    private JTable tabelaUsuarios;
    private DefaultTableModel modelo;

    public TelaListarUsuarios(Sistema sistema) {
        this.sistema = sistema;
        initComponents();
    }

    private void initComponents() {
        setTitle("Lista de Usuários");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] colunas = {"Nome", "Cidade", "Editar"};
        modelo = new DefaultTableModel(colunas, 0);
        tabelaUsuarios = new JTable(modelo);

        // Adicionar dados dos usuários
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) sistema.listarUsuarios();
        for (Usuario usuario : usuarios) {
            modelo.addRow(new Object[]{usuario.getNome(), usuario.getCidade(), "Editar"});
        }

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            new TelaModerador(sistema).setVisible(true);
            dispose();
        });

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(voltarButton);
        add(panelBotoes, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }
}
