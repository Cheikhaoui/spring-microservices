package com.example.exchangeservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "_from")
    private String from;
    @Column(name = "_to")
    private String to;
    private BigDecimal conversionMultiple;
    @Transient
    private String environment;

}
