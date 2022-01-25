package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.*;
import Vista.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener{
    
    UsuarioDAO udao=new UsuarioDAO();
    UsuarioEntidad ue=new UsuarioEntidad();
    ManageUsuarios manageusuarios=new ManageUsuarios();
    DefaultTableModel modelo=new DefaultTableModel();

    public Controlador(ManageUsuarios u) {
        this.manageusuarios=u;
        this.manageusuarios.btnListar.addActionListener(this);
        this.manageusuarios.jButton_editar.addActionListener(this);
        this.manageusuarios.jButton_actualizar.addActionListener(this);
        this.manageusuarios.jButton_registrarusuario.addActionListener(this);
        this.manageusuarios.jButton_nuevo.addActionListener(this);
        this.manageusuarios.jButton_eliminarusuario.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageusuarios.btnListar) {
            limpiarTabla();
            listar(manageusuarios.jTable_data);
            nuevo();
        }
        if (e.getSource() == manageusuarios.jButton_registrarusuario) {
            add();
            listar(manageusuarios.jTable_data);
            nuevo();
        }
        if (e.getSource() == manageusuarios.jButton_editar) {
            int fila = manageusuarios.jTable_data.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(manageusuarios, "Debes seleccionar una fila");
            }else{
                int id = Integer.parseInt((String) manageusuarios.jTable_data.getValueAt(fila, 0).toString());
                String nombre = (String) manageusuarios.jTable_data.getValueAt(fila, 1);
                String clave = (String) manageusuarios.jTable_data.getValueAt(fila, 2);
                String tipo = (String) manageusuarios.jTable_data.getValueAt(fila, 3);
                manageusuarios.jTextField_id.setText("" + id);
                manageusuarios.jTextField_nombre.setText(nombre);
                manageusuarios.jTextField_clave.setText(clave);
                manageusuarios.jTextField_tipo.setText(tipo);
            }
        }
        if (e.getSource() == manageusuarios.jButton_actualizar) {
            actualizar();
            listar(manageusuarios.jTable_data);
            nuevo();
        }
        if (e.getSource() == manageusuarios.jButton_nuevo) {
            nuevo();
        }
        if (e.getSource() == manageusuarios.jButton_eliminarusuario) {
            delete();
            listar(manageusuarios.jTable_data);
            nuevo();
        }
    }
    
    public void listar(JTable jTable_data) {
        modelo=(DefaultTableModel) jTable_data.getModel();

        jTable_data.setModel(modelo);
        List<UsuarioEntidad>lista=udao.listar();
        Object[]objeto=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0]=lista.get(i).getId();
            objeto[1]=lista.get(i).getNombre();
            objeto[2]=lista.get(i).getClave();
            objeto[3]=lista.get(i).getTipo();
            modelo.addRow(objeto);
        }
        
        jTable_data.setRowHeight(35);
        jTable_data.setRowMargin(10);
    }
    
    void nuevo() {
        manageusuarios.jTextField_id.setText("");
        manageusuarios.jTextField_nombre.setText("");
        manageusuarios.jTextField_clave.setText("");
        manageusuarios.jTextField_tipo.setText("");
        manageusuarios.jTextField_nombre.requestFocus();
    }
    
    public void add() {
        String nombre = manageusuarios.jTextField_nombre.getText();
        String clave = manageusuarios.jTextField_clave.getText();
        String tipo = manageusuarios.jComboBox_usuarioTipo.getSelectedItem().toString();
        ue.setNombre(nombre);
        ue.setClave(clave);
        ue.setTipo(tipo);
        int r = udao.insertar(ue);
        if (r == 1) {
            JOptionPane.showMessageDialog(manageusuarios, "Usuario Agregado con Exito.");
        } 
        limpiarTabla();
    }
    
    public void actualizar() {
        if (manageusuarios.jTextField_id.getText().equals("")) {
            JOptionPane.showMessageDialog(manageusuarios, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(manageusuarios.jTextField_id.getText());
            String nombre = manageusuarios.jTextField_nombre.getText();
            String clave = manageusuarios.jTextField_clave.getText();
            String tipo = manageusuarios.jComboBox_usuarioTipo.getSelectedItem().toString();
            ue.setId(id);
            ue.setNombre(nombre);
            ue.setClave(clave);
            ue.setTipo(tipo);
            int r = udao.actualizar(ue);
            if (r == 1) {
                JOptionPane.showMessageDialog(manageusuarios, "Usuario Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(manageusuarios, "Error");
            }
        }
        limpiarTabla();
    }
    
    public void delete() {
        int fila = manageusuarios.jTable_data.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(manageusuarios, "Debe Seleccionar una Fila");
        } else {
            int id = Integer.parseInt((String) manageusuarios.jTable_data.getValueAt(fila, 0).toString());
            udao.Delete(id);
            System.out.println("El Reusltaod es" + id);
            JOptionPane.showMessageDialog(manageusuarios, "Usuario Eliminado");
        }
        limpiarTabla();
    }
    
    
    
    void limpiarTabla() {
        for (int i = 0; i < manageusuarios.jTable_data.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
