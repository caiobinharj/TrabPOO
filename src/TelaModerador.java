import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TelaModerador extends JFrame {
    private Moderador moderador;
    private DefaultTableModel model;
    private JTable table;

    public TelaModerador() {
        this.moderador = new Moderador();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Painel do Moderador");
        setSize(400, 500);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Criar o modelo da tabela
        String[] colunas = {"Nome", "Denúncias", "Ação"};
        model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2; // Apenas a coluna de ação é editável
            }
        };

        // Criar a tabela com o modelo
        table = new JTable(model);

        // Configurar o renderer e editor para a coluna de ação
        TableColumn column = table.getColumnModel().getColumn(2);
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(new JCheckBox()));

        // Preencher tabela com dados
        ArrayList<String[]> denuncias = moderador.consultarDenuncias();
        for (String[] denuncia : denuncias) {
            model.addRow(new Object[]{denuncia[0], denuncia[1], "Remover"});
        }

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
        setLocationRelativeTo(null);
    }
}