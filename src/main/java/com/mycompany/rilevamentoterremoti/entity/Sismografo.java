/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rilevamentoterremoti.entity;

import java.io.Serializable;
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
public class Sismografo implements Serializable {
    
    @Id
    private long id;
    
    @ManyToOne(targetEntity = Centralina.class)
    private Centralina centralina;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Centralina getCentralina() {
        return centralina;
    }

    public void setCentralina(Centralina centralina) {
        this.centralina = centralina;
    }
    
    
}
