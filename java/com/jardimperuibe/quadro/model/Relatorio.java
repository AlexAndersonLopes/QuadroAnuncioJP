package com.jardimperuibe.quadro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Relatorio")
public class Relatorio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "mes")
    private String mes;
    
    @Column(name = "grupo")
    private String grupo;
    
    @Column(name = "participou")
    private String participou;
    
    @Column(name = "horas")
    private String horas;
    
    @Column(name = "estudo")
    private String estudo;
    
    @Size(max = 1000)
    @Column(name = "obs")
    private String obs;
    
    @Column(name = "pioneiro")
    private String pioneiro;
    
    
}
