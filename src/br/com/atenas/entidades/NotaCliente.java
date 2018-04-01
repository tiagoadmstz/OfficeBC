/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.entidades;

import br.com.atenas.converter.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Mov_Notas")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_nota", sequenceName = "nota_seq", allocationSize = 1, initialValue = 1)
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_data", columnNames = {"dataEmissao"}),
    @Index(name = "idx_cliente", columnNames = {"cliente"})
})
@NamedQueries(value = {
    @NamedQuery(name = "nota.findAll", query = "SELECT nota FROM NotaCliente AS nota"),
    @NamedQuery(name = "nota.findById", query = "SELECT nota FROM NotaCliente AS nota WHERE nota.id = :paramId"),
    @NamedQuery(name = "nota.findByDataEmissao", query = "SELECT nota FROM NotaCliente AS nota WHERE nota.dataEmissao = :paramData"),
    @NamedQuery(name = "nota.findByCliente", query = "SELECT nota FROM NotaCliente AS nota WHERE nota.cliente = :paramCliente")
})
public class NotaCliente implements Serializable {

    private Long id;
    private LocalDate dataEmissao;
    private Entidade cliente;
    private List<Item> itens;

    public NotaCliente() {
    }

    public NotaCliente(Long id, LocalDate dataEmissao, Entidade cliente, List<Item> itens) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.cliente = cliente;
        this.itens = itens;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Convert(attributeName = "dataEmissao", converter = LocalDateConverter.class)
    @Column(name = "dataEmissao", columnDefinition = "varchar", length = 10)
    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, targetEntity = Entidade.class)
    public Entidade getCliente() {
        return cliente;
    }

    public void setCliente(Entidade cliente) {
        this.cliente = cliente;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Item.class)
    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void clonar(NotaCliente nota) {
        this.id = nota.getId();
        this.dataEmissao = nota.getDataEmissao();
        this.cliente = nota.getCliente();
        this.itens = nota.getItens();
    }

    public void clear() {
        this.id = null;
        this.dataEmissao = null;
        this.cliente = null;
        this.itens = null;
    }

}
