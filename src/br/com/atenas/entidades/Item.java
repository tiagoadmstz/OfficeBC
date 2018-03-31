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
@Table(name = "Mov_Itens")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_item", sequenceName = "item_seq", allocationSize = 1, initialValue = 1)
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_descricao", columnNames = {"descricao"})
})
@NamedQueries(value = {
    @NamedQuery(name = "item.findAll", query = "SELECT it FROM Item AS it"),
    @NamedQuery(name = "item.findById", query = "SELECT it FROM Item AS it WHERE it.id = :paramId"),
    @NamedQuery(name = "item.findByDescricao", query = "SELECT it FROM Item AS it WHERE it.descricao = :paramDescricao")
})
public class Item implements Serializable {

    private Long id;
    private String descricao;
    private Double quantidade;
    private String unidade;
    private String valor;

    public Item() {
    }

    public Item(Long id, String descricao, Double quantidade, String unidade, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.valor = valor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "descricao", length = 255)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "quantidade")
    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    @Column(name = "unidade", length = 2)
    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    @Column(name = "valor", length = 100)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void clonar(Item item) {
        this.id = item.getId();
        this.descricao = item.getDescricao();
        this.quantidade = item.getQuantidade();
        this.unidade = item.getUnidade();
        this.valor = item.getValor();
    }

}
