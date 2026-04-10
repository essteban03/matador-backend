package com.matadorhouse.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "videojuegos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String consola;

    private String genero;

    private String categoria;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String imagenUrl;

    @Column(nullable = false)
    private BigDecimal precioPrincipal;

    @Column(nullable = false)
    private BigDecimal precioSecundaria;

    @Column(nullable = true)
    private BigDecimal precioOfertaPrincipal;

    @Column(nullable = true)
    private BigDecimal precioOfertaSecundaria;

    @Column(nullable = true)
    private LocalDate ofertaDesde;

    @Column(nullable = true)
    private LocalDate ofertaHasta;

    private Integer pesoGb;

    @Column(nullable = false)
    private boolean enStock = true;

    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CuentaPSN> cuentasPsn;

    @Transient
    private Long stockPrincipal;

    @Transient
    private Long stockSecundaria;
}
