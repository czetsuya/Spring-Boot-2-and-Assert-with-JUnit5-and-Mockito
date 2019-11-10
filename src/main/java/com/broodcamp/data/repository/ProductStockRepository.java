package com.broodcamp.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.broodcamp.data.entity.ProductStock;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

    Optional<ProductStock> findBySerialNo(String serialNo);

    Optional<ProductStock> findByWarrantyCardNo(String warrantyCardNo);
}
