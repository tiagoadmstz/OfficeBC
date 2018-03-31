/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.tablemodel;

import algoritimos.tabelas.TableModelCBIAdapter;
import br.com.atenas.entidades.Entidade;
import java.util.List;

/**
 *
 * @author Tiago
 */
public class ClientePesquisaTableModel extends TableModelCBIAdapter {

    private String[] columnNames = new String[]{"Id", "Nome", "Documento"};

    public ClientePesquisaTableModel() {
        setColmunName(columnNames);
    }

    public ClientePesquisaTableModel(List<?> lista) {
        super(lista);
        setColmunName(columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entidade entidade = (Entidade) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entidade.getId();
            case 1:
                return entidade.getNome();
            case 2:
                return entidade.getDocumento();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Entidade ent = (Entidade) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ent.setId(Long.parseLong(aValue.toString()));
                break;
            case 1:
                ent.setNome(aValue.toString());
                break;
            case 2:
                ent.setDocumento(aValue.toString());
                break;
        }
    }

}
