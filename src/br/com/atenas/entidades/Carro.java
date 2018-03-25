/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atenas.entidades;

import br.com.atenas.interfaces.Veiculo;
import java.io.Serializable;
import java.util.Objects;
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
@SequenceGenerator(name = "seq_carro", sequenceName = "seq_carro", initialValue = 1, allocationSize = 1, schema = "root")
@Indexes({
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_placa", columnNames = {"placa"})
})
@NamedQueries({
    @NamedQuery(name = "carros.findAll", query = "SELECT car FROM Carro AS car"),
    @NamedQuery(name = "carros.findById", query = "SELECT car FROM Carro AS car WHERE car.id = :paramId"),
    @NamedQuery(name = "carros.findByPlaca", query = "SELECT car FROM Carro AS car WHERE car.placa = :paramPlaca")
})
public class Carro extends Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carro")
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.tipo);
        hash = 59 * hash + Objects.hashCode(this.placa);
        hash = 59 * hash + Objects.hashCode(this.marca);
        hash = 59 * hash + Objects.hashCode(this.modelo);
        hash = 59 * hash + Objects.hashCode(this.cor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carro other = (Carro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.placa, other.placa)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.cor, other.cor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carro{" + "id=" + id + ", tipo=" + tipo + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", cor=" + cor + '}';
    }

    @Override
    public void clonar(Veiculo veiculo) {
        Carro car = (Carro) veiculo;
        this.id = car.getId();
        this.tipo = car.getTipo();
        this.placa = car.getPlaca();
        this.marca = car.getMarca();
        this.modelo = car.getModelo();
        this.cor = car.getCor();
    }

}
