/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.interfaces;

/**
 *
 * @author Tiago
 */
public abstract class Veiculo {

    protected Number id;
    protected String tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String cor;

    public abstract void clonar(Veiculo veiculo);

}
