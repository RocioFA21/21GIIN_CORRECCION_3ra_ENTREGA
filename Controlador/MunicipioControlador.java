package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MunicipioControlador implements ActionListener{
    
    MunicipioDAO mdao=new MunicipioDAO();
    MunicipioEntidad me=new MunicipioEntidad();
    ManageMunicipios managemunicipios=new ManageMunicipios();
    DefaultTableModel modelo=new DefaultTableModel();

    public MunicipioControlador(ManageMunicipios m) {
        this.managemunicipios=m;
        this.managemunicipios.btnListar.addActionListener(this);
        this.managemunicipios.jButton_editar.addActionListener(this);
        this.managemunicipios.jButton_actualizar.addActionListener(this);
        this.managemunicipios.jButton_registrarusuario.addActionListener(this);
        this.managemunicipios.jButton_nuevo.addActionListener(this);
        this.managemunicipios.jButton_eliminarusuario.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == managemunicipios.btnListar) {
            limpiarTabla();
            listar(managemunicipios.jTable_data);
            nuevo();
        }
        if (e.getSource() == managemunicipios.jButton_registrarusuario) {
            add();
            listar(managemunicipios.jTable_data);
            nuevo();
        }
        if (e.getSource() == managemunicipios.jButton_editar) {
            int fila = managemunicipios.jTable_data.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(managemunicipios, "Debes seleccionar una fila");
            }else{
                int id = Integer.parseInt((String) managemunicipios.jTable_data.getValueAt(fila, 0).toString());
                String nombre = (String) managemunicipios.jTable_data.getValueAt(fila, 1);
                String categoria = (String) managemunicipios.jTable_data.getValueAt(fila, 2);
                managemunicipios.jTextField_id.setText("" + id);
                managemunicipios.jTextField_nombre.setText(nombre);
                managemunicipios.jTextField_Categoria.setText(categoria);
            }
        }
        if (e.getSource() == managemunicipios.jButton_actualizar) {
            actualizar();
            listar(managemunicipios.jTable_data);
            nuevo();
        }
        if (e.getSource() == managemunicipios.jButton_nuevo) {
            nuevo();
        }
        if (e.getSource() == managemunicipios.jButton_eliminarusuario) {
            delete();
            listar(managemunicipios.jTable_data);
            nuevo();
        }
    }
    
    public void listar(JTable jTable_data) {
        modelo=(DefaultTableModel) jTable_data.getModel();

        jTable_data.setModel(modelo);
        List<MunicipioEntidad>lista=mdao.listar();
        Object[]objeto=new Object[3];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0]=lista.get(i).getId();
            objeto[1]=lista.get(i).getNombre();
            objeto[2]=lista.get(i).getCategoria();
            modelo.addRow(objeto);
        }
        
        jTable_data.setRowHeight(35);
        jTable_data.setRowMargin(10);
    }
    
    void nuevo() {
        managemunicipios.jTextField_id.setText("");
        managemunicipios.jTextField_nombre.setText("");
        managemunicipios.jTextField_Categoria.setText("");
        managemunicipios.jTextField_nombre.requestFocus();
    }
    
    public void add() {
        String nombre = managemunicipios.jTextField_nombre.getText();
        String categoria = managemunicipios.jComboBox_Categoria.getSelectedItem().toString();
        me.setNombre(nombre);
        me.setCategoria(categoria);
        int r = mdao.insertar(me);
        if (r == 1) {
            JOptionPane.showMessageDialog(managemunicipios, "Municipio Agregado con Exito.");
        } 
        limpiarTabla();
    }
    
    public void actualizar() {
        if (managemunicipios.jTextField_id.getText().equals("")) {
            JOptionPane.showMessageDialog(managemunicipios, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(managemunicipios.jTextField_id.getText());
            String nombre = managemunicipios.jTextField_nombre.getText();
            String categoria = managemunicipios.jComboBox_Categoria.getSelectedItem().toString();
            me.setId(id);
            me.setNombre(nombre);
            me.setCategoria(categoria);
            int r = mdao.actualizar(me);
            if (r == 1) {
                JOptionPane.showMessageDialog(managemunicipios, "Municipio Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(managemunicipios, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void delete() {
        int fila = managemunicipios.jTable_data.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(managemunicipios, "Debe Seleccionar una Fila");
        } else {
            int id = Integer.parseInt((String) managemunicipios.jTable_data.getValueAt(fila, 0).toString());
            mdao.Delete(id);
            System.out.println("El Reusltado es" + id);
            JOptionPane.showMessageDialog(managemunicipios, "Municipio Eliminado");
        }
        limpiarTabla();
    }
    
    void limpiarTabla() {
        for (int i = 0; i < managemunicipios.jTable_data.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
