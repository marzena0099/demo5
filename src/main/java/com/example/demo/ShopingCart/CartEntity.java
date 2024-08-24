package com.example.demo.ShopingCart;


import com.example.demo.Subjects.SubjectEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "ShoppingCart")
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_user;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;
    private BigDecimal priceSummaryforTheSameSubject;
    private Long quantity;
    private BigDecimal UnitPrice;

}
