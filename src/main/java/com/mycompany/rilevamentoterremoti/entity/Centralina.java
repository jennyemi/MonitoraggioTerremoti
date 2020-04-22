/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jennifer
 */
@Entity
public class Centralina implements Serializable {
    
    @Id
    private long id;
    
    @Basic
    @Column(nullable = false)
    private String località;
    
    @ManyToOne(targetEntity = PuntoDiRilevamento.class)
    private PuntoDiRilevamento puntoDiRilevamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }

    public PuntoDiRilevamento getPuntoDiRilevamento() {
        return puntoDiRilevamento;
    }

    public void setPuntoDiRilevamento(PuntoDiRilevamento puntoDiRilevamento) {
        this.puntoDiRilevamento = puntoDiRilevamento;
    }
    
    
}
