package com.broodcamp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Entity
@Table
@Data
public class ProductStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "warranty_card_no")
    private String warrantyCardNo;
}
