/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.tablemodel;

import algoritimos.tabelas.TableModelCBIAdapter;
import br.com.atenas.entidades.Item;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tiago
 */
public class ItemTableModel extends TableModelCBIAdapter {

    private final String[] columnName = new String[]{"Descrição", "Quant.", "UND", "Valor"};

    public ItemTableModel() {
        setColmunName(columnName);
    }

    public ItemTableModel(List<?> lista) {
        super(lista);
        setColmunName(columnName);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = (Item) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getDescricao();
            case 1:
                return item.getQuantidade();
            case 2:
                return item.getUnidade();
            case 3:
                return item.getValor();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Item item = (Item) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                item.setDescricao(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 1:
                item.setQuantidade(Objects.equals(null, aValue) ? null : Double.parseDouble(aValue.toString()));
                break;
            case 2:
                item.setUnidade(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 3:
                item.setValor(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
