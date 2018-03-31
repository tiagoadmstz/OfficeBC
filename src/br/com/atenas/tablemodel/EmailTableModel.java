/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.tablemodel;

import algoritimos.tabelas.TableModelCBIAdapter;
import br.com.atenas.entidades.Email;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tiago
 */
public class EmailTableModel extends TableModelCBIAdapter {

    private final String[] columnNames = new String[]{"E-Mail", "Contato"};

    public EmailTableModel() {
        setColmunName(columnNames);
    }

    public EmailTableModel(List<?> lista) {
        super(lista);
        setColmunName(columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Email email = (Email) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return email.getEmail();
            case 1:
                return email.getContato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Email em = (Email) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                em.setEmail(Objects.equals("", aValue) ? null : aValue.toString());
                break;
            case 1:
                em.setContato(Objects.equals("", aValue) ? null : aValue.toString());
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
