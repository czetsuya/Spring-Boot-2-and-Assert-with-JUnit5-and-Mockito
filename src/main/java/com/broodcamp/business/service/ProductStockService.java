package com.broodcamp.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.broodcamp.business.exception.GenericException;
import com.broodcamp.data.entity.ProductStock;
import com.broodcamp.data.repository.ProductStockRepository;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@Service
public class ProductStockService {

    @Autowired
    private ProductStockRepository productStockRepository;

    public void validateSerialNo(String serialNo) {

        if (productStockRepository.findBySerialNo(serialNo).isPresent()) {
            throw new GenericException("SerialNo already exists");
        }
    }

    public void validateWarrantyCardNo(String warrantyCardNo) {

        if (productStockRepository.findByWarrantyCardNo(warrantyCardNo).isPresent()) {
            throw new GenericException("WarrantyCardNo already exists");
        }
    }

    public ProductStock save(ProductStock entity) {

        validateSerialNo(entity.getSerialNo());

        if (entity.getWarrantyCardNo() != null) {
            validateWarrantyCardNo(entity.getWarrantyCardNo());
        }

        return productStockRepository.save(entity);
    }
}
