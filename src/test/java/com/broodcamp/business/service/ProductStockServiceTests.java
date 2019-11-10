package com.broodcamp.business.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.broodcamp.business.exception.GenericException;
import com.broodcamp.data.entity.ProductStock;
import com.broodcamp.data.repository.ProductStockRepository;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 */
@SpringBootTest
public class ProductStockServiceTests {

    private final String serialNo = "1234";
    private final String warrantyCardNo = "1234";
    private final String serialNoEmpty = "1235";
    private final String warrantyCardNoEmpty = "1235";

    @TestConfiguration
    static class ProductStockServiceImplTestContextConfiguration {

        @Bean
        public ProductStockService productStockService() {

            return new ProductStockService();
        }
    }

    @Autowired
    private ProductStockService productStockService;

    @MockBean
    private ProductStockRepository productStockRepository;

    @BeforeEach
    public void setup() {

        ProductStock ps = new ProductStock();
        ps.setSerialNo(serialNo);
        ps.setWarrantyCardNo(warrantyCardNo);
        ps.setId(1L);

        Mockito.when(productStockRepository.findBySerialNo(serialNo)).thenReturn(Optional.of(ps));
        Mockito.when(productStockRepository.findByWarrantyCardNo(warrantyCardNo)).thenReturn(Optional.of(ps));
        Mockito.when(productStockRepository.findBySerialNo(serialNoEmpty)).thenReturn(Optional.empty());
        Mockito.when(productStockRepository.findByWarrantyCardNo(warrantyCardNoEmpty)).thenReturn(Optional.empty());
    }

    @Test
    public void whenSavingInvalidSerialNo_thenReturnException() {

        ProductStock ps = new ProductStock();
        ps.setSerialNo(serialNo);

        assertThatThrownBy(() -> productStockService.save(ps)).isInstanceOf(GenericException.class).hasMessageContaining("SerialNo");
    }

    @Test
    public void whenSavingInvalidWarrantyCardNo_thenReturnException() {

        ProductStock ps = new ProductStock();
        ps.setWarrantyCardNo(warrantyCardNo);

        assertThatThrownBy(() -> productStockService.save(ps)).isInstanceOf(GenericException.class).hasMessageContaining("WarrantyCardNo");
    }

    @Test
    public void whenSaving_thenOk() {

        ProductStock ps = new ProductStock();
        ps.setSerialNo(serialNoEmpty);
        ps.setWarrantyCardNo(warrantyCardNoEmpty);
        ps.setId(1L);

        Mockito.when(productStockRepository.save(ps)).thenReturn(ps);

        assertThat(productStockService.save(ps).getId()).isEqualTo(1L);
        assertThat(productStockService.save(ps).getSerialNo()).isEqualTo(serialNoEmpty);
    }

}
