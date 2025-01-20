package com.clemer.stock.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "STOCK")
    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;
}
