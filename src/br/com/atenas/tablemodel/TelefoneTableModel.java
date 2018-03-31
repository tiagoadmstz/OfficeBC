/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.tablemodel;

import algoritimos.tabelas.TableModelCBIAdapter;
import br.com.atenas.entidades.Telefone;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tiago
 */
public class TelefoneTableModel extends TableModelCBIAdapter {

    private final String[] columnNames = new String[]{"Tipo", "Telefone", "Contato"};

    public TelefoneTableModel() {
        setColmunName(columnNames);
    }

    public TelefoneTableModel(List<?> lista) {
        super(lista);
        setColmunName(columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Telefone tel = (Telefone) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return tel.getTipo();
            case 1:
                return tel.getTelefone();
            case 2:
                return tel.getContato();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Telefone tel = (Telefone) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                tel.setTipo(Objects.equals(null, aValue) ? "" : aValue.toString());
                break;
            case 1:
                tel.setTelefone(Objects.equals(null, aValue) ? "" : aValue.toString());
                break;
            case 2:
                tel.setContato(Objects.equals(null, aValue) ? "" : aValue.toString());
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
