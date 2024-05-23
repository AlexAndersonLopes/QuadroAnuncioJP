package com.jardimperuibe.quadro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Carrinho")
public class Carrinho {
    
    @Id
    private Integer id;
    
    @Column(name = "foto1")
    private String foto1;
    
    @Column(name = "foto2")
    private String foto2;
    
    @Column(name = "foto3")
    private String foto3;
    
    @Column(name = "foto4")
    private String foto4;
    
    @Size(max = 5000)
    @Column(name = "descricao")
    private String descricao;
    
}
