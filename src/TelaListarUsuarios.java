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

        // Mudança nas colunas para mostrar denúncias ao invés de cidade
        String[] colunas = {"Nome", "Denúncias", "Ação"};
        modelo = new DefaultTableModel(colunas, 0);
        tabelaUsuarios = new JTable(modelo);

        // Adicionar dados dos usuários
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) sistema.listarUsuarios();
        for (Usuario usuario : usuarios) {
            modelo.addRow(new Object[]{
                    usuario.getNome(),
                    usuario.getDenuncias(),
                    "Remover"  // Mudança de "Editar" para "Remover"
            });
        }

        // Configurar o renderer e editor para a coluna do botão
        tabelaUsuarios.getColumn("Ação").setCellRenderer(new ButtonRenderer());
        tabelaUsuarios.getColumn("Ação").setCellEditor(new ButtonEditor(new JCheckBox()));

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