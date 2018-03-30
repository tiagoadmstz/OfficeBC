/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.entidades;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "Telefones")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_tel", sequenceName = "tel_seq", allocationSize = 1, initialValue = 1)
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_tipo", columnNames = {"tipo", "telefone"})
})
@NamedQueries(value = {
    @NamedQuery(name = "telefone.findAll", query = "SELECT tel FROM Telefone AS tel"),
    @NamedQuery(name = "telefone.findById", query = "SELECT tel FROM Telefone AS tel WHERE tel.id = :paramId"),
    @NamedQuery(name = "telefone.findByTipo", query = "SELECT tel FROM Telefone AS tel WHERE tel.tipo = :paramTipo"),
    @NamedQuery(name = "telefone.findByTelefone", query = "SELECT tel FROM Telefone AS tel WHERE tel.telefone = :paramTelefone")
})
public class Telefone implements Serializable {

    private Number id;
    private String tipo;
    private String telefone;
    private String contato;

    public Telefone() {
    }

    public Telefone(Number id, String tipo, String telefone, String contato) {
        this.id = id;
        this.tipo = tipo;
        this.telefone = telefone;
        this.contato = contato;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tel_seq")
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    @Column(name = "tipo", length = 50)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = "telefone", length = 20)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "contato", length = 150)
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void clonar(Telefone telefone) {
        this.id = telefone.getId();
        this.tipo = telefone.getTipo();
        this.telefone = telefone.getTelefone();
        this.contato = telefone.getContato();
    }
}
