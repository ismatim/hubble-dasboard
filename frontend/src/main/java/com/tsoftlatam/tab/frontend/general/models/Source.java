package com.tsoftlatam.tab.frontend.general.models;

import javax.persistence.*;

@Entity(name = "sources")
@Table
public class Source {

    /*TODO: Hay que hacer una relación many to many en este modelo
     TODO: El sourcetypeid va enlazado a una tabla intermedia (o many to many en java y mysql)
     TODO: Que lo enlaza con otro modelo que depende del tipo de fuente de datos
     TODO: Es decir, para bac el modelo debe contener host, puerto, usuario, password
     TODO: Para otro tipo de source probablemente se necesiten otros tipos de datos
    */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "source_type_id")
    private int sourceTypeId;

}
