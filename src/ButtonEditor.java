import javax.swing.*;
import javax.swing.table.DefaultTableModel;
class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private Moderador moderador;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        moderador = new Moderador();
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                          boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JTable table = (JTable) button.getParent();
            String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
            int confirm = JOptionPane.showConfirmDialog(button,
                    "Deseja realmente remover o usuário " + nome + "?");
            if (confirm == JOptionPane.YES_OPTION) {
                moderador.removerUsuario(nome);
                // Atualizar a tabela
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
            }
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}