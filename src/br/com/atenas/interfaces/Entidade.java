/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.interfaces;

import br.com.atenas.entidades.Telefone;
import java.util.List;

/**
 *
 * @author Tiago
 */
public abstract class Entidade {

    protected Number id;
    protected String nome;
    protected List<Telefone> telefones;

    public abstract void clonar();
}
