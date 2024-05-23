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
@Table(name = "Semana")
public class Semana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 300)
    @Column(name = "foto")
    private String foto;

    @Column(name = "dia")
    private String dia;

    @Size(max = 300)
    @Column(name = "fotomeiosemana")
    private String fotomeiosemana;

    @Size(max = 300)
    @Column(name = "fotofimsemana")
    private String fotofimsemana;

    @Column(name = "indicador")
    private String indicador;

    @Column(name = "palco")
    private String palco;

    @Column(name = "volante1")
    private String volante1;

    @Column(name = "volante2")
    private String volante2;

    @Column(name = "portao")
    private String portao;

    @Column(name = "midias")
    private String midias;

    @Column(name = "audio")
    private String audio;

    @Column(name = "limpeza")
    private String limpeza;

    @Size(max = 300)
    @Column(name = "link1")
    private String link1;

    @Size(max = 300)
    @Column(name = "link2")
    private String link2;

}
