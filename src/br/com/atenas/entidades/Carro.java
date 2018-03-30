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
@Table(name = "carros")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_car", sequenceName = "car_seq", initialValue = 1, allocationSize = 1, schema = "root")
@Indexes({
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_placa", columnNames = {"placa"})
})
@NamedQueries({
    @NamedQuery(name = "carros.findAll", query = "SELECT car FROM Carro AS car"),
    @NamedQuery(name = "carros.findById", query = "SELECT car FROM Carro AS car WHERE car.id = :paramId"),
    @NamedQuery(name = "carros.findByPlaca", query = "SELECT car FROM Carro AS car WHERE car.placa = :paramPlaca")
})
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Number id;
    private String tipo;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    
    public Carro() {
    }

    public Carro(Number id, String tipo, String placa, String marca, String modelo, String cor) {
        this.id = id;
        this.tipo = tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    @Column(name = "tipo", length = 100)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = "placa", length = 10)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Column(name = "marca", length = 150)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "modelo", length = 150)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "cor", length = 100)
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void clonar(Carro carro) {
        this.id = carro.getId();
        this.tipo = carro.getTipo();
        this.placa = carro.getPlaca();
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.cor = carro.getCor();
    }

}
