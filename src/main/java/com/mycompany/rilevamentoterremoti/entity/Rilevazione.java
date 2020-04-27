/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jennifer
 */
@Entity
public class Rilevazione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataOra;

    @Basic
    @Column(nullable = false)
    private float magnitudo;

    @Basic
    @Column(nullable = false)
    private String località;
        
    @Basic
    @Column(nullable = false)
    private int profondità;

    @ManyToOne(targetEntity = Sismografo.class)
    private Sismografo sismografo;

    public String getLocalità() {
        return località;
    }

    public void setLocalità(String località) {
        this.località = località;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
    }

    public float getMagnitudo() {
        return magnitudo;
    }

    public void setMagnitudo(float magnitudo) {
        this.magnitudo = magnitudo;
    }

    public int getProfondità() {
        return profondità;
    }

    public void setProfondità(int profondità) {
        this.profondità = profondità;
    }

    public Sismografo getSismografo() {
        return sismografo;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

}
