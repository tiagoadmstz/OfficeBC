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
@Table(name = "emails")
@Access(AccessType.PROPERTY)
@SequenceGenerator(name = "seq_email", sequenceName = "email_seq", allocationSize = 1, initialValue = 1)
@Indexes(value = {
    @Index(name = "idx_id", columnNames = {"id"}),
    @Index(name = "idx_contato", columnNames = {"email"})
})
@NamedQueries(value = {
    @NamedQuery(name = "email.findAll", query = "SELECT em FROM Email AS em"),
    @NamedQuery(name = "email.findById", query = "SELECT em FROM Email AS em WHERE em.id = :paramId"),
    @NamedQuery(name = "email.findByEmail", query = "SELECT em FROM Email AS em WHERE em.email = :paramEmail")
})
public class Email implements Serializable {

    private Number id;
    private String email;
    private String contato;

    public Email() {
    }

    public Email(Number id, String email, String contato) {
        this.id = id;
        this.email = email;
        this.contato = contato;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_seq")
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    @Column(name = "email", length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "contato", length = 150)
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void clonar(Email email) {
        this.id = email.getId();
        this.email = email.getEmail();
        this.contato = email.getContato();
    }

}
