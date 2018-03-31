/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "Entidades")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_ent", sequenceName = "ent_seq", allocationSize = 1, initialValue = 1)
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_nome", columnNames = {"nome"}),
    @Index(name = "idx_documento", columnNames = {"documento"})
})
@NamedQueries(value = {
    @NamedQuery(name = "entidade.findAll", query = "SELECT ent FROM Entidade AS ent"),
    @NamedQuery(name = "entidade.findById", query = "SELECT ent FROM Entidade AS ent WHERE ent.id = :paramId"),
    @NamedQuery(name = "entidade.findByNome", query = "SELECT ent FROM Entidade AS ent WHERE ent.nome = :paramNome"),
    @NamedQuery(name = "entidade.findByDocumento", query = "SELECT ent FROM Entidade AS ent WHERE ent.documento = :paramDocumento")
})
public class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String documento;
    private List<Telefone> telefones;
    private List<Email> email;
    private List<Carro> carros;

    public Entidade() {
    }

    public Entidade(Long id, String nome, String documento, List<Telefone> telefones, List<Email> email, List<Carro> carros) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.telefones = telefones;
        this.email = email;
        this.carros = carros;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ent_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", length = 255)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "documento", length = 50)
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Telefone.class)
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Email.class)
    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Carro.class)
    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public void clonar(Entidade entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.documento = entidade.getDocumento();
        this.telefones = entidade.getTelefones();
        this.email = entidade.getEmail();
        this.carros = entidade.getCarros();
    }

    public void clear(){
        this.id = null;
        this.nome = null;
        this.documento = null;
        this.telefones = null;
        this.email = null;
        this.carros = null;
    }
    
}
