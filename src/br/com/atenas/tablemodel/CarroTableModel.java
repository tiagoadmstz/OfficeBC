/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.tablemodel;

import algoritimos.tabelas.TableModelCBIAdapter;
import br.com.atenas.entidades.Carro;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Tiago
 */
public class CarroTableModel extends TableModelCBIAdapter {

    private final String[] columnNames = new String[]{"Tipo", "Placa", "Marca", "Modelo", "Cor"};

    public CarroTableModel() {
        setColmunName(columnNames);
    }

    public CarroTableModel(List<?> lista) {
        super(lista);
        setColmunName(columnNames);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Carro carro = (Carro) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return carro.getTipo();
            case 1:
                return carro.getPlaca();
            case 2:
                return carro.getMarca();
            case 3:
                return carro.getModelo();
            case 4:
                return carro.getCor();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Carro carro = (Carro) lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                carro.setTipo(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 1:
                carro.setPlaca(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 2:
                carro.setMarca(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 3:
                carro.setModelo(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
            case 4:
                carro.setCor(Objects.equals(null, aValue) ? null : aValue.toString());
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
