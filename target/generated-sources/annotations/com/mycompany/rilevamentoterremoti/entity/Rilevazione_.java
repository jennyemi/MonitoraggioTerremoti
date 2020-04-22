package com.mycompany.rilevamentoterremoti.entity;

import com.mycompany.rilevamentoterremoti.entity.Sismografo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T17:08:10")
@StaticMetamodel(Rilevazione.class)
public class Rilevazione_ { 

    public static volatile SingularAttribute<Rilevazione, Float> magnitudo;
    public static volatile SingularAttribute<Rilevazione, Date> dataOra;
    public static volatile SingularAttribute<Rilevazione, Sismografo> sismografo;
    public static volatile SingularAttribute<Rilevazione, Integer> profondità;
    public static volatile SingularAttribute<Rilevazione, Long> id;

}