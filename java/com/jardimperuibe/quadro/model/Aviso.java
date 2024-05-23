package com.jardimperuibe.quadro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Aviso")
public class Aviso {
    
    @Id
    private Integer id;

    @Column(name = "tema")
    private String tema;
    
    @Size(max = 5000)
    @Column(name = "mensagem")
    private String mensagem;
    
    
}
