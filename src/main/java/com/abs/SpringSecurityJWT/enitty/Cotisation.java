package com.abs.SpringSecurityJWT.enitty;

import com.abs.SpringSecurityJWT.enums.ModeDePaiement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cotisation")
@Data
public class Cotisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant")
    private BigDecimal montant;

    @Column(name = "dateCotisation")
    private Date dateCotisation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModeDePaiement modeDePaiement;

    @Column(name = "etat")
    private String etat;

    @ManyToOne
    @JoinColumn(name = "categoryCot_id", referencedColumnName = "id")
    private CategoryCot categoryCot;

    @ManyToOne
    @JoinColumn(name = "association_id", referencedColumnName = "id")
    private Association association;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
